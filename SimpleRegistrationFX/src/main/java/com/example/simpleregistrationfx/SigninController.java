package com.example.simpleregistrationfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SigninController {

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

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == registerButton)
            loadRegisterScene();
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

    public void loadRegisterScene(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup-view.fxml"));
            AnchorPane pane = fxmlLoader.load();
            loginAnchor.getChildren().setAll(pane);

        }catch (Exception e)
        {
            e.printStackTrace();
            showErrorMsg("could not load scene !");
        }
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
        passwd = HelloApplication.dbHandler.encrpytPasswd(loginPasswordField.getText());
        System.out.println(email + " "+ passwd);

        String sql = "select * from users where email = ? and password = ?";

        try
        {
            PreparedStatement statement = HelloApplication.dbHandler.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, passwd);
            ResultSet rs = statement.executeQuery();
            if (rs.next())
                loadHelloScene();
            else
                showErrorMsg("no user was found, check your credentials again or register !");
        }catch (Exception e)
        {
            showErrorMsg("Error : could not signin!");
        }


    }

    public void loadHelloScene()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Hello-view.fxml"));
            AnchorPane pane = fxmlLoader.load();
            loginAnchor.getChildren().setAll(pane);

        }catch (Exception e)
        {
            e.printStackTrace();
            showErrorMsg("could not load scene !");
        }
    }

}


