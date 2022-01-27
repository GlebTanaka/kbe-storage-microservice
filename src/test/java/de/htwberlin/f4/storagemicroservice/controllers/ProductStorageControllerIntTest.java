package de.htwberlin.f4.storagemicroservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import de.htwberlin.f4.storagemicroservice.dao.StorageRepository;
import de.htwberlin.f4.storagemicroservice.models.Storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductStorageControllerIntTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private StorageRepository storageRepository;

    @Autowired 
    private ObjectMapper mapper;

    @Test
    public void postProductTest() throws Exception {
        List<Storage> storages = storageRepository.findAll();
        assertEquals(0, storages.size());

        Storage storage = new Storage(UUID.randomUUID(), 37, 4, "place");
        String json = mapper.writeValueAsString(storage);

        mvc.perform(post("/api/v1/storage/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        storages = storageRepository.findAll();

        assertEquals(1, storages.size());
        assertEquals(storage, storages.get(0));
        storageRepository.deleteAll();
    }

    @Test
    public void getProductTest() throws Exception {
        Storage storage = new Storage(UUID.randomUUID(), 37, 4, "place");
        storageRepository.save(storage);

        MvcResult mvcR = mvc.perform(get("/api/v1/storage/product/" + storage.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        Storage product = mapper.readValue(Objects.requireNonNull(mvcR).getResponse().getContentAsString(), Storage.class);

        assertEquals(storage, product);
        storageRepository.deleteAll();
    }
}
