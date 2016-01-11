package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;

/**
 * Created by 343076 on 10/01/2016.
 */
public class PlanetState extends State {

    private Texture background;

    // Objects
    private Hero hero;

    public PlanetState(GSM gsm){
        super(gsm);

        this.hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        background = new Texture(Gdx.files.internal("bg.png"));

        // Camera initializations
        camX = this.hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

    }

    @Override
    protected void handleInput(float dt) {

    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        hero.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, hero.getY());
        sb.draw(background, background.getWidth(), hero.getY());
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
