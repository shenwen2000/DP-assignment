package com.farm.dp_assignment;

import com.farm.dp_assignment.composite.Shop;
import com.farm.dp_assignment.simpleFactory.SimpleAnimalFactory;
import com.farm.dp_assignment.strategy.Chicken;
import com.farm.dp_assignment.strategy.MoveOnGround;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class Farm {

    Stage startingScene, farmScene;
    Button startButton;
    SimpleAnimalFactory factory = new SimpleAnimalFactory();
    Animal animal = null;

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
        BorderPane topSec = new BorderPane();
        topSec.setPadding(new Insets(5, 5, 5, 5));

        // setup growth point
        HBox growthProgressBox = new HBox(5);

        Circle circle = new Circle(30);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(10);
        circle.setStrokeType(StrokeType.INSIDE);
        circle.setFill(Color.AZURE);
        circle.relocate(0, 0);

        Text stageNumber = new Text("1");
        stageNumber.setStyle("-fx-font-size: 20px");
        stageNumber.setBoundsType(TextBoundsType.VISUAL);
        StackPane stageGrowth = new StackPane();
        stageGrowth.getChildren().addAll(circle, stageNumber);

        ProgressBar growthPointBar = new ProgressBar();
        ProgressIndicator growthPoint = new ProgressIndicator(0);

//         Here are the code to change the value of the progress bar
        growthPointBar.setProgress(20 / 50);
        growthPoint.setProgress(20 / 50);

        growthProgressBox.getChildren().addAll(stageGrowth, growthPointBar, growthPoint);
        growthProgressBox.setAlignment(Pos.BASELINE_LEFT);

        // setup coin (wallet)
        // create coin image
        Image coinImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/coin.png")));
        ImageView coinImageView = new ImageView(coinImage);
        coinImageView.setFitWidth(60);
        coinImageView.setFitHeight(60);

        HBox coinBox = new HBox(5);

        Text cointAmount = new Text("1000");
        cointAmount.setStyle("-fx-font-size: 25px; -fx-font-vertical-align:top");
        cointAmount.setBoundsType(TextBoundsType.VISUAL);

        // After getting the amount of coin, pls add it at below sentence exp: (coinImageView, Amount)
        coinBox.getChildren().addAll(coinImageView, cointAmount);

        coinBox.setStyle("-fx-border-color: #000000; -fx-border-radius: 5px;");
        coinBox.setAlignment(Pos.BASELINE_RIGHT);
        coinBox.setMaxWidth(400);
        coinBox.setMaxHeight(100);
        coinBox.setPadding(new Insets(5, 5, 5, 5));

        topSec.setLeft(growthProgressBox);
        topSec.setRight(coinBox);

        farmLayout.setTop(topSec);
        farmLayout.setAlignment(topSec, Pos.BOTTOM_LEFT);

        //Set up shop
        Button shopButton = new Button();
        Image shopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/shop.png")));
        ImageView shopImageView = new ImageView(shopImage);
        shopImageView.setFitHeight(80);
        shopImageView.setFitWidth(80);
        shopButton.setPrefSize(80, 80);
        shopButton.setGraphic(shopImageView);
        shopButton.setStyle("-fx-cursor: hand;");

        Shop shop = new Shop();

        shopButton.setOnAction(e -> {
            shop.printMenu();
        });

        farmLayout.setBottom(shopButton);
        farmLayout.setAlignment(shopButton, Pos.BOTTOM_LEFT);

        Button shopButton1 = new Button();
        Image shopImage1 = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/shop.png")));
        ImageView shopImageView1 = new ImageView(shopImage1);
        shopImageView.setFitHeight(80);
        shopImageView.setFitWidth(80);
        shopButton1.setPrefSize(80, 80);
        shopButton1.setGraphic(shopImageView1);
        shopButton1.setStyle("-fx-cursor: hand;");

        Button mvOnGrd = new Button("Move On Ground");
        mvOnGrd.setStyle("-fx-padding: 10px;-fx-border-insets: 5px;-fx-background-insets: 5px;-fx-border:2px black");
        mvOnGrd.setStyle("-fx-cursor: hand;");

        farmLayout.setCenter(mvOnGrd);
        farmLayout.setAlignment(mvOnGrd, Pos.BOTTOM_RIGHT);

//        mvOnGrd.setOnAction(e -> {
//            animal.setMoveBehavior(new MoveOnGround());
//        });

        if (!Objects.isNull(animal)) {
            ImageView animalImageView = new ImageView(animal.getImage());
            animalImageView.setFitWidth(80);
            animalImageView.setFitHeight(80);
            animal.performMove(animalImageView);
            farmLayout.setCenter(animalImageView);
            farmLayout.setAlignment(animalImageView, Pos.BOTTOM_RIGHT);
        }

        Scene scene = new Scene(farmLayout, 1500, 800);

        return scene;
    }

    public void createAnimal(String nameType) {
        animal = factory.createAnimal(nameType);
        this.startingScene = Main.primaryStage;
        this.startingScene.setScene(setUpFarmPage());
    }

    public void setAddIngredientPage() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        BorderPane ingredientPageLayout = new BorderPane();
        ingredientPageLayout.setPadding(new Insets(10, 10, 10, 10));

        // Protein Part
        VBox proteinBox = new VBox(10);

        Image proteinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/protein.png")));
        ImageView proteinImageView = new ImageView(proteinImg);
        proteinImageView.setFitWidth(500);
        proteinImageView.setFitHeight(400);

        Text proteinText = new Text("Protein");
        proteinText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        proteinText.setBoundsType(TextBoundsType.VISUAL);
        proteinText.setTextAlignment(TextAlignment.CENTER);

        Label potionText1 = new Label("Potion");
        TextField proteinTxtField = new TextField();

        GridPane proteinInputBox = new GridPane();
        proteinInputBox.addRow(0, potionText1, proteinTxtField);
        proteinInputBox.setAlignment(Pos.CENTER);

        proteinBox.getChildren().addAll(proteinImageView, proteinText, proteinInputBox);

        // Vitamin Part
        VBox vitaminBox = new VBox(10);

        Image vitaminImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/vitamin.png")));
        ImageView vitaminImageView = new ImageView(vitaminImg);
        vitaminImageView.setFitWidth(500);
        vitaminImageView.setFitHeight(400);

        Text vitaminText = new Text("Vitamin");
        vitaminText.setStyle("-fx-font-size: 20px; -fx-font-weight: bold");
        vitaminText.setBoundsType(TextBoundsType.VISUAL);
        vitaminText.setTextAlignment(TextAlignment.CENTER);

        Label potionText2 = new Label("Potion");
        TextField vitaminTxtField = new TextField();

        GridPane vitaminInputBox = new GridPane();
        vitaminInputBox.addRow(0, potionText2, vitaminTxtField);
        vitaminInputBox.setAlignment(Pos.CENTER);

        vitaminBox.getChildren().addAll(vitaminImageView, vitaminText, vitaminInputBox);

        GridPane content = new GridPane();
        content.setPadding(new Insets(10, 20, 10, 20));
        content.addRow(0, proteinBox, vitaminBox);

        ingredientPageLayout.setCenter(content);
        ingredientPageLayout.setAlignment(content, Pos.CENTER);

        Button addBtn = new Button("Add Ingredient");
        addBtn.setStyle("-fx-padding: 10px;-fx-border-insets: 5px;-fx-background-insets: 5px;-fx-border:2px black");

        addBtn.setOnAction(e -> {

        });

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle("-fx-padding: 10px;-fx-border-insets: 5px;-fx-background-insets: 5px;-fx-border:2px black");

        confirmBtn.setOnAction(e -> {
            window.close();
        });

        GridPane buttonBox = new GridPane();
        buttonBox.setPadding(new Insets(30, 20, 10, 20));
        buttonBox.addRow(0, addBtn, confirmBtn);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);

        ingredientPageLayout.setBottom(buttonBox);
        ingredientPageLayout.setAlignment(buttonBox, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(ingredientPageLayout);
        window.setScene(scene);
        window.showAndWait();
    }
}
