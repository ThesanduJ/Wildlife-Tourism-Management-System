package lk.ijse.wildlifetourismmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.VehicleDto;
import lk.ijse.wildlifetourismmanagementsystem.model.VehicleModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleFormController {

    static {
        new Alert(Alert.AlertType.INFORMATION,"If you want to delete any data enter the Vehicle registration number you want and press delete button!!").show();
    }

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
                boolean isAdd = model.isAdd(dto);
                if (!isAdd) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
                if (isAdd) new Alert(Alert.AlertType.INFORMATION, "Update Successfully!!!").show();
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
                boolean isUpdate = model.isUpdate(dto);
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something went wrong").show();
                if (isUpdate) new Alert(Alert.AlertType.INFORMATION, "Update Successfully!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) throws SQLException, JRException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/vehicle details.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport, //compiled report
                        null,
                        DbConnection.getInstance().getConnection() //database connection
                );

        JasperViewer.viewReport(jasperPrint, false);
    }
    public boolean isValidate(){
        Pattern compile=Pattern.compile("[V][0-9]{3,}");
        Matcher matcher=compile.matcher(txtReg.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Registration number").show();
            return false;
        }

        Pattern compile2=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher2=compile2.matcher(txtEmail.getText());
        boolean matches2=matcher2.matches();

        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email").show();
            return false;
        }

        return true;
    }
}
