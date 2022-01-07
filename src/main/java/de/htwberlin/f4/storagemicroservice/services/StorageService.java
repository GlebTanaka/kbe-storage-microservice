package de.htwberlin.f4.storagemicroservice.services;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.persistence.EntityExistsException;

/**
 * Service fuer den Zugriff auf das DAO
 */
@Service
public class StorageService {

    private final StorageRepository storageRepository;

    /**
     * Konstruktor mit Parametern
     * @param storageRepository StorageRepository das DAO
     */
    @Autowired
    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Storage> getStorageProducts() {
        return storageRepository.findAll();
    }

    /**
     * Fügt Storage Product in die Datenbank ein
     * @param product
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

    /**
     * Methode um ein spezifisches Storage Objekt zu bekommen
     * @param uuid UUID Suchparameter
     * @return Storage, das erfragte Objekt
     */
    public Storage getStorageProduct(UUID uuid) throws NoSuchElementException {
        return storageRepository.findById(uuid).orElseThrow();
    }
}
