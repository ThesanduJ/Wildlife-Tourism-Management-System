package lk.ijse.wildlifetourismmanagementsystem;

import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static Stage primaryStage = null;
    public static Scene primaryScene = null;

    @Override
    public void init() throws Exception {
        InitPreloader init = new InitPreloader();
        init.checkFunctions();
    }

    @Override
    public void start(Stage primaryStage) {
        Launcher.primaryStage = primaryStage;
    }

    public static void main(String[] args) {
        LauncherImpl.launchApplication(Launcher.class, LauncherPreloader.class, args);
        launch(args);
    }

}
