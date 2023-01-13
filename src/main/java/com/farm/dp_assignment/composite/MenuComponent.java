package com.farm.dp_assignment.composite;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

public abstract class MenuComponent {

    public void add(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }

    public String getName(){
        throw new UnsupportedOperationException();
    }

    public int getPrice(){
        throw new UnsupportedOperationException();
    }

    public Image getImage(){
        throw new UnsupportedOperationException();
    }

    public boolean isLock(){
        throw new UnsupportedOperationException();
    }

    public VBox print(VBox vBox, MenuComponent menuComponent){
        throw new UnsupportedOperationException();
    }
}
