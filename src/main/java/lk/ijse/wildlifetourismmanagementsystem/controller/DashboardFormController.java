package lk.ijse.wildlifetourismmanagementsystem.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane pane;
    @FXML
    private AnchorPane rootNode1;


    @FXML
    void btnLogoutOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
        Scene scene=new Scene(rootNode);
        Stage stage=(Stage)rootNode1.getScene().getWindow();
        stage.setScene(scene);
    }
    @FXML
    void btnTouristOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/tourist_from.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
    @FXML
    void btnCashierOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/cashier_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }

    @FXML
    void btnTicketOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/ticket_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
    @FXML
    void btnGuideOonAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/guide_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }

    @FXML
    void btnDriverOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/driver_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
    @FXML
    void btnVehicleOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/vehicle_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
    @FXML
    void btnPackageOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/package_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }

    @FXML
    void btnFinanceOnAction(ActionEvent event) throws IOException {
        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/finance_form.fxml"));
        this.pane.getChildren().clear();
        this.pane.getChildren().add(rootNode);
    }
//    @FXML
//    void btnDashboardOnAction(ActionEvent event) throws IOException {
//        Parent rootNode= FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
//        this.pane.getChildren().clear();
//        this.pane.getChildren().add(rootNode);
//    }

}
