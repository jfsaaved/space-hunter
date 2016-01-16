package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.handler.MainPreferences;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.DayImage;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class MenuState extends State{

    private TextureRegion background;
    private float backgroundX;
    private TextImage title;
    private TextImage start;
    private TextImage load;

    private PointerImage pointer;

    public MenuState(GSM gsm){
        super(gsm);

        music.loadAtlas("MENU.mp3","menu");
        music.getAtlas("menu").setLooping(true);
        music.getAtlas("menu").play();

        days = 0;
        turns = 4;
        currentSystem = 0;
        travelTime = 0f;
        nextSystem = 0;

        ship = new Ship(Main.WIDTH/2 - 150, Main.HEIGHT/2, 300, 150);
        hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        title = new TextImage("SPACE HUNTER", 300, 400, 5, 0);
        title.shiftHalfLeft();
        start = new TextImage("NEW GAME", 300, 300, 3, 0);
        start.shiftHalfLeft();
        load = new TextImage("LOAD GAME", 300, 250, 3, 0);
        load.shiftHalfLeft();

        pointer = new PointerImage(load.getTextX(), start.getTextY(), (int) start.getTextHeight(), 3, 1);
        pointer.setX(pointer.getX() - pointer.getWidth());

        preferences = new MainPreferences();

    }

    @Override
    protected void handleInput(float dt){
        pointer.handleInput();
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            Main.sound.getAtlas("fail").play();
            music.getAtlas("menu").stop();
            music.getAtlas("menu").dispose();
            if(pointer.getOption() == 0)
                this.gsm.set(new ShipState(gsm));
            else if(pointer.getOption() == 1){
                preferences.load(hero, ship, this);
                this.gsm.set(new ShipState(gsm));
            }
        }
    }

    @Override
    protected void update(float dt){
        handleInput(dt);
        checkBackground(dt);
        title.update(dt);
        start.update(dt);
        load.update(dt);
    }

    public void checkBackground(float dt){
        if(backgroundX < background.getRegionWidth())
            backgroundX += 10f * dt;
        else
            backgroundX = 0;
    }

    @Override
    protected void render(SpriteBatch sb){
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, backgroundX, -100);
        sb.draw(background, backgroundX + background.getRegionWidth(), -100);
        sb.draw(background, backgroundX - background.getRegionWidth(), -100);
        sb.draw(background, backgroundX - background.getRegionWidth() * 2, -100);
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
        title.drawTextBox(sr);
        start.drawTextBox(sr);
        load.drawTextBox(sr);
        pointer.drawPointerBox(sr);
        sr.end();
    }

}
