package com.example.block_chain.controller;

import com.example.block_chain.db.BlockChainRepository;
import com.example.block_chain.model.Block;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.ResourceBundle;

@Controller
@Component
public class AddDataController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    public JFXTextArea newDataTextArea;

    @FXML
    private JFXButton addDataButton;

    private final BlockChainRepository blockChainRepository;

    private final ApplicationContext applicationContext;

    @Value("${application.key}")
    private String publicKey;

    @Value("${spring.application.ui.applicationTitle}")
    private String appTitle;

    public AddDataController(BlockChainRepository blockChainRepository, ApplicationContext applicationContext) {
        this.blockChainRepository = blockChainRepository;
        this.applicationContext = applicationContext;
    }

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == addDataButton)
        {
            String dataText = newDataTextArea.getText();
            if(dataText == null || dataText.length() < 1)
                return;
            Block last = blockChainRepository.findTopByOrderByIdDesc();
            Block newBlock = new Block(dataText, last.getHash(), publicKey);
            newBlock.setValidHash(2, '0');
            blockChainRepository.save(newBlock);
            Stage stage = (Stage) anchorPane.getScene().getWindow();
            stage.close();
        }
        else
            System.out.println("unknown button clicked !");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
