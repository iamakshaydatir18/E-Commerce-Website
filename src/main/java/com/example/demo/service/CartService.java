package com.example.demo.service;

import org.springframework.context.annotation.Lazy;

import com.example.demo.model.Cart;

public interface CartService {

	Cart getCartByCartId(int CartId);
}
