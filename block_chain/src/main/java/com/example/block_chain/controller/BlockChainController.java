package com.example.block_chain.controller;


import com.example.block_chain.db.BlockChainRepository;
import com.example.block_chain.model.Block;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Controller
@Component
public class BlockChainController implements Initializable {

    private final BlockChainRepository blockChainRepository;

    private final ApplicationContext applicationContext;

    @Value("${application.key}")
    private String publicKey;

    @Value("classpath:/addData-view.fxml")
    private Resource addStageFxml;

    private ObservableList<Block> blocks;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXTreeTableView<Block> blockChainTreeTableView;

    @FXML
    private TreeTableColumn<Block, Long> idCol;

    @FXML
    private TreeTableColumn<Block, String> previousHashCol;

    @FXML
    private TreeTableColumn<Block, String> dataCol;

    @FXML
    private TreeTableColumn<Block, String> publicKeyCol;

    @FXML
    private TreeTableColumn<Block, Integer> nonceCol;

    @FXML
    private TreeTableColumn<Block, String> hashCol;

    @FXML
    private TreeTableColumn<Block, String> timestampCol;

    @FXML
    private Pane buttonsPane;

    @FXML
    private JFXButton refreshButton;

    @FXML
    private JFXButton addDataButton;



    public BlockChainController(BlockChainRepository blockChainRepository, ApplicationContext applicationContext) {
        this.blockChainRepository = blockChainRepository;
        this.applicationContext = applicationContext;
    }


    public void setOnSceneSizeChange(Scene scene)
    {
        idCol.setPrefWidth(scene.getWidth() * 0.05);
        previousHashCol.setPrefWidth(scene.getWidth() * 0.1);
        hashCol.setPrefWidth(scene.getWidth() * 0.1);
        publicKeyCol.setPrefWidth(scene.getWidth() * 0.2);
        nonceCol.setPrefWidth(scene.getWidth() * 0.05);
        timestampCol.setPrefWidth(scene.getWidth() * 0.1);
        dataCol.setPrefWidth(scene.getWidth() * 0.4);
    }

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == refreshButton)
            getDataFromDBToTree();
        else if (clicked == addDataButton)
            loadAddDataStage();
        else
            System.out.println("unknown button clicked !");
    }


    private void loadAddDataStage()
    {
        System.out.println("loading a new stage");
        try {
            Stage stage = new Stage();
            stage.setTitle("add Data");
            FXMLLoader fxmlLoader = new FXMLLoader(addStageFxml.getURL());
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            Scene scene = new Scene(fxmlLoader.load(), 512, 512);
            stage.setScene(scene);
            stage.show();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }


    private void getDataFromDBToTree()
    {
        // get the blocks from db
        List<Block> all = blockChainRepository.findAll();

        // add the blocks to observableList
        blocks = FXCollections.observableArrayList(all);


        TreeItem<Block> blockTreeItem = new RecursiveTreeItem<>(blocks, RecursiveTreeObject::getChildren);
        blockChainTreeTableView.setRoot(blockTreeItem);
        blockChainTreeTableView.setShowRoot(false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // link block data to columns

        idCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, Long>("id"));
        previousHashCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, String>("previousHash"));
        dataCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, String>("data"));
        hashCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, String>("hash"));
        publicKeyCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, String>("publicKey"));
        nonceCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, Integer>("nonce"));
        timestampCol.setCellValueFactory(new TreeItemPropertyValueFactory<Block, String>("timeStamp"));

        getDataFromDBToTree();

    }
}
