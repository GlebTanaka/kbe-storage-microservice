package de.htwberlin.f4.storagemicroservice.dao;

import de.htwberlin.f4.storagemicroservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
