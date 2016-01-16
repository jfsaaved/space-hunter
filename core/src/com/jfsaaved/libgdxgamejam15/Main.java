package com.jfsaaved.libgdxgamejam15;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.handler.Images;
import com.jfsaaved.libgdxgamejam15.handler.Sounds;
import com.jfsaaved.libgdxgamejam15.states.GSM;
import com.jfsaaved.libgdxgamejam15.states.MenuState;

public class Main extends ApplicationAdapter {

    public static final String TITLE = "Space Hunter";

    public static int WIDTH = 1280;
    public static int HEIGHT = 768;

    // Sprites
    public static Images resources;

    private SpriteBatch sb;
    private ShapeRenderer sr;
	private GSM gsm;

    public static Sounds sound;

	@Override
	public void create () {
		sb = new SpriteBatch();
        sr = new ShapeRenderer();

        resources = new Images();
        resources.loadAtlas("pack1.pack","assets");

        sound = new Sounds();
        sound.loadAtlas("FAIL.mp3","fail");
        sound.loadAtlas("CURSOR.mp3","cursor");

        gsm = new GSM();
        gsm.push(new MenuState(gsm));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(sb);

        /*Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        gsm.shapeRender(sr);
        */
	}

    @Override
    public void resize(int width, int height){
        gsm.resize(width, height);
    }
}
