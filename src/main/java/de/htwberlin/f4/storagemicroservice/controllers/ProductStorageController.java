package de.htwberlin.f4.storagemicroservice.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import de.htwberlin.f4.storagemicroservice.models.Product;
import de.htwberlin.f4.storagemicroservice.services.CSVService;
import de.htwberlin.f4.storagemicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import de.htwberlin.f4.storagemicroservice.services.StorageService;
@Validated
@RestController
@RequestMapping("api/v1/storage")
public class ProductStorageController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private ProductService productService;
    @Autowired 
    private CSVService csvService;

    @GetMapping
    public ResponseEntity<List<Storage>> getAllStorageProducts() {
        return ResponseEntity.ok(storageService.getStorageProducts());
    }

    @GetMapping("/product/{uuid}")
    public ResponseEntity<Storage> getStorageProduct(@PathVariable @NotNull UUID uuid) {
        return ResponseEntity.ok(storageService.getStorageProduct(uuid));
    }

    @PostMapping("/product")
    public void postStorageProduct(@RequestBody @Valid Storage product){
        storageService.addNewProduct(product);
    }

    @GetMapping("/product/import")
    public void importCSV() throws IOException {
        List<Product> products = csvService.readCSVFile();
        productService.addNewProducts(products);
    }
}
