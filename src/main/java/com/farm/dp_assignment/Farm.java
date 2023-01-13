package com.farm.dp_assignment;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Stage;

import java.util.Objects;

public class Farm {

    Stage startingScene, farmScene;
    Button startButton;

    public void setUpStartingPage(Stage primaryStage) {
        this.startingScene = primaryStage;
        startingScene.setTitle("Animal Farm");

        startButton = new Button("Start");
        startButton.setMinWidth(200);
        startButton.setStyle("-fx-background-color: #676AC2; -fx-text-fill: #FFFFFF; -fx-border-radius: 25; " +
                "-fx-cursor: hand;");
        startButton.setOnAction(e -> {
            this.startingScene.setScene(setUpFarmPage());
        });

        VBox content = new VBox(15);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/background_image.jpg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(700);
        backgroundImageView.setFitWidth(1300);
        content.getChildren().addAll(backgroundImageView, startButton);
        content.setAlignment(Pos.CENTER);

        Scene scene = new Scene(content, 1500, 800);
//        scene.getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());

        startingScene.setScene(scene);
        startingScene.show();
    }

    public Scene setUpFarmPage() {
        BorderPane farmLayout = new BorderPane();
        farmLayout.setPadding(new Insets(10, 10, 10, 10));

        // setup background image
        farmLayout.setBackground(new Background(
                new BackgroundImage(
                        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/farm.jpg"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, true, true, false, true)
                )));

        // set up top side
//        Flow
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));

        // setup growth point
        HBox growthProgressBox = new HBox(5);

        Circle circle = new Circle(40);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(10);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.AZURE);
        circle.relocate(0, 0);

        Text text = new Text("1");
        text.setStyle("-fx-font-size: 20px");
        text.setBoundsType(TextBoundsType.VISUAL);
        StackPane stageGrowth = new StackPane();
        stageGrowth.getChildren().addAll(circle, text);

        ProgressBar growthPointBar = new ProgressBar();
        ProgressIndicator growthPoint = new ProgressIndicator(0);

//         Here are the code to change the value of the progress bar
        growthPointBar.setProgress(20 / 50);
        growthPoint.setProgress(20 / 50);

        growthProgressBox.getChildren().addAll(stageGrowth, growthPointBar, growthPoint);
        growthProgressBox.setAlignment(Pos.CENTER_LEFT);

        // setup coin (wallet)
        // create coin image
        Image coinImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/coin.png")));
        ImageView coinImageView = new ImageView(coinImage);
        coinImageView.setFitWidth(80);
        coinImageView.setFitHeight(80);

        HBox coinBox = new HBox(5);

        // After getting the amount of coin, pls add it at below sentence exp: (coinImageView, Amount)
        coinBox.getChildren().addAll(coinImageView);

        coinBox.setStyle("-fx-border-color: #000000; -fx-border-radius: 5px");
        coinBox.setAlignment(Pos.BASELINE_LEFT);
        coinBox.setMaxWidth(200);
        coinBox.setMaxHeight(100);
        coinBox.setPadding(new Insets(5, 5, 5, 5));

        GridPane.setConstraints(growthProgressBox,0,0);

        farmLayout.setTop(coinBox);
        farmLayout.setAlignment(coinBox, Pos.CENTER_RIGHT);


        //Set up shop
        Button shopButton = new Button();
        Image shopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/shop.png")));
        ImageView shopImageView = new ImageView(shopImage);
        shopImageView.setFitHeight(80);
        shopImageView.setFitWidth(80);
        shopButton.setPrefSize(80, 80);
        shopButton.setGraphic(shopImageView);
        shopButton.setStyle("-fx-cursor: hand;");

        farmLayout.setBottom(shopButton);
        farmLayout.setAlignment(shopButton, Pos.BOTTOM_LEFT);

        Scene scene = new Scene(farmLayout, 1500, 800);

        return scene;
    }
}