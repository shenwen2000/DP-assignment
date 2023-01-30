package com.farm.dp_assignment.simpleFactory;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.LocatedImage;
import com.farm.dp_assignment.state.NewBornState;
import com.farm.dp_assignment.strategy.*;
import javafx.scene.image.Image;

import java.net.URISyntaxException;
import java.util.Objects;

public class SimpleAnimalFactory {

    Image chickenImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/chicken_1.png").toURI().toString()));
    Image duckImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/duck_1.png").toURI().toString()));
    Image cowImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/cow_1.png").toURI().toString()));
    Image goatImage = new LocatedImage(Objects.requireNonNull(getClass().getResource("/com/farm/dp_assignment/image/goat_1.png").toURI().toString()));

    // Constructor
    public SimpleAnimalFactory() throws URISyntaxException {
    }

    // This is the factory where we create animal
    public Animal createAnimal(String animalType) {
        Animal animal = null;

        if (animalType.equals("Chicken")) {
            animal = new Chicken(chickenImage);
            animal.setType("Chicken");
        } else if (animalType.equals("Cow")) {
            animal = new Cow(cowImage);
            animal.setType("Cow");
        } else if (animalType.equals("Duck")) {
            animal = new Duck(duckImage);
            animal.setType("Duck");
        } else if (animalType.equals("Goat")) {
            animal = new Goat(goatImage);
            animal.setType("Goat");
        }

        animal.setState(new NewBornState(animal));
        return animal;
    }
}
