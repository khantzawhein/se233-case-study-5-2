package com.se233.chapter5_tdd.view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Score extends Pane {
    private final Label point;
    public Score(int x, int y) {
        point = new Label("0");
        setTranslateX(x);
        setTranslateY(y);
        point.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        point.setTextFill(Color.web("#000"));
        this.getChildren().addAll(point);
    }

    public void setPoint(int score) {
        this.point.setText(Integer.toString(score));
    }
}
