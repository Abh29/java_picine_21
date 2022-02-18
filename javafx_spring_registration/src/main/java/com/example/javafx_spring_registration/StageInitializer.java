package com.example.javafx_spring_registration;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {

    private final String applicationTitle;
    private final ApplicationContext applicationContext;
    @Value("classpath:/signin-view.fxml")
    private Resource fxml;

    StageInitializer(@Value("${spring.application.ui.applicationTitle}") String applicationTitle, ApplicationContext applicationContext)
    {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event){
        Stage stage = event.getStage();

        URL url = null;
        Scene scene;

        try {
            url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            scene = new Scene(fxmlLoader.load(), 600, 512);
            stage.setTitle(applicationTitle);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
