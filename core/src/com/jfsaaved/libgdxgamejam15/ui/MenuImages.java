package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by 343076 on 12/01/2016.
 */
public class MenuImages {

    private float textX;
    private float textY;
    private float textHeight;
    private float textWidth;

    private BorderImage menuBorder;
    private ArrayList<TextImage> options;
    private PointerImage pointer;

    public MenuImages(OrthographicCamera cam, String[] options){
        menuBorder = new BorderImage(cam.position.x + (cam.viewportWidth/4), (cam.position.y + cam.viewportHeight/2) - 105, 31, 20, 0.111f);
        this.textX = menuBorder.getBorderX() + 7;
        this.textY = (menuBorder.getBorderY()) + menuBorder.getBorderHeight() - 25;
        this.options = new ArrayList<TextImage>();

        float tempX = textX;
        float tempY = textY;
        for (String item : options){
            while(item.length() < 14)
                item = " "+item;
            TextImage newItem = new TextImage(item, tempX, tempY, 1,0);
            this.options.add(newItem);
            tempX = newItem.getTextX();
            tempY = newItem.getTextY() - newItem.getTextHeight();
        }

        this.textX = this.options.get(0).getTextX();
        this.textY = this.options.get(0).getTextY();
        this.textHeight = this.options.get(0).getTextHeight();
        this.textWidth = this.options.get(0). getTextWidth();

        pointer = new PointerImage(this.textX, this.textY, (int) textHeight, 1, this.options.size()-1);
    }

    public void setOptions(String[] options){
        this.textX = menuBorder.getBorderX() + 7;
        this.textY = (menuBorder.getBorderY()) + menuBorder.getBorderHeight() - 25;
        this.options = new ArrayList<TextImage>();

        float tempX = textX;
        float tempY = textY;
        for (String item : options){
            while(item.length() < 14)
                item = " "+item;
            TextImage newItem = new TextImage(item, tempX, tempY, 1,0);
            this.options.add(newItem);
            tempX = newItem.getTextX();
            tempY = newItem.getTextY() - newItem.getTextHeight();
        }
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

    public void update(float dt){
        for(TextImage item : options){
            item.update(dt);
        }
    }

    public void drawMenu(SpriteBatch sb) {
        menuBorder.drawBorder(sb);
        for(TextImage item : options){
            item.drawText(sb);
        }
        pointer.drawPointer(sb);
    }

    public void drawMenuBox(ShapeRenderer sr){
        menuBorder.drawBorderBox(sr);
        for(TextImage item : options){
            item.drawTextBox(sr);
        }
        pointer.drawPointerBox(sr);
    }
}
