package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.dto.VehicleDto;
import lk.ijse.wildlifetourismmanagementsystem.model.VehicleModel;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleFormController {

    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;

    @FXML
    private DatePicker licenceDate;

    @FXML
    private DatePicker permitDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPermitNo;

    @FXML
    private TextField txtPackage;

    @FXML
    private TextField txtReg;

    VehicleModel model=new VehicleModel();
    @FXML
    void btnTouAddOnAction(ActionEvent event)  {
        String registration=txtReg.getText();
        String packageId=txtPackage.getText();
        String adminEmail=txtEmail.getText();
        String permitNo=txtPermitNo.getText();
        String permitD=permitDate.getValue().toString();
        String licenceD=licenceDate.getValue().toString();

        VehicleDto dto=new VehicleDto(registration,packageId,adminEmail,permitNo,permitD,licenceD);
        try {
            if(isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
                boolean isAdd = model.isAdd(dto);
                if (!isAdd) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String registration=txtReg.getText();
        try {
            boolean isDelete=model.isDelete(registration);
            if (isDelete)new Alert(Alert.AlertType.INFORMATION,"Successfully deleted").show();
            else new Alert(Alert.AlertType.ERROR,"Something went wrong");
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage());
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String registration=txtReg.getText();
        String packageId=txtPackage.getText();
        String adminEmail=txtEmail.getText();
        String permitNo=txtPermitNo.getText();
        String permitD=permitDate.getValue().toString();
        String licenceD=licenceDate.getValue().toString();

        VehicleDto dto=new VehicleDto(registration,packageId,adminEmail,permitNo,permitD,licenceD);
        try {
            if(isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Update Successfully!!!").show();
                boolean isUpdate = model.isUpdate(dto);
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) {

    }
    public boolean isValidate(){
        Pattern compile=Pattern.compile("^[A-Z]{2}[\\d]{2}[A-Z]{2}[\\d]{4}$\n");
        Matcher matcher=compile.matcher(txtReg.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Registration number").show();
            return false;
        }
        Pattern compile1=Pattern.compile("[P][0-9]{3,}");
        Matcher matcher1=compile.matcher(txtReg.getText());
        boolean matches1=matcher.matches();

        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Package Id").show();
            return false;
        }
        Pattern compile2=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\n");
        Matcher matcher2=compile.matcher(txtEmail.getText());
        boolean matches2=matcher.matches();

        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email").show();
            return false;
        }
        Pattern compile3=Pattern.compile("^[A-Z0-9]{6,10}$\n");
        Matcher matcher3=compile.matcher(txtPermitNo.getText());
        boolean matches3=matcher.matches();

        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Permit number").show();
            return false;
        }
        Pattern compile4=Pattern.compile("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$\n");
        Matcher matcher4=compile.matcher(permitDate.getValue().toString());
        boolean matches4=matcher.matches();

        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Permit ExpireDate").show();
            return false;
        }
        Pattern compile5=Pattern.compile("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$\n");
        Matcher matcher5=compile.matcher(licenceDate.getValue().toString());
        boolean matches5=matcher.matches();

        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Licence ExpireDate").show();
            return false;
        }
        return true;
    }
}
