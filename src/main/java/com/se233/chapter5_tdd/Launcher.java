package com.se233.chapter5_tdd;

import com.se233.chapter5_tdd.controller.GameLoop;
import com.se233.chapter5_tdd.controller.KeyDetectLoop;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.model.SpecialFood;
import com.se233.chapter5_tdd.view.Platform;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Platform platform = new Platform();
        Snake snake = new Snake(new Point2D(Platform.WIDTH / 2, Platform.HEIGHT / 2));
        Food food = new Food();
        SpecialFood specialFood = new SpecialFood();
        GameLoop gameLoop = new GameLoop(platform, snake, food, specialFood);
        KeyDetectLoop keyDetectLoop = new KeyDetectLoop(platform, snake);
        Scene scene = new Scene(platform, Platform.WIDTH * Platform.TILE_SIZE, Platform.HEIGHT * Platform.TILE_SIZE);
        scene.setOnKeyPressed(keyEvent -> platform.setKey(keyEvent.getCode()));
        scene.setOnKeyReleased(keyEvent -> platform.setKey(null));
        primaryStage.setTitle("Snake");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        new Thread(gameLoop).start();
        new Thread(keyDetectLoop).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
