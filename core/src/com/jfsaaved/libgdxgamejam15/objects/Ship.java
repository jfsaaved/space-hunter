package com.jfsaaved.libgdxgamejam15.objects;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 31/12/2015.
 */
public class Ship {

    protected boolean hide;
    protected Rectangle box;

    // Stats
    private int health;
    private int fuel;
    private int level;

    public Ship(int x, int y, int width, int height) {
        this.box = new Rectangle(x, y, width, height);
        this.hide = false;

        health = 100;
        fuel = 100;
        level = 1;
    }

    public float getWidth(){
        return this.box.getWidth();
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


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getFuel() {
        return fuel;
    }

    public void setFuel(int fuel) {
        this.fuel = fuel;
    }


}
