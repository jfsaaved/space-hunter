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

    public PointerImage(float x, float y, int moveBy, int options){
        pointer = new Sprite(new Texture(Gdx.files.internal("pointer.png")));
        pointerBox = new Rectangle(x, y, pointer.getRegionWidth(), pointer.getHeight());

        this.moveBy = moveBy;
        this.options = options;
        this.currentOption = 0;
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

    public void drawPointer(SpriteBatch sb){
        sb.draw(pointer, pointerBox.getX(), pointerBox.getY());
    }

    public void drawPointerBox(ShapeRenderer sr){
        sr.rect(pointerBox.getX(), pointerBox.getY(), pointerBox.getWidth(), pointerBox.getHeight());
    }

}
