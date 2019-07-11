package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;


public class RenderSystem extends SimpleSystem {
    public ArrayList<Renderer> renderers = new ArrayList<>();
    public int worldWidth = 600;
    public int worldHeight = 800;
    public OrthographicCamera camera = new OrthographicCamera();
    public Viewport viewport = new ExtendViewport(worldWidth, worldHeight, camera);

    public RenderSystem() {

    }

    public ViewController viewController = new ViewController(this);


    @Override
    public void init() {
        systemManager.inputSystem.inputStack.stack.addFirst(viewController);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
    }


    @Override
    public void update(float delta) {
        render(delta);
        viewController.update(delta);

    }

    public void render(float delta) {
        camera.update();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        renderers.forEach(Renderer::render);
    }

    @Override
    public void dispose() {
        renderers.forEach(Renderer::depose);
    }

}
