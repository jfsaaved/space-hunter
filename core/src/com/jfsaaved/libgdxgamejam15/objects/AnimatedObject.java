package com.jfsaaved.libgdxgamejam15.objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 30/12/2015.
 */
public abstract class AnimatedObject {

    protected boolean hide;
    protected Rectangle box;
    protected Sprite[] standingSprite;

    protected int colFrame;
    protected float colFrameDelay;

    protected AnimatedObject(float x, float y, int width, int height, TextureRegion image){

        // Keeps track of which frame in the Sprite Sheet it's currently at
        colFrame = 0;
        colFrameDelay = 10f;

        // Store each frame into a double TextureRegion array
        standingSprite = new Sprite[4];
        for(int col = 0; col < 4; col++) {
            TextureRegion newSprite = new TextureRegion(image, width * col, 0, width, height);
            standingSprite[col] = new Sprite(newSprite);
        }

        this.box = new Rectangle(x, y, width, height);
        this.hide = false;
    }

    public void update(float dt){
        colFrameDelay -= 70f * dt;
        if(colFrameDelay < 0) {
            colFrame++;
            colFrameDelay = 10f;
        }
        if(colFrame > 3)
            colFrame = 0;
    }

    public void draw(SpriteBatch sb){
        if(!hide)
            sb.draw(standingSprite[colFrame], box.getX(), box.getY());
    }

    public void drawBox(ShapeRenderer sr){
        if(!hide)
            sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    public void moveRight(){
        this.box.setX(this.box.getX() + 1);
    }

    public void moveLeft(){
        this.box.setX(this.box.getX() - 1);
    }

    public boolean contains(Rectangle box2){
        return this.box.contains(box2);
    }

    public boolean overlap(Rectangle box2){
        return this.box.overlaps(box2);
    }

    public float getX(){
        return this.box.getX();
    }

    public float getY(){
        return this.box.getY();
    }

}
