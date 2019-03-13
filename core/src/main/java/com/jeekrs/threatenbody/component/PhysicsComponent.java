package com.jeekrs.threatenbody.component;

import com.jeekrs.threatenbody.entity.Entity;

public class PhysicsComponent implements Component {
    public AccerlerateComponent a = new AccerlerateComponent();
    public VelocityComponent v = new VelocityComponent();
    public CoordinateComponent p = new CoordinateComponent();
    public Entity entity;
    public PhysicsComponent(Entity entity){
        this.entity = entity;
    }
}
