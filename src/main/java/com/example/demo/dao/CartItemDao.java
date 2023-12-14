package com.example.demo.dao;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;


public interface CartItemDao {

	void addCartItem(CartItem cartItem);
	void removeCartItem(int CartItemId);
	void removeAllCartItems(Cart cart);

}
