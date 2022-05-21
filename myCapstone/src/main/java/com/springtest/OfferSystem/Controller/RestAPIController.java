package com.springtest.OfferSystem.Controller;

        import com.springtest.OfferSystem.Models.*;
        import com.springtest.OfferSystem.Service.*;
        import com.springtest.OfferSystem.Repo.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
public class RestAPIController {

    @Autowired
    private ValidationModule ValMod;

    @Autowired
    private PreprocessingModule prepopMod;

    @Autowired
    private NotificationModule notifyMod;

    @Autowired
    private ReqRepo reqRepo;

    @Autowired
    private UserRepo usrRepo;

    @Autowired
    private ProductRepo prdRepo;

    @Autowired
    private AllRequestsRepo allreqRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome to the Crowd Sourcing Offer Decisioning System";
    }

    @GetMapping(value = "/request")
    public List<Request> getReq() {
        return reqRepo.findAll();
    }

    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        return usrRepo.findAll();
    }

    @GetMapping(value = "/notify")
    public String getNotify() {
        return (notifyMod.notifyCustomer());
    }

    @PostMapping(value = "/save")
    public String saveReq(@RequestBody Request req) {
        boolean validUser = ValMod.validateUser(req.getUserName());
        boolean validProd = ValMod.validateProd(req.getProductID());
        if (validUser == false) return "Authentication failed. Request not saved...";
        else if (validProd == false) return "Product validation failed. Request not saved...";
        else {
            reqRepo.save(req);
            prepopMod.enrichRequest(req.getProductID(), req.getRequestedPrice(), req.getZipCode());
            return "Request successfully processed and saved...";
        }
    }

}