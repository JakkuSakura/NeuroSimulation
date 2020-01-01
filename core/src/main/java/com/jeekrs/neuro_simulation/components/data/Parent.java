package com.jeekrs.neuro_simulation.components.data;

import com.jeekrs.neuro_simulation.components.Component;
import com.jeekrs.neuro_simulation.entities.Entity;

public class Parent extends Component {
    public Entity parent;

    public Parent(Entity s) {
        parent = s;
    }
}
