package com.farm.dp_assignment;

import com.farm.dp_assignment.state.State;
import com.farm.dp_assignment.strategy.MoveBehavior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Animal {

    MoveBehavior moveBehavior;
    Image image;
    State state;
    int growthPoints;
    String type;

    public Animal() {

    }

    public void performMove(ImageView imageView) {
        moveBehavior.move(imageView);
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

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setGrowthPoints(int growthPoints) {
        this.growthPoints = growthPoints;
    }

    public int getGrowthPoints() {
        return growthPoints;
    }

    public void checkConditionState() {
        state.checkingCondition();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
