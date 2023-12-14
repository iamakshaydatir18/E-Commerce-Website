package com.example.demo.service;

import java.util.List;

import org.springframework.context.annotation.Lazy;

import com.example.demo.model.Product;



public interface ProductService {

	public List<Product> getAllProducts();

	Product getProductById(int productId);

	void deleteProduct(int productId);
	
	void addProduct(Product product);
	
	void editProduct(Product product);
	
	void deleteProductImage(Product product);
}
