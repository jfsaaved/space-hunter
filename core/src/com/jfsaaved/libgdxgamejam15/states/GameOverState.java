package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

import java.util.ArrayList;

/**
 * Created by 343076 on 15/01/2016.
 */
public class GameOverState extends State {

    private TextureRegion background;
    private TextImage title;

    public GameOverState(GSM gsm){
        super(gsm);

        hero.setPosition(Main.WIDTH/2, Main.HEIGHT/2 + 3);
        camX = hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("space"));
        title = new TextImage("GAME OVER", Main.WIDTH/2, Main.HEIGHT/2 + 150, 3, 0);
        title.shiftHalfLeft();

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.

        ArrayList<String> reasons = new ArrayList<String>();
        int i = 0;

        if(hero.getHunger() <= 0) {
            reasons.add("YOU NEGLECTED YOUR HUNGER");
            i++;
        }
        if(hero.getHealth() <= 0){
            reasons.add("YOU NEGLECTED YOUR HEALTH");
            i++;
        }
        if(ship.getFuel() <= 0) {
            reasons.add("YOU NEGLECTED YOUR SHIP FUEL");
            i++;
        }
        if(ship.getHealth() <= 0) {
            reasons.add("YOU NEGLECTED YOUR SHIP HEALTH");
            i++;
        }

        String[] dialogue = new String[i];
        for(int index = 0; index < dialogue.length; index++)
            dialogue[index] = reasons.get(index);

        dialogueImages = new DialogueImages(this.cam, dialogue);


    }

    @Override
    protected void handleInput(float dt) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            Main.sound.getAtlas("fail").play();
            this.gsm.set(new MenuState(gsm));
        }
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        title.update(dt);
        dialogueImages.update(dt);
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(background, background.getRegionWidth(), 0);
        sb.draw(background, 0 - background.getRegionWidth(), 0);
        title.drawText(sb);
        dialogueImages.drawDialogue(sb);
        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        title.drawTextBox(sr);
        dialogueImages.drawDialogueBox(sr);
        sr.end();
    }
}
