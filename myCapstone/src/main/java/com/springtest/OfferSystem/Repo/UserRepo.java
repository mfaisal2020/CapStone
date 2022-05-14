package com.springtest.OfferSystem.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springtest.OfferSystem.Models.Users;

public interface UserRepo extends JpaRepository<Users, String> {

}



