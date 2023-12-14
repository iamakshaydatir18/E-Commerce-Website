package com.example.demo.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.config.CustomUserDetails;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.ProductService;


@Controller
@EnableTransactionManagement
public class CartItemController {

	
	@Autowired
	private CartService cartService;
	
	
	@Autowired
	private CartItemService cartItemService;
	
	
	@Autowired
	private CustomerService customerService;
	

	@Autowired
	private ProductService productService;

	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	@GetMapping("/cart/add/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable(value = "productId") String productId) {

		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String emailId = user.getUsername();
		
		Customer customer = customerService.getCustomerByemailId(emailId);
		System.out.println("Customer : " + customer.getUsers().getEmailId());
		
		Cart cart = customer.getCart();
		System.out.println(cart);
		List<CartItem> cartItems = cart.getCartItem();
		
		Product product = productService.getProductById(Integer.parseInt(productId));
		for (int i = 0; i < cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			if (product.getProductId() == (cartItem.getProduct().getProductId())) {
				cartItem.setQuality(cartItem.getQuality() + 1);
				cartItem.setPrice(cartItem.getQuality() * cartItem.getProduct().getProductPrice());
				cartItemService.addCartItem(cartItem);
				return;
			}
		}
		CartItem cartItem = new CartItem();
		cartItem.setQuality(1);
		cartItem.setProduct(product);
		cartItem.setPrice(product.getProductPrice() * 1);
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}

	@GetMapping("/cart/removeCartItem/{cartItemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@Transactional
	public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {

		System.out.println("removing item!!!!!! " + cartItemId);

		// CartItem cartItem = cartItemService.getById(Integer.parseInt(cartItemId));
		// System.out.print(cartItem.toString());
		// cartItemService.deleteByCartItemId(Integer.parseInt(cartItemId))
		// cartItemService.delete(cartItem);

		cartItemService.removeCartItem(Integer.parseInt(cartItemId));
	}

	@GetMapping("/cart/removeAllItems/{cartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@Transactional
	public void removeAllCartItems(@PathVariable(value = "cartId") String cartId) {

		Cart cart = cartService.getCartByCartId(Integer.parseInt(cartId));
		cartItemService.removeAllCartItems(cart);

	}

}
