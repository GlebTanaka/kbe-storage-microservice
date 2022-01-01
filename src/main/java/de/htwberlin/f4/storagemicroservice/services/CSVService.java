package de.htwberlin.f4.storagemicroservice.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.bean.CsvToBeanBuilder;

import org.springframework.stereotype.Service;

import de.htwberlin.f4.storagemicroservice.models.Product;

@Service
public class CSVService {
    public List<Product> readCSVFile() {
        /*ICsvBeanReader beanReader = null;
        CellProcessor[] processors = new CellProcessor[]{
                // change Type to UUID? Curretnly String
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
            }*/
            try {
                FileReader reader = new FileReader("products.csv");
                List<Product> products = new CsvToBeanBuilder<Product>(reader).withType(Product.class).build().parse();
                reader.close();
                return products;
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
                return null;
            } 
        }
    
}
