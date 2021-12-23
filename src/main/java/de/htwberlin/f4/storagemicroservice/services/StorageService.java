package de.htwberlin.f4.storagemicroservice.services;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StorageService {

    private final StorageRepository storageRepository;

    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> getStorageProducts() {
        return storageRepository.findAll();
    }

    public Storage getStorgageProduct(UUID uuid) {
        return storageRepository.findById(uuid).orElseThrow();
    }
}
