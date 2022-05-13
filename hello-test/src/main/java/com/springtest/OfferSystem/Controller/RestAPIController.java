package com.springtest.OfferSystem.Controller;

        import com.springtest.OfferSystem.Models.*;
        import com.springtest.OfferSystem.Service.UserAuthModule;
        import com.springtest.OfferSystem.Repo.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.web.bind.annotation.*;
        import java.util.List;

@RestController
public class RestAPIController {

    @Autowired
    private UserAuthModule AuthMod;

    @Autowired
    private ReqRepo reqRepo;

    @Autowired
    private UserRepo usrRepo;

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
        reqRepo.save(req);
        AuthMod.validate(req.getUserName(), req.getDeviceID());
        return "Saved...";
    }

}