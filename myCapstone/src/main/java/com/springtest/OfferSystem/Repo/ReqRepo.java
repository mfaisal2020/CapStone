package com.springtest.OfferSystem.Repo;

        import com.springtest.OfferSystem.Models.Request;
        import org.springframework.data.jpa.repository.JpaRepository;

        import java.util.List;

public interface ReqRepo extends JpaRepository <Request, Long>{
        List<Request> findByReqFlag(String reqFlag);
}


