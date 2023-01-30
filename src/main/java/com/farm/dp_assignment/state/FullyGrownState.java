package com.farm.dp_assignment.state;

import com.farm.dp_assignment.Animal;
import com.farm.dp_assignment.Farm;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.Objects;

public class FullyGrownState implements State {
    Animal animal;
    Farm farm;
    int coin=0;
    final String IDLE_BUTTON_STYLE = "-fx-background-color: #676AC2; -fx-border-color: #676AC2; -fx-text-fill: white; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    final String HOVERED_BUTTON_STYLE = "-fx-background-color: white; -fx-border-color: #676AC2; -fx-text-fill: #676AC2; -fx-cursor: hand; -fx-border-radius: 5px; -fx-font-weight: bold";
    public FullyGrownState(Animal animal) {
        farm.getInstance();
        this.animal = animal;
    }

    @Override
    public void checkingCondition() {
        if (animal.getClass().getName().equals("Chicken")){
            coin=3;
        }
        else if (animal.getClass().getName().equals("Duck")){
            coin=6;
        }
        else if (animal.getClass().getName().equals("Cow")){
            coin=30;
        }
        else if (animal.getClass().getName().equals("Goat")){
            coin=75;
        }
        //Subject to change
        try {
            Thread.sleep(3000);
            Stage window = new Stage();
            window.initModality(Modality.APPLICATION_MODAL);
            window.setMaxWidth(550);
            window.setMinHeight(150);
            window.setTitle("Alert");

            Text alertMsg = new Text("The animal has been sold! \nYay! You earned "+String.valueOf(coin)+" coins!");
            alertMsg.setStyle("-fx-font-size: 15px; -fx-font-vertical-align:top");
            alertMsg.setBoundsType(TextBoundsType.VISUAL);

            BorderPane alertLayout = new BorderPane();
            alertLayout.setPadding(new Insets(10, 10, 10, 10));
            alertLayout.setCenter(alertMsg);
            alertLayout.setAlignment(alertMsg, Pos.CENTER);

            Button okayBtn = new Button("Okay");
            okayBtn.setStyle(IDLE_BUTTON_STYLE);
            okayBtn.setOnMouseEntered(e -> okayBtn.setStyle(HOVERED_BUTTON_STYLE));
            okayBtn.setOnMouseExited(e -> okayBtn.setStyle(IDLE_BUTTON_STYLE));

            okayBtn.setOnAction(e -> {
                animal.setState(new SoldState(animal));
                window.close();
            });

            alertLayout.setRight(okayBtn);
            alertLayout.setAlignment(okayBtn, Pos.BOTTOM_RIGHT);

            Scene scene = new Scene(alertLayout);
            window.setScene(scene);
            window.showAndWait();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
