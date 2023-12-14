package com.example.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Authorities;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.User;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public void addCustomer(Customer customer) {
		
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		customer.getUsers().setEnabled(true);
		customer.getUsers().setPassword(passwordEncoder.encode(customer.getUsers().getPassword()));
		
		System.out.println("Adding customer in dao" + customer.getUsers().getPassword());
		
		customer.getUsers().setRole("ROLE_USER");
		
		Cart cart = new Cart();
		//it is to set CartId for customer table
		customer.setCart(cart);//set the cart to the customer
		//if we omit this statement, then it will insert null for customerid in cart
		//to set the customerid in cart table
		cart.setCustomer(customer);
		
		
		
		session.save(customer);
		session.save(customer.getUsers());
		
		tx.commit();
		
		System.out.println("In save!! ");
		
	}

	public List<Customer> getAllCustomers() {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		List<Customer> customerList = session.createQuery("from Customer").list();
		tx.commit();
		return customerList;
	}

	public Customer getCustomerByemailId(String emailId) {
		System.out.println("Email is --> "+emailId);
		
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where emailId=?1");
		query.setString(1,emailId);
		User users = (User)query.uniqueResult();
		Customer customer = users.getCustomer();
		tx.commit();
		return customer;
	}
	
	
}
