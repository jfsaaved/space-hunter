package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 02/01/2016.
 */
public class TextBox {

    private Rectangle box;
    private String text;
    private Texture image;
    private Sprite[][] fontSheet;

    private int width;
    private int height;
    private int numCols;
    private int numRows;
    private int scale;
    private int spacing;

    public TextBox(String text, float x, float y, int scale) {
        image = new Texture(Gdx.files.internal("font.png"));

        this.text = text;
        this.scale = scale;

        width = 11;
        height = 16;
        numCols = 26;
        numRows = 3;
        spacing = -1;

        box = new Rectangle(x, y, ((width+spacing) * scale) * text.length(), (height * scale));

        fontSheet = new Sprite[numRows][numCols];
        for(int rows = 0 ; rows < numRows ; rows++) {
            for (int cols = 0 ; cols < numCols ; cols++) {
                fontSheet[rows][cols] = new Sprite(image, (width*cols), height*rows, width, height);
                fontSheet[rows][cols].setSize(width * scale, height * scale);
            }
        }
    }

    public void setSize(int size){
        for(int rows = 0 ; rows < numRows ; rows++ ) {
            for(int cols = 0 ; cols < numCols ; cols++) {
                fontSheet[rows][cols].setSize(width * scale, height * scale);
            }
        }
    }

    public void setPosition(float x, float y) {
        box.setPosition(x, y);
    }

    public void render(SpriteBatch sb){
        for(int i = 0; i < text.length(); i ++){
            char c = text.charAt(i);
            int index = 0;

            if((int) c > 64 && (int) c < 91)
                index = c - 39;
            else if ((int) c > 96 && (int) c < 123)
                index = c - 97;

            int row = index / fontSheet[0].length;
            int col = index % fontSheet[0].length;

            fontSheet[row][col].setPosition(box.getX() + (i * (width+spacing) * scale), box.getY());
            fontSheet[row][col].draw(sb);
        }
    }

    public void drawBox(ShapeRenderer sr){
        sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    public boolean contains(float x, float y){
        return box.contains(x,y);
    }

}
