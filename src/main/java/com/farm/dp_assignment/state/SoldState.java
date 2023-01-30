package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;

public class SoldState implements State {
    Animal animal;
    Farm farm;
    //Constructor
    public SoldState() {
        animal = null;
    }
    //Sold
    @Override
    public void checkingCondition() {
        farm.setSlider(null);
        farm.setAnimal(null);
        animal.setImage(null);
        farm.setAnimalImageView(null);
        animal.setType(null);
    }
}
