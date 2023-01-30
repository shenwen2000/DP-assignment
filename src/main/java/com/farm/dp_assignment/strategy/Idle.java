package com.farm.dp_assignment.strategy;

import javafx.scene.image.ImageView;

public class Idle implements MoveBehavior {

    @Override
    public void move(ImageView imageView) {
        translate.pause();
    }
}