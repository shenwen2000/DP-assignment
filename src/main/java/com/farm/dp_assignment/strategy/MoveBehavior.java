package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;

public interface MoveBehavior {

    TranslateTransition translate = new TranslateTransition();

    public void move(ImageView imageView);

}
