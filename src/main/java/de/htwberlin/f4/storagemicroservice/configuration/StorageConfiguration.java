package de.htwberlin.f4.storagemicroservice.configuration;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

/**
 * Class to generate some Objects to store
 */
@Configuration
public class StorageConfiguration {

    /**
     * Stores the rest attributes from 10 Products
     * (UUID is only valid locally)
     * @param storageRepository DAO
     * @return void
     */
    @Bean
    CommandLineRunner commandLineRunner(StorageRepository storageRepository) {
        return args -> {
            Storage storage01 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14600000"),
                    205,
                    1,
                    "Berlin"
            );
            Storage storage02 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14710001"),
                    76,
                    5,
                    "Tokyo"
            );
            Storage storage03 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14710002"),
                    98,
                    2,
                    "Wellington"
            );
            Storage storage04 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720003"),
                    46,
                    3,
                    "Rom"
            );
            Storage storage05 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720004"),
                    25,
                    7,
                    "Kapstadt"
            );
            Storage storage06 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720005"),
                    1,
                    100,
                    "New York"
            );
            Storage storage07 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720006"),
                    17,
                    9,
                    "Peking"
            );
            Storage storage08 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720007"),
                    523,
                    4,
                    "London"
            );
            Storage storage09 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14720008"),
                    34,
                    14,
                    "Dublin"
            );
            Storage storage10 = new Storage(
                    UUID.fromString("c0a8b22e-7de7-1e0d-817d-e7ee14730009"),
                    5,
                    21,
                    "Paris"
            );
            storageRepository.saveAll(
                    List.of(
                            storage01,
                            storage02,
                            storage03,
                            storage04,
                            storage05,
                            storage06,
                            storage07,
                            storage08,
                            storage09,
                            storage10
                    )
            );
        };
    }
}
