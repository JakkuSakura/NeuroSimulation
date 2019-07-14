package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.nest.AntFighterNest;
import com.jeekrs.neuro_simulation.entities.nest.AntWorkerNest;
import com.jeekrs.neuro_simulation.entities.nest.Nest;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class NestRenderer extends Renderer {
    private Texture antFighterTexture = new Texture(Gdx.files.internal("AntNest1.png"));
    private Sprite antFighterSprite = new Sprite(antFighterTexture);

    private Texture antWorkerTexture = new Texture(Gdx.files.internal("AntNest2.png"));
    private Sprite antWorkerSprite = new Sprite(antWorkerTexture);
    public SpriteBatch batch = new SpriteBatch();

    @Override
    public void render() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Nest) {
                Nest n = (Nest) entity;
                renderNest(n);
            }
        }
        batch.end();
    }

    public void renderNest(Nest n) {
        if (n instanceof AntWorkerNest)
        {
            Rectangle rect = n.getRectangle();
            batch.draw(antWorkerSprite, rect.x, rect.y, rect.width, rect.height);
        } else if (n instanceof AntFighterNest)
        {
            Rectangle rect = n.getRectangle();
            batch.draw(antFighterSprite, rect.x, rect.y, rect.width, rect.height);
        }
    }
}
