package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;

public class SoldState implements State {
    Animal animal;
    Farm farm;

    public SoldState(Animal animal) {
        this.animal = animal;
        farm = Farm.getInstance();
    }

    //Sold
    @Override
    public void checkingCondition() {
        farm.getFarmLayout().setCenter(null);
    }
}
