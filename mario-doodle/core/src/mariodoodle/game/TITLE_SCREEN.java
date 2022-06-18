package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import sun.tools.jconsole.Tab;

import javax.swing.text.LabelView;


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

    public void show()
    {
        background = new Image(new Texture("media/mario-background.png"));
        background.setZIndex(0);
        stage.addActor(background);


        //adding the start button
        startButton = new TextButton("Start", skin );
        //looks for left click on button and then opens a new game screen
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GAME_SCREEN(game, playerImageSource));
            }
        });
        startButton.setColor(new Color(11,11,11,11));
        final Table startContainer = new Table(skin);
        startContainer.add(startButton).expandX().fillX();
        startContainer.setBackground("window-c");
        container.add(startContainer).pad(10).colspan(2).fillX();
        container.row();

        marioContainer = new Table(skin);
        luigiContainer = new Table(skin);

        //adding the mario button
        marioButton = new ImageButton(new TextureRegionDrawable(new Texture(marioImageSource)));
        marioContainer.add(marioButton);
        marioButton.setChecked(true);
        marioContainer.setBackground("window-player-c");
        marioButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {

                marioButton.setChecked(true);
                luigiButton.setChecked(false);
                playerImageSource = marioImageSource;
                marioContainer.setBackground("window-player-c");
                luigiContainer.setBackground("label-hp-black");
            }
        });


        //adding the luigi button
        luigiButton = new ImageButton(new TextureRegionDrawable(new Texture(luigiImageSource)));
        luigiContainer.add(luigiButton);
        luigiContainer.setBackground("label-pp-black");
        luigiButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                luigiButton.setChecked(true);
                marioButton.setChecked(false);
                playerImageSource = luigiImageSource;
                luigiContainer.setBackground("window-player-c");
                marioContainer.setBackground("label-hp-black");
            }
        });



        container.add(marioContainer).padRight(10);
        container.add(luigiContainer).padLeft(10);

        container.setFillParent(true); //centers the button(s)


    }

    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 0);
        update(delta);
        stage.addActor(container);
        stage.draw();
    }
    private void update (float delta)
    {
        if(luigiButton.isChecked())
        {


        }
        else if (marioButton.isChecked())
        {

        }

    }

    public void hide()
    {

    }

    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
