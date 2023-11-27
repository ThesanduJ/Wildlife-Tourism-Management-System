package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
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
import lk.ijse.wildlifetourismmanagementsystem.model.AdminModel;

import java.io.IOException;
import java.sql.SQLException;

public class ForgetPasswordFormController {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXButton btnBackToLogin;

    @FXML
    private JFXButton btnResetPassword;

    @FXML
    private PasswordField forPassword;

    @FXML
    private PasswordField forPassword2;

    @FXML
    private TextField forUsername;

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=(Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login");
    }
    @FXML
    void btnResetPasswordOnAction(ActionEvent event) throws IOException {
        String username=forUsername.getText();
        String password=forPassword.getText();
        String password2=forPassword2.getText();

        if (!password.equals(password2)) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
        }
        while (!password.equals(password2)){
            Parent rootNode=FXMLLoader.load(getClass().getResource("/view/forget-password_form.fxml"));
            Scene scene=new Scene(rootNode);
            Stage stage=(Stage) root.getScene().getWindow();
            stage.setTitle("Reset Password");
            stage.setScene(scene);
             username=forUsername.getText();
             password=forPassword.getText();
             password2=forPassword2.getText();
        }
        AdminModel model=new AdminModel();
        try {
            boolean isReset=model.resetPassword(username,password);
            if (isReset)new Alert(Alert.AlertType.INFORMATION,"Reset Successfully").show();
            else new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }
}
