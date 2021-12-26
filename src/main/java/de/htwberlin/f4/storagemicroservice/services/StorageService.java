package de.htwberlin.f4.storagemicroservice.services;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
     * Methode um ein spezifisches Storage Objekt zu bekommen
     * @param uuid UUID Suchparameter
     * @return Storage, das erfragte Objekt
     */
    public Storage getStorageProduct(UUID uuid) {
        return storageRepository.findById(uuid).orElseThrow();
    }
}
