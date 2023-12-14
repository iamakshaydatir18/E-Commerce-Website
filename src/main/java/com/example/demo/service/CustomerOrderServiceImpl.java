package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.CustomerOrderDao;
import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;
import com.example.demo.model.CustomerOrder;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	@Autowired
	private CartService cartService;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDao.addCustomerOrder(customerOrder);
	}

	@Transactional
	public double getCustomerOrderGrandTotal(int cartId) {
		double grandTotal=0;
		Cart cart = cartService.getCartByCartId(cartId);
		List<CartItem> cartItems = cart.getCartItem();
		
		for(CartItem item: cartItems){
			grandTotal += item.getPrice();
		}
		return grandTotal;
	}

	@Transactional
	public void editCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDao.editCustomerOrder(customerOrder);
		
	}

	@Transactional
	public CustomerOrder getCustomerOrder(int customerOrderId) {
		
		return customerOrderDao.getCustomerOrder(customerOrderId);
	}

	@Override
	public void delete(CustomerOrder customerOrder) {
		
		customerOrderDao.delete(customerOrder);
		
	}

}
