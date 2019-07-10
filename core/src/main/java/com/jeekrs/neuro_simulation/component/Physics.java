package com.jeekrs.neuro_simulation.component;

import com.badlogic.gdx.math.Vector2;

public class Physics implements MyCloneable {
    public Vector2 vel = new Vector2();
    public Vector2 acc = new Vector2();
    public Vector2 pos = new Vector2();

    @Override
    public Physics clone() {
        Physics p = null;
        try {
            p = (Physics) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        p.vel = new Vector2(vel);
        p.pos = new Vector2(pos);
        p.acc = new Vector2(acc);
        return p;
    }
}
