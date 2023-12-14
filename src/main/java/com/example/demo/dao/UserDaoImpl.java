package com.example.demo.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();

	public List<User> getAllUsers() {
		Session session = DAO.getsessionFactory().openSession();
		
		Transaction tx = session.beginTransaction();
		// List<Product> products = session.createQuery("from Product").list();
		List<User> users = session.createCriteria(User.class).list();
		tx.commit();
		System.out.println(users);
		
		return users;
	}

	public void deleteUser(String userId) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		session.saveOrUpdate(user);
		tx.commit();
	}

	public void addUser(User user) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		 // your changes
		System.out.print("Saving User");
		session.save(user);
		 tx.commit();

	}

	public User getUserById(String userId) {
		// Reading the records from the table
		Session session =DAO.getsessionFactory().openSession();
		//try {
		//	session = sessionFactory.getCurrentSession();
		//} catch (HibernateException e) {
		//	session = sessionFactory.openSession();
		//}
		// select * from Product where isbn=i
		Transaction tx = session.beginTransaction();
		User user = (User) session.get(User.class, userId);
		 tx.commit();
		return user;
	}

	@Override
	public User getUserByemailId(String emailId) {
		
		Session session =DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from User where emailId=?1");
		query.setString(1,emailId);
		
		User users = (User)query.uniqueResult();
		 tx.commit();
		return users;
	}

}
