package com.farm.dp_assignment.decorator;

public class Vitamin extends FoodDecorator{
    AnimalFood animalFood;

    public Vitamin(AnimalFood animalFood){
        this.animalFood=animalFood;
    }

    public String getDescription(){
        return "\n1 scoop of vitamin is added to " + animalFood.getDescription();
    }
}
