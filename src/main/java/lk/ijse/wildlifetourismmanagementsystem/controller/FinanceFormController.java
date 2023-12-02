package lk.ijse.wildlifetourismmanagementsystem.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lk.ijse.wildlifetourismmanagementsystem.db.DbConnection;
import lk.ijse.wildlifetourismmanagementsystem.dto.FinanceDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.PackageDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.TicketDto;
import lk.ijse.wildlifetourismmanagementsystem.dto.TouristDto;
import lk.ijse.wildlifetourismmanagementsystem.model.FinanceModel;
import lk.ijse.wildlifetourismmanagementsystem.model.PackageModel;
import lk.ijse.wildlifetourismmanagementsystem.model.TicketModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class FinanceFormController implements Initializable {

    static {
        new Alert(Alert.AlertType.INFORMATION,"If you want to delete any data enter the tourist id want and press delete button!!").show();
    }


    @FXML
    private Label lblPackagePrice;

    @FXML
    private Label lblRemainingAmount;

    @FXML
    private Label lblTicketPrice;

    @FXML
    private Label lblTotalPrice;

    @FXML
    private TextField paidPrice;

    @FXML
    private ComboBox<String> touristId;

    @FXML
    private ComboBox<String> ticketId;

    @FXML
    private ComboBox<String> packageId;

    @FXML
    private TextField txtQty;
    FinanceModel financeModel=new FinanceModel();
    @FXML
    void btnTouAddOnAction(ActionEvent event) throws SQLException {
            payment= Double.parseDouble(paidPrice.getText());
            double remaining =payment-total;
            lblRemainingAmount.setText(String.valueOf(remaining));

            String touristIds=touristId.getValue();
            String ticketsId=ticketId.getValue();
            String packagesId=packageId.getValue();

            double ticketsPrice=Double.parseDouble(lblTicketPrice.getText());
            double packagesPrice=Double.parseDouble(lblPackagePrice.getText());
            double paidAmount=Double.parseDouble(paidPrice.getText());
            int qty=Integer.parseInt(txtQty.getText());



            FinanceDto dto=new FinanceDto(touristIds,ticketsId,packagesId,ticketsPrice,packagesPrice,paidAmount,qty);


            try {
                boolean isAdd=financeModel.placeBill(dto);
                if (isAdd) new Alert(Alert.AlertType.INFORMATION,"Successfully Added!!!").show();
                if (!isAdd) new Alert(Alert.AlertType.ERROR,"Something went wrong!!!").show();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

    }

    @FXML
    void btnTouDeleteOnAction(ActionEvent event) {
        String id=touristId.getValue();
        try {
            boolean isDeleted = financeModel.isDeleted(id);
            if (isDeleted) new Alert(Alert.AlertType.INFORMATION, "Successfully Deleted!!!").show();
            else new Alert(Alert.AlertType.ERROR, "Something went wrong!!!").show();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    @FXML
    void btnTouViewOnAction(ActionEvent event) throws SQLException, JRException {

        InputStream resourceAsStream = getClass().getResourceAsStream("/reports/finance.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );

        JasperViewer.viewReport(jasperPrint, false);

    }
    public  TicketModel model=new TicketModel();
    private PackageModel packageModel=new PackageModel();
    private void loadTicketsCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TicketDto> ticketDto = model.loadAllTickets();

            for (TicketDto dto : ticketDto) {
                obList.add(dto.getTicket_id());
            }
            ticketId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadTouristCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<TouristDto> touristDto = model.loadAllTourist();

            for (TouristDto dto : touristDto) {
                obList.add(dto.getTouristId());
            }
            touristId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void loadPackageCodes() {
        PackageModel model1=new PackageModel();
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<PackageDto> touristDto = model1.loadAllTourist();

            for (PackageDto dto : touristDto) {
                obList.add(dto.getPackageId());
            }
            packageId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTicketsCodes();
        loadTouristCodes();
        loadPackageCodes();
    }

    public double ticketPrice;
    public  double packagePrice;
    public double total;
    public double payment;
    @FXML
    void comPackageOnAction(ActionEvent event) {
        String packageID=packageId.getValue();
        try {
            PackageDto packageDto=packageModel.search(packageID);
            lblPackagePrice.setText(String.valueOf(packageDto.getPrice()));
            packagePrice=packageDto.getPrice();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void comTicketOnAction(ActionEvent event) {
        String ticketID=ticketId.getValue();
        try {
            TicketDto ticketDto=model.searchPackage(ticketID);
            lblTicketPrice.setText(String.valueOf(ticketDto.getPrice()));
            ticketPrice=ticketDto.getPrice();
            total=ticketPrice+packagePrice;
            System.out.println(total);
            lblTotalPrice.setText(String.valueOf(total));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
