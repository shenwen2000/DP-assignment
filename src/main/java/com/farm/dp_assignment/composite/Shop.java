package com.farm.dp_assignment.composite;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class Shop {

    MenuComponent animalMenu;
    MenuComponent foodMenu;
    MenuComponent allMenus;

    public void printMenu() {
        this.createMenuList();
        this.createAnimalMenuItemList(animalMenu);
        this.createFoodMenuItemList(foodMenu);

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Shop");
        window.setMaxWidth(800);

        Label title = new Label("All menus");

        BorderPane shopLayout = new BorderPane();
        shopLayout.setPadding(new Insets(10, 10, 10, 10));
        shopLayout.setTop(title);
        shopLayout.setAlignment(title, Pos.CENTER);

        VBox vBox = new VBox(10);

        vBox = this.allMenus.print(vBox, this.allMenus);

        shopLayout.setCenter(vBox);
        shopLayout.setAlignment(vBox, Pos.TOP_LEFT);

        Scene scene = new Scene(shopLayout);
        window.setScene(scene);
        window.showAndWait();
    }

    private void createMenuList() {
        Image animalShopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/pet_shop.png")));
        Image feedShopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/feed.png")));

        animalMenu = new Menu("Animal Shop", animalShopImage);
        foodMenu = new Menu("Food Menu", feedShopImage);

        allMenus = new Menu("Menus", null);
        allMenus.add(animalMenu);
        allMenus.add(foodMenu);
    }

    private void createAnimalMenuItemList(MenuComponent animalMenu) {
        Image chickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/chicken_1.png")));
        Image duckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/duck_1.png")));
        Image cowImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/cow_1.png")));
        Image goatImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/goat_1.png")));

        animalMenu.add(new MenuItem("Chicken", false, 1, chickenImage));
        animalMenu.add(new MenuItem("Duck", true, 2, duckImage));
        animalMenu.add(new MenuItem("Cow", true, 10, cowImage));
        animalMenu.add(new MenuItem("Goat", true, 25, goatImage));
    }

    private void createFoodMenuItemList(MenuComponent foodMenu) {
        Image foodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/food.png")));
        Image goodFoodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("image/good_food.png")));

        foodMenu.add(new MenuItem("Chicken", false, 0, foodImage));
        foodMenu.add(new MenuItem("Duck", true, 0, goodFoodImage));
    }
}
