package com.jfsaaved.libgdxgamejam15.options;

import com.badlogic.gdx.graphics.Color;
import com.jfsaaved.libgdxgamejam15.states.GSM;
import com.jfsaaved.libgdxgamejam15.states.GameOverState;
import com.jfsaaved.libgdxgamejam15.states.ShipState;
import com.jfsaaved.libgdxgamejam15.states.State;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by 343076 on 13/01/2016.
 */
public abstract class Options {

    protected Stack<Integer> currentOption;
    protected State state;

    int originalHealth;
    int originalHunger;
    int originalEnergy;
    int originalShipHealth;
    int originalShipFuel;
    int originalArtifacts;
    int originalFood;

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    protected enum Stats{
        HEALTH(0), HUNGER(1), ENERGY(2),
        HUNTER(3), EXPLORER(4), MECHANIC(5),
        SHIP_HEALTH(6), SHIP_FUEL(7), SHIP_LEVEL(8),
        FOOD(9), ARTIFACTS(10), GOLD(11);

        private int value;

        Stats(int value){
            this.value = value;
        }

        protected int getValue(){
            return this.value;
        }
    }

    public Options(State state) {
        currentOption = new Stack<Integer>();
        this.state = state;
    }

    public void pushOption(int i){
        currentOption.push(i);
    }

    public void handleInput(){
        originalHealth = state.hero.getHealth();
        originalHunger = state.hero.getHunger();
        originalEnergy = state.hero.getEnergy();
        originalShipHealth = state.ship.getHealth();
        originalShipFuel = state.ship.getFuel();
        originalArtifacts = state.hero.getArtifacts();
        originalFood = state.hero.getFood();
    }

    public abstract void setHoverDesc(int i);

    protected void setIncrementPreview(int heroStats, int previewValue, int previewAt) {
        Color color = Color.GREEN;
        int previewThreshold = 101 - previewValue;
        int colourAt = getColourIndex(previewAt);

        if(heroStats < previewThreshold) {
            state.statusImages.changeColourAt(colourAt, color);
            state.statusImages.setPreviewAt(previewAt, previewValue);
        } else if (heroStats >=  previewThreshold && heroStats < 100){
            state.statusImages.changeColourAt(colourAt, color);
            state.statusImages.setPreviewAt(previewAt, 100 - heroStats);
        }
    }

    protected void setDecrementPreview(int heroStats, int previewValue, int previewAt) {
        Color color = Color.RED;
        int previewThreshold = previewValue - 1;
        int colourAt = getColourIndex(previewAt);

        if(heroStats > previewThreshold) {
            state.statusImages.changeColourAt(colourAt, color);
            state.statusImages.setPreviewAt(previewAt, -previewValue);
        } else if (heroStats <= previewThreshold && heroStats > 0) {
            state.statusImages.changeColourAt(colourAt, color);
            state.statusImages.setPreviewAt(previewAt, -(previewValue - (previewValue - heroStats)));
        }
    }

    // Indices for Type
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    // Type true = increment, false = decrement
    protected void changeStatsAt(int index, int value) {
        if(value > 0) {
            int threshold = 101 - value;
            if (getStatusAt(index) < threshold)
                setStatusAt(index, getStatusAt(index) + value);
            else if (getStatusAt(index) >= threshold && getStatusAt(index) < 100)
                setStatusAt(index, 100);
        }
        else if(value < 0) {
            int threshold = -(value) - 1;
            if(getStatusAt(index) > threshold)
                setStatusAt(index, getStatusAt(index) + value);
            else if (getStatusAt(index) <= threshold && getStatusAt(index) > 0)
                setStatusAt(index, 0);
        }
    }

    protected void setStatusAt(int index, int value) {
        if(index == 0)state.hero.setHealth(value);
        else if(index == 1)state.hero.setHunger(value);
        else if(index == 2)state.hero.setEnergy(value);
        else if(index == 3)state.hero.setHunter(value);
        else if(index == 4)state.hero.setExplorer(value);
        else if(index == 5)state.hero.setMechanic(value);
        else if(index == 6)state.ship.setHealth(value);
        else if(index == 7)state.ship.setFuel(value);
        else if(index == 8)state.ship.setLevel(value);
        else if(index == 9)state.hero.setFood(value);
        else if(index == 10)state.hero.setArtifacts(value);
        else if(index == 11)state.hero.setGold(value);
    }

    protected int getStatusAt(int index) {
        int value = 0;
        if(index == 0)value = state.hero.getHealth();
        else if(index == 1)value = state.hero.getHunger();
        else if(index == 2)value = state.hero.getEnergy();
        else if(index == 3)value = state.hero.getHunter();
        else if(index == 4)value = state.hero.getExplorer();
        else if(index == 5)value = state.hero.getMechanic();
        else if(index == 6)value = state.ship.getHealth();
        else if(index == 7)value = state.ship.getFuel();
        else if(index == 8)value = state.ship.getLevel();
        else if(index == 9)value = state.hero.getFood();
        else if(index == 10)value = state.hero.getArtifacts();
        else if(index == 11)value = state.hero.getGold();
        return value;
    }

    // Colour At Index
    // health = 1, hunger = 2, energy = 3, hunter = 4, explorer = 5, mechanic = 6,
    // ship health = 8, ship fuel = 9, ship level = 10;
    // food = 12, artifacts = 13, gold = 14
    // Preview At index
    // health = 0, hunger = 1, energy = 2, hunter = 3, explorer = 4, mechanic = 5,
    // ship health = 6, ship fuel = 7, ship level = 8;
    // food = 9, artifacts = 10, gold = 11
    protected int getColourIndex(int index){
        int value = 0;
        if(index == 0)value = 1;
        else if(index == 1)value = 2;
        else if(index == 2)value = 3;
        else if(index == 3)value = 4;
        else if(index == 4)value = 5;
        else if(index == 5)value = 6;
        else if(index == 6)value = 8;
        else if(index == 7)value = 9;
        else if(index == 8)value = 10;
        else if(index == 9)value = 12;
        else if(index == 10)value = 13;
        else if(index == 11)value = 14;
        return value;
    }
}
