package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import javax.swing.*;

public class PLAYER extends Image {



    float acceleration;


    PLAYER(String imageSource) {
        super(new Texture(imageSource));
        this.setY(700);
        acceleration = 1;
    }

    public void act(float delta)
    {
        float newY = this.getY() - acceleration*delta;
        acceleration += delta*1200;
        this.setY(newY);

        //update the x value, calculate gravity
    }

    public boolean checkPlatformTouch(PLATFORM platform) {
        return false;
    }
    public boolean isTouching() {
        return false;
    }

    //called when player touches platform
    public void boost(int force) {

    }

    public void dispose() {

    }

    //getter methods
    /*
    public Sprite getSprite() {
        return sprite;
    }
    */
}
