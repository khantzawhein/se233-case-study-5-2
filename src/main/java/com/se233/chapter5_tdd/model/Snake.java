package com.se233.chapter5_tdd.model;

import com.se233.chapter5_tdd.view.Platform;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class Snake {
    private Direction direction;
    private Point2D head, prevTail;
    private ArrayList<Point2D> body;

    public Snake(Point2D position) {
        direction = Direction.DOWN;
        body = new ArrayList<>();
        this.head = position;
        this.body.add(this.head);
    }

    public void update() {
        head = head.add(direction.current);
        prevTail = body.remove(body.size() - 1);
        body.add(0, head);
    }

    public boolean isCollidingWith(Food food) {
        return this.head.equals(food.getPosition());
    }
    public void grow() {
        body.add(prevTail);
    }

    public boolean isDead() {
        boolean isOutOfBound = head.getX() < 0 || head.getY() < 0 || head.getX() > Platform.WIDTH || head.getY() > Platform.HEIGHT;
        boolean isHitBody = body.lastIndexOf(head) > 0;
        return isOutOfBound || isHitBody;
    }

    public int getLength() {
        return body.size();
    }

    public void setCurrentDirection(Direction direction) {
        this.direction = direction;
    }

    public Direction getCurrentDirection() {
        return this.direction;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public ArrayList<Point2D> getBody() {
        return body;
    }

    public Point2D getHead() {
        return head;
    }
}
