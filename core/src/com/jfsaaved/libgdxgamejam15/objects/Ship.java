package com.jfsaaved.libgdxgamejam15.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 31/12/2015.
 */
public class Ship {

    protected boolean hide;
    protected Rectangle box;

    public Ship(int x, int y, int width, int height) {
        this.box = new Rectangle(x, y, width, height);
        this.hide = false;
    }

    public float getX(){
        return this.box.getX();
    }

    public float getY(){
        return this.box.getY();
    }

    public void drawBox(ShapeRenderer sr){
        if(!hide)
            sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

}
