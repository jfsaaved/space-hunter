package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;

/**
 * Created by 343076 on 15/01/2016.
 */
public class DayImage {

    public TextImage dayImage;

    private String dayText;
    private int day;
    private float x;
    private float y;

    public DayImage(OrthographicCamera cam, int day) {
        TextImage calcualte = new TextImage("DAY 000", 0,0,1,0);

        this.day = day;
        this.x = cam.position.x + cam.viewportWidth/2 - calcualte.getTextWidth() - 6;
        this.y = Main.HEIGHT/2 - calcualte.getTextHeight();

        dayText = "DAY "+day;
        while(dayText.length() < 7)
            dayText = " "+dayText;
        dayImage = new TextImage(dayText, x, y, 1, 0);
    }

    public void update(float dt, int day){
        if(this.day < day){
            this.day = day;
            dayText = "DAY "+day;
            while(dayText.length() < 7)
                dayText = " "+dayText;
            dayImage = new TextImage(dayText, x, y, 1, 0);
        }
        dayImage.update(dt);
    }

    public void drawDay(SpriteBatch sb){
        dayImage.drawText(sb);
    }

    public void drawDayBox(ShapeRenderer sr){
        dayImage.drawTextBox(sr);
    }

}
