package com.beatrizrdgs.sellers.service;

import com.beatrizrdgs.sellers.model.Seller;
import com.beatrizrdgs.sellers.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
