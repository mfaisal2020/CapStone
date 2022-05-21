// This module is to build the dataset required for the
// Machine Learning algorithm. After the validations are
// complete, this module kicks in and builds the record
// with all relevant characteristic information of the
// product, merchant and geographic information.

package com.springtest.OfferSystem.Service;

import com.springtest.OfferSystem.Models.AllRequests;
import com.springtest.OfferSystem.Repo.AllRequestsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class PreprocessingModule {

    @Autowired
    private AllRequestsRepo allreqRepo;

    public void enrichRequest(@RequestParam String productID, Double requestedPrice, long zipCode){
        AllRequests a = new AllRequests();
        a.setProdID(productID);
        a.setMarketPrice(100);
        a.setRequestedPrice(requestedPrice);
        a.setLocationZIP(zipCode);
        a.setProductDemand("High");
        a.setNbrofReq(550);
        a.setReqCredibility(7);
        a.setOfferByOthers("Y");
        a.setRevenueOpportunity(8);
        a.setMerchantAlignments("N");
        a.setBasicVsNon("Basic");
        a.setLast12mntsOffer("N");
        a.setDecision(9);
        allreqRepo.save(a);
    }
}