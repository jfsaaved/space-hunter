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

    public Hero(float x, float y, int width, int height, TextureRegion image) {
        super(x, y, width, height, image);
        this.boundaryX1 = 0;
        this.boundaryX2 = Main.WIDTH;
    }

    // The two x boundaries for the player's x position
    public void setBoundaries(float boundaryX1, float boundaryX2){
        this.boundaryX1 = boundaryX1;
        this.boundaryX2 = boundaryX2;
    }

    public void handleInput(float dt){
        if(this.box.getX() > boundaryX1) {
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
                moveLeft();
        }

        if(this.box.getX() < (boundaryX2 - this.box.getWidth())) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
                moveRight();
        }
    }

}
