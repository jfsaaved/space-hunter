package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.options.Options;
import com.jfsaaved.libgdxgamejam15.options.ShipOptions;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.StatusImages;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

import java.util.ArrayList;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Space background
    private TextureRegion background;

    // Objects
    public Hero hero;
    public Ship ship;

    // U.I.
    public MenuImages menuImages;
    public DialogueImages dialogueImages;
    public StatusImages statusImages;
    public ShipOptions shipOptions;

    public ShipState(GSM gsm) {
        super(gsm);

        this.ship = new Ship(Main.WIDTH/2 - 150, Main.HEIGHT/2, 300, 150);
        this.hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        this.hero.setBoundaries(ship.getX(), ship.getX() + ship.getWidth());
        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));

        // Camera initializations
        camX = this.hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Menu image
        // Optimal size "       OPTIONS"
        String[] options = {"NAVIGATE","HERO","SHIP","LAND","OPTIONS"};
        menuImages = new MenuImages(cam, options);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"Navigate to different systems."};
        dialogueImages = new DialogueImages(cam, dialogue);

        // Status images
        // int health, int hunger, int energy, int hunter, int explorer, int mechanic, int shipHealth, int shipFuel, int shipLevel
        statusImages = new StatusImages(
                cam,hero.getHealth(),
                hero.getHunger(),
                hero.getEnergy(),
                hero.getHunter(),
                hero.getExplorer(),
                hero.getMechanic(),
                ship.getHealth(),
                ship.getFuel(),
                ship.getLevel(),
                hero.getFood(),
                hero.getArtifacts(),
                hero.getGold());

        // Create an Options object that will handle U.I. stuff
        shipOptions = new ShipOptions(this);
    }

    @Override
    protected void handleInput(float dt) {
        hero.handleInput(dt);

        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            menuImages.handleInput();
            shipOptions.setHoverDesc(menuImages.getOption());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            shipOptions.pushOption(menuImages.getOption());
            shipOptions.setHoverDesc(menuImages.getOption());
            shipOptions.handleInput();
            statusImages.changeStats(hero.getHealth(),
                    hero.getHunger(),
                    hero.getEnergy(),
                    hero.getHunter(),
                    hero.getExplorer(),
                    hero.getMechanic(),
                    ship.getHealth(),
                    ship.getFuel(),
                    ship.getLevel(),
                    hero.getFood(),
                    hero.getArtifacts(),
                    hero.getGold());
        }
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        hero.update(dt);
        menuImages.update(dt);
        dialogueImages.update(dt);
        statusImages.update(dt);
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
        statusImages.drawStatus(sb);

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);
        ship.drawBox(sr);

        menuImages.drawMenuBox(sr);
        dialogueImages.drawDialogueBox(sr);
        statusImages.drawStatusBox(sr);

        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
