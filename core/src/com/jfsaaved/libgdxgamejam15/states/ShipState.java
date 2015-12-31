package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    private Hero hero;

    public ShipState(GSM gsm) {
        super(gsm);
        this.hero = new Hero(0, 0, 100, 100, Main.resources.getAtlas("assets").findRegion("player"));
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
        hero.draw(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);
        sr.end();
    }

}
