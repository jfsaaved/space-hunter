package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class MenuState extends State{

    private TextureRegion background;
    private TextImage title;
    private TextImage start;
    private TextImage load;

    public MenuState(GSM gsm){
        super(gsm);

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        title = new TextImage("SPACE HUNTER", 300, 400, 5);
        title.shiftHalfLeft();
        start = new TextImage("START GAME", 300, 300, 3);
        start.shiftHalfLeft();
        load = new TextImage("LOAD GAME", 300, 250, 3);
        load.shiftHalfLeft();
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
        sb.draw(background, 0, 0);
        title.drawText(sb);
        start.drawText(sb);
        load.drawText(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr){
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        start.drawTextBox(sr);
        load.drawTextBox(sr);
        sr.end();
    }

}
