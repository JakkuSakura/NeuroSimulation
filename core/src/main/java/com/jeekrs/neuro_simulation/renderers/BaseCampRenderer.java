package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.BaseCamp;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class BaseCampRenderer extends Renderer {
    private Texture BaseCampTexture = new Texture(Gdx.files.internal("BaseCamp.png"));
    private Sprite BaseCampSprite = new Sprite(BaseCampTexture);

    public SpriteBatch batch = new SpriteBatch();

    @Override
    public void render() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof BaseCamp) {
                BaseCamp n = (BaseCamp) entity;
                renderBaseCamp(n);
            }
        }
        batch.end();
    }

    public void renderBaseCamp(BaseCamp n) {

        Rectangle rect = n.getRectangle();
        batch.draw(BaseCampSprite, rect.x, rect.y, rect.width, rect.height);

    }
}
