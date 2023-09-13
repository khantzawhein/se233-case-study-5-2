package com.se233.chapter5_tdd.controller;

import com.se233.chapter5_tdd.model.Direction;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.view.Platform;
import javafx.scene.input.KeyCode;

public class KeyDetectLoop implements Runnable {
    private boolean running;
    private Snake snake;
    private Platform platform;

    private float interval = 1000.0f / 20;

    public KeyDetectLoop(Platform platform, Snake snake) {
        this.snake = snake;
        this.platform = platform;
        running = true;
    }

    private void update() {
        KeyCode curKey = platform.getKey();
        Direction curDirection = snake.getCurrentDirection();
        if (curKey == KeyCode.UP && curDirection != Direction.DOWN) {
            snake.setCurrentDirection(Direction.UP);
        } else if (curKey == KeyCode.DOWN && curDirection != Direction.UP) {
            snake.setCurrentDirection(Direction.DOWN);
        } else if (curKey == KeyCode.LEFT && curDirection != Direction.RIGHT) {
            snake.setCurrentDirection(Direction.LEFT);
        } else if (curKey == KeyCode.RIGHT && curDirection != Direction.LEFT) {
            snake.setCurrentDirection(Direction.RIGHT);
        }
        running = !snake.isDead();
    }

    @Override
    public void run() {
        while (running) {
            update();
            try {
                Thread.sleep((long) this.interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Snake getSnake() {
        return snake;
    }

    public Platform getPlatform() {
        return platform;
    }
}
