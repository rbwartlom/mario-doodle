package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


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

    TITLE_SCREEN(MD_GAME game)
    {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        container = new Table();

        luigiImageSource = "media/luigi.png";
        marioImageSource = "media/mario.png";
        playerImageSource = marioImageSource;
    }

    public void show()
    {
        Skin skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        //adding the start button
        startButton = new TextButton("Start", skin );
        //looks for left click on button and then opens a new game screen
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GAME_SCREEN(game, playerImageSource));
            }
        });
        container.add(startButton);
        startButton.center();
        container.row();

        //adding the mario button
        marioButton = new ImageButton(new TextureRegionDrawable(new Texture(marioImageSource)));
        marioButton.setChecked(true);
        marioButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                marioButton.setChecked(true);
                luigiButton.setChecked(false);
                playerImageSource = marioImageSource;

            }
        });
        container.add(marioButton);

        //adding the luigi button
        luigiButton = new ImageButton(new TextureRegionDrawable(new Texture(luigiImageSource)));
        luigiButton.setChecked(true);
        luigiButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                luigiButton.setChecked(true);
                marioButton.setChecked(false);
                playerImageSource = luigiImageSource;

            }
        });
        container.add(luigiButton);


        container.setFillParent(true); //centers the button(s)
    }

    public void render(float delta) {
        update(delta);
        stage.addActor(container);
        stage.draw();

    }
    public void update (float delta)
    {
    }

    public void hide()
    {

    }

}
