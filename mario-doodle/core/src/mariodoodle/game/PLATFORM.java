package mariodoodle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.*;

public class PLATFORM extends Actor {

    private float posX, posY;
    private int power;
    private Texture image;

    PLATFORM()
    {
        generate();
    }

    public void generate()
    {
        //add random x,y
        //randomly select if booster
    }

    public int getPower()
    {
        return power;
    }
    public Texture getImage()
    {
        return image;
    }
    public float getX()
    {
        return posX;
    }
    public float getY()
    {
        return posY;
    }

}
