package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;
import com.farm.dp_assignment.LocatedImage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;
import java.util.Objects;

public class YoungState implements State {
    Animal animal;

    Image chickenImage;
    Image duckImage;
    Image cowImage;
    Image goatImage;
    Farm farm;

    public YoungState(Animal animal) {
        farm = Farm.getInstance();
        this.animal = animal;
    }

    @Override
    public void checkingCondition() {
        try {
            if (animal.getClass().getName().equals("Chicken") && animal.getGrowthPoints() >= 10) {
                chickenImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_3.png").toURI().toString()));

                animal.setImage(chickenImage);
                ImageView chick3ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(chick3ImageView);

            } else if (animal.getClass().getName().equals("Duck") && animal.getGrowthPoints() >= 20) {
                duckImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_3.png").toURI().toString()));
                animal.setImage(duckImage);
                ImageView duck3ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(duck3ImageView);

            } else if (animal.getClass().getName().equals("Cow") && animal.getGrowthPoints() >= 100) {
                cowImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/cow_3.png").toURI().toString()));
                animal.setImage(cowImage);
                ImageView cow3ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(cow3ImageView);

            } else if (animal.getClass().getName().equals("Goat") && animal.getGrowthPoints() >= 250) {
                goatImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/goat_3.png").toURI().toString()));
                animal.setImage(goatImage);
                ImageView goat3ImageView = new ImageView(animal.getImage());

                farm.setAnimalImageView(null);
                farm.setAnimalImageView(goat3ImageView);

            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        animal.setState(new FullyGrownState(animal));
    }
}
