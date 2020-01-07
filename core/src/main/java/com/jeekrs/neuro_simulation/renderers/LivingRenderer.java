package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.jeekrs.neuro_simulation.components.abilities.Fighting;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.components.data.Rotation;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.populations.Population;

import java.util.Map;

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
        for (Entity e : systemManager.entitySystem.populations.get(Ant.class).getEntities()) {
            ++count;
            Ant s = (Ant) e;
            Position pos = Position.getPosition(s);
            if (!systemManager.renderSystem.inView(pos)) continue;

            AntSprite.setRotation(e.getComponentByClass(Rotation.class).rotation - 90);
            AntSprite.setPosition(pos.x - s.radius, pos.y - s.radius);
            AntSprite.draw(batch);

            if (count > 20) {
                batch.end();
                batch.begin();
                count = 0;
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
        for (Map.Entry<Class, Population> entry : systemManager.entitySystem.populations.entrySet()) {
            Population v = entry.getValue();
            for (Entity e : v.getEntities()) {
                if (e.hasComponentByClass(Fighting.class)) {
                    Position pos = Position.getPosition(e);
                    if (!systemManager.renderSystem.inView(pos)) continue;

                    Fighting fighting = e.getComponentByClass(Fighting.class);
                    solidRenderer.setColor(0, 0.8f, 0, 1);
                    solidRenderer.rect(pos.x - 30, pos.y + 50, 60 * fighting.health / fighting.health_limit, 15);
                    lineRenderer.rect(pos.x - 30, pos.y + 50, 60, 15);

                    count += 1;
                    if (count > 20) {
                        lineRenderer.end();
                        lineRenderer.begin(Line);
                        count = 0;
                    }
                }
            }

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
