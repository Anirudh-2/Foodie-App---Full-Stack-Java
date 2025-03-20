package com.project.Food_App.controller;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.CartItemException;
import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Model.Cart;
import com.project.Food_App.Model.CartItem;
import com.project.Food_App.request.AddCartItemRequest;
import com.project.Food_App.request.UpdateCartItemRequest;
import com.project.Food_App.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public ResponseEntity<?> findUserCart(@RequestHeader("Authorization") String jwt) {
        try {
            String userId = cartService.extractUserIdFromToken(jwt);
            Cart cart = cartService.findCartByUserId(userId);
            return ResponseEntity.ok(cart);
        } catch (CartException e) {
            return ResponseEntity.status(404).body("Cart not found");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching cart: " + e.getMessage());
        }
    }


    @PutMapping("/add")
    public ResponseEntity<?> addItemToCart(@RequestBody AddCartItemRequest req,
                                           @RequestHeader("Authorization") String jwt) {
        try {
            String userId = cartService.extractUserIdFromToken(jwt);
            CartItem cartItem = cartService.addItemToCart(req);
            return ResponseEntity.ok(cartItem);
        } catch (FoodException | CartException | CartItemException e) {
            return ResponseEntity.status(500).body("Error adding item: " + e.getMessage());
        }
    }

    @PutMapping("/item/update")
    public ResponseEntity<?> updateCartItemQuantity(@RequestBody UpdateCartItemRequest req) {
        try {
            CartItem cartItem = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
            return ResponseEntity.ok(cartItem);
        } catch (CartItemException e) {
            return ResponseEntity.status(500).body("Error updating cart item: " + e.getMessage());
        }
    }

    @DeleteMapping("/item/{id}/remove")
    public ResponseEntity<?> removeItemFromCart(@PathVariable String id,
                                                @RequestHeader("Authorization") String jwt) {
        try {
            String userId = cartService.extractUserIdFromToken(jwt);
            Cart cart = cartService.removeItemFromCart(id, userId);
            return ResponseEntity.ok(cart);
        } catch (CartException | CartItemException e) {
            return ResponseEntity.status(500).body("Error removing item: " + e.getMessage());
        }
    }

    @GetMapping("/total")
    public ResponseEntity<?> calculateCartTotals(@RequestParam String cartId) {
        try {
            return ResponseEntity.ok(cartService.calculateCartTotals(cartId));
        } catch (CartException e) {
            return ResponseEntity.status(500).body("Error calculating total: " + e.getMessage());
        }
    }

    @PutMapping("/clear")
    public ResponseEntity<?> clearCart(@RequestHeader("Authorization") String jwt) {
        try {
            String userId = cartService.extractUserIdFromToken(jwt);
            return ResponseEntity.ok(cartService.clearCart(userId));
        } catch (CartException e) {
            return ResponseEntity.status(500).body("Error clearing cart: " + e.getMessage());
        }
    }
}
