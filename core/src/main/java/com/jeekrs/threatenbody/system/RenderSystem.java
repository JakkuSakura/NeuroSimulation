package com.jeekrs.threatenbody.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;


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

    public boolean zoomin, zoomout;
    public float mvx, mvy;

    public void init() {
        addRenderer(new PlanetRenderer());
        systemManager.inputSystem.inputStack.stack.addFirst(new SimpleInputProcessor() {

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.J) {
                    zoomin = true;
                    return true;
                }
                if (keycode == Input.Keys.K) {
                    zoomout = true;
                    return true;
                }

                if (keycode == Input.Keys.W) {
                    mvy += 300;
                    return true;
                }

                if (keycode == Input.Keys.S) {
                    mvy += -300;
                    return true;
                }

                if (keycode == Input.Keys.A) {
                    mvx += -300;
                    return true;
                }

                if (keycode == Input.Keys.D) {
                    mvx += 300;
                    return true;
                }
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                if (keycode == Input.Keys.J) {
                    zoomin = false;
                    return true;
                }

                if (keycode == Input.Keys.K) {
                    zoomout = false;
                    return true;
                }
                if (keycode == Input.Keys.W) {
                    mvy -= 300;
                    return true;
                }

                if (keycode == Input.Keys.S) {
                    mvy -= -300;
                    return true;
                }

                if (keycode == Input.Keys.A) {
                    mvx -= -300;
                    return true;
                }

                if (keycode == Input.Keys.D) {
                    mvx -= 300;
                    return true;
                }

                return false;
            }

        });
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
        if (zoomin)
            camera.zoom *= 1 - 0.5 * delta;

        if (zoomout)
            camera.zoom *= 1 + 0.5 * delta;

        camera.translate(mvx * delta * camera.zoom, mvy * delta * camera.zoom, 0);

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
