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

    public NewBornState(Animal animal) {
        farm = Farm.getInstance();
        this.animal = animal;
    }

    @Override
    public void checkingCondition() {
        try {
            //System.out.println(animal.getClass().getName());
            //System.out.println(animal.getClass().getName().contains("Chicken"));
            //System.out.println(animal.getGrowthPoints() >= 5);
            //System.out.println(animal.getGrowthPoints());
            //System.out.println(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_2.png").toURI().toString()));
            //System.out.println(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_2.png").toURI().toString()));
            if (animal.getClass().getName().contains("Chicken") && animal.getGrowthPoints() >= 5) {
                chickenImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_2.png").toURI().toString()));
                animal.setImage(null);
                animal.setImage(chickenImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(10);

            }
            else if (animal.getClass().getName().contains("Duck") && animal.getGrowthPoints() >= 10) {

                duckImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_2.png").toURI().toString()));
                animal.setImage(null);
                animal.setImage(duckImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(20);

            }
            else if (animal.getClass().getName().contains("Cow") && animal.getGrowthPoints() >= 50) {
                cowImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/cow_2.png").toURI().toString()));
                animal.setImage(null);
                animal.setImage(cowImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(100);

            }
            else if (animal.getClass().getName().contains("Goat") && animal.getGrowthPoints() >= 125) {
                goatImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/goat_2.png").toURI().toString()));
                animal.setImage(null);
                animal.setImage(goatImage);
                imageView = new ImageView(animal.getImage());

                farm.getSlider().setMax(250);
            }
            farm.getGrowthPointBar().setProgress(0);
            farm.getGrowthPoint().setProgress(0);
            farm.setAnimalImageView(null);
            farm.setAnimalImageView(imageView);
            imageView.setFitWidth(100);
            imageView.setFitHeight(100);
            MoveBehavior.translate.stop();
            animal.setMoveBehavior(new MoveOnGround());
            animal.performMove(imageView);
            farm.getFarmLayout().setCenter(imageView);
            farm.getFarmLayout().setAlignment(imageView, Pos.BOTTOM_RIGHT);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        animal.setState(new YoungState(animal));
    }
}
