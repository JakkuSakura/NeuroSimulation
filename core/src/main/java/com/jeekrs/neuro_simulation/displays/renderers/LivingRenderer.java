package com.jeekrs.neuro_simulation.displays.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jeekrs.neuro_simulation.components.abilities.Fighting;
import com.jeekrs.neuro_simulation.components.data.Position;
import com.jeekrs.neuro_simulation.components.data.Rotation;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.populations.Population;
import com.jeekrs.neuro_simulation.utils.TextHelper;

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
        for (Entity e : systemManager.worldSystem.populations.get(Ant.class).getEntities()) {
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
        drawStatus();
    }

    private void drawStatus() {
        lineRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        lineRenderer.setColor(0, 0, 0, 1);
        lineRenderer.begin(Line);

        solidRenderer.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        solidRenderer.begin(Filled);

        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        TextHelper.setLineHeight(TextHelper.font, 12);
        int count = 0;
        for (Population v : systemManager.worldSystem.populations.values()) {
            for (Entity e : v.getEntities()) {
                if (e.hasComponentByClass(Fighting.class)) {
                    Position pos = Position.getPosition(e);
                    if (!systemManager.renderSystem.inView(pos)) continue;

                    Fighting fighting = e.getComponentByClass(Fighting.class);
                    solidRenderer.setColor(0, 0.8f, 0, 1);
                    solidRenderer.rect(pos.x - 30, pos.y + 50, 60 * fighting.health / fighting.health_limit, 15);
                    lineRenderer.rect(pos.x - 30, pos.y + 50, 60, 15);

                    solidRenderer.setColor(0.8f, 0, 0, 1);
                    solidRenderer.rect(pos.x - 30, pos.y + 70, 60 * fighting.energy / fighting.energy_limit, 15);
                    lineRenderer.rect(pos.x - 30, pos.y + 70, 60, 15);
                    if (e instanceof Ant)
                        TextHelper.printf(batch, pos.x - 30, pos.y + 100, "Nest %d", ((Ant) e).antNest.hashCode() % 10);
                    count += 1;
                    if (count > 20) {
                        lineRenderer.end();
                        lineRenderer.begin(Line);
                        solidRenderer.end();
                        solidRenderer.begin(Filled);
                        batch.end();
                        batch.begin();
                        count = 0;
                    }
                }
            }

        }
        batch.end();
        lineRenderer.end();
        solidRenderer.end();
    }

    @Override
    public void dispose() {
        AntTexture.dispose();
        batch.dispose();
    }
}
