package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;

public class SoldState implements State {
    Animal animal;

    public SoldState(Animal animal) {
        this.animal = animal;
    }

    @Override
    public void checkingCondition() {

    }

}
