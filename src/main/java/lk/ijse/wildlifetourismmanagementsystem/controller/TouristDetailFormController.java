package lk.ijse.wildlifetourismmanagementsystem.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.wildlifetourismmanagementsystem.dto.tm.TouristTm;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TouristDetailFormController implements Initializable {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colCashierId;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colNumber;

    @FXML
    private TableColumn<?, ?> colRegion;

    @FXML
    private TableColumn<?, ?> colTouId;

    @FXML
    private TableView<TouristTm> touristTable;
    public static List<TouristTm> touristTms;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }
    private void getAll() {
        ObservableList<TouristTm> obList = FXCollections.observableArrayList();

        for (TouristTm touristTm : touristTms) {

            obList.add(touristTm);


        }

        touristTable.setItems(obList);
    }
    private void setCellValueFactory() {
        colTouId.setCellValueFactory(new PropertyValueFactory<>("touristId"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("NIC"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAge.setCellValueFactory(new PropertyValueFactory<>("ageLevel"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colNumber.setCellValueFactory(new PropertyValueFactory<>("mobileNumber"));
        colRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
        colCashierId.setCellValueFactory(new PropertyValueFactory<>("cashierId"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

    }
}



