package com.farm.dp_assignment.decorator;

public class Protein extends FoodDecorator{
    AnimalFood animalFood;

    public Protein(AnimalFood animalFood){
        this.animalFood=animalFood;
    }

    public String getDescription(){
        return "\n1 scoop of protein is added to " + animalFood.getDescription();
    }
}
