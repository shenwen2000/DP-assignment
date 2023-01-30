package com.farm.dp_assignment.decorator;

public class Food extends AnimalFood {

    public Food() {
        description = "\nAdded to normal food";
    }

    @Override
    public Integer cost() {
        return 5;
    }

    @Override
    public Double growthPoint() {
        return 0.05;
    }


}
