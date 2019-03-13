package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract public class Renderer {
    public SpriteBatch batch;
    public Camera camera;
    public World world;

    public void render() {

    }

    public void depose() {

    }

    public void setBatch(SpriteBatch batch) {
        this.batch = batch;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
