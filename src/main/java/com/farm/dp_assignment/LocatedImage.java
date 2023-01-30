package com.farm.dp_assignment;

import javafx.scene.image.Image;
import java.io.InputStream;

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
