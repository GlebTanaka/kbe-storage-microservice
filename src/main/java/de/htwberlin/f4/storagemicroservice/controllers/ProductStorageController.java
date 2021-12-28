package de.htwberlin.f4.storagemicroservice.controllers;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import de.htwberlin.f4.storagemicroservice.models.Product;
import de.htwberlin.f4.storagemicroservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.htwberlin.f4.storagemicroservice.models.Storage;
import de.htwberlin.f4.storagemicroservice.services.StorageService;
import org.springframework.web.client.RestTemplate;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

@RestController
@RequestMapping("api/v1/storage")
public class ProductStorageController {
    @Autowired
    private StorageService service;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Storage>> getAllStorageProducts() {
        return ResponseEntity.ok(service.getStorageProducts());
    }

    @GetMapping("/product/{uuid}")
    public ResponseEntity<Storage> getStorage(@PathVariable @NotNull UUID uuid) {
        return ResponseEntity.ok(service.getStorageProduct(uuid));
    }

    @GetMapping("/product/import")
    public void importCSV() {
        final String uri = "http://localhost:8080/api/v1/product/export";
        RestTemplate restTemplate = new RestTemplate();
        String productCsv = restTemplate.getForObject(uri, String.class);
        try (FileWriter fw = new FileWriter("target/classes/Product.csv");
             BufferedWriter bw = new BufferedWriter(fw)) {
            if (productCsv != null) {
                bw.write(productCsv);
            }
            bw.flush();
        } catch (IOException e) {
            System.err.println("File couldn't be written: " + e);
        }
        String csvFileName = "target/classes/Product.csv";
        readCSVFile(csvFileName);

    }

    public void readCSVFile(String csvFileName) {
        ICsvBeanReader beanReader = null;
        CellProcessor[] processors = new CellProcessor[]{
                // TODO change Type to UUID? Curretnly String
                new org.supercsv.cellprocessor.constraint.NotNull(), // ID
                new org.supercsv.cellprocessor.constraint.NotNull(), // Name
                new org.supercsv.cellprocessor.constraint.NotNull(), // Description
                new org.supercsv.cellprocessor.constraint.NotNull(), // Size
                new org.supercsv.cellprocessor.constraint.NotNull(), // Color
                new ParseDouble(), // Price
                new ParseDouble(), // Weight
                new org.supercsv.cellprocessor.constraint.NotNull(), // Place
                new ParseInt(), // Amount
                new ParseDouble(), // Mehrwertsteuer
                new org.supercsv.cellprocessor.constraint.NotNull(), // FormattedAddress
                new ParseDate("yyyy-MM-dd") // Delivery Date
        };

        try {
            beanReader = new CsvBeanReader(new FileReader(csvFileName),
                    CsvPreference.STANDARD_PREFERENCE);

            String[] header = beanReader.getHeader(true);
            Product productBean;
            while ((productBean = beanReader.read(Product.class, header, processors)) != null) {
                Product product = new Product(
                        productBean.getId(),
                        productBean.getName(),
                        productBean.getDescription(),
                        productBean.getSize(),
                        productBean.getColor(),
                        productBean.getPrice(),
                        productBean.getWeight(),
                        productBean.getPlace(),
                        productBean.getAmount(),
                        productBean.getMehrwertsteuer(),
                        productBean.getFormattedAddress(),
                        productBean.getDeliveryDate()
                );
                productService.addNewProduct(product);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not find the CSV file: " + e);
        } catch (IOException e) {
            System.err.println("Could not read the CSV file: " + e);
            e.printStackTrace();
        } finally {
            if (beanReader != null) {
                try {
                    beanReader.close();
                } catch (IOException e) {
                    System.err.println("Error closing the reader: " + e);
                }
            }
        }
    }
}
