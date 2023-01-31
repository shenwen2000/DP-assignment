package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.state.NewBornState;
import javafx.scene.image.Image;

public class Cow extends Animal {

    public Cow(Image image) {
        super.setImage(image);
        super.setMoveBehavior(new MoveOnGround());
        super.setType("Cow");
        this.setState(new NewBornState(this));
    }
}
