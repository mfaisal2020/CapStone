package com.springtest.OfferSystem.Service;

import com.springtest.OfferSystem.Models.Users;
import com.springtest.OfferSystem.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.stream.Stream;


@Service
public class UserAuthModule {

    @Autowired
    private UserRepo usrRepo;

    public void validate(@RequestParam String userName, String deviceID){
        boolean a = usrRepo.existsById(2L);
        Stream<Users> abc = usrRepo.findAll().stream().filter(t -> t.getUserName().equals(userName));
        //boolean abc = usrRepo.exists(userName);
        System.out.println("Count" + abc);

    }
}
