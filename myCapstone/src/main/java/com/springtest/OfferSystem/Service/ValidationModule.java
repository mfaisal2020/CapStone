package com.springtest.OfferSystem.Service;

import com.springtest.OfferSystem.Repo.ProductRepo;
import com.springtest.OfferSystem.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class ValidationModule {

    @Autowired
    private UserRepo usrRepo;

    @Autowired
    private ProductRepo prdRepo;

    public boolean validateUser(@RequestParam String userName){
        boolean a = usrRepo.existsById(userName);
        return a;
    }

    public boolean validateProd(@RequestParam String prodID){
        boolean a = prdRepo.existsById(prodID);
        return a;
    }
}
