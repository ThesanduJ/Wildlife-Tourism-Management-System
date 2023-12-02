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
import lk.ijse.wildlifetourismmanagementsystem.dto.AdminDto;
import lk.ijse.wildlifetourismmanagementsystem.model.AdminModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpFormController {
    @FXML
    private AnchorPane root;
    @FXML
    private TextField txtEmail;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private PasswordField txtRePassword;

    @FXML
    private TextField txtUsername;
    AdminModel adminModel = new AdminModel();

    @FXML
    void btnLogin2OnAction(ActionEvent event) throws IOException {
        Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=(Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Page");
    }

    @FXML
    void btnOnActionRegister(ActionEvent event) throws IOException {
        String username=txtUsername.getText();
        String email=txtEmail.getText();
        String password=txtPassword.getText();
        String password2=txtRePassword.getText();

        if (!password.equals(password2)) {
            new Alert(Alert.AlertType.ERROR, "Password doen't match").show();
        }
        while (!password.equals(password2)){
            Parent rootNode=FXMLLoader.load(this.getClass().getResource("/view/signup_form.fxml"));
            Scene scene=new Scene(rootNode);
            Stage stage=(Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.setTitle("SignUp Page");

           username=txtUsername.getText();
             email=txtEmail.getText();
           password=txtPassword.getText();
             password2=txtRePassword.getText();
        }

        AdminDto adminDto=new AdminDto(username,email,password);
        try {
            if(isValidation()) {

                Boolean isSaved = adminModel.saveAdmin(adminDto);
                if (isSaved) new Alert(Alert.AlertType.CONFIRMATION, "successfully Added!!!").show();
                if (!isSaved) new Alert(Alert.AlertType.ERROR, "Some thing went wrong").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
    public boolean isValidation(){
        Pattern compile = Pattern.compile("^[A-Za-z0-9_]{3,16}$");
        Matcher matcher = compile.matcher(txtUsername.getText());
        boolean matches = matcher.matches();


        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a username").show();
            return false;
        }
        Pattern compile1 = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher1 = compile1.matcher(txtEmail.getText());
        boolean matches1 = matcher1.matches();


        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email").show();
            return false;
        }

        return  true;
    }
}
