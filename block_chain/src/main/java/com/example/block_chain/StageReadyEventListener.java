package com.example.block_chain;

import com.example.block_chain.controller.BlockChainController;
import com.example.block_chain.db.BlockChainRepository;
import com.example.block_chain.model.Block;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Random;

@Component
public class StageReadyEventListener implements ApplicationListener<StageReadyEvent> {

    @Value("${spring.application.ui.applicationTitle}")
    private String applicationTitle;

    private final ApplicationContext applicationContext;

    @Value("classpath:/blockChain-view.fxml")
    private Resource fxml;

    StageReadyEventListener(ApplicationContext applicationContext)
    {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event){

        populateDBRandom(20);

        try {
            Stage stage = event.getStage();
            FXMLLoader fxmlLoader = new FXMLLoader(fxml.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(fxmlLoader.load(), 1000, 512);
            stage.setTitle(applicationTitle);
            stage.setScene(scene);
            stage.show();

            BlockChainController controller = fxmlLoader.getController();
            controller.setOnSceneSizeChange(scene);

            scene.heightProperty().addListener((observable, oldValue, newValue) -> {
               controller.setOnSceneSizeChange(scene);
            });

            scene.widthProperty().addListener((obs, oldVal, newVal) -> {
                controller.setOnSceneSizeChange(scene);
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Value("${application.key}")
    private String publicKey;

    @Autowired
    private BlockChainRepository blockChainRepository;

    private void populateDBRandom(int exemplars)
    {

        Random random = new Random();
        String previous_hash = null;

        for (int i = 0; i < exemplars; i++) {

            String generatedString = random.ints('a', 'z' + 1)
                    .limit(1024)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            Block newBlock = new Block(generatedString, previous_hash, publicKey);
            previous_hash = newBlock.setValidHash(2,'0');
            blockChainRepository.save(newBlock);
        }
    }


}
