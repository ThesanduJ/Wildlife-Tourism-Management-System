package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.dto.PackageDto;
import lk.ijse.wildlifetourismmanagementsystem.model.PackageModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PackageFormController {

    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;

    @FXML
    private TextField txtPackageType;

    @FXML
    private TextField txtPackageDescription;

    @FXML
    private TextField txtPackageFeatures;

    @FXML
    private TextField txtPackageId;

    @FXML
    private TextField txtPackagePrice;

    private PackageModel model=new PackageModel();

    @FXML
    void btnTouAddOnAction(ActionEvent event) {
        String packageId=txtPackageId.getText();
        String description=txtPackageDescription.getText();
        double price= Double.parseDouble(txtPackagePrice.getText());
        String packageFeatures=txtPackageFeatures.toString();
        String packageType=txtPackageType.getText();

        PackageDto dto=new PackageDto(packageId,description,price,packageFeatures,packageType);
        try {
            if (isValidation()) {
                boolean isSaved = model.IsAdd(dto);
                if (!isSaved) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
                if (isSaved) new Alert(Alert.AlertType.INFORMATION, "Successfully Added!!!").show();

            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String packageId=txtPackageId.getText();
        try {
            boolean isDelete=model.isDelete(packageId);
            if (isDelete) new Alert(Alert.AlertType.INFORMATION,"Deleted Successfully!!!").show();
            else new Alert(Alert.AlertType.ERROR,"Something is wrong!!!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String packageId=txtPackageId.getText();
        String description=txtPackageDescription.getText();
        double price= Double.parseDouble(txtPackagePrice.getText());
        String packageFeatures=txtPackageFeatures.toString();
        String packageType=txtPackageType.getText();

        PackageDto dto=new PackageDto(packageId,description,price,packageFeatures,packageType);
        try {
            if (isValidation()) {
                boolean isUpdate = model.isUpdate(dto);
                 if (!isUpdate)new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
                if (isUpdate)new Alert(Alert.AlertType.INFORMATION, "Successfully Updated!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) {

    }

    public boolean isValidation(){
        Pattern compile=Pattern.compile("[P][0-9]{3,}");
        Matcher matcher=compile.matcher(txtPackageId.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Package ID").show();
            return false;
        }
        Pattern compile1 = Pattern.compile("^[0-9]+$");
        Matcher matcher1 = compile1.matcher(txtPackagePrice.getText());
        boolean matches1 = matcher1.matches();


        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Package Price").show();
            return false;
        }
        return true;
    }

}
