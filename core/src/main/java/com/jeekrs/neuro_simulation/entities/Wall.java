package com.jeekrs.neuro_simulation.entities;

import com.jeekrs.neuro_simulation.component.Rectangle;
import com.jeekrs.neuro_simulation.utils.RandomUtil;
import com.jeekrs.neuro_simulation.utils.Vec2d;

public class Wall extends Structure implements Rectangle {
    private double width, height;

    public Wall(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    @Override
    public boolean hitCheck(Vec2d pos) {
        if (pos.x > getPhy().pos.x && pos.x < getPhy().pos.x + width)
            if (pos.y > getPhy().pos.y && pos.y < getPhy().pos.y + height)
                return true;
        return false;

    }

    @Override
    public Vec2d getInnerPoint() {
        return new Vec2d(getPhy().pos.x + width * RandomUtil.nextDouble(0,1),
                    getPhy().pos.y + height * RandomUtil.nextDouble(0,1));
    }
}
