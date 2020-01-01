package com.jeekrs.neuro_simulation.components.abilities;

import com.badlogic.gdx.math.Vector2;
import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.components.data.Vec2f;

public abstract class Hittable extends Component {
    public abstract boolean contains(float x, float y);

    public boolean contains(Vec2f p) {
        return contains(p.x, p.y);
    }

    public boolean contains(Vector2 p) {
        return contains(p.x, p.y);
    }
}
