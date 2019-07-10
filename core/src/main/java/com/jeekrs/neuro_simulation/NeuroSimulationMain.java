package com.jeekrs.neuro_simulation;

import com.badlogic.gdx.Game;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class NeuroSimulationMain extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());

    }

}