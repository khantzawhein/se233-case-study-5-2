package com.se233.chapter5_tdd.controller;

import com.se233.chapter5_tdd.model.Direction;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.view.Platform;
import javafx.scene.input.KeyCode;

public class GameLoop implements Runnable {
    private Platform platform;
    private Snake snake;
    private Food food;
    private float interval = 1000.0f / 8;
    private boolean running;

    public GameLoop(Platform platform, Snake snake, Food food) {
        this.platform = platform;
        this.snake = snake;
        this.food = food;
        running = true;
    }

    private void update() {
        snake.update();
    }

    private void checkCollision() {
        if (snake.isCollidingWith(food)) {
            snake.grow();
            food.respawn();
        }
        if (snake.isDead()) {
            running = false;
        }
    }

    private void redraw() {
        platform.render(snake, food);
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
}
