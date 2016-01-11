package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 06/01/2016.
 */
public class PointerImage {

    private Rectangle pointerBox;
    private Sprite pointer;

    private int options;
    private int currentOption;
    private int moveBy;
    private int scale;

    public PointerImage(float x, float y, int moveBy, int scale, int options){
        this.scale = scale;
        this.moveBy = moveBy;
        this.options = options;
        this.currentOption = 0;

        pointer = new Sprite(new Texture(Gdx.files.internal("pointer.png")));
        pointer.setSize(pointer.getWidth() * scale, pointer.getHeight() * scale);
        pointerBox = new Rectangle(x, y, pointer.getWidth(), pointer.getHeight());
    }

    public void handleInput(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)) {
            if(currentOption >= 0 && currentOption < options) {
                currentOption += 1;
                pointerBox.setY(pointerBox.getY() - moveBy);
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            if(currentOption <= options && currentOption > 0) {
                currentOption -= 1;
                pointerBox.setY(pointerBox.getY() + moveBy);
            }
        }
    }

    public int getOption(){
        return currentOption;
    }

    public void drawPointer(SpriteBatch sb){
        pointer.setPosition(pointerBox.getX(), pointerBox.getY());
        pointer.draw(sb);
    }

    public void drawPointerBox(ShapeRenderer sr){
        sr.rect(pointerBox.getX(), pointerBox.getY(), pointerBox.getWidth(), pointerBox.getHeight());
    }

    public void setX(float x){
        pointerBox.setX(x);
    }

    public float getX(){
        return pointerBox.getX();
    }

    public float getWidth(){
        return pointerBox.getWidth();
    }

}
