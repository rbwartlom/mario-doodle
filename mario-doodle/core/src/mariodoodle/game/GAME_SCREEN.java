package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.LinkedList;

public class GAME_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private Stage stage;
    //private OrthographicCamera cam;
    private String player_image_source;

    private PLAYER player;
    private LinkedList<PLATFORM> platforms;

    private Image background;
    private int score;
    private Label scoreLabel;

    GAME_SCREEN(MD_GAME game, String player_image_source)
    {
        this.game = game;
        this.player_image_source = player_image_source;

        platforms = new LinkedList<>();
    }

    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        background = new Image(new Texture("media/mario-sky-background.png"));
        background.setZIndex(0);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        player = new PLAYER(player_image_source);
        stage.addActor(player);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);


        //generate start platform
        for (int i = 20; i < Gdx.graphics.getHeight(); ) {
            PLATFORM newPlatform = new PLATFORM(i);
            platforms.addLast(newPlatform);
            stage.addActor(newPlatform);
            i += newPlatform.getHeight();
            i += newPlatform.getPower() * 70;
        }

        scoreLabel = new Label("Score: " + score, skin);
        scoreLabel.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);
        scoreLabel.setColor(Color.BLACK);
        //scoreLabel.setColor(0, 0, 0, 0);
        stage.addActor(scoreLabel);
    }

    public void update(float delta)
    {
        player.setXPos(Gdx.input.getX() - player.getWidth()/2);
        float posYold = player.getY();
        player.moveY(delta);
        managePlatforms(posYold);
        checkCollision(posYold);

        scoreLabel.setText("Score: " + score);
        if(player.getY() <= 0)
        {
            game.setScreen(new END_SCREEN(game, player_image_source));
        }


    }



    private boolean checkCollision (float posYold)
    {
        float posYnew = player.getY();
        for (int i = 0; i < platforms.size(); i++)
        {
            PLATFORM currentPlatform = platforms.get(i);
            float platformY = currentPlatform.getY() + currentPlatform.getHeight();
            if(platformY >= posYnew && platformY <= posYold)
            {
                System.out.println("touching");
                //if player bounds and platform bounds are touching
                if(player.getBounds().overlaps(currentPlatform.getBounds()))
                {
                    int platformPower = currentPlatform.getPower();
                    player.boost(platformPower);
                    if(!currentPlatform.wasTouched())
                    {
                        score++;
                        currentPlatform.setTouched(true);
                    }
                    return true;
                }

            }
        }
        return false;
    }

    private void managePlatforms(float posYold)
    {
        float playerDelta = player.getY() - posYold;
        if(player.getY() > Gdx.graphics.getHeight()*0.6 && playerDelta > 0)
        {

            for (int i = 0; i < platforms.size(); i++)
            {
                PLATFORM currentPlatform = platforms.get(i);
                float newY = currentPlatform.getY() - playerDelta*2;
                currentPlatform.setYpos(newY);
            }
            //if platform is too low remove it and add a new one on top
            if(platforms.getFirst().getY() < 0)
            {
                platforms.getFirst().remove();
                platforms.removeFirst();
                PLATFORM lastPlatform = platforms.getLast();
                float newPlatY = lastPlatform.getY() + lastPlatform.getHeight() + lastPlatform.getPower()*50;
                PLATFORM newPlat = new PLATFORM(newPlatY);
                platforms.addLast(newPlat);
            }

        }
    }
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(20, 0, 0, 1);
        //cam.position.set(player.getX(), player.getY(), 0);
        //cam.update();
        for (int i = 0; i < platforms.size(); i++)
        {
            stage.addActor(platforms.get(i));
        }
        stage.draw();
        /*
        game.batch.begin();

        game.batch.draw(player.getSprite(), player.getX(), player.getY());
        for (int i = 0; i < platforms.size(); i++)
        {
            PLATFORM currentPlatform = platforms.get(i);
            game.batch.draw(currentPlatform.getImage(), currentPlatform.getX(), currentPlatform.getY());
        }
        game.batch.end();
         */
    }


    //use: player.isTouching(platform)
    public boolean isTouching(PLATFORM p)
    {
        return true;
    }

    public void end()
    {


    }
    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
