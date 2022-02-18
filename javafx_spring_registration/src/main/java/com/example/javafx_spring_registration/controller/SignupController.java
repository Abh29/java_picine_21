package com.example.javafx_spring_registration.controller;

import com.example.javafx_spring_registration.db.ApplicationUserRepository;
import com.example.javafx_spring_registration.db.ApplicationUserService;
import com.example.javafx_spring_registration.model.ApplicationUser;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

@Component
@Controller
public class SignupController implements Initializable {

    @FXML
    private AnchorPane registerAnchor;

    @FXML
    private JFXButton registerRegisterButton;

    @FXML
    private JFXButton registerLoginButton;

    @FXML
    private TextField registerNameTextField;

    @FXML
    private TextField registerEmailTextField;

    @FXML
    private PasswordField registerPasswordField;

    @FXML
    private PasswordField registerConfirmPasswrodField;

    @FXML
    private Text registerErrorTextField;

    @Autowired
    ApplicationUserRepository userRepository;

    @Autowired
    ApplicationContext  applicationContext;

    private FXMLLoader fxmlLoader;

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == registerLoginButton)
            loadNewScene("signin-view.fxml");
        else if (clicked == registerRegisterButton)
            signup();
        else
            registerErrorTextField.setText("Unknown source !");
        registerErrorTextField.setVisible(true);
    }

    public void showErrorMsg(String msg)
    {
        registerErrorTextField.setText(msg);
        registerErrorTextField.setVisible(true);
    }

    public void loadNewScene(String fxmlFileName){
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            fxmlLoader = new FXMLLoader(loader.getResource(fxmlFileName));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            AnchorPane pane = fxmlLoader.load();
            registerAnchor.getChildren().setAll(pane);

        }catch (Exception e)
        {
            e.printStackTrace();
            showErrorMsg("could not load scene !");
        }
    }

    public void signup()
    {
        String email = null , passwd = null, user_name = null;

        if (registerNameTextField.getText().length() < 4)
            showErrorMsg("please fill in the userName field!");
        else if (registerEmailTextField.getText().length() < 4 || !registerEmailTextField.getText().matches(".+@.+\\..+"))
            showErrorMsg("please fill in the email correctly !");
        else if (registerPasswordField.getText().isEmpty())
            showErrorMsg("please fill in the password");
        else if (!registerConfirmPasswrodField.getText().equals(registerPasswordField.getText()))
            showErrorMsg("wrong password confirmation");
        else {
            user_name = registerNameTextField.getText();
            email = registerEmailTextField.getText();
            passwd = registerPasswordField.getText();
        }
        System.out.println(user_name + email + passwd);
        ApplicationUser user = new ApplicationUser(user_name, email, passwd);
        System.out.println(user.getPassword());
        userRepository.save(user);
        System.out.println("saved successfully");
        loadNewScene("hello-view.fxml");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
