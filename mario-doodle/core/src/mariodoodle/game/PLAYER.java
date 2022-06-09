package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.swing.*;
import java.awt.*;

public class PLAYER extends Image {



    float acceleration;

    public Rectangle bounds;


    PLAYER(String imageSource) {
        super(new Texture(imageSource));
        Texture text = new Texture(imageSource);
        System.out.println(this.getHeight());
        System.out.println(this.getWidth());
        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
        this.setBounds(0, 700, this.getWidth(), this.getHeight());
        acceleration = 1;
    }

    public void act(float delta)
    {
        float newY = this.getY() - acceleration*delta;
        acceleration += delta*1200;
        this.setYPos(newY);

        //update the x value, calculate gravity
    }

    public boolean checkPlatformTouch(PLATFORM platform) {
        return false;
    }
    public boolean isTouching() {
        return false;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //called when player touches platform
    public void boost(int force) {

    }

    public void dispose() {

    }

    public void setYPos(float y)
    {
        this.setY(y);
        bounds.setY(y);
    }

    public void setXPos(float y)
    {
        this.setX(y);
        bounds.setX(y);
    }


    //getter methods
    /*
    public Sprite getSprite() {
        return sprite;
    }
    */
}
