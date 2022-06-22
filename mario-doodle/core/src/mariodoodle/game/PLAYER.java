package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

//Hendrik
public class PLAYER extends Image {
    private float movementDown;
    private Rectangle bounds;
    private float boostCount;


    PLAYER(String imageSource) {
        super(new Texture(imageSource));

        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
        float startHeight = 400;
        this.setBounds(0, startHeight, this.getWidth(), this.getHeight()); //setzt Spielerposition
        movementDown = 1;
    }

    public void moveY(float delta)
    {
        float newY = this.getY() - movementDown *delta;

        if(this.getY() > Gdx.graphics.getHeight() - 100)
        {
            movementDown += delta*1000;
        }

        movementDown += delta*1200;
        this.setYPos(newY);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //called when player touches platform
    public void boost(int power) {
        this.movementDown = -900*power;
    }

    public void setYPos(float y)
    {
        this.setY(y);
        bounds.setY(y);
    }

    public void setXPos(float x)
    {
        this.setX(x);
        bounds.setX(x);
    }
}
