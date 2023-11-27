package lk.ijse.wildlifetourismmanagementsystem;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InitPreloader implements Initializable {

    public Label lblLoading;
    public static Label lblLoadingg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblLoadingg=lblLoading;
    }

    public String checkFunctions(){

        final String[] message = {""};
        Thread t1 = new Thread(() -> {
            message[0] = "First Function";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            message[0] = "Second Function";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t3 = new Thread(() -> {
            message[0] = "Open Main Stage";
            Platform.runLater(() -> lblLoadingg.setText(message[0]));

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("/view/login_form.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        });

        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return message[0];
    }

}