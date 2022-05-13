package com.springtest.OfferSystem.Repo;

        import com.springtest.OfferSystem.Models.Request;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface ReqRepo extends JpaRepository <Request, Long>{
}


