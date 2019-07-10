package com.jeekrs.neuro_simulation.utils;

import com.jeekrs.neuro_simulation.component.Shape;

public class Geometry {
    public static boolean intersects(Shape s1, Shape s2)
    {
        for (int i = 0; i < 100; i++) {
            if(s2.hitCheck(s1.getInnerPoint()))
                return true;
            if(s1.hitCheck(s2.getInnerPoint()))
                return true;
        }
        return false;
    }
}
