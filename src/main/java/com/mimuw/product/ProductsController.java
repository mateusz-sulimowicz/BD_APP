package com.mimuw.product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {


	private final ProductDAO productDAO;

	@Autowired
	public ProductsController(ProductDAO productDAO) {
		this.productDAO = productDAO;
	}

	@GetMapping
	public List<Product> getProducts() {
		return productDAO.findAll();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return productDAO
				.findById(id)
				.orElseThrow(RuntimeException::new);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Product> deleteById(@PathVariable Long id) {
		productDAO.deleteById(id);
		return ResponseEntity.ok().build();
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
		Product savedProduct = productDAO.create(product);
		return ResponseEntity
				.created(new URI("/api/products/" + product.getId()))
				.body(savedProduct);
	}

	@PostMapping("{id}")
	public ResponseEntity<Product> copyProduct(@RequestBody Product product) throws URISyntaxException {
		Product savedProduct = productDAO.createCopyOf(product);
		return ResponseEntity
				.created(new URI("/api/products/" + product.getId()))
				.body(savedProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		productDAO.update(product);
		return ResponseEntity.ok(product);
	}

}
