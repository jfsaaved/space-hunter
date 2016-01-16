package com.jfsaaved.libgdxgamejam15.handler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

/**
 * Created by 343076 on 16/01/2016.
 */
public class Musics {

    private HashMap<String, Music> atlases;

    public Musics(){
        atlases = new HashMap<String, Music>();
    }

    public void loadAtlas(String path, String key){
        atlases.put(key, Gdx.audio.newMusic(Gdx.files.internal(path)));
    }

    public Music getAtlas(String key){
        return atlases.get(key);
    }
}
