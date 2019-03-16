package com.jeekrs.threatenbody.system;

abstract public class SimpleSystem {
    public SystemManager systemManager;

    public void init() {

    }
    public void update(float delta) {
    }

    public void dispose() {
    }

    public void setSystemManager(SystemManager systemManager) {
        this.systemManager = systemManager;
    }

    public void resize(int width, int height) {
    }
}
