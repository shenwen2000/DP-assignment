package com.farm.dp_assignment.composite;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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

    public static BorderPane shopLayout;

    private static Shop uniqueShop = new Shop();

    private Shop() {
    }

    //make the shop singleton
    public static Shop getShop() {
        return uniqueShop;
    }

    //print menu as a modal
    public void printMenu() {
        if (Objects.isNull(allMenus)) {
            this.createMenuList();
            this.createAnimalMenuItemList(animalMenu);
            this.createFoodMenuItemList(foodMenu);
        }

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Shop");
        window.setMaxWidth(600);
        window.setMinHeight(700);

        shopLayout = new BorderPane();
        shopLayout.setPadding(new Insets(10, 10, 10, 10));

        VBox vBox = new VBox(10);

        vBox = this.allMenus.printMenu(vBox, this.allMenus);

        shopLayout.setCenter(vBox);
        shopLayout.setAlignment(vBox, Pos.TOP_LEFT);

        Scene shopScene = new Scene(shopLayout);
        window.setScene(shopScene);
        window.showAndWait();
    }

    //create list of menus
    private void createMenuList() {
        Image allMenusImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/hamburger.png")));
        Image animalShopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/pet_shop.png")));
        Image feedShopImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/feed.png")));

        animalMenu = new Menu("Animal Menu", animalShopImage, "Animal");
        foodMenu = new Menu("Food Menu", feedShopImage, "Food");

        allMenus = new Menu("Menus", allMenusImage, "All");
        allMenus.add(animalMenu);
        allMenus.add(foodMenu);
    }

    //add animal items to animal menu
    private void createAnimalMenuItemList(MenuComponent animalMenu) {
        Image chickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/chicken_1.png")));
        Image duckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/duck_1.png")));
        Image cowImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/cow_1.png")));
        Image goatImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/goat_1.png")));

        animalMenu.add(new MenuItem("Chicken", false, 1, chickenImage, "Animal"));
        animalMenu.add(new MenuItem("Duck", true, 2, duckImage, "Animal"));
        animalMenu.add(new MenuItem("Cow", true, 10, cowImage, "Animal"));
        animalMenu.add(new MenuItem("Goat", true, 25, goatImage, "Animal"));
    }

    //add food items to food menu
    private void createFoodMenuItemList(MenuComponent foodMenu) {
        Image foodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/food.png")));
        Image goodFoodImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/good_food.png")));

        foodMenu.add(new MenuItem("Normal food", false, 0, foodImage, "Food"));
        foodMenu.add(new MenuItem("Premium food", false, 0, goodFoodImage, "Food"));
    }

    public MenuComponent getAnimalMenu() {
        return animalMenu;
    }

    public MenuComponent getFoodMenu() {
        return foodMenu;
    }

    public MenuComponent getAllMenus() {
        return allMenus;
    }

    public void setAnimalMenu(MenuComponent animalMenu) {
        this.animalMenu = animalMenu;
    }

    public void setFoodMenu(MenuComponent foodMenu) {
        this.foodMenu = foodMenu;
    }

    public void setAllMenus(MenuComponent allMenus) {
        this.allMenus = allMenus;
    }
}
