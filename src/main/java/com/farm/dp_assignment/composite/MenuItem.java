package com.farm.dp_assignment.composite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.Objects;

public class MenuItem extends MenuComponent {
    String name;
    Boolean isLocked;
    int price;
    Image image;

    public MenuItem(String name, boolean isLocked, int price, Image image) {
        this.name = name;
        this.isLocked = isLocked;
        this.price = price;
        this.image = image;
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

    public VBox print(VBox vBox, MenuComponent menuComponent) {
        VBox animalBox = new VBox(5);

        ImageView animalImageView = new ImageView(menuComponent.getImage());

        Text animalType = new Text(menuComponent.getName());
        animalType.setStyle("-fx-font-size: 15px");
        animalType.setBoundsType(TextBoundsType.VISUAL);

        Text price = new Text(String.valueOf(menuComponent.getPrice()));
        price.setStyle("-fx-font-size: 15px");
        price.setBoundsType(TextBoundsType.VISUAL);

        animalBox.getChildren().addAll(animalImageView, animalType, price);

        if (menuComponent.isLock()) {
            StackPane stackPane = new StackPane();
            Image lockImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/lock.png")));
            ImageView lockImageView = new ImageView(lockImage);

            stackPane.getChildren().addAll(animalBox, lockImageView);
        }

        return vBox;
    }
}
