package lk.ijse.wildlifetourismmanagementsystem.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.dto.TicketDto;
import lk.ijse.wildlifetourismmanagementsystem.model.TicketModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TicketFormController implements Initializable {
    @FXML
    private JFXButton btnTouAdd;

    @FXML
    private JFXButton btnTouDelete;

    @FXML
    private JFXButton btnTouUpdate;

    @FXML
    private JFXButton btnTouView;
    @FXML
    private ChoiceBox<String> ticketType;

    @FXML
    private TextField txtCashierId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtTicketId;
    @FXML
    private TextField txtTicketCount;
    @FXML
    private TextField txtPackageID;
    TicketModel model=new TicketModel();

    private String[] type={"Day Safari Tickets","Night Safari Tickets","Guided Safari Tours","Self-Drive Safari Permits","Specialized Safaris","Multi-Day Safari "};

    @FXML
    void btnTouAddOnAction(ActionEvent event) {
        String ticketId=txtTicketId.getText();
        double price=Double.parseDouble(txtPrice.getText());
        String ticketT=ticketType.getValue();
        String cashierID=txtCashierId.getText();
        int ticketCount=Integer.parseInt(txtTicketCount.getText());
        String packageId=txtPackageID.getText();

        TicketDto dto=new TicketDto(ticketId,price,ticketT,cashierID,ticketCount,packageId);

        try {
            if (isValidate()) {
                boolean isSaved = model.addTicket(dto);
                if (isSaved)new Alert(Alert.AlertType.INFORMATION, "Added Successfully!!!").show();
                if (!isSaved) new Alert(Alert.AlertType.ERROR, "Something is Wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String ticketId=txtTicketId.getText();
        try {
            boolean isDelete=model.idDelete(ticketId);
            if (isDelete)new Alert(Alert.AlertType.INFORMATION,"Successfully Deleted!!!").show();
            else new Alert(Alert.AlertType.INFORMATION,"Something went wrong!!!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouUpdateOnAction(ActionEvent event) {
        String ticketId=txtTicketId.getText();
        double price=Double.parseDouble(txtPrice.getText());
        String ticketT=ticketType.getValue();
        String cashierID=txtCashierId.getText();
        int ticketCount=Integer.parseInt(txtTicketCount.getText());
        String packageId=txtPackageID.getText();

        TicketDto dto=new TicketDto(ticketId,price,ticketT,cashierID,ticketCount,packageId);
        try {
            if(isValidate()) {
                boolean isUpdate = model.isUpdate(dto);
                if (isUpdate)new Alert(Alert.AlertType.INFORMATION, "Update Successfully!!!").show();
                if (!isUpdate) new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ticketType.getItems().addAll(type);
    }

    public boolean isValidate(){
        Pattern compile=Pattern.compile("[T][0-9]{3,}");
        Matcher matcher=compile.matcher(txtTicketId.getText());
        boolean matches=matcher.matches();

        if (!matches){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Ticket ID").show();
            return false;
        }
        Pattern compile1=Pattern.compile("[C][0-9]{3,}");
        Matcher matcher1=compile1.matcher(txtCashierId.getText());
        boolean matches1=matcher1.matches();

        if (!matches1){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Cashier ID").show();
            return false;
        }
        Pattern compile2 = Pattern.compile("^\\d*\\.?\\d+$");
        Matcher matcher2 = compile2.matcher(txtPrice.getText().toString());
        boolean matches2 = matcher2.matches();


        if (!matches2){
            new Alert(Alert.AlertType.ERROR,"Something went wrong in a Ticket price").show();
            return false;
        }
        return true;
    }
}
