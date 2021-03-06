package de.htwberlin.f4.storagemicroservice.dao;

import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StorageRepository extends JpaRepository<Storage, UUID> {
}
