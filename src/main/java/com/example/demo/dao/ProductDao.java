package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Product;



public interface ProductDao {

	List<Product> getAllProducts();

	Product getProductById(int productId);

	void deleteProduct(int productId);

	void addProduct(Product product);
	
	void editProduct(Product product);
	
    void deleteProductImage(Product product);
	
}
