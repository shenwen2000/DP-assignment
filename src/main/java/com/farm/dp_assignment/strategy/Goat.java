package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.state.NewBornState;
import javafx.scene.image.Image;

public class Goat extends Animal {

    public Goat(Image image) {
        super.setImage(image);
        super.setMoveBehavior(new MoveOnGround());
        super.setType("Goat");
        this.setState(new NewBornState(this));
    }
}
