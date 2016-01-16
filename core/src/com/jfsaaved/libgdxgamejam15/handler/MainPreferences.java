package com.jfsaaved.libgdxgamejam15.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.states.State;

/**
 * Created by 343076 on 16/01/2016.
 */
public class MainPreferences {

    private Preferences prefs;

    public MainPreferences(){
        prefs = Gdx.app.getPreferences("SpaceHunter-Save");
    }

    public Preferences getPrefs(){
        return prefs;
    }

    public void save(Hero hero, Ship ship, State state){
        prefs.putInteger("days", state.days);
        prefs.putInteger("turns", state.turns);
        prefs.putInteger("hunger", hero.getHunger());
        prefs.putInteger("energy", hero.getEnergy());
        prefs.putInteger("health", hero.getHealth());
        prefs.putInteger("hunter", hero.getHunter());
        prefs.putInteger("explorer", hero.getExplorer());
        prefs.putInteger("mechanic", hero.getMechanic());
        prefs.putInteger("food", hero.getFood());
        prefs.putInteger("artifacts", hero.getArtifacts());
        prefs.putInteger("gold", hero.getGold());
        prefs.putInteger("ship fuel", ship.getFuel());
        prefs.putInteger("ship health", ship.getHealth());
        prefs.putInteger("ship level", ship.getLevel());
        prefs.flush();
    }

    public void load(Hero hero, Ship ship, State state){
        state.setDays(prefs.getInteger("days"));
        state.setTurns(prefs.getInteger("turns"));
        hero.setHunger(prefs.getInteger("hunger"));
        hero.setEnergy(prefs.getInteger("energy"));
        hero.setHealth(prefs.getInteger("health"));
        hero.setHunter(prefs.getInteger("hunter"));
        hero.setExplorer(prefs.getInteger("explorer"));
        hero.setMechanic(prefs.getInteger("mechanic"));
        hero.setFood(prefs.getInteger("food"));
        hero.setArtifacts(prefs.getInteger("artifacts"));
        hero.setGold(prefs.getInteger("gold"));
        ship.setFuel(prefs.getInteger("ship fuel"));
        ship.setHealth(prefs.getInteger("ship health"));
        ship.setLevel(prefs.getInteger("ship level"));
    }

}
