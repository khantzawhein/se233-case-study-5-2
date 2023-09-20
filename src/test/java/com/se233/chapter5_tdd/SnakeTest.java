package com.se233.chapter5_tdd;

import com.se233.chapter5_tdd.model.Direction;
import com.se233.chapter5_tdd.model.Food;
import com.se233.chapter5_tdd.model.Snake;
import com.se233.chapter5_tdd.model.SpecialFood;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Point2D;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeTest {
    private Snake snake;

    @BeforeEach
    public void setup() {
        JFXPanel jfxPanel = new JFXPanel();
        snake = new Snake(new Point2D(0, 0));
    }

    @Test
    public void snakeShouldBeSpawnAtTheCoordinateItWasCreated() {
        assertEquals(snake.getHead(), new Point2D(0, 0));
    }

    @Test
    public void snakeShouldMoveDownwardIfItIsHeadingDownward() {
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        assertEquals(snake.getHead(), new Point2D(0, 1));
    }

    @Test
    public void collisionFlagShouldRaiseIfSnakeCollideWithFood() {
        Food food = new Food(new Point2D(0, 0));
        assertTrue(snake.isCollidingWith(food));
    }

    @Test
    public void foodShouldRespawnOnDifferentCoordinateAfterBeingEaten() {
        Food food = new Food(new Point2D(0, 0));
        food.respawn();
        assertNotSame(food.getPosition(), new Point2D(0, 0));
    }


    @Test
    public void snakeGrowthShouldAddItsLengthByOne() {
        snake.grow();
        assertEquals(snake.getLength(), 2);
    }

    @Test
    public void bodyOfGrownSnakeShouldContainItsPreviousHead() {
        Point2D cur_head = snake.getHead();
        snake.update();
        snake.grow();
        assertTrue(snake.getBody().contains(cur_head));
    }

    @Test
    public void snakeWillDieIfItGoesBeyondTheGameBorder() {
        snake = new Snake(new Point2D(30, 30));
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        assertTrue(snake.isDead());
    }

    @Test
    public void snakeWillDiesIfItHitsItsBody() {
        snake = new Snake(new Point2D(0, 0));
        snake.setCurrentDirection(Direction.DOWN);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.LEFT);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.UP);
        snake.update();
        snake.grow();
        snake.setCurrentDirection(Direction.RIGHT);
        snake.update();
        snake.grow();
        assertTrue(snake.isDead());
    }

    @Test
    public void specialFoodShouldRespawnOnDifferentCoordinateAfterBeingEaten() {
        SpecialFood food = new SpecialFood(new Point2D(0, 0));
        food.respawn();
        assertNotSame(food.getPosition(), new Point2D(0, 0));
    }
}
