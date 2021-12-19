package com.mati;

import com.mati.domain.module.Module;
import com.mati.domain.module.ModuleRepository;
import com.mati.domain.product.Product;
import com.mati.domain.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/*@Component
public class DatabaseLoader implements CommandLineRunner {

	private final ProductRepository productRepository;
    private final ModuleRepository moduleRepository;

    @Autowired
    public DatabaseLoader(ProductRepository productRepository, ModuleRepository moduleRepository) {
        this.productRepository = productRepository;
        this.moduleRepository = moduleRepository;
    }

	@Override
	public void run(String... strings) throws Exception {
		this.productRepository.save(new Product("Iphone 10 Pro", new BigDecimal(999)));
        this.productRepository.save(new Product("Iphone 10 Pro", new BigDecimal(999)));
        this.moduleRepository.save(new Module("Camera"));
        this.moduleRepository.save(new Module("Storage"));
	}

}*/
