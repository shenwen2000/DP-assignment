package com.farm.dp_assignment.composite;

import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.decorator.*;
import com.farm.dp_assignment.singleton.SingletonWallet;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuItem extends MenuComponent {
    SingletonWallet wallet = SingletonWallet.getInstance();
    Farm farm = Farm.getInstance();
    String name;
    Boolean isLocked;
    int price;
    Image image;
    String type;

    // button styles
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #676AC2; -fx-border-color: #676AC2; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-border-color: #676AC2; -fx-text-fill: #676AC2; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    final String IDLE_CANCEL_STYLE = "-fx-background-color: transparent; fx-cursor: hand;  -fx-text-fill: #676AC2; -fx-border-bottom-color: #676AC2; -fx-cursor: hand; -fx-font-weight: bold";

    public MenuItem(String name, boolean isLocked, int price, Image image, String type) {
        this.name = name;
        this.isLocked = isLocked;
        this.price = price;
        this.image = image;
        this.type = type;
    }

    //get item name
    @Override
    public String getName() {
        return name;
    }

    //check whether the item is locked (some are locked for animal menu)
    public Boolean getLocked() {
        return isLocked;
    }

    //get the price of the item
    @Override
    public int getPrice() {
        return price;
    }

    //image of the item
    @Override
    public Image getImage() {
        return image;
    }

    //type of the item such as Animal or Food
    public String getType() {
        return type;
    }

    //change the name of the item
    public void setName(String name) {
        this.name = name;
    }

    //to lock or unlock the item
    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    //change the price of the item
    public void setPrice(int price) {
        this.price = price;
    }

    //change the image of the item
    public void setImage(Image image) {
        this.image = image;
    }

    //change the type of the item
    public void setType(String type) {
        this.type = type;
    }

    //print menu item as a row in the pop up window
    public VBox printMenu(VBox vBox, MenuComponent menuComponent) {
        StackPane stackPane = new StackPane();

        FlowPane menuItemBox = new FlowPane();
        menuItemBox.setVgap(8);
        menuItemBox.setHgap(12);

        ImageView imageView = new ImageView(getImage());
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);

        Button itemButton = new Button();
        itemButton.setPrefSize(50, 50);
        itemButton.setStyle("-fx-border:none; -fx-cursor: hand;");

        //if the item is locked, there is a tooltip when pointing on the button
        if (getLocked()) {
            itemButton.setTooltip(new Tooltip(getName() + " is locked"));
        }

        //set image for the item button
        itemButton.setGraphic(imageView);

        //call unlock page once click on item button
        itemButton.setOnAction(e -> {
            setUnlockPage();
        });

        //set text for the item name
        Text nameType = new Text(getName());
        nameType.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        nameType.setBoundsType(TextBoundsType.VISUAL);

        //set text for the item price
        Text price;
        if (getName().equals("Premium food")) {
            price = new Text("Will according to ingredient(s) added");
        } else {
            price = new Text((getPrice() == 0 ? "Free" : String.valueOf(getPrice())));
        }

        price.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        price.setBoundsType(TextBoundsType.VISUAL);

        menuItemBox.getChildren().addAll(itemButton, nameType, price);

        //add lock image for the animals that are locked
        if (getType().equals("Animal")) {
            if (getLocked()) {
                Image lockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/lock.png")));
                ImageView lockImageView = new ImageView(lockImage);
                lockImageView.setFitWidth(50);
                lockImageView.setFitHeight(50);
                stackPane.getChildren().addAll(menuItemBox, lockImageView);
                vBox.getChildren().addAll(stackPane);
            } else {
                vBox.getChildren().addAll(menuItemBox);
            }
        } else {
            vBox.getChildren().addAll(menuItemBox);
        }

        return vBox;
    }

    //pop up modal for the item button's actions (buy or unlock)
    private void setUnlockPage() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        // Window title
        if (getLocked()) {
            window.setTitle("Unlock " + getName());
        } else {
            window.setTitle("Buy " + getName());
        }

        window.setMaxWidth(400);
        window.setMaxHeight(400);

        BorderPane farmLayout = new BorderPane();
        farmLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox content = new VBox(10);

        // Unlock message
        Text unlockMsg = new Text(getLocked() ? "Are you sure to unlock " + getName() : "Are you sure to buy " + getName());
        unlockMsg.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        unlockMsg.setBoundsType(TextBoundsType.VISUAL);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(8);
        flowPane.setHgap(12);

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle(IDLE_BUTTON_STYLE);
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle(HOVERED_BUTTON_STYLE));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle(IDLE_BUTTON_STYLE));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(IDLE_CANCEL_STYLE);

        confirmButton.setOnAction(e -> {
            if (getType().equals("Animal")) {
                if (getLocked()) {
                    // check the amount of the wallet enough to buy or unlock
                    if (wallet.getTotalAmount() >= getPrice()) {
                        // set lock status to unlock
                        setLocked(!getLocked());

                        // deduct amount
                        wallet.deductAmount(getPrice());

                        // refresh again the manu page
                        VBox vBox = new VBox(10);
                        vBox = farm.getShop().getAllMenus().printMenu(vBox, farm.getShop().getAllMenus());

                        // Set up the new menu
                        farm.getShop().shopLayout.setCenter(vBox);
                        farm.getShop().shopLayout.setAlignment(vBox, Pos.TOP_LEFT);

                        // update the coin amount
                        farm.updateCoinAmount();
                    } else {
                        setAlertMsg(getLocked() ? "Unlock" : "Buy", getName());
                    }
                } else {
                    if (Objects.isNull(farm.getAnimal())) {
                        buyAnimal();
                    } else {
                        confirmChgAnimal();
                    }
                }
            } else {
                if (getName().equals("Premium food")) {
                    //error message if there is no animal
                    if (farm.getAnimal() == null) {
                        noAnimalMsg();
                    } else farm.setAddIngredientPage();
                } else if (getName().equals("Normal food")) {
                    //error message if there is no animal
                    if (farm.getAnimal() == null) {
                        noAnimalMsg();
                    } else {
                        //update progress bar
                        AnimalFood animalFood = new Food();
                        farm.getGrowthPoint().setProgress(farm.getGrowthPoint().getProgress() + (animalFood.growthPoint() / farm.getSlider().getMax()));
                        farm.getGrowthPointBar().setProgress(farm.getGrowthPointBar().getProgress() + (animalFood.growthPoint() / farm.getSlider().getMax()));
                        //update the state if progress bar is full
                        if (farm.getGrowthPointBar().getProgress() >= 1) {
                            farm.getAnimal().checkConditionState();
                        }
                    }
                }
            }

            window.close();
        });

        cancelButton.setOnAction(e -> {
            window.close();
        });

        flowPane.getChildren().addAll(confirmButton, cancelButton);
        flowPane.setAlignment(Pos.CENTER);
        content.getChildren().addAll(unlockMsg, flowPane);
        farmLayout.setCenter(content);
        farmLayout.setAlignment(content, Pos.TOP_LEFT);

        Scene scene = new Scene(farmLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    private void noAnimalMsg() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaxWidth(550);
        window.setMinHeight(150);
        window.setTitle("Alert");

        Text alertMsg = new Text("You have no animal to feed. Please buy a new animal first.");
        alertMsg.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        alertMsg.setBoundsType(TextBoundsType.VISUAL);

        BorderPane alertLayout = new BorderPane();
        alertLayout.setPadding(new Insets(10, 10, 10, 10));
        alertLayout.setCenter(alertMsg);
        alertLayout.setAlignment(alertMsg, Pos.CENTER);

        Button okayBtn = new Button("Okay");
        okayBtn.setStyle(IDLE_BUTTON_STYLE);
        okayBtn.setOnMouseEntered(e -> okayBtn.setStyle(HOVERED_BUTTON_STYLE));
        okayBtn.setOnMouseExited(e -> okayBtn.setStyle(IDLE_BUTTON_STYLE));

        okayBtn.setOnAction(e -> {
            window.close();
        });

        alertLayout.setRight(okayBtn);
        alertLayout.setAlignment(okayBtn, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(alertLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    //pop up alert messages when wallet amount is not enuf to unlock or buy
    private void setAlertMsg(String actionType, String type) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaxWidth(550);
        window.setMinHeight(150);
        window.setTitle("Alert");

        Text alertMsg = new Text("You do not have enough money to " + actionType + " this " + type);
        alertMsg.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        alertMsg.setBoundsType(TextBoundsType.VISUAL);

        BorderPane alertLayout = new BorderPane();
        alertLayout.setPadding(new Insets(10, 10, 10, 10));
        alertLayout.setCenter(alertMsg);
        alertLayout.setAlignment(alertMsg, Pos.CENTER);

        Button okayBtn = new Button("Okay");
        okayBtn.setStyle(IDLE_BUTTON_STYLE);
        okayBtn.setOnMouseEntered(e -> okayBtn.setStyle(HOVERED_BUTTON_STYLE));
        okayBtn.setOnMouseExited(e -> okayBtn.setStyle(IDLE_BUTTON_STYLE));

        okayBtn.setOnAction(e -> {
            window.close();
        });

        alertLayout.setRight(okayBtn);
        alertLayout.setAlignment(okayBtn, Pos.BOTTOM_RIGHT);

        Scene scene = new Scene(alertLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    // pop up confirm message when buy a new animal
    private void confirmChgAnimal() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle("Change animal");

        window.setMaxWidth(400);
        window.setMaxHeight(400);

        BorderPane farmLayout = new BorderPane();
        farmLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox content = new VBox(10);

        Text confirmChgMsg = new Text("You have already bought an animal and it is not ready to sell yet." +
                "\nAre you sure to replace it with new " + getName() +
                "?\nNOTE: The growth progress cannot be transferred to new animal.");
        confirmChgMsg.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        confirmChgMsg.setBoundsType(TextBoundsType.VISUAL);

        FlowPane flowPane = new FlowPane();
        flowPane.setVgap(8);
        flowPane.setHgap(12);

        Button confirmButton = new Button("Confirm");
        confirmButton.setStyle(IDLE_BUTTON_STYLE);
        confirmButton.setOnMouseEntered(e -> confirmButton.setStyle(HOVERED_BUTTON_STYLE));
        confirmButton.setOnMouseExited(e -> confirmButton.setStyle(IDLE_BUTTON_STYLE));

        Button cancelButton = new Button("Cancel");
        cancelButton.setStyle(IDLE_CANCEL_STYLE);

        confirmButton.setOnAction(e -> {
            buyAnimal();
            window.close();
        });

        cancelButton.setOnAction(e -> {
            window.close();
        });

        flowPane.getChildren().addAll(confirmButton, cancelButton);
        flowPane.setAlignment(Pos.CENTER);
        content.getChildren().addAll(confirmChgMsg, flowPane);
        farmLayout.setCenter(content);
        farmLayout.setAlignment(content, Pos.TOP_LEFT);

        Scene scene = new Scene(farmLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    // Buy animal method
    private void buyAnimal() {
        if (!wallet.deductAmount(getPrice())) {
            setAlertMsg(getLocked() ? "Unlock" : "Buy", getName());
        } else {
            farm.setAnimal(null);
            farm.setSlider(null);
            farm.setGrowthPointBar(null);
            farm.setGrowthPoint(null);
            farm.setAnimalImageView(null);
            farm.createAnimal(getName());
        }
    }
}
