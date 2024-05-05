package com.beatrizrdgs.sellers.service;

import com.beatrizrdgs.sellers.model.Seller;
import com.beatrizrdgs.sellers.repository.SellerRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller findById(int id) {
        return sellerRepository.findById(id).orElse(null);
    }

    @Override
    public Seller deleteById(int id) {
        Seller seller = sellerRepository.findById(id).orElse(null);
        sellerRepository.deleteById(id);
        return seller;
    }

    @Override
    public Seller generateRandomSeller() {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://randomuser.me/api/"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            ObjectMapper objectMapper = new ObjectMapper();

            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode resultsNode = rootNode.get("results").get(0);

            String gender = resultsNode.get("gender").asText();

            JsonNode nameNode = resultsNode.get("name");
            String firstName = nameNode.get("first").asText();
            String lastName = nameNode.get("last").asText();

            Seller seller = new Seller();
            seller.setGender(gender);
            seller.setName(firstName + " " + lastName);

            return sellerRepository.save(seller);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
