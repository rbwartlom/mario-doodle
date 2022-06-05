package mariodoodle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

public class PLAYER {
    int posX, posY;
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
    //called when player touches platform
    public void boost(int force)
    {

    }
    public void update(int mouseX)
    {
        posX = mouseX;
    }

    public void dispose()
    {
        image.dispose();
    }

    public Texture getTexture()
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
