package com.jeekrs.neuro_simulation.components;

import com.jeekrs.neuro_simulation.interfaces.PublicClonable;

public abstract class Component implements PublicClonable<Component> {
    @Override
    public Component clone() {
        try {
            return (Component) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

