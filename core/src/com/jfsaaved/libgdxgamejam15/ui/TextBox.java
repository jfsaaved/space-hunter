package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by 343076 on 02/01/2016.
 */
public class TextBox {

    private Rectangle box;
    private Sprite[][] fontSheet;

    private int numCols;
    private int numRows;
    private int letterWidth;
    private int letterHeight;

    private int scale;
    private int spacing;
    private Color color;
    private String text;

    public TextBox(String text, float x, float y, int scale) {
        // Presentation variables
        this.text = text;
        this.scale = scale;
        this.spacing = -1;
        this.color = Color.WHITE;

        // Hard coded stuff of attributes of the .png file
        letterWidth = 11;
        letterHeight = 16;
        numCols = 29;
        numRows = 3;

        fontSheet = new Sprite[numRows][numCols];
        box = new Rectangle(x, y, ((letterWidth+spacing) * scale) * text.length(), (letterHeight * scale));

        Texture image = new Texture(Gdx.files.internal("font.png"));
        for(int rows = 0 ; rows < numRows ; rows++) {
            for (int cols = 0 ; cols < numCols ; cols++) {
                fontSheet[rows][cols] = new Sprite(image, (letterWidth *cols), letterHeight *rows, letterWidth, letterHeight);
                fontSheet[rows][cols].setColor(color);
                fontSheet[rows][cols].setSize(letterWidth * scale, letterHeight * scale);
            }
        }
    }

    public void draw(SpriteBatch sb){
        for(int i = 0; i < text.length(); i ++){
            char c = text.charAt(i);
            int index = 26;

            // Parser
            if ((int) c > 64 && (int) c < 91) // Upper case
                index = c - 36;
            else if ((int) c > 96 && (int) c < 123) // Lower case
                index = c - 97;
            else if ((int) c > 47 && (int) c < 58) // Numbers
                index = c + 10;
            else if((int) c == 33) // Exclamation point
                index = 74;
            else if((int) c == 35) // Hash
                index = 79;
            else if((int) c == 36) // Dollar sign
                index = 80;
            else if((int) c == 37) // Percentage sign
                index = 82;
            else if((int) c == 38) // And sign
                index = 84;
            else if((int) c == 43) // Plus sign
                index = 86;
            else if((int) c == 44) // Comma
                index = 70;
            else if((int) c == 45) // Minus sign
                index = 85;
            else if((int) c == 46) // Period
                index = 68;
            else if((int) c == 58) // Colon
                index = 69;
            else if((int) c == 63) // Question mark
                index = 75;
            else
                index = 26;

            int row = index / fontSheet[0].length;
            int col = index % fontSheet[0].length;

            fontSheet[row][col].setPosition(box.getX() + (i * (letterWidth +spacing) * scale), box.getY());
            fontSheet[row][col].setColor(color);
            fontSheet[row][col].draw(sb);
        }
    }

    public void centerOrigin() {
        box.setX(box.getX() - box.getWidth()/2);
    }

    public void drawBox(ShapeRenderer sr){
        sr.rect(box.getX(), box.getY(), box.getWidth(), box.getHeight());
    }

    public void setPosition(float x, float y) {
        box.setPosition(x, y);
    }

    public void setScale(int scale){
        this.scale = scale;
    }

    public float getHeight(){
        return box.getHeight();
    }

    public float getWidth(){
        return box.getWidth();
    }

    public float getX(){
        return box.getX();
    }

    public float getY(){
        return box.getY();
    }

    public void setColor(Color color){
        this.color = color;
    }

    public boolean contains(float x, float y){
        return box.contains(x,y);
    }

}
