package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by 343076 on 13/01/2016.
 */
public class DialogueImages {

    private ArrayList<TextImage> dialogues;
    private BorderImage dialogueBorder;
    private int dialoguesSize;

    public DialogueImages(OrthographicCamera cam, String[] text){
        dialoguesSize = 0;

        // We use 0.111f for scale since we want to turn 45 to 5, 45/9 = 5
        // Thus each tile of border image is 5px
        // Each unit of width and height corresponds to 1 tile of the border image, 1 unit = 5px
        dialogueBorder = new BorderImage((cam.position.x - cam.viewportWidth/2) + 5, cam.position.y - cam.viewportHeight/2 + 10, (int) (cam.viewportWidth/5) - 2, 20, 0.111f);

        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        this.dialogues = new ArrayList<TextImage>();
        float newLineY = 0;
        for(String item : text) {
            TextImage dialogue = new TextImage(item,
                    dialogueBorder.getBorderX() + dialogueBorder.getBorderTileWidth(),
                    dialogueBorder.getBorderY() + dialogueBorder.getBorderHeight() - (16f + dialogueBorder.getBorderTileHeight()) - newLineY, 1, 1f);
            newLineY += dialogue.getTextHeight();
            dialogues.add(dialogue);
        }
    }

    public void setDialogues(String[] text){
        // Dialogue
        // Full width is Hello World! This is a testing. Check out my mixtape at ayyyy.
        this.dialoguesSize = 0;
        this.dialogues = new ArrayList<TextImage>();
        float newLineY = 0;
        for(String item : text) {
            TextImage dialogue = new TextImage(item,
                    dialogueBorder.getBorderX() + dialogueBorder.getBorderTileWidth(),
                    dialogueBorder.getBorderY() + dialogueBorder.getBorderHeight() - (16f + dialogueBorder.getBorderTileHeight()) - newLineY, 1, 1f);
            newLineY += dialogue.getTextHeight();
            dialogues.add(dialogue);
        }
    }

    public void update(float dt){
        for(int i = 0; i < dialoguesSize + 1; i++) {
            dialogues.get(i).update(dt);
            if(dialogues.get(i).isComplete())
                if(dialoguesSize < dialogues.size() - 1)
                    dialoguesSize++;
        }
    }

    public void drawDialogue(SpriteBatch sb){
        dialogueBorder.drawBorder(sb);
        for(int i = 0; i < dialoguesSize + 1; i++) {
            dialogues.get(i).drawText(sb);
            if(dialogues.get(i).isComplete())
                if(dialoguesSize < dialogues.size() - 1)
                    dialoguesSize++;
        }
    }

    public void drawDialogueBox(ShapeRenderer sr){
        dialogueBorder.drawBorderBox(sr);
        for(TextImage item : dialogues)
            item.drawTextBox(sr);
    }

}
