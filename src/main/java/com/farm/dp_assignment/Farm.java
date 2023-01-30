package com.farm.dp_assignment;

import com.farm.dp_assignment.composite.Shop;
import com.farm.dp_assignment.decorator.*;
import com.farm.dp_assignment.simpleFactory.SimpleAnimalFactory;
import com.farm.dp_assignment.singleton.SingletonWallet;
import com.farm.dp_assignment.singleton.WalletFactory;
import com.farm.dp_assignment.strategy.Idle;
import com.farm.dp_assignment.strategy.MoveBehavior;
import com.farm.dp_assignment.strategy.MoveOnGround;
import com.farm.dp_assignment.strategy.Sleeping;
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

import java.net.URISyntaxException;
import java.util.Objects;

public class Farm {

    public static Stage startingScene;
    Button startButton;
    SimpleAnimalFactory factory = new SimpleAnimalFactory();
    public static Animal animal = null;

    final String IDLE_BUTTON_STYLE = "-fx-background-color: #676AC2; -fx-border-color: #676AC2; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-border-color: #676AC2; -fx-text-fill: #676AC2; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";

    private FoodFactory foodFactory = new FoodFactory();
    private AnimalFood animalFood;

    public static Shop shop;

    private static SingletonWallet wallet;

    public static double screenWidth;

    private static ImageView animalImageView;

    public Farm() throws URISyntaxException {
    }

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
        screenWidth = bounds.getWidth();
        VBox content = new VBox(15);
        Scene scene = new Scene(content, screenWidth, bounds.getHeight());

        startingScene.setScene(scene);
        startingScene.setX(bounds.getMinX());
        startingScene.setY(bounds.getMinY());
        startingScene.setMaximized(true);

        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/background_image.jpg")));
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitHeight(bounds.getHeight() - 100);
        backgroundImageView.setFitWidth(screenWidth - 100);
        backgroundImageView.setPreserveRatio(true);
        content.getChildren().addAll(backgroundImageView, startButton);
        content.setAlignment(Pos.CENTER);

        startingScene.show();
    }

    public static Scene setUpFarmPage() {

        BorderPane farmLayout = new BorderPane();
        farmLayout.setPadding(new Insets(10, 10, 10, 10));

        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();

        // setup background image
        farmLayout.setBackground(new Background(
                new BackgroundImage(
                        new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/farm.jpg"))),
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
        Image coinImage = new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/coin.png")));
        ImageView coinImageView = new ImageView(coinImage);
        coinImageView.setFitWidth(60);
        coinImageView.setFitHeight(60);

        HBox coinBox = new HBox(5);

        //WalletFactory
        WalletFactory walletFactory = new WalletFactory();

        // Singleton wallet
        wallet = walletFactory.getWallet();
        int totalCoin = wallet.getTotalAmount();

        String totalCoinStr = Integer.toString(totalCoin);
        Text totalCoinText = new Text(totalCoinStr);
        totalCoinText.setStyle("-fx-font-size: 25px; -fx-font-vertical-align:top");
        totalCoinText.setBoundsType(TextBoundsType.VISUAL);

        coinBox.getChildren().addAll(coinImageView, totalCoinText);

        coinBox.setStyle("-fx-border-color: #000000; -fx-border-radius: 5px;");
        coinBox.setAlignment(Pos.BASELINE_RIGHT);
        coinBox.setMaxWidth(400);
        coinBox.setMaxHeight(100);
        coinBox.setPadding(new Insets(5, 5, 15, 5));

        topSec.setLeft(growthProgressBox);
        topSec.setRight(coinBox);

        farmLayout.setTop(topSec);
        farmLayout.setAlignment(topSec, Pos.BOTTOM_LEFT);

        // Set up action button
        //Idle
        Button idleButton = new Button();
        Image idleImage = new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/donald_duck_idle.png")));
        ImageView idleImageView = new ImageView(idleImage);
        idleImageView.setFitHeight(80);
        idleImageView.setFitWidth(80);
        idleButton.setPrefSize(80, 80);
        idleButton.setGraphic(idleImageView);
        idleButton.setStyle("-fx-cursor: hand;");
        idleButton.setTooltip(new Tooltip("Set movement of animal to idle."));

        idleButton.setOnAction(e -> {
            if (!Objects.isNull(animal)) {
                animal.setMoveBehavior(new Idle());
                animal.performMove(new ImageView(animal.getImage()));
            }
        });

        // move
        Button moveButton = new Button();
        Image movementImage = new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/donald_duck_move.gif")));
        ImageView movementImageView = new ImageView(movementImage);
        movementImageView.setFitHeight(80);
        movementImageView.setFitWidth(80);
        moveButton.setPrefSize(80, 80);
        moveButton.setGraphic(movementImageView);
        moveButton.setStyle("-fx-cursor: hand;");
        moveButton.setTooltip(new Tooltip("Set movement of animal to move."));

        moveButton.setOnAction(e -> {
            if (!Objects.isNull(animal)) {
                animal.setMoveBehavior(new MoveOnGround());
                animal.performMove(new ImageView(animal.getImage()));
            }
        });

        // Sleep
        Button sleepButton = new Button();
        Image sleepImage = new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/donald_duck_sleep.gif")));
        ImageView sleepImageView = new ImageView(sleepImage);
        sleepImageView.setFitHeight(80);
        sleepImageView.setFitWidth(80);
        sleepButton.setPrefSize(80, 80);
        sleepButton.setGraphic(sleepImageView);
        sleepButton.setStyle("-fx-cursor: hand;");
        sleepButton.setTooltip(new Tooltip("Set movement of animal to sleep."));

        sleepButton.setOnAction(e -> {
            if (!Objects.isNull(animal)) {
                animal.setMoveBehavior(new Sleeping());
                animal.performMove(new ImageView(animal.getImage()));
            }
        });

        //Set up shop
        Button shopButton = new Button();
        Image shopImage = new Image(Objects.requireNonNull(Farm.class.getResourceAsStream("image/shop.png")));
        ImageView shopImageView = new ImageView(shopImage);
        shopImageView.setFitHeight(80);
        shopImageView.setFitWidth(80);
        shopButton.setPrefSize(80, 80);
        shopButton.setGraphic(shopImageView);
        shopButton.setStyle("-fx-cursor: hand; -fx-background-color: transparent;");
        shopButton.setAlignment(Pos.CENTER);

        shop = Shop.getShop();

        shopButton.setOnAction(e -> {
            shop.printMenu();
        });

        HBox bottomMenu = new HBox(10);
        bottomMenu.getChildren().addAll(shopButton, idleButton, moveButton, sleepButton);

        farmLayout.setBottom(bottomMenu);
        farmLayout.setAlignment(bottomMenu, Pos.BASELINE_LEFT);

        if (!Objects.isNull(animal)) {
            String[] pathArr = ((LocatedImage) animal.getImage()).getURL().split("/");
            String[] imageNameArr = pathArr[pathArr.length - 1].split("\\.");

            if (Objects.isNull(animalImageView)) {
                animalImageView = new ImageView(animal.getImage());
                animalImageView.setFitWidth(80);
                animalImageView.setFitHeight(80);
                animal.performMove(animalImageView);
                farmLayout.setCenter(animalImageView);
                farmLayout.setAlignment(animalImageView, Pos.BOTTOM_RIGHT);
            } else {
                animalImageView.setImage(null);
                animalImageView.setImage(animal.getImage());
            }
        }

        Scene scene = new Scene(farmLayout, bounds.getWidth(), bounds.getHeight());

        return scene;
    }

    public void createAnimal(String nameType) {
        animal = factory.createAnimal(nameType);
        MoveBehavior.translate.stop();
        refreshFarmPage();
    }

    public void setAddIngredientPage() {
        animalFood = foodFactory.getAnimalFood("Normal");

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Add Ingredient(s)");

        BorderPane ingredientPageLayout = new BorderPane();
        ingredientPageLayout.setPadding(new Insets(10, 10, 10, 10));

        FlowPane content = new FlowPane();
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setVgap(8);
        content.setHgap(12);

        Text addedIngredientListText = new Text();
        addedIngredientListText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        addedIngredientListText.setBoundsType(TextBoundsType.VISUAL);
        addedIngredientListText.setTextAlignment(TextAlignment.LEFT);

        Text priceText = new Text("Price:5");
        priceText.setStyle("-fx-font-size: 15px; -fx-font-weight: bold");
        priceText.setBoundsType(TextBoundsType.VISUAL);
        priceText.setTextAlignment(TextAlignment.LEFT);

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

        proteinButton.setOnAction(e -> {
            animalFood = new Protein(animalFood);
            addedIngredientListText.setText(animalFood.getDescription());
            priceText.setText("Price:" + String.valueOf(animalFood.cost()));
        });

        // create vitamin button
        Button vitaminButton = new Button();
        Image vitaminImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/vitamin.png")));
        ImageView vitaminImageView = new ImageView(vitaminImg);
        vitaminButton.setGraphic(vitaminImageView);
        vitaminButton.setPrefSize(100, 100);
        vitaminImageView.setFitWidth(100);
        vitaminImageView.setFitHeight(100);
        vitaminButton.setStyle("-fx-cursor: hand;");

        vitaminButton.setOnAction(e -> {
            animalFood = new Vitamin(animalFood);
            addedIngredientListText.setText(animalFood.getDescription());
            priceText.setText("Price:" + String.valueOf(animalFood.cost()));
        });

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

        ingredientPriceList.getChildren().addAll(ingredientListText, addedIngredientListText, lineBreak1, priceText);
        content.getChildren().addAll(ingredientSec, ingredientPriceList);

        Button confirmBtn = new Button("Confirm");
        confirmBtn.setStyle(IDLE_BUTTON_STYLE);
        confirmBtn.setOnMouseEntered(e -> confirmBtn.setStyle(HOVERED_BUTTON_STYLE));
        confirmBtn.setOnMouseExited(e -> confirmBtn.setStyle(IDLE_BUTTON_STYLE));
        confirmBtn.setAlignment(Pos.BASELINE_RIGHT);

        // set the action at here
        confirmBtn.setOnAction(e -> {
//                //WalletFactory
//                WalletFactory walletFactory = new WalletFactory();
//
//                // Singleton wallet
//                SingletonWallet wallet = walletFactory.getWallet();
//                int totalCoin = wallet.getTotalAmount();
//               totalCoin= totalCoin- animalFood.cost();

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

    public static void refreshFarmPage() {
        startingScene = Main.primaryStage;
        startingScene.setScene(setUpFarmPage());
    }
}
