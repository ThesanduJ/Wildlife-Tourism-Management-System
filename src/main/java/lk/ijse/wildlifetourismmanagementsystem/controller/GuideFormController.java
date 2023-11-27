package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.dto.GuideDto;
import lk.ijse.wildlifetourismmanagementsystem.model.GuideModel;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuideFormController {
    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;

    @FXML
    private DatePicker date;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPackage;

    GuideModel guideModel=new GuideModel();

    @FXML
    void btnTouAddOnAction(ActionEvent event) {
        String nic=txtNic.getText();
        String name=txtName.getText();
        String mobile=txtMobile.getText();
        String day= String.valueOf(date.getValue());
        String address=txtAddress.getText();
        String packageText=txtPackage.getText();
        String email=txtEmail.getText();

        GuideDto guideDto=new GuideDto(nic,name,mobile,day,address,packageText,email);
        try {
            if (isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
                boolean isAdd = guideModel.isAdd(guideDto);
                if (!isAdd) new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String nic=txtNic.getText();
        try {
            boolean isDeleted=guideModel.isDeleted(nic);
            if (isDeleted) new Alert(Alert.AlertType.INFORMATION,"Sucessfully Deleted!!!").show();
            else new Alert(Alert.AlertType.ERROR,"Something went wrong!!!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String nic=txtNic.getText();
        String name=txtName.getText();
        String mobile=txtMobile.getText();
        String day= String.valueOf(date.getValue());
        String address=txtAddress.getText();
        String packageText=txtPackage.getText();
        String email=txtEmail.getText();

        GuideDto dto=new GuideDto(nic,name,mobile,day,address,packageText,email);
        try {
            if(isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully Updated!!!").show();

                boolean isUpdated = guideModel.isUpdated(dto);
                if (!isUpdated) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) {

    }
    public boolean isValidate(){
        Pattern compile=Pattern.compile("^(?:[0-9]{9}|[0-9]{12})|(v|V|x|X)$");
        Matcher matcher=compile.matcher(txtNic.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Guide NIC").show();
            return false;
        }
        Pattern compile1=Pattern.compile("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        Matcher matcher1=compile.matcher(txtName.getText());
        boolean matches1=matcher.matches();

        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Guide Name").show();
            return false;
        }
        Pattern compile2=Pattern.compile("^(?:0|94|\\+94)?(?:7|11|07|107|011|1011)[1-9]{7}$\n");
        Matcher matcher2=compile.matcher(txtMobile.getText());
        boolean matches2=matcher.matches();

        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Guide mobile number").show();
            return false;
        }
        Pattern compile3=Pattern.compile("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$\n");
        Matcher matcher3=compile.matcher(date.getValue().toString());
        boolean matches3=matcher.matches();

        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a date").show();
            return false;
        }
        Pattern compile4=Pattern.compile("^(\\d{1,5}) (?:[A-Za-z]+\\s?)+, ([A-Z]{2}), (\\d{5})$\n");
        Matcher matcher4=compile.matcher(txtAddress.getText());
        boolean matches4=matcher.matches();

        if (!matches4) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong in a Guide Address").show();
            return false;
        }
        Pattern compile5=Pattern.compile("[P][0-9]{3,}");
        Matcher matcher5=compile.matcher(txtPackage.getText());
        boolean matches5=matcher.matches();

        if (!matches5) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong in a package id").show();
            return false;
        }
        Pattern compile6=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\n");
        Matcher matcher6=compile.matcher(txtEmail.getText());
        boolean matches6=matcher.matches();

        if (!matches6) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong in a admin Email").show();
            return false;
        }
        return true;
    }
}
