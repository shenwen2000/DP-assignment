package com.farm.dp_assignment.simpleFactory;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.strategy.Chicken;
import com.farm.dp_assignment.strategy.Cow;
import com.farm.dp_assignment.strategy.Duck;
import com.farm.dp_assignment.strategy.Goat;
import javafx.scene.image.Image;

import java.util.Objects;

public class SimpleAnimalFactory {

    Image chickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/chicken_1.png")));
    Image duckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/duck_1.png")));
    Image cowImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/cow_1.png")));
    Image goatImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/goat_1.png")));

    public Animal createAnimal(String animalType) {
        Animal animal = null;

        if (animalType.equals("Chicken")) {
            animal = new Chicken(chickenImage);
        } else if (animalType.equals("Cow")) {
            animal = new Cow(cowImage);
        } else if (animalType.equals("Duck")) {
            animal = new Duck(duckImage);
        } else if (animalType.equals("Goat")) {
            animal = new Goat(goatImage);
        }

        return animal;
    }
}
