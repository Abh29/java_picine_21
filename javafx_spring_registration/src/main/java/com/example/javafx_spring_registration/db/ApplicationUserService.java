package com.example.javafx_spring_registration.db;

import com.example.javafx_spring_registration.model.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.MessageDigest;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class ApplicationUserService {

    @Value("${application.key}")
    private String applicationKey;


}
