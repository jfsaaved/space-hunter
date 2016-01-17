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
import com.jfsaaved.libgdxgamejam15.ui.DayImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.StatusImages;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by 343076 on 30/12/2015.
 */
public class ShipState extends State{

    // Space background
    private TextureRegion background;
    private float backgroundX;

    // U.I.
    public ShipOptions shipOptions;

    // Travelling


    public ShipState(GSM gsm) {
        super(gsm);

        music.loadAtlas("SHIP.mp3","ship");
        music.getAtlas("ship").setLooping(true);
        music.getAtlas("ship").play();
        hero.setPosition(Main.WIDTH/2, Main.HEIGHT/2 + 3);

        // Objects stuff
        hero.setBoundaries(ship.getX(), ship.getX() + ship.getWidth());

        // Background stuff
        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        backgroundX = 0;

        // Camera initializations
        camX = hero.getX();
        camY = hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        // Day image
        dayImage = new DayImage(cam,days);

        // Menu image
        // Optimal size "       OPTIONS"
        String[] options = {"NAVIGATE","HERO","SHIP","LAND","OPTIONS"};
        menuImages = new MenuImages(cam, options);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"NAVIGATE THROUGH THE GALAXY TO DIFFERENT SYSTEMS"};
        dialogueImages = new DialogueImages(cam, dialogue);

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
            Main.sound.getAtlas("fail").play();
            shipOptions.pushOption(menuImages.getOption());
            shipOptions.handleInput();
            shipOptions.setHoverDesc(menuImages.getOption());
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
        checkTravel(dt);

        hero.update(dt);
        menuImages.update(dt);
        dialogueImages.update(dt);
        notificationImages.update(dt);
        statusImages.update(dt);
        dayImage.update(dt, days);
    }

    public void checkGameOver(float dt){
        boolean gameOver = false;
        if(hero.getHunger() <= 0)
            gameOver = true;
        if(hero.getHealth() <= 0)
            gameOver = true;
        if(ship.getFuel() <= 0)
            gameOver = true;
        if(ship.getHealth() <= 0)
            gameOver = true;

        if(hero.getEnergy() <= 0) {
            hero.setEnergy(50);
            hero.setHunger(hero.getHunger() - 20);

            String[] notification = {"YOU PASSED OUT","ENERGY     %"+hero.getEnergy(),"HUNGER     %"+hero.getHunger()};
            notificationImages = new NotificationImages(getCam(),notification);
            statusImages.setStatsEnergy(hero.getEnergy());
            statusImages.setStatsHunger(hero.getHunger());

            shipOptions.calculateTravelingTurns(4);
        }

        if(gameOver){
            music.getAtlas("ship").stop();
            music.getAtlas("ship").dispose();
            getGSM().set(new GameOverState(getGSM()));
        }
    }

    public void checkTravel(float dt){
        if(travelTime > 0f) {
            travelTime -= 100f * dt;
            if(currentSystem == 0) {
                if (backgroundX < background.getRegionWidth())
                    backgroundX += 100f * dt;
                else
                    backgroundX = 0;
            }else{
                if (backgroundX > 0)
                    backgroundX -= 100f * dt;
                else
                    backgroundX = background.getRegionWidth();
            }
        } else {
            if(currentSystem != nextSystem) {
                currentSystem = nextSystem;
                ship.setFuel(ship.getFuel() - 20);
                ship.setHealth(ship.getHealth() - 20);
                String[] notification = {"ARRIVED AT","SYSTEM "+(currentSystem+1),"-20% FUEL","-20% SHIP HP"};
                notificationImages = new NotificationImages(cam,notification);
                Main.sound.getAtlas("fail").stop();
                Main.sound.getAtlas("fail").play();
                statusImages.setStatsShipFuel(ship.getFuel());
                statusImages.setStatsShipHealth(ship.getHealth());
            }

            if(currentSystem == 1) {
                if (backgroundX < background.getRegionWidth())
                    backgroundX += 5f * dt;
                else
                    backgroundX = 0;
            }else{
                if (backgroundX > 0)
                    backgroundX -= 5f * dt;
                else
                    backgroundX = background.getRegionWidth();
            }
        }
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, backgroundX, 0);
        sb.draw(background, backgroundX + background.getRegionWidth(), 0);
        sb.draw(background, backgroundX - background.getRegionWidth(), 0);
        ship.draw(sb);
        hero.draw(sb);

        menuImages.drawMenu(sb);
        dialogueImages.drawDialogue(sb);
        notificationImages.drawNotification(sb);
        statusImages.drawStatus(sb);
        dayImage.drawDay(sb);

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
        notificationImages.drawNotificationBox(sr);
        statusImages.drawStatusBox(sr);
        dayImage.drawDayBox(sr);

        sr.end();
    }

    @Override
    protected void resize(int width, int height) {
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);
    }

    public boolean travelTo(int i){
        boolean success = false;
        if(currentSystem != i) {
            travelTime = 1000f;
            nextSystem = i;
            success = true;
        }
        return success;
    }

    public int getCurrentSystem(){
        return currentSystem;
    }

    public void setTravelTime(float time){
        travelTime = time;
    }

    public float getTravelTime(){
        return travelTime;
    }

    public void changeTravelTime(float time){
        travelTime += time;
    }

}
