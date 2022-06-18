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

    private int past_score;

    END_SCREEN(MD_GAME game, String player_image_source, int past_score)
    {
        this.game = game;
        this.player_image_source = player_image_source;
        this.past_score = past_score;
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
        Table buttonStyleContainer = new Table(skin);
        container.setFillParent(true);
        Label gameOverText = new Label("GAME OVER", skin);
        gameOverText.setFontScale(3);
        gameOverText.setColor(Color.RED);
        container.add(gameOverText);
        container.row();
        container.add(new Label("Score: " + past_score, skin)).padBottom(150);
        container.row();

        container.add(buildButton("PLAY AGAIN")).fillX();

        container.row();

        container.add(buildButton("MENU")).fillX();
        


        return container;
    }

    private Table buildButton(final String buttonText)
    {
        menuButton = new TextButton(buttonText, skin);
        if(buttonText == "MENU") {
            menuButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    game.setScreen(new TITLE_SCREEN(game));
                }
            });

            }
        else {
            menuButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {

                    game.setScreen(new GAME_SCREEN(game, player_image_source));
                }
            });
        }
        Table buttonStyleContainer = new Table(skin);
        buttonStyleContainer.add(menuButton).expandX().fillX();;
        buttonStyleContainer.setBackground("window-c");
        return buttonStyleContainer;
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
