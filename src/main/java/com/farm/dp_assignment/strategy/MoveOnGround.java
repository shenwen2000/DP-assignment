package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Farm;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Objects;


public class MoveOnGround implements MoveBehavior {

    @Override
    public void move(ImageView imageView) {
        
        if (!translate.getStatus().equals("RUNNING")) {
            //shifting the X coordinate of the centre of the image by 400
            translate.setByX(-900);

            //setting the duration for the Translate transition
            translate.setDuration(Duration.millis(6000));

            //setting cycle count for the Translate transition
            translate.setCycleCount(Animation.INDEFINITE);

            //the transition will set to be auto reversed by setting this to true
            translate.setAutoReverse(true);

            //setting animal as the node onto which the transition will be applied

            translate.setNode(imageView);
        }

        //playing the transition
        translate.play();
    }
}
