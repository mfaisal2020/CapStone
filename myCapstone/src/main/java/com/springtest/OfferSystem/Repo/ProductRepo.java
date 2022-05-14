package com.springtest.OfferSystem.Repo;

        import com.springtest.OfferSystem.Models.Product;
        import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, String> {

}



