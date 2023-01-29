package com.farm.dp_assignment.strategy;

import javafx.animation.TranslateTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Idle implements MoveBehavior {
    @Override
    public void move(ImageView imageView) {
        //Instantiating TranslateTransition class
        TranslateTransition translate = new TranslateTransition();


        translate.setByY(50);

        //setting the duration for the Translate transition
        translate.setDuration(Duration.millis(3000));

        //setting cycle count for the Translate transition
        translate.setCycleCount(5);

        //the transition will set to be auto reversed by setting this to true
        translate.setAutoReverse(true);

        //setting Circle as the node onto which the transition will be applied
        translate.setNode(imageView);

        //playing the transition
        translate.play();
    }
}