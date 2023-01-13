package com.farm.dp_assignment.composite;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import java.util.ArrayList;
import java.util.Iterator;

public class Menu extends MenuComponent {
    ArrayList<MenuComponent> menuComponents = new ArrayList<>();
    String name;
    Image image;

    public Menu(String name, Image image) {
        this.name = name;
        this.image = image;
    }

    public void add(MenuComponent menuComponent) {
        menuComponent.add(menuComponent);
    }

    public VBox print(VBox vBox, MenuComponent menuComponent) {
        Iterator<MenuComponent> menuIterator = menuComponents.iterator();

        VBox allMenus = new VBox(10);

        while (menuIterator.hasNext()) {
            MenuComponent menu = menuIterator.next();
            allMenus = menu.print(vBox, menu);
        }

        return allMenus;
    }
}
