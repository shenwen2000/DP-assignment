package com.farm.dp_assignment;

import com.farm.dp_assignment.strategy.MoveBehavior;
import com.farm.dp_assignment.strategy.MoveOnGround;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class Animal {

    MoveBehavior moveBehavior;
    Image image;

    public Animal() {

    }

    public void performMove(ImageView imageView) {
        moveBehavior.move(imageView);
    }

    public void setMoveBehavior(MoveBehavior mb) {
        moveBehavior = mb;
    }

    public MoveBehavior getMoveBehavior() {
        return moveBehavior;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

//    public void setMovement(){
//        Button mvOnGrd = new Button("Move On Ground");
//        mvOnGrd.setStyle("-fx-padding: 10px;-fx-border-insets: 5px;-fx-background-insets: 5px;-fx-border:2px black");
//        mvOnGrd.setAlignment(Pos.BOTTOM_RIGHT);
//        mvOnGrd.setOnAction(e -> {
//            setMoveBehavior(new MoveOnGround());
//        });
//    }
}
