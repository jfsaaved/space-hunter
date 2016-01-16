package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.PointerImage;
import com.jfsaaved.libgdxgamejam15.ui.TextImage;

/**
 * Created by 343076 on 16/01/2016.
 */
public class OptionState extends State {

    private TextureRegion background;
    private float backgroundX;

    private TextImage title;
    private TextImage save;
    private TextImage showControls;
    private TextImage skills;
    private TextImage back;

    private PointerImage pointer;
    private DialogueImages dialogueImages;

    public OptionState(GSM gsm) {
        super(gsm);

        hero.setPosition(Main.WIDTH/2, Main.HEIGHT/2 + 3);
        camX = hero.getX();
        camY = this.hero.getY() + 50;
        this.updateCam(Main.WIDTH/2, Main.HEIGHT/2, camX, camY);

        this.background = new TextureRegion(Main.resources.getAtlas("assets").findRegion("tiled_bg"));

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        String[] dialogue = {"SAVE THE GAME","IT WILL OVERWRITE ANY OTHER SAVES"};
        dialogueImages = new DialogueImages(this.cam, dialogue);

        title = new TextImage("SETTINGS", Main.WIDTH/2, Main.HEIGHT/2 + 150, 3, 0);
        title.shiftHalfLeft();
        save= new TextImage("SAVE", Main.WIDTH/2, Main.HEIGHT/2 + 100, 2, 0);
        save.shiftHalfLeft();
        showControls = new TextImage("CONTROLS", Main.WIDTH/2, save.getTextY() - save.getTextHeight(), 2, 0);
        showControls.shiftHalfLeft();
        skills = new TextImage("SKILLS", Main.WIDTH/2, showControls.getTextY() - showControls.getTextHeight(), 2, 0);
        skills.shiftHalfLeft();
        back = new TextImage("BACK", Main.WIDTH/2, skills.getTextY() - skills.getTextHeight(),  2, 0);
        back.shiftHalfLeft();

        TextImage test = new TextImage("CONTROLS",Main.WIDTH/2, Main.HEIGHT/2 + 150,2,0);
        test.shiftHalfLeft();

        pointer = new PointerImage(test.getTextX(), save.getTextY(), (int) save.getTextHeight(), 2, 3);
        pointer.setX(pointer.getX() - pointer.getWidth());

    }

    @Override
    protected void handleInput(float dt) {
        pointer.handleInput();

        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) || Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(pointer.getOption() == 0){
                String[] dialogue = {"SAVE THE GAME","IT WILL OVERWRITE ANY OTHER SAVES"};
                dialogueImages = new DialogueImages(this.cam, dialogue);
            }
            else if(pointer.getOption() == 1){
                String[] dialogue = {"PRESS UP OR DOWN TO MOVE CURSOR","PRESS LEFT OR RIGHT TO MOVE HERO","PRESS Z TO PICK OPTION"};
                dialogueImages = new DialogueImages(this.cam, dialogue);
            }
            else if(pointer.getOption() == 2){
                String[] dialogue = {"SKILLS HELP","EARN FOOD AND EARN HUNTER BENEFITS AFTER HUNTER 15+","EARN MORE ARTIFACTS WITH EXPLORER BENEFITS AFTER EXPLORER 10+","GAIN BETTER MAINTENANCE RESULTS AFTER MECHANIC 5+"};
                dialogueImages = new DialogueImages(this.cam, dialogue);
            }
            else if(pointer.getOption() == 3){
                String[] dialogue = {"GO BACK"};
                dialogueImages = new DialogueImages(this.cam, dialogue);
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            if(pointer.getOption() == 0) {
                preferences.save(hero, ship, this);
                String[] dialogue = {"SAVED!"};
                dialogueImages = new DialogueImages(this.cam, dialogue);
            }
            else if(pointer.getOption() == 3)
                this.gsm.pop();
        }
    }

    @Override
    protected void update(float dt) {
        handleInput(dt);
        checkBackground(dt);

        title.update(dt);
        save.update(dt);
        showControls.update(dt);
        skills.update(dt);
        back.update(dt);
        dialogueImages.update(dt);
    }

    public void checkBackground(float dt){
        if(backgroundX < background.getRegionWidth())
            backgroundX += 10f * dt;
        else
            backgroundX = 0;
    }

    @Override
    protected void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, backgroundX, - 50);
        sb.draw(background, backgroundX - background.getRegionWidth(), - 50);
        sb.draw(background, backgroundX + background.getRegionWidth(), - 50);

        title.drawText(sb);
        save.drawText(sb);
        showControls.drawText(sb);
        skills.drawText(sb);
        back.drawText(sb);
        dialogueImages.drawDialogue(sb);

        pointer.drawPointer(sb);

        sb.end();
    }

    @Override
    protected void shapeRender(ShapeRenderer sr) {
        sr.setProjectionMatrix(cam.combined);
        sr.begin(ShapeRenderer.ShapeType.Line);
        title.drawTextBox(sr);
        save.drawTextBox(sr);
        showControls.drawTextBox(sr);
        back.drawTextBox(sr);
        skills.drawTextBox(sr);
        dialogueImages.drawDialogueBox(sr);

        pointer.drawPointerBox(sr);
        sr.end();
    }

}
