package com.example.demo.service;

import org.springframework.context.annotation.Lazy;

import com.example.demo.model.CustomerOrder;

public interface CustomerOrderService {

	void addCustomerOrder(CustomerOrder customerOrder);
	double getCustomerOrderGrandTotal(int cartId);
	public void editCustomerOrder(CustomerOrder customerOrder);
	public CustomerOrder getCustomerOrder(int customerOrder);
	void delete(CustomerOrder customerOrder);
}