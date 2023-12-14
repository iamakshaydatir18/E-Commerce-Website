package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CartDao;
import com.example.demo.model.Cart;



@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartDao cartDao;

	public CartDao getCartDao() {
		return cartDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public Cart getCartByCartId(int CartId) {

		return cartDao.getCartByCartId(CartId);
	}

}
