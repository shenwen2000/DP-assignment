package com.farm.dp_assignment;

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
        Farm farm = Farm.getInstance();
        farm.setUpStartingPage(this.primaryStage);
    }
}