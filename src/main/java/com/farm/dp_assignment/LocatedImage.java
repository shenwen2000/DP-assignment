package com.farm.dp_assignment;

import javafx.scene.image.Image;


// Self created class for storing URL of the image
public class LocatedImage extends Image {

    private final String url;

    public LocatedImage(String url) {
        super(url);
        this.url = url;
    }

    public String getURL() {
        return url;
    }
}
