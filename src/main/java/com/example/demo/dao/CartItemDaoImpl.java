package com.example.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cart;
import com.example.demo.model.CartItem;



@Repository
@Transactional
public class CartItemDaoImpl implements CartItemDao {

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void addCartItem(CartItem cartItem) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(cartItem);
		tx.commit();
	}

	public void removeCartItem(int CartItemId) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		CartItem cartItem = (CartItem) session.get(CartItem.class, CartItemId);
		session.delete(cartItem);
		Cart cart = cartItem.getCart();
		List<CartItem> cartItems = cart.getCartItem();
		cartItems.remove(cartItem);
		tx.commit();

	}

	public void removeAllCartItems(Cart cart) {
		List<CartItem> cartItems = cart.getCartItem();
		for (CartItem cartItem : cartItems) {
			removeCartItem(cartItem.getCartItemId());
		}
	}

}
