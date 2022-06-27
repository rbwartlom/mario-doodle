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

    //game als durchlaufender Parameter, welcher das Spiel zusammenknuepft
    //player_image_source von TITLE_SCREEN uebergeben (entweder Mario oder Luigi)
    GAME_SCREEN(MD_GAME game, String player_image_source)
    {
        this.game = game;
        this.player_image_source = player_image_source;
        platforms = new LinkedList<>();


    }

    //Artem
    @Override
    public void show() {
        Skin skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //initialisiert die Wiedergabe der Musik
        marioMusic = Gdx.audio.newMusic(Gdx.files.internal("mariomusic.mp3"));
        marioMusic.setLooping(true);
        marioMusic.play();

        //erstellt Hintergrund
        background = new Image(new Texture("media/mario-sky-background.png"));
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(background);

        player = new PLAYER(player_image_source);
        stage.addActor(player);


        //generiert die Plattformen, die am Start auf dem Display sind
        ////schaut dabei, dass erste PLATFORM unter dem Spieler ist
        float startingHeight = player.getY() - 130;
        for (float i = startingHeight; i < Gdx.graphics.getHeight() - 160; ) {
            PLATFORM newPlatform = new PLATFORM(i);
            if(i == startingHeight)
            {
                newPlatform.setXpos(Gdx.input.getX());
            }
            platforms.addLast(newPlatform);
            stage.addActor(newPlatform);
            i += newPlatform.getHeight();
            i += 200;
            i += newPlatform.getPower() * 50;
        }

        //erstellt einen Score-counter oben rechts
        scoreLabel = new Label("Score: " + score, skin);
        scoreLabel.setPosition(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() - 50);
        scoreLabel.setColor(Color.BLACK);
        stage.addActor(scoreLabel);
    }

    //Artem
    //render-funktion wird bei jedem Frame automatisch aufgerufen
    public void render(float delta) {
        //aufteilung in update und render
        update(delta);
        ScreenUtils.clear(20, 0, 0, 1);
        for (int i = 0; i < platforms.size(); i++)
        {
            stage.addActor(platforms.get(i));
        }
        stage.draw();
    }

    //Artem
    private void update(float delta)
    {
        player.setXPos(Gdx.input.getX() - player.getWidth()/2);
        float posYold = player.getY(); //wichtig fuer die zwei unteren methoden

        player.moveY(delta); //Berechnet und setzt schwerkraft beim Spieler

        managePlatforms(posYold);
        checkCollision(posYold);

        scoreLabel.setText("Score: " + score);

        //bricht das Spiel ab
        if(player.getY() <= 0)
        {
            marioMusic.dispose();
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
                float newY = currentPlatform.getY() - playerDelta*1.7F;
                currentPlatform.setYpos(newY);
            }
            //if platform is too low remove it and add a new one on top
            if(platforms.getFirst().getY()+ platforms.getFirst().getHeight() < 0)
            {
                platforms.getFirst().remove();
                platforms.removeFirst();
                PLATFORM lastPlatform = platforms.getLast();
                float newPlatY = lastPlatform.getY() + lastPlatform.getHeight() + lastPlatform.getPower()*50 + 200;
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
