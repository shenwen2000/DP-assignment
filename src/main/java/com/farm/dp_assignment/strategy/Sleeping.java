package com.farm.dp_assignment.strategy;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sleeping implements MoveBehavior {

    @Override
    public void move(ImageView imageView) {
        //Instantiating TranslateTransition class
        TranslateTransition translate = new TranslateTransition();

        //setting the duration for the Translate transition
        translate.setDuration(Duration.millis(2000));

        //setting cycle count for the Translate transition
        translate.setCycleCount(Animation.INDEFINITE);

        //setting Circle as the node onto which the transition will be applied
        translate.setNode(imageView);

        //playing the transition
        translate.play();
    }
}
