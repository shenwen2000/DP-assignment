package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import javafx.scene.image.Image;

import java.util.Objects;

public class FullyGrownState implements State {
    Animal animal;
    Image chickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/chicken_3.png")));
    Image duckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/duck_3.png")));
    Image cowImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/cow_3.png")));
    Image goatImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/goat_3.png")));

    public FullyGrownState(Animal animal) {
        this.animal = animal;
        if (animal.getClass().getName().equals("Chicken")) {
            animal.setImage(chickenImage);
        } else if (animal.getClass().getName().equals("Duck")) {
            animal.setImage(duckImage);
        } else if (animal.getClass().getName().equals("Cow")) {
            animal.setImage(cowImage);
        } else if (animal.getClass().getName().equals("Goat")) {
            animal.setImage(goatImage);
        }
    }

    @Override
    public void checkingCondition() {
        //Subject to change
        try {
            Thread.sleep(3000);
            changeState();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void changeState() {
        animal.setState(new SoldState(animal));
    }
}
