package mariodoodle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class PLAYER extends Actor {
    float posX, posY;
    int acceleration;

    private Texture image;

    PLAYER(String imageSource)
    {
        image = new Texture(imageSource);
        posY = 100;
    }

    public boolean checkPlatformTouch(PLATFORM platform)
    {
        //if x, y is the same return true else return false
        return false;
    }

    public boolean isTouching()
    {
        return false;
    }

    //called when player touches platform
    public void boost(int force)
    {

    }
    public void updateX(int mouseX)
    {
        posX = mouseX;
    }

    public void dispose()
    {
        image.dispose();
    }

    //getter methods
    public Texture getTexture()
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
