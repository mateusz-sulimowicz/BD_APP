package com.mati.domain.product;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	private final ProductRepository repository;

	@Autowired
	public ProductsController(ProductRepository productRepository) {
		this.repository = productRepository;
	}

	@GetMapping
	public List<Product> getProducts() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		return repository
				.findById(id)
				.orElseThrow(RuntimeException::new);
	}

	@PostMapping
	public ResponseEntity<Product> createProduct(@RequestBody Product product) throws URISyntaxException {
		Product savedProduct = repository.save(product);
		return ResponseEntity
				.created(new URI("/api/products/" + product.getId()))
				.body(savedProduct);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
		Product currentProduct = repository
				.findById(id)
				.orElseThrow(RuntimeException::new);

		currentProduct.setName(product.getName());
		currentProduct = repository.save(product);

		return ResponseEntity.ok(currentProduct);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		repository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
