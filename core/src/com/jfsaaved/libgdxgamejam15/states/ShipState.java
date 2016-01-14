package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.options.Options;
import com.jfsaaved.libgdxgamejam15.options.ShipOptions;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Space background
    private TextureRegion background;

    // Objects
    public Hero hero;

    // U.I.
    public MenuImages menuImages;
    public DialogueImages dialogueImages;
    public ShipOptions shipOptions;

    public ShipState(GSM gsm) {
        super(gsm);

        this.hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));

        // Camera initializations
        camX = this.hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Options
        // Optimal size "       OPTIONS"
        String[] options = {"NAVIGATE","SUPPLIES","MAINTENANCE","LAND","OPTIONS"};
        menuImages = new MenuImages(this.cam, options);
        shipOptions = new ShipOptions(this);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"YOU: So what exactly are you trying to do?", "ME: I have no idea."};
        dialogueImages = new DialogueImages(this.cam, dialogue);
    }

    @Override
    protected void handleInput(float dt) {
        hero.handleInput(dt);
        menuImages.handleInput();
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            shipOptions.pushOption(menuImages.getOption());
            shipOptions.handleInput();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        hero.update(dt);
        menuImages.update(dt);
        dialogueImages.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 100);
        sb.draw(background, background.getRegionWidth(), 100);
        hero.draw(sb);

        menuImages.drawMenu(sb);
        dialogueImages.drawDialogue(sb);

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);

        menuImages.drawMenuBox(sr);
        dialogueImages.drawDialogueBox(sr);

        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
