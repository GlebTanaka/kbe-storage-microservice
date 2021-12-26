package de.htwberlin.f4.storagemicroservice.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.htwberlin.f4.storagemicroservice.models.Storage;
import de.htwberlin.f4.storagemicroservice.services.StorageService;

@RestController
@RequestMapping("api/v1/storage")
public class ProductStorageController {
    @Autowired
    private StorageService service;

    @GetMapping
    public ResponseEntity<List<Storage>> getAllStorageProducts() {
        return ResponseEntity.ok(service.getStorageProducts());
    }

    @GetMapping("/product/{uuid}")
    public ResponseEntity<Storage> getStorage(@PathVariable @NotNull UUID uuid){
        return ResponseEntity.ok(service.getStorageProduct(uuid));
    }
    
    @GetMapping("/product/import")
    public void importCSV(){
        //TODO: call CSV importer
    }

}
