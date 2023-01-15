package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import javafx.scene.image.Image;

public class Cow extends Animal {

    public Cow(Image image) {
        super.setImage(image);
        super.setMoveBehavior(new MoveOnGround());
    }

}
