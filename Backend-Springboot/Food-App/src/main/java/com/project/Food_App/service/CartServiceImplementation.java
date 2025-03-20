package com.project.Food_App.service;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.CartItemException;
import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Model.Cart;
import com.project.Food_App.Model.CartItem;
import com.project.Food_App.Model.Food;
import com.project.Food_App.repository.CartItemRepository;
import com.project.Food_App.repository.CartRepository;
import com.project.Food_App.repository.FoodRepository;
import com.project.Food_App.request.AddCartItemRequest;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImplementation implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final FoodRepository foodRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public CartServiceImplementation(CartRepository cartRepository,
                                     CartItemRepository cartItemRepository,
                                     FoodRepository foodRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    public CartItem addItemToCart(AddCartItemRequest req) throws FoodException, CartException, CartItemException {
        String user = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Food food = foodRepository.findById(req.getMenuItemId())
                .orElseThrow(() -> new FoodException("Food item does not exist with id " + req.getMenuItemId()));

        Cart cart = findCartByUserId(user);

        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem(food, req.getQuantity(), cart, req.getIngredients(), food.getPrice() * req.getQuantity());
        cart.getItems().add(cartItemRepository.save(newCartItem));
        cartRepository.save(cart);

        return newCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(String cartItemId, int quantity) throws CartItemException {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemException("Cart item not found with id " + cartItemId));

        cartItem.setQuantity(quantity);
        cartItem.setTotalPrice(cartItem.getFood().getPrice() * quantity);
        return cartItemRepository.save(cartItem);
    }

    @Override
    public Cart removeItemFromCart(String cartItemId, String jwt) throws CartException, CartItemException {
        String user = extractUserIdFromToken(jwt);
        Cart cart = findCartByUserId(user);

        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemException("Cart item not found with id " + cartItemId));

        cart.getItems().remove(cartItem);
        cartItemRepository.delete(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Double calculateCartTotals(String cartId) throws CartException {
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new CartException("Cart not found with id " + cartId));

        return cart.getItems().stream().mapToDouble(item -> item.getTotalPrice()).sum();
    }

    @Override
    public Cart findCartByUserId(String userId) throws CartException {
        return cartRepository.findByCustomerId(userId)
                .orElseGet(() -> cartRepository.save(new Cart(userId, userId, new ArrayList<>(), 0L)));
    }

    @Override
    public Cart clearCart(String userId) throws CartException {
        Cart cart = findCartByUserId(userId);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }

    @Override
    public String extractUserIdFromToken(String jwt) throws CartException {
        return Jwts.parser().setSigningKey(jwtSecret.getBytes()).parseClaimsJws(jwt.replace("Bearer ", "")).getBody().getSubject();
    }
}
