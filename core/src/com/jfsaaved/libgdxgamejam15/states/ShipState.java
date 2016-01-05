package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.NPC;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.ui.TextBox;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Logs
    private FPSLogger fps;

    // Text test
    private TextBox navigate;
    private TextBox supplies;
    private TextBox maintenance;
    private TextBox astromech;
    private TextBox options;

    // Space background
    private TextureRegion background;

    // Objects
    private Hero hero;

    public ShipState(GSM gsm) {
        super(gsm);

        this.hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));


        // Camera initializations
        camX = this.hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Options
        navigate = new TextBox("NAVIGATE", camX + 50, camY + 50, 1);
        supplies = new TextBox("SUPPLIES", camX + 50, navigate.getY() - navigate.getHeight() , 1);
        maintenance = new TextBox("MAINTENANCE", camX + 50, supplies.getY() - supplies.getHeight(), 1);
        astromech = new TextBox("ASTROMECH", camX + 50, maintenance.getY() - maintenance.getHeight(), 1);
        options = new TextBox("OPTIONS", camX + 50, astromech.getY() - astromech.getHeight(), 1);

        fps = new FPSLogger();

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

        fps.log();
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 100);
        sb.draw(background, background.getRegionWidth(), 100);
        hero.draw(sb);

        navigate.draw(sb);
        supplies.draw(sb);
        maintenance.draw(sb);
        astromech.draw(sb);
        options.draw(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);

        navigate.drawBox(sr);
        supplies.drawBox(sr);
        maintenance.drawBox(sr);
        astromech.drawBox(sr);
        options.drawBox(sr);
        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
