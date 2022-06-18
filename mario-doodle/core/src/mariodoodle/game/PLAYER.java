package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PLAYER extends Image {



    float forceDown;

    public Rectangle bounds;
    float boostCount;


    PLAYER(String imageSource) {
        super(new Texture(imageSource));

        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
        this.setBounds(0, 400, this.getWidth(), this.getHeight());
        forceDown = 1;
        boostCount = 1;
    }

    public void moveY(float delta)
    {
        float newY = this.getY() - forceDown*delta;
        if(this.getY() > Gdx.graphics.getHeight() - 100)
        {
            //newY = Gdx.graphics.getHeight() - 20;
            forceDown += delta*1000;
        }
        forceDown += delta*1200;
        System.out.println("Player X: " + this.getY());
        this.setYPos(newY);


        //update the x value, calculate gravity
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //called when player touches platform
    public void boost(int force) {
        System.out.println("Boosted, forcedown: " + forceDown);
        this.forceDown = -900*force/boostCount;
        //boostCount += 0.01;
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
