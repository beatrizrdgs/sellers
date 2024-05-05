package com.beatrizrdgs.sellers.controller;

import com.beatrizrdgs.sellers.model.Seller;
import com.beatrizrdgs.sellers.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSeller(@PathVariable int id) {
        Seller seller = sellerService.findById(id);
        if (seller == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(seller);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSellers() {
        List<Seller> sellers = sellerService.findAllSellers();
        return ResponseEntity.ok(sellers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Seller> deleteSeller(@PathVariable int id) {
        if (sellerService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        Seller seller = sellerService.deleteById(id);
        return ResponseEntity.ok(seller);
    }

    @PostMapping
    public ResponseEntity<Seller> saveSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerService.save(seller);
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(savedSeller);
    }

    @PostMapping("/random")
    public ResponseEntity<Seller> saveRandomSeller() {
        Seller savedSeller = sellerService.generateRandomSeller();
        return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(savedSeller);
    }

}
