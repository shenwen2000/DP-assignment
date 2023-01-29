package com.farm.dp_assignment.decorator;

public class Food extends AnimalFood {

    public Food() {
        description = "Normal food";
    }

    @Override
    public Integer cost() {
        return 2;
    }
}
