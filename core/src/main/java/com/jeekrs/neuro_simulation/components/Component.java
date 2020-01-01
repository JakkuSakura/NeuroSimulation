package com.jeekrs.neuro_simulation.components;

import com.jeekrs.neuro_simulation.interfaces.MyCloneable;

public abstract class Component implements MyCloneable {
    @Override
    public Component clone() {
        try {
            return (Component) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T as() {
        if (this != NONE) {
            // this forced cast should not be checked
            return (T) this;
        }
        return null;
    }

    static public final Component NONE = new Component() {
    };
}

