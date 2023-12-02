package lk.ijse.wildlifetourismmanagementsystem.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
;
import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;
import lk.ijse.wildlifetourismmanagementsystem.model.TouristModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TouristFromController implements Initializable {

    static {
        new Alert(Alert.AlertType.INFORMATION,"If you want to delete any data enter the Tourist id you want and press delete button!!").show();
    }

    @FXML
    private ChoiceBox<String> adultOrChild;


    @FXML
    private ChoiceBox<String> localOrForeign;


    @FXML
    private TextField txtAddress;


    @FXML
    private TextField txtCashierId;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNumber;

    @FXML
    private TextField txtNIC;

    @FXML
    private TextField txtName;


    @FXML
    private TextField txtTouristId;

    private String[] ageLevel={"Adult","Child"};
    private String[] region={"Local","Foreign"};

    private TouristModel model=new TouristModel();




    @FXML
    void btnTouAddOnAction(ActionEvent event) {

        String touristId=txtTouristId.getText();
        String nic=txtNIC.getText();
        String name=txtName.getText();
        String ageLevel=adultOrChild.getValue();
        String touristEmail=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String region=localOrForeign.getValue();
        String cashierId=txtCashierId.getText();
        String address=txtAddress.getText();

        TouristDto dto=new TouristDto(touristId,nic,name,ageLevel,touristEmail,mobileNumber,region,cashierId,address);
        try {
            if(isValidate()) {
                boolean isAdd = model.isAdd(dto);
                if (isAdd)new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
                if (!isAdd) new Alert(Alert.AlertType.INFORMATION, "Something went Wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String touristId=txtTouristId.getText();
        try {
            boolean isDelete=model.isDelete(touristId);
            if (isDelete) new Alert(Alert.AlertType.INFORMATION,"Delete Successfully!!!").show();
            else new Alert(Alert.AlertType.ERROR,"Something wants wrong").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String touristId=txtTouristId.getText();
        String nic=txtNIC.getText();
        String name=txtName.getText();
        String ageLevel=adultOrChild.getValue();
        String touristEmail=txtEmail.getText();
        String mobileNumber=txtMobileNumber.getText();
        String region=localOrForeign.getValue();
        String cashierId=txtCashierId.getText();
        String address=txtAddress.getText();

        TouristDto dto=new TouristDto(touristId,nic,name,ageLevel,touristEmail,mobileNumber,region,cashierId,address);

        try {

            if (isValidate()) {
                boolean isUpdate = model.isUpdate(dto);
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something want wrong!!!").show();
                if (isUpdate)new Alert(Alert.AlertType.INFORMATION, "Successfully updated!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) throws IOException, JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/tourist details.jrxml");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adultOrChild.getItems().addAll(ageLevel);
        localOrForeign.getItems().addAll(region);
    }
    public boolean isValidate(){
        Pattern compile=Pattern.compile("[T][0-9]{3,}");
        Matcher matcher=compile.matcher(txtTouristId.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Tourist ID").show();
            return false;
        }
        Pattern compile1=Pattern.compile("^(?:[0-9]{9}|[0-9]{12})|(v|V|x|X)$");
        Matcher matcher1=compile1.matcher(txtNIC.getText());
        boolean matches1=matcher1.matches();

        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a  NIC").show();
            return false;
        }
        Pattern compile2=Pattern.compile("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        Matcher matcher2=compile2.matcher(txtName.getText());
        boolean matches2=matcher2.matches();

        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a  Name").show();
            return false;
        }

        Pattern compile5 = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher matcher5 = compile5.matcher(txtEmail.getText());
        boolean matches5 = matcher5.matches();

        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email").show();
            return false;
        }

        Pattern compile6=Pattern.compile("[C][0-9]{3,}");
        Matcher matcher6=compile6.matcher(txtCashierId.getText());
        boolean matches6=matcher6.matches();

        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a cashier ID").show();
            return false;
        }

        return true;
    }
}
