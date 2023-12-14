package com.example.demo.dao;

import java.io.IOException;

import com.example.demo.model.Cart;



public interface CartDao {

	Cart getCartByCartId(int CartId);
	
	Cart validate(int cartId) throws IOException;
	
	void update(Cart cart);
}
