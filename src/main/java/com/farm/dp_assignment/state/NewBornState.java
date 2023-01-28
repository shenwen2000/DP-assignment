package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import javafx.scene.image.Image;

import java.util.Objects;

public class NewBornState implements State{
    Animal animal;
    Image chickenImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/chicken_1.png")));
    Image duckImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/duck_1.png")));
    Image cowImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/cow_1.png")));
    Image goatImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/farm/dp_assignment/image/goat_1.png")));

    public NewBornState(Animal animal){
        this.animal=animal;
        if(animal.getClass().getName().equals("Chicken")){
            animal.setImage(chickenImage);
        }
        else if(animal.getClass().getName().equals("Duck")){
            animal.setImage(duckImage);
        }
        else if(animal.getClass().getName().equals("Cow")){
            animal.setImage(cowImage);
        }
        else if(animal.getClass().getName().equals("Goat")){
            animal.setImage(goatImage);
        }
    }
    @Override
    public void checkingCondition() {
        if(animal.getClass().getName().equals("Chicken")&&animal.getGrowthPoints()>=5){
            changeState();
        }
        else if(animal.getClass().getName().equals("Duck")&&animal.getGrowthPoints()>=10){
            changeState();
        }
        else if(animal.getClass().getName().equals("Cow")&&animal.getGrowthPoints()>=50){
            changeState();
        }
        else if(animal.getClass().getName().equals("Goat")&&animal.getGrowthPoints()>=125){
            changeState();
        }
    }


    public void changeState() {
        animal.setState(new YoungState(animal));
    }
}