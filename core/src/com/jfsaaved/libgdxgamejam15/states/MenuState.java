package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class MenuState extends State{

    private TextureRegion background;
    private TextImage title;
    private TextImage start;
    private TextImage load;

    private PointerImage pointer;

    public MenuState(GSM gsm){
        super(gsm);

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        title = new TextImage("SPACE HUNTER", 300, 400, 5);
        title.shiftHalfLeft();
        start = new TextImage("START GAME", 300, 300, 3);
        start.shiftHalfLeft();
        load = new TextImage("LOAD GAME", 300, 250, 3);
        load.shiftHalfLeft();

        pointer = new PointerImage(start.getTextX(), start.getTextY(), (int) start.getTextHeight(), 3, 1);
        pointer.setX(pointer.getX() - pointer.getWidth());
    }

    @Override
    protected void handleInput(float dt){
        pointer.handleInput();
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            if(pointer.getOption() == 0)
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
        sb.draw(background, background.getRegionWidth(), 0);
        sb.draw(background, 0 - background.getRegionWidth(), 0);
        title.drawText(sb);
        start.drawText(sb);
        load.drawText(sb);
        pointer.drawPointer(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr){
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        start.drawTextBox(sr);
        load.drawTextBox(sr);
        pointer.drawPointerBox(sr);
        sr.end();
    }

}
