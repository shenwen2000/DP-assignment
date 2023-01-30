package com.farm.dp_assignment.decorator;

public class Vitamin extends FoodDecorator {

    AnimalFood animalFood;

    public Vitamin(AnimalFood animalFood) {
        this.animalFood = animalFood;
    }

    public String getDescription() {
        return "\nVitamin * 1" + animalFood.getDescription();
    }

    @Override
    public Integer cost() {
        return animalFood.cost() + 1;
    }

    @Override
    public Double growthPoint() {
        return animalFood.growthPoint() + 0.02;
    }
}
