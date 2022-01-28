package de.htwberlin.f4.storagemicroservice.controllers;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import de.htwberlin.f4.storagemicroservice.models.Product;
import de.htwberlin.f4.storagemicroservice.services.CSVService;
import de.htwberlin.f4.storagemicroservice.services.ProductService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    private final StorageService storageService;
    private final ProductService productService;
    private final CSVService csvService;

    @Autowired
    public ProductStorageController(StorageService storageService, ProductService productService, CSVService csvService) {
        this.storageService = storageService;
        this.productService = productService;
        this.csvService = csvService;
    }

    @GetMapping
    public ResponseEntity<List<Storage>> getAllStorageProducts() {
        return ResponseEntity.ok(storageService.getStorageProducts());
    }

    @GetMapping("/product/{uuid}")
    @ApiOperation(value = "Get Product Information",
            notes = "Provide a matching UUID to retrieve additional Information of the Product",
            response = Storage.class)
    public ResponseEntity<Storage> getStorageProduct(@PathVariable @NotNull UUID uuid) {
        return ResponseEntity.ok(storageService.getStorageProduct(uuid));
    }

    @PostMapping("/product")
    @ApiOperation(value = "Store Product Details",
            notes = "Provide additional Information of the Product, including matching UUID",
            response = Storage.class)
    public ResponseEntity<Storage> postStorageProduct(@RequestBody @Valid Storage product) {
        storageService.addNewProduct(product);
        return new ResponseEntity<Storage>(product, HttpStatus.CREATED);
    }

    @GetMapping("/product/import")
    @ApiOperation(value = "Read Data from CSV",
            notes = "Store all avalibale data from Products and store them in separate DB")
    public void importCSV() throws IOException {
        List<Product> products = csvService.readCSVFile();
        productService.addNewProducts(products);
    }
}
