package mariodoodle.game;

import com.badlogic.gdx.Audio;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;

public class GAME_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private Stage stage;
    private String player_image_source;

    private PLAYER player;
    private LinkedList<PLATFORM> platforms;

    private Image background;
    private int score;
    private Label scoreLabel;
    private Music marioMusic;
    //private AudioDevice audio;

    GAME_SCREEN(MD_GAME game, String player_image_source)
    {
        this.game = game;
        this.player_image_source = player_image_source;
        platforms = new LinkedList<>();

        marioMusic = Gdx.audio.newMusic(Gdx.files.internal("mariomusic.mp3"));
        marioMusic.setLooping(true);
        marioMusic.play();
        //audio = Gdx.audio.newAudioDevice(100, false);
    }

    //Artem & Philipp
    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        background = new Image(new Texture("media/mario-sky-background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        player = new PLAYER(player_image_source);
        stage.addActor(player);
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);


        //generate start platforms
        PLATFORM firstPlatform = new PLATFORM(Gdx.input.getY(), 80);
        platforms.addLast(firstPlatform);
        for (int i = 180; i < Gdx.graphics.getHeight() - 160; ) {
            PLATFORM newPlatform = new PLATFORM(i);
            platforms.addLast(newPlatform);
            stage.addActor(newPlatform);
            i += newPlatform.getHeight();
            i += 100;
            i += newPlatform.getPower() * 50;
        }

        scoreLabel = new Label("Score: " + score, skin);
        scoreLabel.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);
        scoreLabel.setColor(Color.BLACK);
        stage.addActor(scoreLabel);
    }

    //Artem
    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(20, 0, 0, 1);
        for (int i = 0; i < platforms.size(); i++)
        {
            stage.addActor(platforms.get(i));
        }
        stage.draw();
    }

    //Artem
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
            game.setScreen(new END_SCREEN(game, player_image_source, score));
        }
    }


    //Hendrik
    private boolean checkCollision (float posYold)
    {
        float posYnew = player.getY();
        for (int i = 0; i < platforms.size(); i++)
        {
            PLATFORM currentPlatform = platforms.get(i);
            float platformY = currentPlatform.getY() + currentPlatform.getHeight();
            if(platformY >= posYnew && platformY <= posYold)
            {
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

    //Hendrik
    private void managePlatforms(float posYold)
    {
        float playerDelta = player.getY() - posYold;
        if(playerDelta > 0)
        {

            for (int i = 0; i < platforms.size(); i++)
            {
                PLATFORM currentPlatform = platforms.get(i);
                float newY = currentPlatform.getY() - playerDelta*1;
                currentPlatform.setYpos(newY);
            }
            //if platform is too low remove it and add a new one on top
            if(platforms.getFirst().getY()+ platforms.getFirst().getHeight() < 0)
            {
                platforms.getFirst().remove();
                platforms.removeFirst();
                PLATFORM lastPlatform = platforms.getLast();
                float newPlatY = lastPlatform.getY() + lastPlatform.getHeight() + lastPlatform.getPower()*50 + 100;
                PLATFORM newPlat = new PLATFORM(newPlatY);
                platforms.addLast(newPlat);
            }

        }
    }


    //Artem
    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
