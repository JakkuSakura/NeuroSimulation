package com.jeekrs.threatenbody.component;

import com.jeekrs.threatenbody.utils.Vec2d;

public class CoordinateComponent extends Vec2d implements Component {
    public CoordinateComponent(float x, float y) {
        super(x, y);
    }

    public CoordinateComponent() {
        super();
    }
}
