package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Stack;

/**
 * Created by 343076 on 30/12/2015.
 */
public class GSM {

    private Stack<State> states;

    public GSM() {
        states = new Stack<State>();
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public void shapeRender(ShapeRenderer sr){
        states.peek().shapeRender(sr);
    }

    public void push(State state) {
        states.push(state);
    }

    public void pop(){
        states.pop();
    }

    public void set(State state){
        states.pop();
        states.push(state);
    }

}
