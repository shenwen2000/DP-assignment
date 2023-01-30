package com.farm.dp_assignment;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    // Main method
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        Farm farm = Farm.getInstance();
        farm.setUpStartingPage(this.primaryStage);
    }
}