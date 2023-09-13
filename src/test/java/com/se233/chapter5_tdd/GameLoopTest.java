package com.se233.chapter5_tdd;

import com.se233.chapter5_tdd.controller.GameLoop;
import com.se233.chapter5_tdd.controller.KeyDetectLoop;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.view.Platform;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Snake snake = new Snake(new Point2D(0, 0));
        gameLoopUnderTest = new GameLoop(new Platform(), snake, new Food());
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
}
