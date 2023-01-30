package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.LocatedImage;
import com.farm.dp_assignment.strategy.MoveBehavior;
import com.farm.dp_assignment.strategy.MoveOnGround;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.Objects;

public class NewBornState implements State {
    Animal animal;

    Image chickenImage;
    Image duckImage;
    Image cowImage;
    Image goatImage;
    Farm farm;
    ImageView imageView;

    boolean change = false;
    //Constructor
    public NewBornState(Animal animal) {
        farm = Farm.getInstance();
        this.animal = animal;
    }

    //Check if growth point is enough to evolve to Young state
    @Override
    public void checkingCondition() {
        try {
            if (animal.getType().equals("Chicken") && animal.getGrowthPoints() >= 5) {
                chickenImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_2.png").toURI().toString()));
                animal.setImage(chickenImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(10);
                change = true;
            } else if (animal.getType().equals("Duck") && animal.getGrowthPoints() >= 10) {
                duckImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_2.png").toURI().toString()));
                animal.setImage(duckImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(20);
                change = true;
            } else if (animal.getType().equals("Cow") && animal.getGrowthPoints() >= 50) {
                cowImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/cow_2.png").toURI().toString()));
                animal.setImage(cowImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(100);
                change = true;
            } else if (animal.getType().equals("Goat") && animal.getGrowthPoints() >= 125) {
                goatImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/goat_2.png").toURI().toString()));
                animal.setImage(goatImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(250);
                change = true;
            }
            // evolve to Young state and update the page
            if (change) {
                farm.getGrowthPoint().setProgress(0);
                farm.getGrowthPointBar().setProgress(0);
                animal.setGrowthPoints(0);
                farm.setAnimalImageView(imageView);
                imageView.setFitWidth(100);
                imageView.setFitHeight(100);
                MoveBehavior.translate.stop();
                animal.setMoveBehavior(new MoveOnGround());
                animal.performMove(imageView);
                farm.getFarmLayout().setCenter(imageView);
                farm.getFarmLayout().setAlignment(imageView, Pos.BOTTOM_RIGHT);
                change = false;
            }
            animal.setState(new YoungState(animal));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
