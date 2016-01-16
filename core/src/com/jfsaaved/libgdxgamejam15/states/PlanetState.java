package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.options.PlanetOptions;
import com.jfsaaved.libgdxgamejam15.options.ShipOptions;
import com.jfsaaved.libgdxgamejam15.ui.BorderImage;
import com.jfsaaved.libgdxgamejam15.ui.DayImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.StatusImages;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 10/01/2016.
 */
public class PlanetState extends State {

    // Backgrounds
    private Texture background;
    private TextureRegion space;
    private float spaceX;
    private TextureRegion ground;

    // U.I.
    private PlanetOptions planetOptions;

    public PlanetState(GSM gsm){
        super(gsm);

        hero.setPosition(Main.WIDTH/2, Main.HEIGHT/2);

        // Images
        this.background = new Texture(Gdx.files.internal("bg.png"));
        this.space = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        this.ground = new TextureRegion(Main.resources.getAtlas("assets").findRegion("ground1"));

        // Camera initializations
        camX = hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Day image
        dayImage = new DayImage(cam,days);

        // Options
        // Optimal size "       OPTIONS"
        String[] options = {"EXPLORE","HUNT","CAMP","SHIP","OPTIONS"};
        menuImages = new MenuImages(this.cam, options);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"YOU HAVE LANDED AT BLACK MAMBA 24"};
        dialogueImages = new DialogueImages(this.cam, dialogue);

        // Notification images
        String[] notification = {"SUCCESS","+20% HUNGER","NO ENERGY","NO FUEL"};
        notificationImages = new NotificationImages(cam, notification);
        notificationImages.setHide(true);

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

        planetOptions = new PlanetOptions(this);
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
            planetOptions.setHoverDesc(menuImages.getOption());
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            planetOptions.pushOption(menuImages.getOption());
            planetOptions.handleInput();
            planetOptions.setHoverDesc(menuImages.getOption());
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

        checkGameOver(dt);
        checkBackground(dt);

        hero.update(dt);
        menuImages.update(dt);
        dialogueImages.update(dt);
        notificationImages.update(dt);
        statusImages.update(dt);
        dayImage.update(dt, days);

    }

    public void checkBackground(float dt){
        if(spaceX < space.getRegionWidth())
            spaceX += 5f * dt;
        else
            spaceX = 0;
    }

    public void checkGameOver(float dt){
        if(hero.getHunger() <= 0)
            getGSM().set(new GameOverState(getGSM()));
        if(hero.getHealth() <= 0)
            getGSM().set(new GameOverState(getGSM()));
        if(ship.getFuel() <= 0)
            getGSM().set(new GameOverState(getGSM()));
        if(ship.getHealth() <= 0)
            getGSM().set(new GameOverState(getGSM()));
        if(hero.getEnergy() <= 0) {
            hero.setEnergy(50);
            hero.setHunger(hero.getHunger() - 20);

            String[] notification = {"YOU PASSED OUT","ENERGY     %"+hero.getEnergy(),"HUNGER     %"+hero.getHunger()};
            notificationImages = new NotificationImages(getCam(),notification);
            statusImages.setStatsEnergy(hero.getEnergy());
            statusImages.setStatsHunger(hero.getHunger());

            useTurn(4);
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(space, spaceX, hero.getY());
        sb.draw(space, spaceX - space.getRegionWidth(), hero.getY());
        sb.draw(space, spaceX + space.getRegionWidth(), hero.getY());

        sb.draw(background, 0, hero.getY());
        sb.draw(background, background.getWidth(), hero.getY());
        sb.draw(ground, 0, hero.getY() - 30);
        sb.draw(ground, ground.getRegionWidth(), hero.getY() - 30);
        sb.draw(ground, 2 * ground.getRegionWidth(), hero.getY() - 30);

        menuImages.drawMenu(sb);
        dialogueImages.drawDialogue(sb);
        notificationImages.drawNotification(sb);
        statusImages.drawStatus(sb);
        dayImage.drawDay(sb);

        hero.draw(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);

        menuImages.drawMenuBox(sr);
        dialogueImages.drawDialogueBox(sr);
        notificationImages.drawNotificationBox(sr);
        statusImages.drawStatusBox(sr);
        dayImage.drawDayBox(sr);

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
