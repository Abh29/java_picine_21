package com.example.javafx_spring_registration.controller;

import com.example.javafx_spring_registration.db.ApplicationUserRepository;
import com.example.javafx_spring_registration.model.ApplicationUser;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class SigninController implements Initializable {

    @FXML
    private AnchorPane loginAnchor;

    @FXML
    private TextField loginEmailTextField;

    @FXML
    private TextField loginPasswordField;

    @FXML
    private JFXCheckBox LoginRememberme;

    @FXML
    private JFXButton registerButton;

    @FXML
    private JFXButton logInButton;

    @FXML
    private Text loginErrorText;

    @Autowired
    private ApplicationUserRepository userRepository;

    @Autowired
    private ApplicationContext  applicationContext;

    private FXMLLoader fxmlLoader;

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == registerButton)
            loadNewScene("signup-view.fxml");
        else if (clicked == logInButton)
            signin();
        else
            loginErrorText.setText("Unknoun source !");
        loginErrorText.setVisible(true);
    }

    public void showErrorMsg(String msg)
    {
        loginErrorText.setText(msg);
        loginErrorText.setVisible(true);
    }


    public void signin()
    {
        String email;
        String passwd;

        if (loginEmailTextField.getText().equals(""))
            showErrorMsg("please fill email field!");
        if (loginPasswordField.getText().equals(""))
            showErrorMsg("please fill in the password !");
        email = loginEmailTextField.getText();
        passwd = loginPasswordField.getText();
        System.out.println(email + " "+ passwd);
        ApplicationUser user = userRepository.findByEmail(email);
        if (user == null)
            showErrorMsg("email does not exist in the database !");
        else
        {
            if (user.getPassword().equals(ApplicationUser.encrpytPasswd(passwd)))
                loadNewScene("hello-view.fxml");
            else
                showErrorMsg("Incorrect password !");
        }
    }

    public void loadNewScene(String fxmlFileName){
        try
        {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            fxmlLoader = new FXMLLoader(loader.getResource(fxmlFileName));
            fxmlLoader.setControllerFactory(applicationContext::getBean);
            AnchorPane pane = fxmlLoader.load();
            loginAnchor.getChildren().setAll(pane);

        }catch (Exception e)
        {
            e.printStackTrace();
            showErrorMsg("could not load scene !");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


