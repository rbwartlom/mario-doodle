package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;

public class END_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private String player_image_source;
    private Stage stage;
    Skin skin;
    private Table container;

    private TextButton playAgainButton;
    private TextButton menuButton;

    private Image background;

    END_SCREEN(MD_GAME game, String player_image_source)
    {
        this.game = game;
        this.player_image_source = player_image_source;
    }

    public void show()
    {
        skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image(new Texture("media/mario-background.png"));
        background.setZIndex(0);
        stage.addActor(background);

        this.container = buildContainer();
        stage.addActor(container);
    }

    private Table buildContainer()
    {
        Table container = new Table();
        container.setFillParent(true);
        Label gameOverText = new Label("GAME OVER", skin);
        gameOverText.setFontScale(2);
        gameOverText.setColor(Color.RED);
        container.add(gameOverText).padBottom(200);
        container.row();

        playAgainButton = new TextButton("PLAY AGAIN", skin);
        playAgainButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new GAME_SCREEN(game, player_image_source));
            }
        });

        menuButton = new TextButton("MENU", skin);
        menuButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y)
            {
                game.setScreen(new TITLE_SCREEN(game));
            }
        });

        container.add(playAgainButton);
        container.row();
        container.add(menuButton).padTop(10);
        return container;
    }

    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0, 0);
        stage.draw();
    }
    public void update (float delta)
    {

    }

    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
