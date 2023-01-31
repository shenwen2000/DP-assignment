package com.farm.dp_assignment.strategy;

import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.LocatedImage;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.Objects;

public class Sleeping implements MoveBehavior {

    @Override
    public void move(ImageView imageView) {
        translate.pause();

        Farm farm = Farm.getInstance();

        String[] pathArr = ((LocatedImage) imageView.getImage()).getURL().split("/");
        String[] imageNameArr = pathArr[pathArr.length - 1].split("\\.");
        if (!imageNameArr[0].contains("sleep")) {
            String sleepImageName = imageNameArr[0] + "sleep";
            String path = "/com/farm/dp_assignment/image/" + sleepImageName + ".png";
            try {
                farm.getAnimal().setImage(new LocatedImage(Objects.requireNonNull(getClass().getResource(path).toURI().toString())));
                farm.setUpFarmPage();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
    }
}
