package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.NPC;

/**
 * Created by 343076 on 31/12/2015.
 */
public class PlanetState extends State{

    public PlanetState(GSM gsm){
        super(gsm);
    }

    @Override
    protected void handleInput(float dt) {

    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);

        sr.end();
    }
}
