package com.example.demo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.CustomerOrder;

@Repository
@Transactional
public class CustomerOrderDaoImpl implements CustomerOrderDao {

//	@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();

	public void addCustomerOrder(CustomerOrder customerOrder) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(customerOrder);
		tx.commit();
		
	}
	
	public void editCustomerOrder(CustomerOrder customerOrder) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(customerOrder);
		tx.commit();
		
	}

	@Override
	public CustomerOrder getCustomerOrder(int customerOrderId) {
		
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		CustomerOrder customer = session.get(CustomerOrder.class,customerOrderId );
		tx.commit();
		
		return customer;
	}

	@Override
	public void delete(CustomerOrder customerOrder) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.delete(customerOrder);
		tx.commit();
		
	}

}
