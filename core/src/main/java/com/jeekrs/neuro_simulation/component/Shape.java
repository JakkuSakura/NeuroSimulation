package com.jeekrs.neuro_simulation.component;

import com.jeekrs.neuro_simulation.utils.Vec2d;

public interface Shape {
    boolean hitCheck(Vec2d pos);
    Vec2d getInnerPoint();
}
