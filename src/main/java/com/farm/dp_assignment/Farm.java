package com.farm.dp_assignment;

import com.farm.dp_assignment.composite.Shop;
import com.farm.dp_assignment.simpleFactory.SimpleAnimalFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;

import java.util.Objects;

public class Farm {

    Stage startingScene, farmScene;
    Button startButton;
    SimpleAnimalFactory factory = new SimpleAnimalFactory();
    Animal animal = null;

    final String IDLE_BUTTON_STYLE = "-fx-background-color: #676AC2; -fx-border-color: #676AC2; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-border-color: #676AC2; -fx-text-fill: #676AC2; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";

    public void setUpStartingPage(Stage primaryStage) {
        this.startingScene = primaryStage;
        startingScene.setTitle("Animal Farm");

        startButton = new Button("Start");
        startButton.setMinWidth(200);
        startButton.setStyle(IDLE_BUTTON_STYLE);
        startButton.setOnMouseEntered(e -> startButton.setStyle(HOVERED_BUTTON_STYLE));
        startButton.setOnMouseExited(e -> startButton.setStyle(IDLE_BUTTON_STYLE));

        startButton.setOnAction(e -> {
            this.startingScene.setScene(setUpFarmPage());
        });

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        VBox content = new VBox(15);
        Scene scene = new Scene(content, bounds.getWidth(), bounds.getHeight());
//        scene.getStylesheets().addAll(this.getClass().getResource("css/style.css").toExternalForm());

        startingScene.setScene(scene);
        startingScene.setX(bounds.getMinX());
        startingScene.setY(bounds.getMinY());
        startingScene.setMaximized(true);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/background_image.jpg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(bounds.getHeight() - 100);
        backgroundImageView.setFitWidth(bounds.getWidth() - 100);
        backgroundImageView.setPreserveRatio(true);
        content.getChildren().addAll(backgroundImageView, startButton);
        content.setAlignment(Pos.CENTER);

        startingScene.show();
    }

    public Scene setUpFarmPage() {

        BorderPane farmLayout = new BorderPane();
        farmLayout.setPadding(new Insets(10, 10, 10, 10));

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // setup background image
        farmLayout.setBackground(new Background(
                new BackgroundImage(
                        new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/farm.jpg"))),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
                        new BackgroundPosition(Side.LEFT, 0, true, Side.BOTTOM, 0, true),
                        new BackgroundSize(BackgroundSize.AUTO, bounds.getHeight() - 100, true, true, false, true)
                )));

        farmLayout.setPrefWidth(bounds.getWidth() - 150);
        farmLayout.setPrefHeight(bounds.getHeight() - 1500);

        // set up top side
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
        coinBox.setPadding(new Insets(5, 5, 15, 5));

        topSec.setLeft(growthProgressBox);
        topSec.setRight(coinBox);

        farmLayout.setTop(topSec);
        farmLayout.setAlignment(topSec, Pos.BOTTOM_LEFT);


        // set up Action button

        //Idle
        Button idleButton = new Button();
        Image idleImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/donald_duck_idle.png")));
        ImageView idleImageView = new ImageView(idleImage);
        idleImageView.setFitHeight(80);
        idleImageView.setFitWidth(80);
        idleButton.setPrefSize(80, 80);
        idleButton.setGraphic(idleImageView);
        idleButton.setStyle("-fx-cursor: hand;");
        idleButton.setTooltip(new Tooltip("Set movement of animal to idle."));

        // move
        Button moveButton = new Button();
        Image movementImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/donald_duck_move.gif")));
        ImageView movementImageView = new ImageView(movementImage);
        movementImageView.setFitHeight(80);
        movementImageView.setFitWidth(80);
        moveButton.setPrefSize(80, 80);
        moveButton.setGraphic(movementImageView);
        moveButton.setStyle("-fx-cursor: hand;");
        moveButton.setTooltip(new Tooltip("Set movement of animal to move."));

        // Sleep
        Button sleepButton = new Button();
        Image sleepImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/donald_duck_sleep.gif")));
        ImageView sleepImageView = new ImageView(sleepImage);
        sleepImageView.setFitHeight(80);
        sleepImageView.setFitWidth(80);
        sleepButton.setPrefSize(80, 80);
        sleepButton.setGraphic(sleepImageView);
        sleepButton.setStyle("-fx-cursor: hand;");
        sleepButton.setTooltip(new Tooltip("Set movement of animal to sleep."));

        //Set up shop
        Button shopButton = new Button();
        Image shopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/shop.png")));
        ImageView shopImageView = new ImageView(shopImage);
        shopImageView.setFitHeight(80);
        shopImageView.setFitWidth(80);
        shopButton.setPrefSize(80, 80);
        shopButton.setGraphic(shopImageView);
        shopButton.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
        shopButton.setAlignment(Pos.CENTER);


        Shop shop = new Shop();

        shopButton.setOnAction(e -> {
            shop.printMenu();
        });

        HBox bottomMenu = new HBox(10);
        bottomMenu.getChildren().addAll(shopButton, idleButton, moveButton, sleepButton);

        farmLayout.setBottom(bottomMenu);
        farmLayout.setAlignment(bottomMenu, Pos.BASELINE_LEFT);

        if (!Objects.isNull(animal)) {
            ImageView animalImageView = new ImageView(animal.getImage());
            animalImageView.setFitWidth(80);
            animalImageView.setFitHeight(80);
            animal.performMove(animalImageView);
            farmLayout.setCenter(animalImageView);
            farmLayout.setAlignment(animalImageView, Pos.BOTTOM_RIGHT);
        }

        Scene scene = new Scene(farmLayout, bounds.getWidth(), bounds.getHeight());

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
        window.setTitle("Add Ingredient(s)");

        BorderPane ingredientPageLayout = new BorderPane();
        ingredientPageLayout.setPadding(new Insets(10, 10, 10, 10));

        FlowPane content = new FlowPane();
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setVgap(8);
        content.setHgap(12);

        VBox ingredientSec = new VBox(10);

        // Create title text
        Text titleText = new Text("Press the image to add ingredient");
        titleText.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        titleText.setBoundsType(TextBoundsType.VISUAL);
        titleText.setTextAlignment(TextAlignment.LEFT);

        // create protein button
        Button proteinButton = new Button();
        Image proteinImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/protein.png")));
        ImageView proteinImageView = new ImageView(proteinImg);
        proteinButton.setGraphic(proteinImageView);
        proteinButton.setPrefSize(100, 100);
        proteinImageView.setFitWidth(100);
        proteinImageView.setFitHeight(100);
        proteinButton.setStyle("-fx-cursor: hand;");

        // create vitamin button
        Button vitaminButton = new Button();
        Image vitaminImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/vitamin.png")));
        ImageView vitaminImageView = new ImageView(vitaminImg);
        vitaminButton.setGraphic(vitaminImageView);
        vitaminButton.setPrefSize(100, 100);
        vitaminImageView.setFitWidth(100);
        vitaminImageView.setFitHeight(100);
        vitaminButton.setStyle("-fx-cursor: hand;");

        ingredientSec.getChildren().addAll(proteinButton, vitaminButton);

        VBox ingredientPriceList = new VBox(10);
        Text ingredientListText = new Text("Added Ingredient List");
        ingredientListText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        titleText.setBoundsType(TextBoundsType.VISUAL);
        titleText.setTextAlignment(TextAlignment.LEFT);

        Text lineBreak1 = new Text("------------------------------");
        lineBreak1.setStyle("-fx-font-size: 12px; -fx-font-weight: bold");
        lineBreak1.setBoundsType(TextBoundsType.VISUAL);
        lineBreak1.setTextAlignment(TextAlignment.LEFT);

        Text priceText = new Text("Price:5");
        priceText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        priceText.setBoundsType(TextBoundsType.VISUAL);
        priceText.setTextAlignment(TextAlignment.LEFT);

        ingredientPriceList.getChildren().addAll(ingredientListText, lineBreak1, priceText);
        content.getChildren().addAll(ingredientSec, ingredientPriceList);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle(IDLE_BUTTON_STYLE);
        confirmBtn.setOnMouseEntered(e -> confirmBtn.setStyle(HOVERED_BUTTON_STYLE));
        confirmBtn.setOnMouseExited(e -> confirmBtn.setStyle(IDLE_BUTTON_STYLE));
        confirmBtn.setAlignment(Pos.BASELINE_RIGHT);
        confirmBtn.setOnAction(e -> {
            // set the action at here
            window.close();
        });

        ingredientPageLayout.setTop(titleText);
        ingredientPageLayout.setAlignment(titleText, Pos.BOTTOM_LEFT);

        ingredientPageLayout.setCenter(content);
        ingredientPageLayout.setBottom(confirmBtn);
        ingredientPageLayout.setAlignment(confirmBtn, Pos.BASELINE_RIGHT);

        Scene scene = new Scene(ingredientPageLayout);
        window.setScene(scene);
        window.showAndWait();
    }
}
