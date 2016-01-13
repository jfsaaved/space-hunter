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
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Logs
    private FPSLogger fps;

    // U.I.
    private MenuImages menuImages;
    private TextImage dialogue;

    // Borders
    private BorderImage dialogueBorder;
    private BorderImage menuBorder;

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
        // Optimal size "       OPTIONS"
        String[] options = {"       OPTIONS","     ASTROMECH","   MAINTENANCE","      SUPPLIES","      NAVIGATE"};
        menuImages = new MenuImages(menuBorder.getBorderX() + 7, menuBorder.getBorderY() + 10, 1, options);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        dialogue = new TextImage("Hello World! This is a ShipState.",
                dialogueBorder.getBorderX() + dialogueBorder.getBorderTileWidth(),
                dialogueBorder.getBorderY() + dialogueBorder.getBorderHeight() - (menuImages.getTextHeight() + dialogueBorder.getBorderTileHeight()),
                1);

        fps = new FPSLogger();
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
            if(menuImages.getOption() == 0)
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

        menuImages.drawMenu(sb);
        dialogue.drawText(sb);

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        hero.drawBox(sr);

        menuImages.drawMenuBox(sr);

        dialogueBorder.drawBorderBox(sr);
        menuBorder.drawBorderBox(sr);
        dialogue.drawTextBox(sr);

        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

}
