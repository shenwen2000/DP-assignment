package com.farm.dp_assignment;

import com.farm.dp_assignment.state.State;
import com.farm.dp_assignment.strategy.MoveBehavior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Animal {

    MoveBehavior moveBehavior;
    Image image;
    State state;
    int growthPoints;
    String type;

    public Animal() {

    }

    // Animal perform move
    public void performMove(ImageView imageView) {
        moveBehavior.move(imageView);
    }

    // Set the move behavior of the animal
    public void setMoveBehavior(MoveBehavior mb) {
        moveBehavior = mb;
    }

    // Get the move behavior of the animal
    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    // Get the image of the animal
    public Image getImage() {
        return image;
    }

    // Set the image of the animal
    public void setImage(Image image) {
        this.image = image;
    }

    // Set the state of the animal
    public void setState(State state) {
        this.state = state;
    }

    // Get the state of the animal
    public State getState() {
        return state;
    }

    // set the growth points of the animal
    public void setGrowthPoints(int growthPoints) {
        this.growthPoints = growthPoints;
    }

    // Get the growth points of the animal
    public int getGrowthPoints() {
        return growthPoints;
    }

    // Check condition of the animal
    // If growth point full, then check condition, update state
    public void checkConditionState() {
        state.checkingCondition();
    }

    // Get the type of the animal
    public String getType() {
        return type;
    }

    // Set the type of the animal
    public void setType(String type) {
        this.type = type;
    }
}
