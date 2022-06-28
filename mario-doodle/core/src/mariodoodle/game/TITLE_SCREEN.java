package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class TITLE_SCREEN extends ScreenAdapter{
    private MD_GAME game;
    private Table container;
    private Stage stage;
    private TextButton startButton;

    private String playerImageSource;
    private ImageButton marioButton;
    private ImageButton luigiButton;
    private String marioImageSource;
    private String luigiImageSource;
    private Image background;

    private Skin skin;

    Table marioContainer;
    Table luigiContainer;


    TITLE_SCREEN(MD_GAME game)
    {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        container = new Table(skin);

        luigiImageSource = "media/luigi.png";
        marioImageSource = "media/mario.png";
        playerImageSource = marioImageSource;

    }

    //wird bei erstellen des Fensters aufgerufen
    public void show()
    {
        ScreenUtils.clear(0, 0, 0, 0);//Entfernt alle vorherigen Objekte
        background = new Image(new Texture("media/mario-background.png"));// Setzen des Hintergunds als Bild
        stage.addActor(background);// Der Hintergund wird zur Stage hinzugefügt

        // Der Start Button wird erstellt welcher einen Text erhält
        startButton = new TextButton("Start", skin);

        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                //setzt das Fenster auf das Hauptfenster
                game.setScreen(new GAME_SCREEN(game, playerImageSource));
            }
        });// wenn der Startbutton geklickt wird wird auf den Spielbildschirm gewechselt
        Table startContainer = new Table(skin);
        startContainer.add(startButton).expandX().fillX();// Der Startbutton wird formatiert
        startContainer.setBackground("window-c");
        container.add(startContainer).pad(10).colspan(2).fillX();
        container.row();

        //container der beiden Knoepfe
        marioContainer = new Table(skin);
        luigiContainer = new Table(skin);

        //erstellt mario-knopf
        marioButton = new ImageButton(new TextureRegionDrawable(new Texture(marioImageSource)));
        marioContainer.add(marioButton);
        marioContainer.setBackground("window-player-c"); //setzt den Hintergrund standardmaessig auf den Rahmen
        marioButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                //setzt mario als ausgewaehlt
                playerImageSource = marioImageSource;
                marioContainer.setBackground("window-player-c");
                luigiContainer.setBackground("label-hp-black");
            }
        });

        //erstellt luigi-knopf
        luigiButton = new ImageButton(new TextureRegionDrawable(new Texture(luigiImageSource)));
        luigiContainer.add(luigiButton);
        luigiContainer.setBackground("label-hp-black"); //setzt den Hintergrund standardmaessig auf den Schatten
        luigiButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                //setzt luigi als ausgewaehlt
                playerImageSource = luigiImageSource;
                luigiContainer.setBackground("window-player-c");
                marioContainer.setBackground("label-hp-black");
            }
        });


        container.add(marioContainer).padRight(10);
        container.add(luigiContainer).padLeft(10);

        container.setFillParent(true); //zentriert container

        stage.addActor(container);
    }

    public void render(float delta) {
        stage.draw();
    }

    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
