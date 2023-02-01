package com.farm.dp_assignment.decorator;

public class Food extends AnimalFood {

    public Food() {
        description = "\nAdded to normal food";
    }

    @Override
    public Integer cost() {
        return 0;
    }

    @Override
    public Double growthPoint() {
        return 1.00;
    }
}
