package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.Random;

public class PLATFORM extends Image {

    private final Texture texture = new Texture("media/mario.png");
    private int power;


    PLATFORM(float posY)
    {
        super(new Texture("media/mushroom.png"));

        generate(posY);
    }

    public void act(float delta)
    {
    }
    public void generate(float posY)
    {
        this.setY(posY);

        Random rand = new Random();
        float posX = rand.nextFloat();
        posX *= Gdx.graphics.getWidth();
        this.setX(posX);

        this.power = rand.nextInt(1, 10);
    }

    public int getPower()
    {
        return power;
    }


    /*
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
    */

}
