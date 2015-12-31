package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jfsaaved.libgdxgamejam15.Main;

/**
 * Created by 343076 on 30/12/2015.
 */
public class MenuState extends State{

    private Rectangle start;

    public MenuState(GSM gsm){
        super(gsm);
        start = new Rectangle(Main.WIDTH/2 - 50, Main.HEIGHT/2, 100, 100);
    }

    @Override
    public void handleInput(float dt){
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
            if(start.contains(mouse.x, mouse.y)){
                this.gsm.set(new ShipState(gsm));
            }
        }
    }

    @Override
    public void update(float dt){
        handleInput(dt);
    }

    @Override
    public void render(SpriteBatch sb){

    }
    @Override
    public void shapeRender(ShapeRenderer sr){
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.rect(start.getX(), start.getY(), start.getWidth(), start.getHeight());
        sr.end();
    }

}
