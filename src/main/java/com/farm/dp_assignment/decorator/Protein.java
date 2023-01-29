package com.farm.dp_assignment.decorator;

public class Protein extends FoodDecorator {
    AnimalFood animalFood;

    public Protein(AnimalFood animalFood) {
        this.animalFood = animalFood;
    }

    public String getDescription() {
        return "\nProtein * 1 "+ animalFood.getDescription();
    }

    @Override
    public Integer cost() {
        return animalFood.cost() + 1;
    }
}
