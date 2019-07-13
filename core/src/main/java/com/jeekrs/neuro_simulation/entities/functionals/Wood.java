package com.jeekrs.neuro_simulation.entities.functionals;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.entities.Structure;
import com.jeekrs.neuro_simulation.interfaces.Rectangle;
import com.jeekrs.neuro_simulation.utils.RandomUtil;

public class Wood extends Structure implements Rectangle {
    private long delay_ms = 1000;
    private long last_time;
    private float width = 64, height = 64;

    public Food produceFood() {
        Food food = new Food();
        food.getPos().x = getPos().x + RandomUtil.nextFloat(-500, 500);
        food.getPos().y = getPos().y + RandomUtil.nextFloat(-500, 500);
        food.setValue(RandomUtil.nextFloat(50, 80));
        return food;
    }

    public long getDelayMs() {
        return delay_ms;
    }

    public void setDelayMs(long delay_ms) {
        this.delay_ms = delay_ms;
    }

    public long getLastTime() {
        return last_time;
    }

    public void setLastTime(long last_time) {
        this.last_time = last_time;
    }

    @Override
    public com.badlogic.gdx.math.Rectangle getRectangle() {
        return new com.badlogic.gdx.math.Rectangle(getPos().x - width / 2, getPos().y - height / 2, width, height);
    }

    @Override
    public boolean contains(Vector2 point) {
        return getRectangle().contains(point);
    }

    @Override
    public boolean contains(float x, float y) {
        return getRectangle().contains(x, y);
    }
}
