package com.beatrizrdgs.sellers.controller;

import com.beatrizrdgs.sellers.model.Seller;
import com.beatrizrdgs.sellers.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping("/add")
    public String add(@RequestBody Seller seller) {
        sellerService.save(seller);
        return "Added successfully";
    }

    @GetMapping("/all")
    public List<Seller> getAllSellers() {
        return sellerService.findAllSellers();
    }

}
