package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.Random;

public class PLATFORM extends Image {

    private int power;
    private boolean touched;

    private Rectangle bounds;

    PLATFORM(float posY)
    {
        //setzt die Textur fuer Image
        super(new Texture("media/mushroom.png"));

        //setzt eine zufaellige X-position
        Random rand = new Random();
        float posX = rand.nextFloat(Gdx.graphics.getWidth() - this.getWidth());

        generate(posY, posX);
    }

    //generiert eine Plattform mit Koordinaten posX, posY
    public void generate(float posX, float posY)
    {
        this.setY(posY);
        this.setX(posX);

        //setzt die Kraft auf eine Zahl von 1-3
        Random rand = new Random();
        this.power = rand.nextInt(2) + 1;

        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    //ersatz fuer setX
    ////updated dazu noch die bounds, damit Objekte interagieren koennen
    public void setXpos(float x)
    {
        this.setX(x);
        bounds.setX(x);
    }

    //ersatz fuer setY
    ////updated dazu noch die bounds, damit Objekte interagieren koennen
    public void setYpos(float y)
    {
        this.setY(y);
        bounds.setY(y);
    }

    //setter-methode
    public void setTouched(boolean touched)
    {
        this.touched = touched;
    }

    //getter-methode
    public boolean wasTouched() {
        return touched;
    }

    //getter-methode
    public int getPower()
    {
        return power;
    }

    //getter-methode
    public Rectangle getBounds() {
        return bounds;
    }

}
