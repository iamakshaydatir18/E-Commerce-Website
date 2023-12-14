package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;


@Controller
public class CartController {
	
	
	@Autowired
	private CustomerService customerService;

	@Autowired
	private CartService cartService;

	
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public CartService getCartService() {
		return cartService;
	}
	
	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}
	
	@GetMapping("cart/getCartById")
	public String getCartId(Model model){
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = user.getUsername();
		Customer customer = customerService.getCustomerByemailId(emailId);
		model.addAttribute("cartId", customer.getCart().getCartId());
		return "cart";
	}
	
	@GetMapping("/cart/getCart/{cartId}")
	@ResponseBody
	public Cart getCartItems(@PathVariable(value="cartId")String cartId){
		
		return cartService.getCartByCartId(Integer.parseInt(cartId));
	}
	
}
