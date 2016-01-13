package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
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

    // Text
    private TextImage explore;
    private TextImage hunt;
    private TextImage camp;
    private TextImage astromech;
    private TextImage options;
    private TextImage dialogue;

    // Borders
    private BorderImage dialogueBorder;
    private BorderImage menuBorder;

    // Other assets
    private PointerImage pointer;

    // Objects
    private Hero hero;

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

        // We use 0.111f for scale since we want to turn 45 to 5, 45/9 = 5
        // Thus each tile of border image is 5px
        // Each unit of width and height corresponds to 1 tile of the border image, 1 unit = 5px
        dialogueBorder = new BorderImage((cam.position.x - cam.viewportWidth/2) + 5, cam.position.y - cam.viewportHeight/2 + 10, (int) (cam.viewportWidth/5) - 2, 20, 0.111f);
        menuBorder = new BorderImage(cam.position.x + (cam.viewportWidth/4), (cam.position.y + cam.viewportHeight/2) - 105, 31, 20, 0.111f);

        // Options
        options = new TextImage("       OPTIONS", menuBorder.getBorderX() + 7, menuBorder.getBorderY() + 10, 1);
        astromech = new TextImage("     ASTROMECH", options.getTextX(), options.getTextY() + options.getTextHeight(), 1);
        camp = new TextImage("          CAMP", options.getTextX(), astromech.getTextY() + astromech.getTextHeight(), 1);
        hunt = new TextImage("          HUNT", options.getTextX(), camp.getTextY() + camp.getTextHeight() , 1);
        explore = new TextImage("       EXPLORE", options.getTextX(), hunt.getTextY() + hunt.getTextHeight(), 1);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        dialogue = new TextImage("Hello World! This is a PlanetState.",
                dialogueBorder.getBorderX() + dialogueBorder.getBorderTileWidth(),
                dialogueBorder.getBorderY() + dialogueBorder.getBorderHeight() - (options.getTextHeight() + dialogueBorder.getBorderTileHeight()),
                1);

        // Others
        pointer = new PointerImage(explore.getTextX(), explore.getTextY(), (int) explore.getTextHeight(), 1, 4);

    }

    @Override
    protected void handleInput(float dt) {
        hero.handleInput(dt);
        pointer.handleInput();
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

        dialogueBorder.drawBorder(sb);
        menuBorder.drawBorder(sb);

        dialogue.drawText(sb);
        explore.drawText(sb);
        hunt.drawText(sb);
        camp.drawText(sb);
        astromech.drawText(sb);
        options.drawText(sb);

        hero.draw(sb);

        pointer.drawPointer(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);

        dialogueBorder.drawBorderBox(sr);
        menuBorder.drawBorderBox(sr);

        dialogue.drawTextBox(sr);
        explore.drawTextBox(sr);
        hunt.drawTextBox(sr);
        camp.drawTextBox(sr);
        astromech.drawTextBox(sr);
        options.drawTextBox(sr);

        hero.drawBox(sr);

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
