package com.se233.chapter5_tdd.view;

import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Platform extends Pane {
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
    public static final int TILE_SIZE = 10;

    private Canvas canvas;
    private KeyCode key;

    public Platform() {
        this.setHeight(HEIGHT * TILE_SIZE);
        this.setWidth(WIDTH * TILE_SIZE);
        canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.getChildren().add(canvas);
    }

    public void render(Snake snake, Food food) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        gc.setFill(Color.BLUE);

        snake.getBody().forEach(p -> {
            gc.fillRect(p.getX() * TILE_SIZE, p.getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
        });

        gc.setFill(Color.RED);
        gc.fillRect(food.getPosition().getX() * TILE_SIZE, food.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

    public void setKey(KeyCode key) {
        this.key = key;
    }

    public KeyCode getKey() {
        return key;
    }
}
