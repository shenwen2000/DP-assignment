package com.farm.dp_assignment;

import com.farm.dp_assignment.singleton.WalletFactory;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    Button startButton;
    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Farm farm = new Farm();
        farm.setUpStartingPage(this.primaryStage);
    }
}