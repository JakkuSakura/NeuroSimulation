package com.jeekrs.neuro_simulation.system;


import com.badlogic.gdx.Gdx;

public class InputSystem extends SimpleSystem{
    public InputStack inputStack = new InputStack();
    public Picker picker = new Picker();
    public EscExit escExit = new EscExit();

    @Override
    public void init() {
        inputStack.stack.addFirst(picker);
        inputStack.stack.addFirst(escExit);
        Gdx.input.setInputProcessor(inputStack);
    }



}
