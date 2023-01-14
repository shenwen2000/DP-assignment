package com.farm.dp_assignment;

import com.farm.dp_assignment.strategy.MoveBehavior;
import javafx.scene.image.Image;

public abstract class Animal {

    MoveBehavior moveBehavior;
    Image image;

    public Animal() {

    }

    public void performMove() {
        moveBehavior.move();
    }

    public void setMoveBehavior(MoveBehavior mb) {
        moveBehavior = mb;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
