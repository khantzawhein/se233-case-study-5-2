package com.se233.chapter5_tdd.controller;

import com.se233.chapter5_tdd.model.Direction;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.model.SpecialFood;
import com.se233.chapter5_tdd.view.Platform;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyCode;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private SpecialFood specialFood;
    private float interval = 1000.0f / 8;
    private boolean running;
    private int score;

    public GameLoop(Platform platform, Snake snake, Food food, SpecialFood specialFood) {
        this.platform = platform;
        this.snake = snake;
        this.food = food;
        this.specialFood = specialFood;
        running = true;
    }

    private void update() {
        snake.update();
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
            score++;

            javafx.application.Platform.runLater(() -> {
                platform.getScore().setPoint(score);
            });
        }

        if (snake.isCollidingWith(specialFood)) {
            snake.grow();
            specialFood.respawn();
            score += 5;

            javafx.application.Platform.runLater(() -> {
                platform.getScore().setPoint(score);
            });
        }

        if (snake.isDead()) {
            javafx.application.Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("You died!");
                alert.setContentText("Your score is " + score);
                alert.showAndWait();
            });
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake, food, specialFood);
    }

    @Override
    public void run() {
        while (running) {
            update();
            checkCollision();
            redraw();
            try {
                Thread.sleep((long) interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public float getInterval() {
        return interval;
    }

    public Platform getPlatform() {
        return platform;
    }

    public Food getFood() {
        return food;
    }

    public Snake getSnake() {
        return snake;
    }

    public int getScore() {
        return score;
    }
}
