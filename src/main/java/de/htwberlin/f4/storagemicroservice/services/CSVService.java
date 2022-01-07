package de.htwberlin.f4.storagemicroservice.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.stereotype.Service;

import de.htwberlin.f4.storagemicroservice.models.Product;

@Service
public class CSVService {
    public @NotEmpty List<@Valid Product> readCSVFile() throws IOException {
        FileReader reader = new FileReader("products.csv");
        List<Product> products = new CsvToBeanBuilder<Product>(reader).withType(Product.class).build().parse();
        reader.close();
        return products;
    }
}
