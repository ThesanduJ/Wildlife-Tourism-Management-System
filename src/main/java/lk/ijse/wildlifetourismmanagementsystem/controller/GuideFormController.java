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
                boolean isAdd = guideModel.isAdd(guideDto);
                if (isAdd)new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
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
                boolean isUpdated = guideModel.isUpdated(dto);
                if (isUpdated)new Alert(Alert.AlertType.INFORMATION, "Successfully Updated!!!").show();
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
        Matcher matcher1=compile1.matcher(txtName.getText());
        boolean matches1=matcher1.matches();

        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Guide Name").show();
            return false;
        }
        Pattern compile4=Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        Matcher matcher4=compile4.matcher(txtMobile.getText());
        boolean matches4=matcher4.matches();
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Mobile number").show();
            return false;
        }


//        Pattern compile4=Pattern.compile("^(\\d{1,5}) (?:[A-Za-z]+\\s?)+, ([A-Z]{2}), (\\d{5})$\n");
//        Matcher matcher4=compile4.matcher(txtAddress.getText());
//        boolean matches4=matcher4.matches();
//
//        if (!matches4) {
//            new Alert(Alert.AlertType.ERROR, "Something went wrong in a Guide Address").show();
//            return false;
//        }
        Pattern compile5=Pattern.compile("[P][0-9]{3,}");
        Matcher matcher5=compile5.matcher(txtPackage.getText());
        boolean matches5=matcher5.matches();

        if (!matches5) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong in a package id").show();
            return false;
        }
        Pattern compile6=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher6=compile6.matcher(txtEmail.getText());
        boolean matches6=matcher6.matches();

        if (!matches6) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong in a admin Email").show();
            return false;
        }
        return true;
    }
}
