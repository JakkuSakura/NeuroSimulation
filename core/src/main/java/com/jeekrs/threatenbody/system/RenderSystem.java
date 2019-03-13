package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;


public class RenderSystem extends SimpleSystem {
    public ArrayList<Renderer> renderers = new ArrayList<>();

    public RenderSystem() {

    }

    public void init() {
        addRenderer(new PlanetRenderer());
    }

    public int width = 600;
    public int height = 800;

    public Camera camera = new OrthographicCamera();
    public Viewport viewport = new ExtendViewport(800, 480, camera);

    public SpriteBatch batch = new SpriteBatch();

    public void setWindowSize(int width, int height) {
        this.width = width;
        this.height = height;
        viewport.update(width, height);
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
        bind(renderer);
    }

    private void bind(Renderer renderer) {
        renderer.setBatch(batch);
        renderer.setCamera(camera);
        renderer.setWorld(systemManager.world);
    }


    @Override
    public void update(float delta) {
        render(delta);
    }

    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        batch.begin();
        renderers.forEach(Renderer::render);
        batch.end();
    }

    @Override
    public void dispose() {
        renderers.forEach(Renderer::depose);
    }

}
