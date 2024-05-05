package com.beatrizrdgs.sellers.service;

import com.beatrizrdgs.sellers.model.Seller;
import java.util.List;

public interface SellerService {

    public Seller save(Seller seller);

    public List<Seller> findAllSellers();

}
