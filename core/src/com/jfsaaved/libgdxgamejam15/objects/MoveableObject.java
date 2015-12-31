package com.jfsaaved.libgdxgamejam15.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 30/12/2015.
 */
public abstract class MoveableObject {

    protected boolean hide;
    protected Rectangle box;
    protected TextureRegion image;

    protected MoveableObject(float x, float y, float width, float height, TextureRegion image){
        this.box = new Rectangle(x, y, image.getRegionWidth(), image.getRegionHeight());
        this.image = image;
        this.hide = false;
    }

    public boolean contains(Rectangle box2){
        return this.box.contains(box2);
    }

    public boolean overlap(Rectangle box2){
        return this.box.overlaps(box2);
    }

    public void draw(SpriteBatch sb){
        if(!hide)
            sb.draw(image, box.getX(), box.getY());
    }

    public void drawBox(ShapeRenderer sr){
        if(!hide)
            sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    protected void setBox(float x, float y, float width, float height){
        this.box.set(x,y,width,height);
    }

    protected abstract void update(float dt);

}
