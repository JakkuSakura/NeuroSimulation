package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.components.Position;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Swarm;

import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled;
import static com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line;
import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class LivingRenderer extends Renderer {
    private Texture AntTexture = new Texture(Gdx.files.internal("ant64.png"));
    private Sprite AntSprite = new Sprite(AntTexture);

    private SpriteBatch batch = new SpriteBatch();
    private ShapeRenderer lineRenderer = new ShapeRenderer();
    private ShapeRenderer solidRenderer = new ShapeRenderer();

    private void drawLivings() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        int count = 0;
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Swarm) {
                Swarm s = (Swarm)e;
                ++count;
                Position pos = s.getPos();
                batch.draw(AntSprite, pos.x - s.radius, pos.y - s.radius);

                if (count > 20) {
                    batch.end();
                    batch.begin();
                    count = 0;
                }
            }
        }
        batch.end();

    }

    @Override
    public void render() {
        drawLivings();
        drawLives();
    }

    private void drawLives() {
        lineRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        lineRenderer.setColor(0, 0, 0, 1);
        lineRenderer.begin(Line);

        solidRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        solidRenderer.begin(Filled);
        int count = 0;
        for (Entity e : systemManager.entitySystem.entities) {
            // todo
//            if (e instanceof Entity) {
//                lineRenderer.rect(e.getPos().x - 30, e.getPos().y + 50, 60, 15);
//
//                count += 1;
//                if (count > 20) {
//                    lineRenderer.end();
//                    lineRenderer.begin(Line);
//                    count = 0;
//                }
//            }
        }
        lineRenderer.end();
        solidRenderer.end();
    }

    @Override
    public void dispose() {
        AntTexture.dispose();
        batch.dispose();
    }
}
