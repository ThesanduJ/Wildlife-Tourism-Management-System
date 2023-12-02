package lk.ijse.wildlifetourismmanagementsystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.wildlifetourismmanagementsystem.dto.CashierDto;
import lk.ijse.wildlifetourismmanagementsystem.model.AdminModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;
    CashierDto cashierDtodto=new CashierDto();

    @FXML
    void btnSignUpOnAction(ActionEvent event) throws IOException {
        Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=(Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("SignUp Page");

    }
    public void login() throws IOException {
        Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=(Stage) root.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
        stage.setScene(scene);
    }
    public void clearText(){
        txtPassword.setText("");
        txtUsername.setText("");
    }

    public void btnForgetOnAction(ActionEvent actionEvent) throws IOException {
            Parent rootNode=FXMLLoader.load(getClass().getResource("/view/forget-password_form.fxml"));
            Scene scene=new Scene(rootNode);
            Stage stage=(Stage) root.getScene().getWindow();
            stage.setTitle("Reset Password");
            stage.setScene(scene);
    }
    @FXML
    void btnLoginOnAction(ActionEvent event) throws SQLException{
        String username=txtUsername.getText();
        String password=txtPassword.getText();

        try {
            AdminModel adminModel=new AdminModel();
            boolean isValid=adminModel.isValid(username,password);

            if (isValid) {
                login();
            }else {
                new Alert(Alert.AlertType.ERROR,"User Name And Password Did Not Matched try again").show();
                clearText();
            }
        } catch (SQLException | IOException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

}
