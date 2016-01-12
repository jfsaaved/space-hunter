package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Logs
    private FPSLogger fps;

    // Text
    private TextImage navigate;
    private TextImage supplies;
    private TextImage maintenance;
    private TextImage astromech;
    private TextImage options;
    private TextImage dialogue;

    // Borders
    private BorderImage dialogueBorder;
    private BorderImage menuBorder;

    // Other assets
    private PointerImage pointer;

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

        // We use 0.111f for scale since we want to turn 45 to 5, 45/9 = 5
        // Thus each tile of border image is 5px
        // Each unit of width and height corresponds to 1 tile of the border image, 1 unit = 5px
        dialogueBorder = new BorderImage((cam.position.x - cam.viewportWidth/2) + 5, cam.position.y - cam.viewportHeight/2 + 10, (int) (cam.viewportWidth/5) - 2, 20, 0.111f);
        menuBorder = new BorderImage(cam.position.x + (cam.viewportWidth/4), (cam.position.y + cam.viewportHeight/2) - 105, 31, 20, 0.111f);

        // Options
        options = new TextImage("       OPTIONS", menuBorder.getBorderX() + 7, menuBorder.getBorderY() + 10, 1, "options");
        astromech = new TextImage("     ASTROMECH", options.getTextX(), options.getTextY() + options.getTextHeight(), 1, "astromech");
        maintenance = new TextImage("   MAINTENANCE", options.getTextX(), astromech.getTextY() + astromech.getTextHeight(), 1, "maintenance");
        supplies = new TextImage("      SUPPLIES", options.getTextX(), maintenance.getTextY() + maintenance.getTextHeight() , 1, "supplies");
        navigate = new TextImage("      NAVIGATE", options.getTextX(), supplies.getTextY() + supplies.getTextHeight(), 1, "navigate");

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        dialogue = new TextImage("Hello World! This is a ShipState.",
                dialogueBorder.getBorderX() + dialogueBorder.getBorderTileWidth(),
                dialogueBorder.getBorderY() + dialogueBorder.getBorderHeight() - (options.getTextHeight() + dialogueBorder.getBorderTileHeight()),
                1, "dialogue");

        // Others
        pointer = new PointerImage(navigate.getTextX(), navigate.getTextY(), (int) navigate.getTextHeight(), 1, 4);

        fps = new FPSLogger();
    }

    @Override
    protected void handleInput(float dt) {
        hero.handleInput(dt);
        pointer.handleInput();
        if(Gdx.input.justTouched()){
            mouse.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            cam.unproject(mouse);
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            if(pointer.getOption() == 0)
                this.gsm.set(new PlanetState(gsm));
        }
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

        dialogueBorder.drawBorder(sb);
        menuBorder.drawBorder(sb);

        navigate.drawText(sb);
        supplies.drawText(sb);
        maintenance.drawText(sb);
        astromech.drawText(sb);
        options.drawText(sb);
        dialogue.drawText(sb);

        pointer.drawPointer(sb);

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);

        navigate.drawTextBox(sr);
        supplies.drawTextBox(sr);
        maintenance.drawTextBox(sr);
        astromech.drawTextBox(sr);
        options.drawTextBox(sr);

        dialogueBorder.drawBorderBox(sr);
        menuBorder.drawBorderBox(sr);
        dialogue.drawTextBox(sr);

        pointer.drawPointerBox(sr);
        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
