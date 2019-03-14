package com.jeekrs.threatenbody;

import com.badlogic.gdx.Game;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class ThreatenBodyMain extends Game {

    @Override
    public void create() {
        setScreen(new GameScreen());

    }

}