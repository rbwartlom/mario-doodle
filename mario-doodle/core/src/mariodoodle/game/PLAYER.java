package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class PLAYER extends Image {

    float forceDown; //nach unten gerichtete Kraft. Wenn Vorzeichen negativ, wirkt Kraft nach oben.
    public Rectangle bounds; //Deklarieren des Aktionsfelds des Players

    // Konstruktormethode mit Uebergabeparameter String imageSource
    // Zuordnung Bild
    // Definition der bounds
    PLAYER(String imageSource) {
        super(new Texture(imageSource));

        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
        this.setYPos(200);
        forceDown = 1;
    }


    //sorgt sich um die Gravittionskraft, delta dabei aus render()
    public void moveY(float delta)
    {
        float newY = this.getY() - forceDown*delta;
        //erstellt eine 'decke' am oberen Bildschirmrand
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

    //wird aufgerufen, wenn Spieler eine Plattform beruehrt
    public void boost(int force) {
        this.forceDown = -800*force;
    }

    //ersatz fuer setY
    ////updated dazu noch die bounds, damit Objekte interagieren koennen
    public void setYPos(float y)
    {
        this.setY(y);
        bounds.setY(y);
    }

    //ersatz fuer setX
    ////updated dazu noch die bounds, damit Objekte interagieren koennen
    public void setXPos(float x)
    {
        this.setX(x);
        bounds.setX(x);
    }
}
