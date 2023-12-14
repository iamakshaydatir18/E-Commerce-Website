package com.example.demo.dao;

import java.io.File;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Product;



@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	

	//@Autowired
	private SessionFactory sessionFactory = DAO.getsessionFactory();


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> getAllProducts() {
		// Reading the records from the table
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		// List<Product> products = session.createQuery("from Product").list();
		List<Product> products = session.createCriteria(Product.class).list();
		//System.out.println("----- List of Products-----");
		//System.out.println(products.toString());
		
		tx.commit();
		return products;
	}

	public Product getProductById(int productId) {

		// Reading the records from the table
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		// select * from Product where isbn=i
		Product product = (Product) session.get(Product.class, productId);
		tx.commit();
		return product;
	}

	public void deleteProduct(int productId) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		Product product = (Product) session.get(Product.class, productId);
		session.delete(product);
		
		tx.commit();
		
	}

	public void addProduct(Product product) {
		
		//Session session = sessionFactory.openSession();
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		
		System.out.println(" --------->"+ product.toString());
		session.save(product);
		tx.commit();
		
	}

	public void editProduct(Product product) {
		Session session = DAO.getsessionFactory().openSession();
		Transaction tx = session.beginTransaction();
	
		session.update(product);
		tx.commit();

	}
	
	
    public void deleteProductImage(Product product)
    {
         try { 
             File file = new File("/Users/akshaydatir/Documents/workspace-spring-tool-suite-4-4.19.1.RELEASE/web/src/main/resources/static/images/products/"
						+ product.getProductId() + ".jpg");
             if(file.delete()) { 
                System.out.println(file.getName() + " is deleted!");
             } else {
                System.out.println("Delete operation is failed.");
                }
          }
            catch(Exception e)
            {
                System.out.println("Failed to Delete image !!");
            }
    }
	
	
	public static void main(String[] args) {
		
		ProductDaoImpl dao = new ProductDaoImpl();
		
		Product pro = new Product();
		pro.setProductName("Akshay");
		pro.setProductId(20);
		
		dao.addProduct(pro);
		
	}

	

}
