package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.LocatedImage;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.net.URISyntaxException;
import java.util.Objects;


public class MoveOnGround implements MoveBehavior {

    @Override
    public void move(ImageView imageView) {

        Farm farm = Farm.getInstance();

        String[] pathArr = ((LocatedImage) imageView.getImage()).getURL().split("/");
        String[] imageNameArr = pathArr[pathArr.length - 1].split("\\.");
        if (imageNameArr[0].contains("sleep")) {
            imageNameArr[0] = imageNameArr[0].replace("sleep", "");
            String path = "/com/farm/dp_assignment/image/" + imageNameArr[0] + ".png";
            try {
                farm.getAnimal().setImage(new LocatedImage(Objects.requireNonNull(getClass().getResource(path).toURI().toString())));
                Farm.setUpFarmPage();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

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
