package com.jfsaaved.libgdxgamejam15.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.jfsaaved.libgdxgamejam15.Main;

/**
 * Created by 343076 on 30/12/2015.
 */
public class Hero extends AnimatedObject {

    private float boundaryX1;
    private float boundaryX2;

    // Stats
    private int hunger;
    private int energy;
    private int health;

    // Skills
    private int hunter;
    private int explorer;
    private int mechanic;

    // Supplies
    private int food;
    private int artifacts;
    private int gold;

    public Hero(float x, float y, int width, int height, TextureRegion image) {
        super(x, y, width, height, image);
        this.boundaryX1 = 0;
        this.boundaryX2 = Main.WIDTH;

        hunger = 100;
        energy = 100;
        health = 100;

        hunter = 1;
        explorer = 1;
        mechanic = 1;

        food = 2;
        artifacts = 0;
        gold = 1000;
    }

    public void setPosition(float x, float y){
        this.box.setX(x);
        this.box.setY(y);
    }

    // The two x boundaries for the player's x position
    public void setBoundaries(float boundaryX1, float boundaryX2){
        this.boundaryX1 = boundaryX1;
        this.boundaryX2 = boundaryX2;
    }

    public void handleInput(float dt){
        currentState = currentState.Standing;
        if(this.box.getX() > boundaryX1) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                moveLeft();
                flip = true;
                currentState = currentState.Walking;
            }
        }

        if(this.box.getX() < (boundaryX2 - this.box.getWidth())) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                moveRight();
                flip = false;
                currentState = currentState.Walking;
            }
        }
    }


    public int getMechanic() {
        return mechanic;
    }

    public void setMechanic(int mechanic) {
        this.mechanic = mechanic;
    }

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHunter() {
        return hunter;
    }

    public void setHunter(int hunter) {
        this.hunter = hunter;
    }

    public int getExplorer() {
        return explorer;
    }

    public void setExplorer(int explorer) {
        this.explorer = explorer;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(int artifacts) {
        this.artifacts = artifacts;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

}
