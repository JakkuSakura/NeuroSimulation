package com.jeekrs.neuro_simulation.entities.functionals;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.interfaces.Circle;

public class Food extends Entity implements Circle {
    private float radius = 10;
    private float value = 100;
    private boolean picked = false;

    @Override
    public com.badlogic.gdx.math.Circle getCircle() {
        return new com.badlogic.gdx.math.Circle(getPos().x, getPos().y, radius);
    }

    @Override
    public boolean contains(Vector2 point) {
        return getPos().dst2(point) <= radius * radius;
    }

    @Override
    public boolean contains(float x, float y) {
        return getPos().dst2(x, y) <= radius * radius;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }

    @Override
    public boolean canEat() {
        return false;
    }

    @Override
    public boolean overlaps(com.badlogic.gdx.math.Circle circle) {
        return false;
    }
}
