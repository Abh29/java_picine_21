package com.example.simpleregistrationfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignupController {

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

    public void onClickButton(ActionEvent event)
    {
        JFXButton clicked = (JFXButton) event.getSource();

        if (clicked == registerLoginButton)
        {
            try
            {
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin-view.fxml"));
                AnchorPane pane = fxmlLoader.load();
                registerAnchor.getChildren().setAll(pane);

            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else if (clicked == registerRegisterButton)
            signup();
        else
            registerErrorTextField.setText("Unknoun source !");
        registerErrorTextField.setVisible(true);
    }



    public void showErrorMsg(String msg)
    {
        registerErrorTextField.setText(msg);
        registerErrorTextField.setVisible(true);
    }

    public void loadLoginScene(){
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin-view.fxml"));
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
        String email, passwd, user_name;

        System.out.println(registerConfirmPasswrodField.getText() + "\n" + registerPasswordField.getText());
        showErrorMsg("button clicked");
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
            passwd = HelloApplication.dbHandler.encrpytPasswd(registerPasswordField.getText());

            User user = new User(user_name, email, passwd);
            String sql = "select * from users where email = ?";

            try {
                PreparedStatement statement = HelloApplication.dbHandler.getConnection().prepareStatement(sql);
                statement.setString(1, email);
                ResultSet rs = statement.executeQuery();
                if (rs.next())
                    showErrorMsg("this email already exists, try to signin or change the email !");
                else
                    if (HelloApplication.dbHandler.add_user_to_db(user))
                        loadHelloScene();
            } catch (Exception e) {
                showErrorMsg("Error : could not signup!");
            }
        }
    }

    public void loadHelloScene()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Hello-view.fxml"));
            AnchorPane pane = fxmlLoader.load();
            registerAnchor.getChildren().setAll(pane);

        }catch (Exception e)
        {
            e.printStackTrace();
            showErrorMsg("could not load scene !");
        }
    }

}
