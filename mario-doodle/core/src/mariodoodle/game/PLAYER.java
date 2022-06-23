package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

//Hendrik
public class PLAYER extends Image {
    //Deklarieren der Attribute
    float forceDown; //nach unten gerichtete Kraft. Sofern Vorzeichen negativ, Kraft wirkt nach oben.
    public Rectangle bounds; //Deklarieren des Aktionsfeld des Players
    float boostCount; //Deklarieren der Zaehlvariablen boostCount, die die Anzahl der Boosts und d

    // Konstruktormethode mit Uebergabeparameter String imageSource. Dieser
    // Zuordnung Bild
    // Definition des Rectangles
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
        if(this.getY() > Gdx.graphics.getHeight() - 50)
        {
            forceDown += delta*3000;
        }
        forceDown += delta*1200;
        this.setYPos(newY);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    //called when player touches platform
    public void boost(int force) {
        this.forceDown = -900*force/boostCount;
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
