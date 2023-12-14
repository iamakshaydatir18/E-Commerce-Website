package com.example.demo.dao;

import com.example.demo.model.CustomerOrder;

public interface CustomerOrderDao {

	void addCustomerOrder(CustomerOrder customerOrder);
	public void editCustomerOrder(CustomerOrder customerOrder);
	
	public CustomerOrder getCustomerOrder(int customerOrder);
	
	void delete(CustomerOrder customerOrder);
}
