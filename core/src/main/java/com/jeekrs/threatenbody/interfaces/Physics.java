package com.jeekrs.threatenbody.interfaces;

import com.jeekrs.threatenbody.entity.Entity;
import com.jeekrs.threatenbody.utils.Vec2d;

public interface Physics extends Entity {
    Vec2d getAcc();
    Vec2d getVel();
    Vec2d getPos();
    double getMass();
}
