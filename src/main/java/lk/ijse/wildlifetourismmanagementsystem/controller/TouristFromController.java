package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.tm.TouristTm;
import lk.ijse.wildlifetourismmanagementsystem.model.TouristModel;

import java.io.IOException;
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
    void btnTouViewOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/tourist_detail_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.show();
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

//        Pattern compile4=Pattern.compile("^(?:0|94|\\+94)?(?:7|11|07|107|011|1011)[1-9]{7}$\n");
//        Matcher matcher4=compile4.matcher(txtMobileNumber.getText());
//        boolean matches4=matcher4.matches();
//        if (!matches4){
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Mobile number").show();
//            return false;
//        }
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

//        Pattern compile7=Pattern.compile("^(\\d{1,5}) (?:[A-Za-z]+\\s?)+, ([A-Z]{2}), (\\d{5})$\n");
//        Matcher matcher7=compile7.matcher(txtAddress.getText());
//        boolean matches7=matcher7.matches();
//
//        if (!matches7){
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Address").show();
//            return false;
//        }
//        Pattern compile8=Pattern.compile("^[A-Z][0-9]{7,9}$\n");
//        Matcher matcher8=compile8.matcher(txtPassportId.getText());
//        boolean matches8=matcher8.matches();
//
//        if (!matches8){
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in a passport id").show();
//            return false;
//        }
//        Pattern compile9=Pattern.compile("^(?:[0-9]|[1-9][0-9]|[1][0-1][0-9]|120)");
//        Matcher matcher9=compile9.matcher(txtAge.getText());
//        boolean matches9=matcher9.matches();
//
//        if (!matches9){
//            new Alert(Alert.AlertType.ERROR,"Something went wrong in a age").show();
//            return false;
//        }
        return true;
    }
}
