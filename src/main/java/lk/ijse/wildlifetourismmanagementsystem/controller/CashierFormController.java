package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.wildlifetourismmanagementsystem.dto.CashierDto;
import lk.ijse.wildlifetourismmanagementsystem.model.CashierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CashierFormController {
    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;

    @FXML
    private AnchorPane pane1;
    @FXML
    private TextField txtAdminEmail;

    @FXML
    private TextField txtCashierId;

    @FXML
    private TextField txtCashierPassword;

    @FXML
    private TextField txtCashierUsername;
    private CashierModel cashierModel=new CashierModel();



    @FXML
    void btnTouAddOnAction(ActionEvent event) {
        String cashierId=txtCashierId.getText();
        String cashierUsername=txtCashierUsername.getText();
        String cashierPassword=txtCashierPassword.getText();
        String adminEmail=txtAdminEmail.getText();

        CashierDto dto=new CashierDto(cashierId,cashierUsername,cashierPassword,adminEmail);
        try {
            if (isValidate()) {
                boolean isAdd = cashierModel.addCashier(dto);
                if (!isAdd) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
                if (isAdd)new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }

    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String cashierID=txtCashierId.getText();
        try {
            boolean isDelete=cashierModel.isDelete(cashierID);
            if (isDelete) new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted!!!").show();
            else new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String cashierId=txtCashierId.getText();
        String cashierUsername=txtCashierUsername.getText();
        String cashierPassword=txtCashierPassword.getText();
        String adminEmail=txtAdminEmail.getText();

        CashierDto dto=new CashierDto(cashierId,cashierUsername,cashierPassword,adminEmail);
        try {
            if (isValidate()) {
                boolean isUpdate = cashierModel.isUpdate(dto);
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
                if(isUpdate) new Alert(Alert.AlertType.INFORMATION, "Sucessfuly Updated!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }


    public boolean isValidate(){
        Pattern compile=Pattern.compile("[C][0-9]{3,}");
        Matcher matcher=compile.matcher(txtCashierId.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Cashier ID").show();
            return false;
        }

        Pattern compile1 = Pattern.compile("^[a-zA-Z0-9_.-]{4,20}$");
        Matcher matcher1 = compile1.matcher(txtCashierUsername.getText());
        boolean matches1 = matcher1.matches();


        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Cashier username").show();
            return false;
        }

//        Pattern compile2 = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\\$%\\^&\\*])(?=.{8,12})$");
//        Matcher matcher2 = compile2.matcher(txtCashierPassword.getText());
//        boolean matches2 = matcher2.matches();
//
//
//        if (!matches2){
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Cashier password").show();
//            return false;
//        }

        Pattern compile3 = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher3 = compile3.matcher(txtAdminEmail.getText());
        boolean matches3 = matcher3.matches();


        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Admin Email").show();
            return false;
        }
        return true;
    }
}
