
package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;

import javax.validation.Valid;

@Controller
public class ProductController {
	
	
	@Autowired
	private ProductService productService;
	
	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}


	@GetMapping("/getAllProducts")
	public ModelAndView getAllProducts() {
		List<Product> products = productService.getAllProducts();

		return new ModelAndView("productList", "products", products);

	}

	

	@GetMapping("getProductById/{productId}")
	public ModelAndView getProductById(@PathVariable(value = "productId") String productId) {
		Product product = productService.getProductById(Integer.parseInt(productId));
		return new ModelAndView("productPage", "productObj", product);
	}

	@GetMapping("/admin/delete/{productId}")
	public String deleteProduct(@PathVariable(value = "productId") String productId) {

		Path path = Paths.get(
				"/Users/akshaydatir/Documents/workspace-spring-tool-suite-4-4.19.1.RELEASE/web/src/main/resources/static/images/products/"
						+ productId + ".jpg");

		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		productService.deleteProduct(Integer.parseInt(productId));
		return "redirect:/getAllProducts";

	}

	@GetMapping("/admin/product/addProduct")
	public String getProductForm(Model model) {
		Product product = new Product();



		product.setProductCategory("Android");
		model.addAttribute("productFormObj", product);
		return "addProduct";

	}

	@PostMapping("/admin/product/addProduct")
	public String addProduct(@Valid @ModelAttribute(value = "productFormObj") Product product, BindingResult result) {
		
		

		if (result.hasErrors())
			return "addProduct";

		productService.addProduct(product);
		
		MultipartFile image = product.getProductImage();
		if (image != null && !image.isEmpty()) {
			Path path = Paths.get(
					"/Users/akshaydatir/Documents/workspace-spring-tool-suite-4-4.19.1.RELEASE/web/src/main/resources/static/images/products/"
							+ product.getProductId() + ".jpg");

			try {
				image.transferTo(new File(path.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		return "redirect:/getAllProducts";
	}

	@GetMapping(value = "/admin/product/editProduct/{productId}")
	public ModelAndView getEditForm(@PathVariable(value = "productId") String productId) {
		Product product = productService.getProductById(Integer.parseInt(productId));
		
		return new ModelAndView("editProduct", "editProductObj", product);
	}

	@PostMapping("/admin/product/editProduct")
	public String editProduct(@ModelAttribute(value = "editProductObj") Product product) {
		
	
		System.out.println("Edited product details -->"+product.toString());
		
		
		Product pro = productService.getProductById(product.getProductId());
		
		System.out.print("Extracted product details -->"+ pro.toString());
		
		pro.setProductName(product.getProductName());
		pro.setProductDescription(product.getProductDescription());
		pro.setProductCategory(product.getProductCategory());
		pro.setProductManufacturer(product.getProductManufacturer());
		pro.setProductPrice(product.getProductPrice());
		pro.setUnitStock(product.getUnitStock());
		pro.setProductImage(product.getProductImage());
		
		
		System.out.print("New set product details -->"+ pro.toString());
		
		Path path = Paths.get(
				"/Users/akshaydatir/Documents/workspace-spring-tool-suite-4-4.19.1.RELEASE/web/src/main/resources/static/images/products/"
						+ product.getProductId() + ".jpg");

		productService.deleteProductImage(pro);
		
		productService.editProduct(pro);
		
		MultipartFile image = product.getProductImage();
		
		if (image != null && !image.isEmpty()) {

			try {
				image.transferTo(new File(path.toString()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
		return "redirect:/getAllProducts";
	}
	
	//Angular return List
	@GetMapping("/getProductsList")
	public @ResponseBody List<Product> getProductsListInJson() {
		return productService.getAllProducts();
	}

	@GetMapping("/productsListAngular")
	public String getProducts() {
		return "productListAngular";
	}

}
