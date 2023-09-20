package com.se233.chapter5_tdd;

import com.se233.chapter5_tdd.controller.GameLoop;
import com.se233.chapter5_tdd.controller.KeyDetectLoop;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.model.SpecialFood;
import com.se233.chapter5_tdd.view.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameLoopTest {
    private GameLoop gameLoopUnderTest;

    private KeyDetectLoop keyDetectLoopUnderTest;
    private Method updateKeyDetectLoop;

    private Method update;
    private Method collision;
    private Method redraw;

    @BeforeEach
    public void init() throws NoSuchMethodException {
        JFXPanel jfxPanel = new JFXPanel();
        Snake snake = new Snake(new Point2D(0, 0));
        gameLoopUnderTest = new GameLoop(new Platform(), snake, new Food(), new SpecialFood());
        keyDetectLoopUnderTest = new KeyDetectLoop(new Platform(), snake);
        update = GameLoop.class.getDeclaredMethod("update");
        update.setAccessible(true);
        updateKeyDetectLoop = KeyDetectLoop.class.getDeclaredMethod("update");
        updateKeyDetectLoop.setAccessible(true);
        collision = GameLoop.class.getDeclaredMethod("checkCollision");
        collision.setAccessible(true);
        redraw = GameLoop.class.getDeclaredMethod("redraw");
        redraw.setAccessible(true);
    }

    private void clockTickHelper() throws InvocationTargetException, IllegalAccessException {
        updateKeyDetectLoop.invoke(keyDetectLoopUnderTest);
        update.invoke(gameLoopUnderTest);
        collision.invoke(gameLoopUnderTest);
        redraw.invoke(gameLoopUnderTest);

    }

    @Test
    public void testClockTick() throws InvocationTargetException, IllegalAccessException {
        clockTickHelper();
        assertEquals(gameLoopUnderTest.getSnake().getHead(), new Point2D(0, 1));
    }

    @Test
    public void testNoBack() throws InvocationTargetException, IllegalAccessException {
        keyDetectLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(keyDetectLoopUnderTest.getSnake().getHead(), new Point2D(0, 1));
        keyDetectLoopUnderTest.getPlatform().setKey(KeyCode.DOWN);
        clockTickHelper();
        assertEquals(keyDetectLoopUnderTest.getSnake().getHead(), new Point2D(0, 2));
        keyDetectLoopUnderTest.getPlatform().setKey(KeyCode.UP);
        clockTickHelper();
        assertEquals(keyDetectLoopUnderTest.getSnake().getHead(), new Point2D(0, 3));
    }

    @Test
    public void IfSnakeCollidedWithFoodScoreShouldIncrease() throws IllegalAccessException, NoSuchFieldException, InvocationTargetException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food(new Point2D(0, 1)), new SpecialFood());
        clockTickHelper();
        assertEquals(1, gameLoopUnderTest.getScore(), "Score should increase by 1");
    }

    @Test
    public void collidingWithSpecialFoodShouldGiveFivePoints() throws InvocationTargetException, IllegalAccessException {
        gameLoopUnderTest = new GameLoop(new Platform(), new Snake(new Point2D(0, 0)), new Food(), new SpecialFood(new Point2D(0, 1)));
        clockTickHelper();
        assertEquals(5, gameLoopUnderTest.getScore(), "Score should increase by 5");

    }
}
