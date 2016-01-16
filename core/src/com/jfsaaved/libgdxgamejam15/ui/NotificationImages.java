package com.jfsaaved.libgdxgamejam15.ui;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.jfsaaved.libgdxgamejam15.Main;

import java.util.ArrayList;

/**
 * Created by 343076 on 15/01/2016.
 */
public class NotificationImages {

    private BorderImage notificationBorder;
    private ArrayList<TextImage> notifications;

    private boolean hide;
    private float dieTimer;

    public NotificationImages(OrthographicCamera cam, String[] input) {
        notificationBorder = new BorderImage(cam.position.x - 77, (cam.position.y + cam.viewportHeight/4) - 105, 31, 16, 0.111f);

        this.notifications = new ArrayList<TextImage>();
        float tempX = notificationBorder.getBorderX() + 7;
        float tempY = (notificationBorder.getBorderY()) + notificationBorder.getBorderHeight() - 24;
        for (String item : input){
            while(item.length() < 14)
                item = item + " ";
            TextImage newItem = new TextImage(item, tempX, tempY, 1,0);
            this.notifications.add(newItem);
            tempX = newItem.getTextX();
            tempY = newItem.getTextY() - newItem.getTextHeight();
        }

        dieTimer = 10f;
    }

    public void setHide(boolean b){
        hide =  b;
    }

    public void update(float dt) {
        if(dieTimer > 0)
            dieTimer -= 5f * dt;
        else
            hide = true;
        for(TextImage item : notifications)
            item.update(dt);
    }

    public void drawNotification(SpriteBatch sb) {
        if(!hide) {
            notificationBorder.drawBorder(sb);
            for (TextImage item : notifications)
                item.drawText(sb);
        }
    }

    public void drawNotificationBox(ShapeRenderer sr) {
        if(!hide) {
            notificationBorder.drawBorderBox(sr);
            for (TextImage item : notifications) {
                item.drawTextBox(sr);
            }
        }
    }


}
