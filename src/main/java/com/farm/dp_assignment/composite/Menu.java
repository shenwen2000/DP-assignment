package com.farm.dp_assignment.composite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;

import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    Image image;
    String type;

    public Menu(String name, Image image, String type) {
        this.name = name;
        this.image = image;
        this.type = type;
    }

    public void add(MenuComponent menuComponent) {
        menuComponents.add(menuComponent);
    }

    @Override
    public String getName() {
        return name;
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

    public void setImage(Image image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }

    //print the whole menu with the items in the menu
    public VBox printMenu(VBox vBox, MenuComponent menuComponent) {
        Iterator<MenuComponent> menuIterator = menuComponents.iterator();

        VBox menuVbox = new VBox(10);

        VBox shopBoxWithDashLine = new VBox(10);

        FlowPane shopBox = new FlowPane();
        shopBox.setVgap(8);
        shopBox.setHgap(4);

        ImageView imageView = new ImageView(getImage());
        imageView.setFitHeight(getType().equals("All") ? 40 : 60);
        imageView.setFitWidth(getType().equals("All") ? 60 : 80);

        Text nameType = new Text(getName());
        nameType.setStyle("-fx-font-size: 25px; -fx-font-vertical-align:top");
        nameType.setBoundsType(TextBoundsType.VISUAL);

        Text dashLine1 = new Text("------------------------------------------------------------");
        dashLine1.setStyle("-fx-font-size: 25px; -fx-font-vertical-align:top");
        dashLine1.setBoundsType(TextBoundsType.VISUAL);

        Text dashLine2 = new Text("------------------------------------------------------------");
        dashLine2.setStyle("-fx-font-size: 25px; -fx-font-vertical-align:top");
        dashLine2.setBoundsType(TextBoundsType.VISUAL);

        shopBox.getChildren().addAll(imageView, nameType);
        if (getType().equals("Food")) {
            shopBoxWithDashLine.getChildren().addAll(dashLine1, shopBox, dashLine2);
        } else {
            shopBoxWithDashLine.getChildren().addAll(shopBox, dashLine1);
        }

        menuVbox.getChildren().addAll(shopBoxWithDashLine);

        //iterate through each menu component
        while (menuIterator.hasNext()) {
            MenuComponent menu = menuIterator.next();
            VBox tempVbox = new VBox(10);
            tempVbox = menu.printMenu(tempVbox, menuComponent);
            tempVbox.setStyle("-fx-margin-bottom: 15px");
            menuVbox.getChildren().addAll(tempVbox);
        }

        return menuVbox;
    }
}
