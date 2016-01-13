package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Vector;

/**
 * Created by 343076 on 12/01/2016.
 */
public class MenuImages {

    private float textX;
    private float textY;
    private float textHeight;
    private float textWidth;
    private int scale;

    private Vector<TextImage> options;
    private PointerImage pointer;

    public MenuImages(float textX, float textY, int scale, String[] options){
        this.scale = scale;
        this.options = new Vector<TextImage>();

        float tempX = textX;
        float tempY = textY;
        for (String item : options){
            TextImage newItem = new TextImage(item, tempX, tempY, scale);
            this.options.add(newItem);
            tempX = newItem.getTextX();
            tempY = newItem.getTextY() + newItem.getTextHeight();
        }

        this.textX = this.options.lastElement().getTextX();
        this.textY = this.options.lastElement().getTextY();
        this.textHeight = this.options.lastElement().getTextHeight();
        this.textWidth = this.options.lastElement(). getTextWidth();

        pointer = new PointerImage(this.textX, this.textY, (int) textHeight, scale, this.options.size()-1);
    }

    public void handleInput(){
        pointer.handleInput();
    }

    public int getOption(){
        return pointer.getOption();
    }

    public float getTextHeight(){
        return this.textHeight;
    }

    public float getTextWidth(){
       return this.textWidth;
    }

    public float getTextX(){
        return this.textX;
    }

    public float getTextY(){
        return this.textY;
    }

    public void drawMenu(SpriteBatch sb) {
        for(TextImage item : options){
            item.drawText(sb);
        }
        pointer.drawPointer(sb);
    }

    public void drawMenuBox(ShapeRenderer sr){
        for(TextImage item : options){
            item.drawTextBox(sr);
        }
        pointer.drawPointerBox(sr);
    }




}
