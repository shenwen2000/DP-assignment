package com.farm.dp_assignment.decorator;

public abstract class AnimalFood {
    String description = "Unknown Food";
    String cost = "2 coin";

    public String getDescription() {
        return description;
    }

    public String cost(){
        return cost;
    }
}
