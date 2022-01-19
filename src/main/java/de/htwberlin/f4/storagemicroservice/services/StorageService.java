package de.htwberlin.f4.storagemicroservice.services;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.persistence.EntityExistsException;

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

    /**
     * Add Product to database
     */
    public void addNewProduct(Storage product) throws EntityExistsException{
        if(productExists(product)){
            throw new EntityExistsException("Entity with UUID: " + product.getId().toString() +" already Exists");
        }
        storageRepository.save(product);
    }

    private boolean productExists(Storage product){
        try{
            getStorageProduct(product.getId());
            return true;
        }catch(NoSuchElementException e){
            return false;
        }
    }

    public Storage getStorageProduct(UUID uuid) throws NoSuchElementException {
        return storageRepository.findById(uuid).orElseThrow();
    }
}
