package com.jfsaaved.libgdxgamejam15.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.jfsaaved.libgdxgamejam15.Main;

/**
 * Created by 343076 on 30/12/2015.
 */
public abstract class State {

    protected GSM gsm;
    protected OrthographicCamera cam;
    protected Vector3 mouse;

    protected float camX;
    protected float camY;

    protected State(GSM gsm){
        cam = new OrthographicCamera();
        mouse = new Vector3();
        camX = 0;
        camY = 0;

        this.gsm = gsm;
        this.updateCam(Main.WIDTH, Main.HEIGHT, camX, camY);
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

}
