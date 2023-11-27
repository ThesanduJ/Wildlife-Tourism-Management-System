package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.ijse.wildlifetourismmanagementsystem.dto.DriverDto;
import lk.ijse.wildlifetourismmanagementsystem.model.DriverModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DriverFormController  {



    @FXML
    private DatePicker date;

    @FXML
    private TextField txtDriverId;

    @FXML
    private TextField txtDriverNIC;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobile;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPackageID;
    @FXML
    private AnchorPane pane;

    @FXML
    private ChoiceBox<String> txtViolatedLaw;

    private DriverModel driverModel=new DriverModel();

    @FXML
    void btnTouAddOnAction(ActionEvent event) {
        String id=txtDriverId.getText();
        String NIC=txtDriverNIC.getText();
        String name=txtName.getText();
        String p_id=txtPackageID.getText();
        String mobile=txtMobile.getText();
        String email=txtEmail.getText();
        String expairDate=date.getValue().toString();
        boolean validate=isValidate();
        DriverDto dto=new DriverDto(id,NIC,name,p_id,mobile,email,expairDate);
        try {
            if (validate) {
                boolean isAdd = driverModel.isAdd(dto);
                if(!isAdd) new Alert(Alert.AlertType.INFORMATION,"Something went wrong!!!").show();
                if (isAdd)new Alert(Alert.AlertType.INFORMATION, "Successfully Added!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String id=txtDriverId.getText();
        try {
                boolean isDeleted = driverModel.isDeleted(id);
                if (isDeleted) new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted!!!").show();
                else new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String id=txtDriverId.getText();
        String NIC=txtDriverNIC.getText();
        String name=txtName.getText();
        String p_id=txtPackageID.getText();
        String mobile=txtMobile.getText();
        String email=txtEmail.getText();
        String expairDate=date.getValue().toString();
        String lawId1=txtViolatedLaw.getValue().toString();
        boolean validate=isValidate();
        DriverDto dto=new DriverDto(id,NIC,name,p_id,mobile,email,expairDate);

        try {
            if(validate){
                boolean isUpdate=driverModel.isUpdate(dto);
                if(!isUpdate) new Alert(Alert.AlertType.ERROR,"Something went wrong!!!").show();
                if (isUpdate)new Alert(Alert.AlertType.INFORMATION,"Successfully Added!!!").show();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/driver_view_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
    public boolean isValidate(){
        Pattern compile=Pattern.compile("[D][0-9]{3,}");
        Matcher matcher=compile.matcher(txtDriverId.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Driver ID").show();
            return false;
        }
        Pattern compile1=Pattern.compile("^(?:[0-9]{9}|[0-9]{12})|(v|V|x|X)$");
        Matcher matcher1=compile1.matcher(txtDriverNIC.getText());
        boolean matches1=matcher1.matches();
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Driver NIC").show();
            return false;
        }
        Pattern compile2=Pattern.compile("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        Matcher matcher2=compile2.matcher(txtName.getText());
        boolean matches2=matcher2.matches();
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Driver Nane").show();
            return false;
        }
        Pattern compile3=Pattern.compile("[P][0-9]{3,}");
        Matcher matcher3=compile3.matcher(txtPackageID.getText());
        boolean matches3=matcher3.matches();
        if (!matches3){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Package ID").show();
            return false;
        }
        Pattern compile4=Pattern.compile("^(?:0|94|\\+94)?(?:7|11|07|107|011|1011)[1-9]{7}$\n");
        Matcher matcher4=compile4.matcher(txtMobile.getText());
        boolean matches4=matcher4.matches();
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Mobile number").show();
            return false;
        }
        Pattern compile5=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\n");
        Matcher matcher5=compile5.matcher(txtEmail.getText());
        boolean matches5=matcher5.matches();

        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email").show();
            return false;
        }
        Pattern compile6=Pattern.compile("^(0[1-9]|1[012])[-/.](0[1-9]|[12][0-9]|3[01])[-/.](19|20)\\d\\d$\n");
        Matcher matcher6=compile6.matcher(date.getValue().toString());
        boolean matches6=matcher6.matches();

        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Date").show();
            return false;
        }

        Pattern compile7=Pattern.compile("[L][0-9]{3,}");
        Matcher matcher7=compile7.matcher(txtViolatedLaw.getValue().toString());
        boolean matches7=matcher7.matches();

        if (!matches7){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Law ID").show();
            return false;
        }
        return true;
    }



}
