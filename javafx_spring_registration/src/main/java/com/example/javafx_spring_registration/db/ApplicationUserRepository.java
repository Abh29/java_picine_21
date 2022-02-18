package com.example.javafx_spring_registration.db;

import com.example.javafx_spring_registration.model.ApplicationUser;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {

    ApplicationUser findByEmail(String email);
}