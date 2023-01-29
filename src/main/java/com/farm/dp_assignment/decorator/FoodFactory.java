package com.farm.dp_assignment.decorator;

public class FoodFactory {

    public AnimalFood getAnimalFood(String type) {

        if (type.equals("Normal"))
            return new Food();
        else if (type.equals("Premium")) {

        }
        return null;
    }
}
