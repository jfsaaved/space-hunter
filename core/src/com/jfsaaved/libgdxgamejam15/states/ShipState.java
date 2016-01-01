package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.NPC;
import com.jfsaaved.libgdxgamejam15.objects.Ship;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Space background
    private TextureRegion background;

    private Ship ship;
    private Hero hero;
    private NPC android;

    public ShipState(GSM gsm) {
        super(gsm);

        this.hero = new Hero(Main.WIDTH/2 - 18, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        this.android = new NPC(Main.WIDTH/2 - 100, Main.HEIGHT/2, 32, 32, Main.resources.getAtlas("assets").findRegion("blob1"));
        this.ship = new Ship(Main.WIDTH/2 - 150, Main.HEIGHT/2, 300, 150);
        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));

        cam.setToOrtho(false, 400, 400);
        cam.position.set(Main.WIDTH/2, this.hero.getY() + 50, 0);
        cam.update();

        this.hero.setBoundaries(this.ship.getX(), this.ship.getX() + 300);
    }

    @Override
    protected void handleInput(float dt) {
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }

        hero.handleInput(dt);
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        hero.update(dt);
        android.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background,0,100);
        hero.draw(sb);
        android.draw(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);
        android.drawBox(sr);
        ship.drawBox(sr);
        sr.end();
    }

}
