package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.jfsaaved.libgdxgamejam15.Main;

/**
 * Created by 343076 on 05/01/2016.
 */
public class BorderImage {

    private Rectangle borderBox;
    private Sprite[][] borderSheet;

    private int borderTileWidth;
    private int borderTileHeight;

    private float borderScale;
    private int width;
    private int height;

    public BorderImage(float x, float y, int width, int height, float borderScale){

        this.borderScale = borderScale;
        this.width = width;
        this.height = height;

        borderTileWidth = 45;
        borderTileHeight = 45;

        TextureRegion image = Main.resources.getAtlas("assets").findRegion("textbox1");
        int numRows = image.getRegionWidth()/borderTileHeight;
        int numCols = image.getRegionWidth()/borderTileWidth;
        borderSheet = new Sprite[numRows][numCols];
        borderBox = new Rectangle(x, y, borderTileWidth*borderScale*width, borderTileHeight*borderScale*height);

        for(int rows = 0; rows < numRows; rows++){
            for(int cols = 0; cols < numCols; cols++){
                borderSheet[rows][cols] = new Sprite(image, borderTileWidth*cols, borderTileHeight*rows, borderTileWidth, borderTileHeight);
                borderSheet[rows][cols].setSize(borderTileWidth*borderScale, borderTileHeight*borderScale);
            }
        }
    }

    public void drawBorder(SpriteBatch sb){
        int rowIndex = 0;
        int colIndex = 0;
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {

                if (row > 0 && row < height - 1) rowIndex = 1;
                else if (row == height - 1) rowIndex = 2;
                else rowIndex = row;

                if (col > 0 && col < width - 1) colIndex = 1;
                else if (col == width - 1) colIndex = 2;
                else colIndex = col;

                borderSheet[rowIndex][colIndex].setPosition(borderBox.getX() + (col*borderTileHeight*borderScale), borderBox.getY() + (row*borderTileWidth*borderScale));
                borderSheet[rowIndex][colIndex].draw(sb);

            }
        }
    }

    public void drawBorderBox(ShapeRenderer sr){
        sr.rect(borderBox.getX(), borderBox.getY(), borderBox.getWidth(), borderBox.getHeight());
    }

    public void setBorderScale(float borderScale){
        this.borderScale = borderScale;
    }

    public float getBorderX(){
        return borderBox.getX();
    }

    public float getBorderY(){
        return borderBox.getY();
    }
}
