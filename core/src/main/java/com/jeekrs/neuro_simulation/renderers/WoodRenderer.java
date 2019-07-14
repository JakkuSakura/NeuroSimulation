package com.jeekrs.neuro_simulation.renderers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.jeekrs.neuro_simulation.entities.Entity;
import com.jeekrs.neuro_simulation.entities.functionals.Wood;
import com.jeekrs.neuro_simulation.system.Renderer;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class WoodRenderer extends Renderer {
    private Texture BaseCampTexture = new Texture(Gdx.files.internal("Wood.png"));
    private Sprite BaseCampSprite = new Sprite(BaseCampTexture);

    public SpriteBatch batch = new SpriteBatch();

    @Override
    public void render() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        for (Entity entity : systemManager.entitySystem.entities) {
            if (entity instanceof Wood) {
                Wood n = (Wood) entity;
                renderWood(n);
            }
        }
        batch.end();
    }

    public void renderWood(Wood n) {

        Rectangle rect = n.getRectangle();
        batch.draw(BaseCampSprite, rect.x, rect.y, rect.width, rect.height);

    }
}
