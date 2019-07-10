package com.jeekrs.neuro_simulation.system;

abstract public class Renderer {
    public RenderSystem renderSystem;

    public void render() {

    }

    public void depose() {

    }

    public void setSystem(RenderSystem renderSystem) {
        this.renderSystem = renderSystem;
    }
}
