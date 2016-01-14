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
public class TextImage {

    protected Rectangle textBox;
    protected Sprite[][] textSheet;

    protected int textNumCols;
    protected int testNumRows;
    protected int textLetterWidth;
    protected int textLetterHeight;

    protected int textScale;
    protected int textSpacing;
    protected Color textColor;
    protected String text;

    protected float textDelay;
    protected float textDelayCurrent;
    protected int textLength;
    protected boolean textComplete;

    public TextImage(String text, float x, float y, int textScale, float textDelay) {
        // Text delays
        this.textDelay = textDelay;
        this.textDelayCurrent = textDelay;
        this.textLength = 0;
        this.textComplete = false;
        // Presentation variables
        this.text = text;
        this.textScale = textScale;
        this.textSpacing = -1;
        this.textColor = Color.WHITE;

        // Hard coded stuff of attributes of the .png file
        textLetterWidth = 11;
        textLetterHeight = 16;
        textNumCols = 29;
        testNumRows = 3;

        textSheet = new Sprite[testNumRows][textNumCols];
        textBox = new Rectangle(x, y, ((textLetterWidth + textSpacing) * textScale) * text.length(), (textLetterHeight * textScale));

        Texture image = new Texture(Gdx.files.internal("font.png"));
        for(int rows = 0 ; rows < testNumRows; rows++) {
            for (int cols = 0 ; cols < textNumCols; cols++) {
                textSheet[rows][cols] = new Sprite(image, (textLetterWidth *cols), textLetterHeight *rows, textLetterWidth, textLetterHeight);
                textSheet[rows][cols].setColor(textColor);
                textSheet[rows][cols].setSize(textLetterWidth * textScale, textLetterHeight * textScale);
            }
        }
    }

    private int parse(int i){
        char c = text.charAt(i);
        int index = 26;

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

        return index;
    }

    public void update(float dt){
        if(!textComplete){
            if(textLength < text.length()){
                if(textDelayCurrent > 0)
                    textDelayCurrent -= 100f * dt;
                else{
                    textLength++;
                    textDelayCurrent = textDelay;
                }
            }else{
                textComplete = true;
                textLength = text.length();
            }
        }
    }

    public void drawText(SpriteBatch sb){
        for(int i = 0; i < textLength; i ++){
            int row = parse(i) / textSheet[0].length;
            int col = parse(i) % textSheet[0].length;

            textSheet[row][col].setPosition(textBox.getX() + (i * (textLetterWidth + textSpacing) * textScale), textBox.getY());
            textSheet[row][col].setColor(textColor);
            textSheet[row][col].draw(sb);
        }
    }

    public void drawTextBox(ShapeRenderer sr){
        sr.rect(textBox.getX(), textBox.getY(), textBox.getWidth(), textBox.getHeight());
    }

    public boolean isComplete(){
        return textComplete;
    }

    public void shiftHalfLeft() {
        textBox.setX(textBox.getX() - textBox.getWidth()/2);
    }

    public void setTextPosition(float x, float y) {
        textBox.setPosition(x, y);
    }

    public void setTextScale(int textScale){
        this.textScale = textScale;
    }

    public float getTextHeight(){
        return textBox.getHeight();
    }

    public float getTextWidth(){
        return textBox.getWidth();
    }

    public float getTextX(){
        return textBox.getX();
    }

    public float getTextY(){
        return textBox.getY();
    }

    public void setTextColor(Color textColor){
        this.textColor = textColor;
    }

    public boolean contains(float x, float y){
        return textBox.contains(x,y);
    }

}
