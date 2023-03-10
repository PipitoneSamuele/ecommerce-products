package it.ecommerce.products_microservice.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ecommerce.products_microservice.model.Product;
import it.ecommerce.products_microservice.repository.ProductsRepository;

@RestController
@RequestMapping(path = "/ecommerce")
public class MainController {
	
	@Autowired
	private ProductsRepository productRepository;
	
	@GetMapping(path = "/test")
	public Product getTestProduct() {
		return new Product(1L, "prova", "descrizione prova", 2.2);
	}
	
	@GetMapping(path = "/product/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productRepository.findById(id).orElse(null);
	}
	
	@GetMapping(path = "/products")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getProducts() {
		Iterable<Product> productsIterable = productRepository.findAll();
		List<Product> productsList = new ArrayList<>();
		productsIterable.forEach(productsList::add);
		return productsList;
	}
	
	@PostMapping(
			path = "/product",
			consumes = MediaType.APPLICATION_JSON_VALUE
			)
	public void saveProduct(@RequestBody Product product) throws Exception {
		if(product != null) {
			productRepository.save(product);
		} else throw new Exception("Nessun prodotto inserito in input");
	}
	
}
