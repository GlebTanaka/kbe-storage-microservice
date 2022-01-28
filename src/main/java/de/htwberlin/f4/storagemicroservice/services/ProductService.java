package de.htwberlin.f4.storagemicroservice.services;

import de.htwberlin.f4.storagemicroservice.dao.ProductRepository;
import de.htwberlin.f4.storagemicroservice.models.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void addNewProducts(List<Product> product) {
        product.forEach(this::addNewProduct);
    }
}
