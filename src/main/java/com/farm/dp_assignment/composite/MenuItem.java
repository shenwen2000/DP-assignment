package com.farm.dp_assignment.composite;

import com.farm.dp_assignment.Farm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class MenuItem extends MenuComponent {
    String name;
    Boolean isLocked;
    int price;
    Image image;
    String type;

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
//        imageView.setStyle("-fx-cursor: hand;");

        Button itemButton = new Button();
        itemButton.setPrefSize(50, 50);
        itemButton.setStyle("-fx-background:transparent; -fx-border:none; -fx-cursor: hand;");
        itemButton.setGraphic(imageView);

        itemButton.setOnAction(e -> {
            setUnlockPage();
        });

        Text nameType = new Text(getName());
        nameType.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
        nameType.setBoundsType(TextBoundsType.VISUAL);

        Text price;
        if (getName().equals("premium food")) {
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
        Button cancelButton = new Button("Cancel");

        // Get the wallet amount and check enuf or not, then unlock
        confirmButton.setOnAction(e -> {
            // if enuf then create a animal for him
            Farm farm = new Farm();
            farm.createAnimal(getName());
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
}
