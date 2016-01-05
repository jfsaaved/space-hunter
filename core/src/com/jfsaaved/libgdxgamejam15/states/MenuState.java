package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.ui.TextBox;

/**
 * Created by 343076 on 30/12/2015.
 */
public class MenuState extends State{

    private TextBox start;

    public MenuState(GSM gsm){
        super(gsm);

        start = new TextBox("START", 0, 0, 3);
    }

    @Override
    protected void handleInput(float dt){
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
            this.gsm.set(new ShipState(gsm));
        }
    }

    @Override
    protected void update(float dt){
        handleInput(dt);
    }

    @Override
    protected void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        start.render(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr){
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        start.drawBox(sr);
        sr.end();
    }

}
