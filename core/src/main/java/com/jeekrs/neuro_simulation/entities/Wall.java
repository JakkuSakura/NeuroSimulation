package com.jeekrs.neuro_simulation.entities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.interfaces.Rectangle;


public class Wall extends Structure implements Rectangle {
    private float width, height;

    public Wall(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Wall";
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    @Override
    public boolean contains(Vector2 pos) {
        return contains(pos.x, pos.y);
    }

    @Override
    public boolean contains(float x, float y) {
        return getRectangle().contains(x, y);
    }

    @Override
    public com.badlogic.gdx.math.Rectangle getRectangle() {
        return new com.badlogic.gdx.math.Rectangle(getPos().x - width / 2, getPos().y - height / 2, width, height);
    }
}
