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

    protected enum CurrentState {
        Standing, Walking, Jumping;
    }

    protected boolean hide;
    protected Rectangle box;
    protected Sprite[] standingSprite;
    protected Sprite[] walkingSprite;

    protected CurrentState currentState;
    protected int colFrame;
    protected float colFrameDelay;
    protected boolean flip;

    protected AnimatedObject(float x, float y, int width, int height, TextureRegion image){
        // Keeps track of which frame in the Sprite Sheet it's currently at
        colFrame = 0;
        colFrameDelay = 10f;

        // Store each frame into a Sprite array
        standingSprite = new Sprite[4];
        for(int col = 0; col < 4; col++) {
            standingSprite[col] = new Sprite(image, width * col, 0, width, height);
        }

        walkingSprite = new Sprite[10];
        for(int col = 0; col < 10; col++) {
            walkingSprite[col] = new Sprite(image, width * (col + 5), 0, width, height);
        }

        this.currentState = CurrentState.Standing;
        this.box = new Rectangle(x, y, width, height);
        this.hide = false;
        this.flip = false;
    }

    public void update(float dt){
        if(currentState == currentState.Standing)
            updateStandingAnim(dt);
        else if(currentState == currentState.Walking)
            updateWalkingAnim(dt);
    }

    protected void updateStandingAnim(float dt){
        colFrameDelay -= 70f * dt;
        if(colFrameDelay < 0) {
            colFrame++;
            colFrameDelay = 10f;
        }
        if(colFrame > 3)
            colFrame = 0;
    }

    protected void updateWalkingAnim(float dt){
        colFrameDelay -= 70f * dt;
        if(colFrameDelay < 0) {
            colFrame++;
            colFrameDelay = 10f;
        }
        if(colFrame > 9)
            colFrame = 0;
    }

    public void draw(SpriteBatch sb){
        if(!hide) {
            if(currentState == CurrentState.Standing) {
                standingSprite[colFrame].setFlip(flip, false);
                sb.draw(standingSprite[colFrame], box.getX(), box.getY());
            }
            else if(currentState == CurrentState.Walking) {
                walkingSprite[colFrame].setFlip(flip, false);
                sb.draw(walkingSprite[colFrame], box.getX(), box.getY());
            }
        }
    }

    public void drawBox(ShapeRenderer sr){
        if(!hide)
            sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    public void moveRight(){
        box.setX(this.box.getX() + 1);
    }

    public void moveLeft(){
        this.box.setX(this.box.getX() - 1);
    }

    public float getX(){
        return this.box.getX();
    }

    public float getY(){
        return this.box.getY();
    }

    public boolean contains(Rectangle box2){
        return this.box.contains(box2);
    }

    public boolean overlap(Rectangle box2){
        return this.box.overlaps(box2);
    }



}
