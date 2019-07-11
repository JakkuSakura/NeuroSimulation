package com.jeekrs.neuro_simulation.system;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.jeekrs.neuro_simulation.entities.Ant;
import com.jeekrs.neuro_simulation.entities.Entity;

import static com.jeekrs.neuro_simulation.GameScreen.systemManager;

public class AntRenderer extends Renderer {
    private Texture texture = new Texture(Gdx.files.internal("ant64.png"));
    private Sprite sprite = new Sprite(texture);
    private SpriteBatch batch = new SpriteBatch();

    @Override
    public void render() {
        batch.setProjectionMatrix(systemManager.renderSystem.camera.combined);
        batch.begin();
        int count = 0;
        for (Entity e : systemManager.entitySystem.entities) {
            if (e instanceof Ant) {
                Ant ant = (Ant) e;
                sprite.setRotation(ant.getDirection());
                sprite.setPosition(ant.getPos().x - ant.getRadius(), ant.getPos().y - ant.getRadius());
                sprite.draw(batch);
                TextHelper.printf(batch, ant.getPos().x - 30, ant.getPos().y + 55, "%s", ant.toString());
                count += 1;
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
    public void depose() {
        texture.dispose();
        batch.dispose();
    }
}
