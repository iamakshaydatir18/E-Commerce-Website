package com.example.demo.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Queries;



@Repository
public class QueriesDaoImpl implements QueriesDao  {

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();
	
	public void addQuery(Queries queries) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.save(queries);
		tx.commit();
		
	}

}
