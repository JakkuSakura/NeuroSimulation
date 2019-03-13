package com.jeekrs.threatenbody.utils;

public class Vec2d implements Cloneable {
    public double x;
    public double y;

    public Vec2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vec2d() {

    }

    public void clear() {
        x = 0;
        y = 0;
    }

    public double distSquare(Vec2d p) {
        return _2(x - p.x) + _2(y - p.y);
    }

    public double dist(Vec2d p) {
        return Math.sqrt(distSquare(p));
    }

    private double _2(double x) {
        return x * x;
    }

    public Vec2d minus(Vec2d p) {
        return new Vec2d(x - p.x, y - p.y);
    }

    public Vec2d sub(Vec2d p) {
        x -= p.x;
        y -= p.y;
        return this;
    }

    public Vec2d add(Vec2d p) {
        x += p.x;
        y += p.y;
        return this;
    }

    public Vec2d plus(Vec2d p) {
        return new Vec2d(x + p.x, y + p.y);
    }


    public double multi(Vec2d p) {
        return x * p.x + y * p.y;
    }

    public Vec2d multi(double n) {
        return new Vec2d(x * n, y * n);
    }

    public Vec2d multiBy(double n) {
        x *= n;
        y *= n;
        return this;
    }

    @Override
    public Vec2d clone() {
        try {
            return (Vec2d) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public double abs() {
        return Math.sqrt(square());
    }

    public double square() {
        return x * x + y * y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
