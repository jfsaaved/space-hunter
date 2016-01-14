package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.options.ShipOptions;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 10/01/2016.
 */
public class PlanetState extends State {

    // Backgrounds
    private Texture background;
    private TextureRegion space;
    private TextureRegion ground;

    // Objects
    private Hero hero;

    // U.I.
    public MenuImages menuImages;
    public DialogueImages dialogueImages;

    public PlanetState(GSM gsm){
        super(gsm);

        // Images
        this.hero = new Hero(Main.WIDTH/2, Main.HEIGHT/2, 36, 54, Main.resources.getAtlas("assets").findRegion("player"));
        this.background = new Texture(Gdx.files.internal("bg.png"));
        this.space = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        this.ground = new TextureRegion(Main.resources.getAtlas("assets").findRegion("ground1"));

        // Camera initializations
        camX = this.hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Options
        // Optimal size "       OPTIONS"
        String[] options = {"NAVIGATE","SUPPLIES","MAINTENANCE","ASTROMECH","OPTIONS"};
        menuImages = new MenuImages(this.cam, options);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"You have landed at some planet."};
        dialogueImages = new DialogueImages(this.cam, dialogue);
    }

    @Override
    protected void handleInput(float dt) {
        hero.handleInput(dt);
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
        sb.draw(space, 0, hero.getY());
        sb.draw(space, space.getRegionWidth(), hero.getY());
        sb.draw(background, 0, hero.getY());
        sb.draw(background, background.getWidth(), hero.getY());
        sb.draw(ground, 0, hero.getY() - 30);
        sb.draw(ground, ground.getRegionWidth(), hero.getY() - 30);
        sb.draw(ground, 0, hero.getY() - 30);
        sb.draw(ground, 2 * ground.getRegionWidth(), hero.getY() - 30);
        menuImages.drawMenu(sb);
        dialogueImages.drawDialogue(sb);

        hero.draw(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        menuImages.drawMenuBox(sr);
        dialogueImages.drawDialogueBox(sr);

        hero.drawBox(sr);
        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
