package de.htwberlin.f4.storagemicroservice.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.htwberlin.f4.storagemicroservice.models.Storage;
import de.htwberlin.f4.storagemicroservice.services.StorageService;

@RestController
@RequestMapping("api/v1/storage")
public class ProductStorageController {
    @Autowired
    private StorageService service;

    @GetMapping("/product/{UUID}")
    public ResponseEntity<Storage> getStorage(@PathVariable UUID uuid){
        return ResponseEntity.ok(service.getStorgageProduct(uuid));
    }
    
    @GetMapping("/product/import")
    public void importCSV(){
        //TODO: call CSV importer
    }

}
