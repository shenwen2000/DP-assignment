package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.LocatedImage;
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

    public NewBornState(Animal animal) {
        farm = Farm.getInstance();
        this.animal = animal;
    }

    @Override
    public void checkingCondition() {
        try {
            if (animal.getClass().getName().equals("Chicken") && animal.getGrowthPoints() >= 5) {
                chickenImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_2.png").toURI().toString()));

                animal.setImage(chickenImage);
                ImageView chick2ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(chick2ImageView);

                farm.getSlider().setMax(10);
                farm.getGrowthPoint().setProgress(0);
                farm.getGrowthPointBar().setProgress(0);

            } else if (animal.getClass().getName().equals("Duck") && animal.getGrowthPoints() >= 10) {
                duckImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_2.png").toURI().toString()));
                animal.setImage(duckImage);
                ImageView duck2ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(duck2ImageView);

                farm.getSlider().setMax(20);
                farm.getGrowthPoint().setProgress(0);
                farm.getGrowthPointBar().setProgress(0);

            } else if (animal.getClass().getName().equals("Cow") && animal.getGrowthPoints() >= 50) {
                cowImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/cow_2.png").toURI().toString()));
                animal.setImage(cowImage);
                ImageView cow2ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(cow2ImageView);

                farm.getSlider().setMax(100);
                farm.getGrowthPoint().setProgress(0);
                farm.getGrowthPointBar().setProgress(0);

            } else if (animal.getClass().getName().equals("Goat") && animal.getGrowthPoints() >= 125) {
                goatImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/goat_2.png").toURI().toString()));
                animal.setImage(goatImage);
                ImageView goat2ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(goat2ImageView);

                farm.getSlider().setMax(250);
                farm.getGrowthPoint().setProgress(0);
                farm.getGrowthPointBar().setProgress(0);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        animal.setState(new YoungState(animal));
    }
}
