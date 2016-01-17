package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.jfsaaved.libgdxgamejam15.Main;
import com.jfsaaved.libgdxgamejam15.handler.MainPreferences;
import com.jfsaaved.libgdxgamejam15.handler.Musics;
import com.jfsaaved.libgdxgamejam15.objects.Hero;
import com.jfsaaved.libgdxgamejam15.objects.Ship;
import com.jfsaaved.libgdxgamejam15.ui.DayImage;
import com.jfsaaved.libgdxgamejam15.ui.DialogueImages;
import com.jfsaaved.libgdxgamejam15.ui.MenuImages;
import com.jfsaaved.libgdxgamejam15.ui.NotificationImages;
import com.jfsaaved.libgdxgamejam15.ui.StatusImages;

/**
 * Created by 343076 on 30/12/2015.
 */
public abstract class State {

    protected GSM gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected float camX;
    protected float camY;

    // Objects
    public static Hero hero;
    public static Ship ship;

    // U.I.
    public MenuImages menuImages;
    public DialogueImages dialogueImages;
    public NotificationImages notificationImages;
    public StatusImages statusImages;
    public DayImage dayImage;

    // Travelling
    public static int days;
    public static int turns;
    public static int currentSystem;
    public static float travelTime;
    public static int nextSystem;

    // Preferences
    public static MainPreferences preferences;
    public Musics music;

    protected State(GSM gsm){
        cam = new OrthographicCamera();
        mouse = new Vector3();
        camX = 300;
        camY = 300;

        music = new Musics();

        this.gsm = gsm;
        this.updateCam(Main.WIDTH, Main.HEIGHT, camX, camY);
    }

    public void useTurn(int i){
        turns -= i;
        if(turns <= 0) {
            days += 1;
            turns = 4;
        }
    }

    public void setDays(int i){
        days = i;
    }

    public void setTurns(int i){
        turns = i;
    }

    public int getDays(){
        return days;
    }

    public int getTurns(){
        return turns;
    }

    protected void updateCam(int width, int height, float x, float y){
        cam.setToOrtho(false, width, height);
        cam.position.set(x, y, 0);
        cam.update();
    }

    protected void resize(int width, int height){
        Main.WIDTH = width;
        Main.HEIGHT = height;
        this.updateCam(width, height, camX, camY);
    }

    protected abstract void handleInput(float dt);
    protected abstract void update(float dt);
    protected abstract void render(SpriteBatch sb);
    protected abstract void shapeRender(ShapeRenderer sr);

    public void changeTo(State state){
        this.gsm.set(state);
    }
    public OrthographicCamera getCam(){ return this.cam; }
    public GSM getGSM(){
        return this.gsm;
    }

}
