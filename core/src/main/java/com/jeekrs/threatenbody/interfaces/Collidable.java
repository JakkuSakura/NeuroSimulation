package com.jeekrs.threatenbody.interfaces;

import com.jeekrs.threatenbody.entity.Entity;

public interface Collidable extends Physics {
    float getCollidingRadius(Collidable other);
}
