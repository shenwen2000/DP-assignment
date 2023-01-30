package com.farm.dp_assignment.strategy;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public interface MoveBehavior {

    TranslateTransition translate = new TranslateTransition();

    public void move(ImageView imageView);

}
