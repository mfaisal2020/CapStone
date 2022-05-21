package com.springtest.OfferSystem.Service;

import com.springtest.OfferSystem.Repo.AllRequestsRepo;
import com.springtest.OfferSystem.Repo.ReqRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springtest.OfferSystem.Models.*;
import java.util.List;



@Service
public class NotificationModule {

    @Autowired
    private ReqRepo reqRepo;

    public String notifyCustomer(){
        List <Request> r = reqRepo.findByReqFlag("New");
        String abc = new String();
        for (int i=0; i<r.size(); i++) {
            Request a = r.get(i);
            if (a.getReqDecision() != null) {
                abc = abc + "\n" + "The company has " + a.getReqDecision() +
                        " your requested for an offer on the product " + a.getProductID() +
                        " for a request price of $" + a.getRequestedPrice();

            } else {
                abc = abc + "\n" + "The " + a.getProductID() +
                        " product you requested an offer for, is still under review process";
            }
        }
        return abc;
    }
}