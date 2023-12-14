package com.example.demo.service;

import org.springframework.context.annotation.Lazy;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;

public interface CartItemService {

	void addCartItem(CartItem cartItem);
	void removeCartItem(int CartItemId);
	void removeAllCartItems(Cart cart);
}
