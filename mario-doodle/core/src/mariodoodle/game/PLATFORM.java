package mariodoodle.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class PLATFORM {

    private int posX, posY;
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
    public int getX()
    {
        return posX;
    }
    public int getY()
    {
        return posY;
    }

}
