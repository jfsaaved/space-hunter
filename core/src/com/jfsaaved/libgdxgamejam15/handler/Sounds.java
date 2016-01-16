package com.jfsaaved.libgdxgamejam15.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import java.util.HashMap;

/**
 * Created by 343076 on 16/01/2016.
 */
public class Sounds {

    private HashMap<String, Sound> atlases;

    public Sounds(){
        atlases = new HashMap<String, Sound>();
    }

    public void loadAtlas(String path, String key){
        atlases.put(key, Gdx.audio.newSound(Gdx.files.internal(path)));
    }

    public Sound getAtlas(String key){
        return atlases.get(key);
    }

}
