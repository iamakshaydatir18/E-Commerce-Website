package com.example.demo.dao;

import java.io.IOException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cart;
import com.example.demo.service.CustomerOrderService;


@Repository
@Transactional
public class CartDaoImpl implements CartDao {

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();

	@Autowired
	private CustomerOrderService customerOrderService;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Cart getCartByCartId(int CartId) {
		
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		Cart cart = (Cart) session.get(Cart.class, CartId);
		// System.out.println(cart.getCartId() + " " + cart.getCartItem());
		System.out.println(cart);
		tx.commit();
		
		return cart;

	}
	
public Cart validate(int cartId) throws IOException {
		
		Cart cart = getCartByCartId(cartId);
		
		if (cart == null || cart.getCartItem().size() == 0) {
			throw new IOException(cartId + "");
		}
	
		update(cart);
		return cart;
	}

	public Cart validate1(String cartId) throws IOException {
		
		if(cartId == null) {
			System.out.println("cart is Nulll!!!!!");
			
		}
		  try {
		        Cart cart = getCartByCartId(Integer.parseInt(cartId));
		        if (cart == null || cart.getCartItem().size() == 0) {
		            System.out.println("Invalid cart: " + cartId);
		            throw  new IOException();
		        }
		        System.out.println("Valid cart---------->: " + cart);
		        double grandTotal = customerOrderService.getCustomerOrderGrandTotal(Integer.parseInt(cartId));
				cart.setTotalPrice(grandTotal);
		        return cart;
		    } catch (IOException e) {
		        e.printStackTrace();
		        return null;
		    }
	
	}

	public void update(Cart cart) {

		int cartId = cart.getCartId();
		double grandTotal = customerOrderService.getCustomerOrderGrandTotal(cartId);
		cart.setTotalPrice(grandTotal);
		
		
		System.out.print("********Cart is  -->>>>>"+cart.toString()+" *****CartID is --"+cart.getCartId());
		
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(cart);
		tx.commit();
		
	
	}

}
