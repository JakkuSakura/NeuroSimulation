package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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

    public SpriteBatch batch = new SpriteBatch();
    public ShapeRenderer shapeRenderer = new ShapeRenderer();

    public RenderSystem() {

    }

    public ViewController viewController = new ViewController(this);
    public void init() {
        systemManager.inputSystem.inputStack.stack.addFirst(viewController);
    }


    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void addRenderer(Renderer renderer) {
        renderers.add(renderer);
        bind(renderer);
    }

    private void bind(Renderer renderer) {
        renderer.setSystem(this);
    }


    @Override
    public void update(float delta) {
        render(delta);
        viewController.update(delta);

    }

    public void render(float delta) {
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        renderers.forEach(Renderer::render);
    }

    @Override
    public void dispose() {
        renderers.forEach(Renderer::depose);
    }

}
