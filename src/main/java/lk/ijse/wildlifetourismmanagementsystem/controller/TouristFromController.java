package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;
import lk.ijse.wildlifetourismmanagementsystem.model.TouristModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TouristFromController implements Initializable {

    @FXML
    private ChoiceBox<String> adultOrChild;

    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;

    @FXML
    private ChoiceBox<String> localOrForeign;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtAge;

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
    private TextField txtPassportId;

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
        String passwordNumber=txtPassportId.getId();
        int age=Integer.parseInt(txtAge.getText());

        TouristDto dto=new TouristDto(touristId,nic,name,ageLevel,touristEmail,mobileNumber,region,cashierId,address,passwordNumber,age);
        try {
            if(isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
                boolean isAdd = model.isAdd(dto);
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
        String passwordNumber=txtPassportId.getId();
        int age=Integer.parseInt(txtAge.getText());
        TouristDto dto=new TouristDto(touristId,nic,name,ageLevel,touristEmail,mobileNumber,region,cashierId,address,passwordNumber,age);

        try {

            if (isValidate()) {
                new Alert(Alert.AlertType.INFORMATION, "Successfully updated!!!").show();
                boolean isUpdate = model.isUpdate(dto);
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something want wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) {

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
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Tourist ID");
            return false;
        }
        Pattern compile1=Pattern.compile("^(?:[0-9]{9}|[0-9]{12})|(v|V|x|X)$");
        Matcher matcher1=compile.matcher(txtNIC.getText());
        boolean matches1=matcher.matches();
        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a  NIC");
            return false;
        }
        Pattern compile2=Pattern.compile("^[a-zA-Z]+(?: [a-zA-Z]+)*$");
        Matcher matcher2=compile.matcher(txtName.getText());
        boolean matches2=matcher.matches();
        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a  Name");
            return false;
        }

        Pattern compile4=Pattern.compile("^(?:0|94|\\+94)?(?:7|11|07|107|011|1011)[1-9]{7}$\n");
        Matcher matcher4=compile.matcher(txtMobileNumber.getText());
        boolean matches4=matcher.matches();
        if (!matches4){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Mobile number");
            return false;
        }
        Pattern compile5=Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$\n");
        Matcher matcher5=compile.matcher(txtEmail.getText());
        boolean matches5=matcher.matches();

        if (!matches5){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Email");
            return false;
        }

        Pattern compile6=Pattern.compile("[C][0-9]{3,}");
        Matcher matcher6=compile.matcher(txtCashierId.getText());
        boolean matches6=matcher.matches();

        if (!matches6){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a cashier ID");
            return false;
        }

        Pattern compile7=Pattern.compile("^(\\d{1,5}) (?:[A-Za-z]+\\s?)+, ([A-Z]{2}), (\\d{5})$\n");
        Matcher matcher7=compile.matcher(txtAddress.getText());
        boolean matches7=matcher.matches();

        if (!matches7){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Address");
            return false;
        }
        Pattern compile8=Pattern.compile("^[A-Z][0-9]{7,9}$\n");
        Matcher matcher8=compile.matcher(txtPassportId.getText());
        boolean matches8=matcher.matches();

        if (!matches8){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a password id");
            return false;
        }
        Pattern compile9=Pattern.compile("^(?:[0-9]|[1-9][0-9]|[1][0-1][0-9]|120)$\n");
        Matcher matcher9=compile.matcher(txtAge.getText());
        boolean matches9=matcher.matches();

        if (!matches9){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a age");
            return false;
        }
        return true;
    }
}
