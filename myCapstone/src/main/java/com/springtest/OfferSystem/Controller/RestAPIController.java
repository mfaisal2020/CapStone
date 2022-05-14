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
    private ReqRepo reqRepo;

    @Autowired
    private UserRepo usrRepo;

    @Autowired
    private ProductRepo prdRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "Welcome Buddy";
    }

    @GetMapping(value = "/request")
    public List<Request> getReq() {
        return reqRepo.findAll();
    }

    @GetMapping(value = "/users")
    public List<Users> getUsers() {
        return usrRepo.findAll();
    }

    @PostMapping(value = "/save")
    public String saveReq(@RequestBody Request req) {
        boolean validUser = ValMod.validateUser(req.getUserName(), req.getDeviceID());
        boolean validProd = ValMod.validateProd(req.getProductID());
        if (validUser == false) {
            return "Authentication failed. Request not saved...";
        } else if (validProd == false) {
            return "Product validation failed. Request not saved...";
        }
        else {
            reqRepo.save(req);
            return "Request successfully processed and saved...";
        }
    }
}