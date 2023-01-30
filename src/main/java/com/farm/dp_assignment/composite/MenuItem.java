package com.farm.dp_assignment.composite;

import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.singleton.SingletonWallet;
import com.farm.dp_assignment.singleton.WalletFactory;
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

import java.net.URISyntaxException;
import java.util.Objects;

public class MenuItem extends MenuComponent {

    String name;
    Boolean isLocked;
    int price;
    Image image;
    String type;

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

    @Override
    public String getName() {
        return name;
    }

    public Boolean getLocked() {
        return isLocked;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public Image getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocked(Boolean locked) {
        isLocked = locked;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    public VBox print(VBox vBox, MenuComponent menuComponent) {
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

        if (getLocked()) {
            itemButton.setTooltip(new Tooltip(getName() + " is locked"));
        }

        itemButton.setGraphic(imageView);

        itemButton.setOnAction(e -> {
            setUnlockPage();
        });

        Text nameType = new Text(getName());
        nameType.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        nameType.setBoundsType(TextBoundsType.VISUAL);

        Text price;
        if (getName().equals("Premium food")) {
            price = new Text("Will according to ingredient(s) added");
        } else {
            price = new Text((getPrice() == 0 ? "Free" : String.valueOf(getPrice())));
        }

        price.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        price.setBoundsType(TextBoundsType.VISUAL);

        menuItemBox.getChildren().addAll(itemButton, nameType, price);

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

    private void setUnlockPage() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);

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

        WalletFactory walletFactory = new WalletFactory();
        SingletonWallet wallet = walletFactory.getWallet();

        // Get the wallet amount and check enuf or not, then unlock
        confirmButton.setOnAction(e -> {
            if (getType().equals("Animal")) {
                if (getLocked()) {
                    if (wallet.getTotalAmount() >= getPrice()) {
                        // set lock status to unlock
                        setLocked(!getLocked());

                        wallet.deductAmount(getPrice());

                        // refresh again the manu page
                        VBox vBox = new VBox(10);
                        vBox = Farm.shop.getAllMenus().print(vBox, Farm.shop.getAllMenus());

                        Farm.shop.shopLayout.setCenter(vBox);
                        Farm.shop.shopLayout.setAlignment(vBox, Pos.TOP_LEFT);
                    } else {
                        setAlertMsg(getLocked() ? "Unlock" : "Buy", getType());
                    }
                } else {
                    Farm farm = null;
                    try {
                        farm = new Farm();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                    wallet.deductAmount(getPrice());
                    farm.createAnimal(getName());
                }
            } else {
                if (getName().equals("Premium food")) {
                    Farm farm = null;
                    try {
                        farm = new Farm();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                    farm.setAddIngredientPage();
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

    private void setAlertMsg(String actionType, String type) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMaxWidth(550);
        window.setMinHeight(150);
        window.setTitle("Alert");

        Text alertMsg = new Text("Not have enough money to " + actionType + " this " + type);
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
}
