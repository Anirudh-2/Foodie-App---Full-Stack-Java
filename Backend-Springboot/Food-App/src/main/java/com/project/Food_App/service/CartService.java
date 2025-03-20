package com.project.Food_App.service;

import com.project.Food_App.Exception.CartException;
import com.project.Food_App.Exception.CartItemException;
import com.project.Food_App.Exception.FoodException;
import com.project.Food_App.Model.Cart;
import com.project.Food_App.Model.CartItem;
import com.project.Food_App.request.AddCartItemRequest;

public interface CartService {

    CartItem addItemToCart(AddCartItemRequest req) throws FoodException, CartException, CartItemException;

    CartItem updateCartItemQuantity(String cartItemId, int quantity) throws CartItemException;

    Cart removeItemFromCart(String cartItemId, String jwt) throws CartException, CartItemException;

    Double calculateCartTotals(String cartId) throws CartException;

    Cart findCartByUserId(String userId) throws CartException;

    Cart clearCart(String userId) throws CartException;

    String extractUserIdFromToken(String jwt) throws CartException;
}
