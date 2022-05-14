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

    public boolean validateUser(@RequestParam String userName, String deviceID){
        boolean a = usrRepo.existsById(1L);
        //Stream<Users> abc = usrRepo.findAll().stream().filter(t -> t.getUserName().equals(userName));
        //boolean abc = usrRepo.exists(userName);
        //System.out.println("Count" + abc);
        return a;
    }

    public boolean validateProd(@RequestParam String prodID){
        boolean a = true;
        //Stream<Users> abc = usrRepo.findAll().stream().filter(t -> t.getUserName().equals(userName));
        //boolean abc = usrRepo.exists(userName);
        //System.out.println("Count" + abc);
        return a;
    }
}
