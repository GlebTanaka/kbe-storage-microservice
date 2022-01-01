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

    public void addNewProduct(Product product) {
        //TODO check for null and existing UUID
        productRepository.save(product);
    }

    public void addNewProducts(List<Product> product) {
        //TODO check for null and existing UUID
        product.forEach(p -> addNewProduct(p));
    }
}
