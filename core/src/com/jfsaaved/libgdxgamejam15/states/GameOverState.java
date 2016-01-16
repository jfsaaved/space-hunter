package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 15/01/2016.
 */
public class GameOverState extends State {

    private TextureRegion background;
    private TextImage title;

    public GameOverState(GSM gsm){
        super(gsm);

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        title = new TextImage("GAME OVER", 300, 400, 5, 0);
        title.shiftHalfLeft();
    }

    @Override
    protected void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            this.gsm.set(new MenuState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        title.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(background, background.getRegionWidth(), 0);
        sb.draw(background, 0 - background.getRegionWidth(), 0);
        title.drawText(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        title.drawTextBox(sr);
        sr.end();
    }
}
