package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.BillingAddress;
import com.example.demo.model.Customer;
import com.example.demo.model.ShippingAddress;
import com.example.demo.model.User;
import com.example.demo.service.CustomerService;


@Controller
public class UserController {
	
	
	@Autowired
	private CustomerService customerService;
	
	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/img")
	public String getImg() {

		System.out.println("Hello from Akshay!!!!!");
		return "user";
	}

	@GetMapping("/customer/registration")
	public String getUser(Model model) {

		Customer customer = new Customer();
		User user = new User();
		BillingAddress ba = new BillingAddress();
		ShippingAddress sa = new ShippingAddress();
		customer.setShippingAddress(sa);
		customer.setBillingAddress(ba);
		customer.setUsers(user);

		model.addAttribute(customer);
		System.out.println("Hello from Akshay!!!!!");

		return "register";
	}

	// to insert the data
	@PostMapping("/customer/registration")
	public String registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer, Model model,
			BindingResult result) {
		if (result.hasErrors())
			return "register";
		customerService.addCustomer(customer);
		model.addAttribute("registrationSuccess", "Registered Successfully. Login using username and password");
		return "login";
	}

}
