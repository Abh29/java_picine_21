package com.example.javafx_spring_registration;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavafxSpringRegistrationApplication {

    public static void main(String[] args) {
        Application.launch(RegisterApplication.class, args);
    }

}
