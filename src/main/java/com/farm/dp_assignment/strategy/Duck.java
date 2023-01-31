package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.state.NewBornState;
import javafx.scene.image.Image;

public class Duck extends Animal {

    public Duck(Image image) {
        super.setImage(image);
        super.setMoveBehavior(new MoveOnGround());
        super.setType("Duck");
        this.setState(new NewBornState(this));
    }
}
