package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import java.util.Random;

public class PLATFORM extends Image {

    private final Texture texture = new Texture("media/mario.png");
    private int power;
    private boolean touched;

    private Rectangle bounds;

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

        this.power = rand.nextInt(1) + 1;
        bounds = new Rectangle((int) this.getX(), (int) this.getY(), (int) this.getWidth(), (int) this.getHeight());
    }

    public int getPower()
    {
        return power;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setXpos(float x)
    {
        this.setX(x);
        bounds.setX(x);
    }

    public void setYpos(float y)
    {
        this.setY(y);
        bounds.setY(y);
    }

    public void setTouched(boolean touched)
    {
        this.touched = touched;
    }

    public boolean wasTouched() {
        return touched;
    }

}
