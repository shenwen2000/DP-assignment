package com.farm.dp_assignment.decorator;

public abstract class AnimalFood {

    String description = "Unknown Food";

    public String getDescription() {
        return description;
    }

    public abstract Integer cost();
}
