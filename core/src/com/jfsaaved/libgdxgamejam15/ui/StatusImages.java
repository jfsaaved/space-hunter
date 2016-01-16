package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/**
 * Created by 343076 on 14/01/2016.
 */
public class StatusImages {

    private BorderImage statsBorder;
    private ArrayList<TextImage> stats;

    // Status for Hero
    private int health;
    private int hunger;
    private int energy;
    private int hunter;
    private int explorer;
    private int mechanic;

    // Status for Ship
    private int shipHealth;
    private int shipFuel;
    private int shipLevel;

    // Supplies
    private int food;
    private int artifacts;
    private int gold;

    // Preview values
    private ArrayList<Integer> previews;

    public StatusImages(OrthographicCamera cam, int health, int hunger, int energy, int hunter, int explorer, int mechanic, int shipHealth, int shipFuel, int shipLevel, int food, int artifacts, int gold) {
        statsBorder = new BorderImage((cam.position.x - (cam.viewportWidth/2)) + 5, (cam.position.y + cam.viewportHeight/2) - 265, 31, 52, 0.111f);

        this.health = health;
        this.hunger = hunger;
        this.energy = energy;
        this.hunter = hunter;
        this.explorer = explorer;
        this.mechanic = mechanic;
        this.shipHealth = shipHealth;
        this.shipFuel = shipFuel;
        this.shipLevel = shipLevel;
        this.food = food;
        this.artifacts = artifacts;
        this.gold = gold;

        previews = new ArrayList<Integer>();
        for(int i = 0; i < 12; i++)
            previews.add(0);

        // Creating the text images for the status names
        String[] statList = {
                "     HERO     ","HEALTH    "+percentageToString(this.health)+"%",
                "HUNGER    "+percentageToString(this.hunger)+"%",
                "ENERGY    "+percentageToString(this.energy)+"%",
                "HUNTER      "+levelToString(this.hunter),
                "EXPLORER    "+levelToString(this.explorer),
                "MECHANIC    "+levelToString(this.mechanic),
                "     SHIP     ","HEALTH    "+percentageToString(this.shipHealth)+"%",
                "FUEL      "+percentageToString(this.shipFuel)+"%",
                "LEVEL       "+levelToString(this.shipLevel),
                "   SUPPLIES   ","FOOD        "+levelToString(this.food),
                "ARTIFACTS   "+levelToString(this.artifacts),
                "GOLD       $"+levelToString(this.gold)};

        stats = new ArrayList<TextImage>();
        float tempX = statsBorder.getBorderX() + 7;
        float tempY = (statsBorder.getBorderY()) + statsBorder.getBorderHeight() - 25;
        for (String item : statList){
            TextImage newItem = new TextImage(item, tempX, tempY, 1,0);
            stats.add(newItem);
            tempX = newItem.getTextX();
            tempY = newItem.getTextY() - newItem.getTextHeight();
        }
    }

    public void changeStats(int health, int hunger, int energy, int hunter, int explorer, int mechanic, int shipHealth, int shipFuel, int shipLevel, int food, int artifacts, int gold) {
        this.health = health;
        this.hunger = hunger;
        this.energy = energy;
        this.hunter = hunter;
        this.explorer = explorer;
        this.mechanic = mechanic;
        this.shipHealth = shipHealth;
        this.shipFuel = shipFuel;
        this.shipLevel = shipLevel;
        this.food = food;
        this.artifacts = artifacts;
        this.gold = gold;
    }

    private String percentageToString(int val){
        String value = val + "";
        while(value.length() < 3)
            value = " " + value;
        return value;
    }

    private String levelToString(int val){
        String value = val + "";
        while(value.length() < 2)
            value = " " + value;
        return value;
    }

    public void update(float dt) {
        String[] statList = {
                "     HERO     ","HEALTH    "+percentageToString(this.health + previews.get(0))+"%",
                "HUNGER    "+percentageToString(this.hunger + previews.get(1))+"%",
                "ENERGY    "+percentageToString(this.energy + previews.get(2))+"%",
                "HUNTER      "+levelToString(this.hunter + previews.get(3)),
                "EXPLORER    "+levelToString(this.explorer + previews.get(4)),
                "MECHANIC    "+levelToString(this.mechanic + previews.get(5)),
                "     SHIP     ","HEALTH    "+percentageToString(this.shipHealth + previews.get(6))+"%",
                "FUEL      "+percentageToString(this.shipFuel + previews.get(7))+"%",
                "LEVEL       "+levelToString(this.shipLevel + previews.get(8)),
                "   SUPPLIES   ","FOOD        "+levelToString(this.food + previews.get(9)),
                "ARTIFACTS   "+levelToString(this.artifacts + previews.get(10)),
                "GOLD       $"+levelToString(this.gold + previews.get(11))};

        // health = 1, hunger = 2, energy = 3, hunter = 4, explorer = 5, mechanic = 6, shealth = 8, sfueld = 9, slevel = 10;
        int i = 0;
        for(TextImage item : stats){
            item.updateText(statList[i]);
            item.update(dt);
            i++;
        }
    }

    public void changeColourAt(int index, Color color) {
        stats.get(index).setTextColor(color);
    }

    public void resetColorAll(){
        for(TextImage item : stats)
            item.setTextColor(Color.WHITE);
    }

    public void setPreviewAt(int index, int val){
        previews.set(index, val);
    }

    public void resetPreviewAll(){
        for(int i = 0 ; i < 12 ; i++)
            previews.set(i,0);
    }

    public void drawStatus(SpriteBatch sb){
        statsBorder.drawBorder(sb);
        for(TextImage item : stats){
            item.drawText(sb);
        }
    }

    public void drawStatusBox(ShapeRenderer sr){
        statsBorder.drawBorderBox(sr);
        for(TextImage item : stats){
            item.drawTextBox(sr);
        }
    }


}
