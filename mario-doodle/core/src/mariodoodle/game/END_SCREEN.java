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

//Philipp
public class END_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private String player_image_source;
    private Stage stage;
    Skin skin;
    private Table container;
    private TextButton menuButton;

    private Image background;

    private int past_score;

    //setzt Uebergabeparameter
    END_SCREEN(MD_GAME game, String player_image_source, int past_score)
    {
        this.game = game;
        this.player_image_source = player_image_source;
        this.past_score = past_score;
    }

    //wird bei erstellen des Fensters aufgerufen
    public void show()
    {
        skin = new Skin(Gdx.files.internal("data/skin/terra-mother-ui.json"));
        ScreenUtils.clear(0, 0, 0, 0);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        background = new Image(new Texture("media/mario-background.png"));
        stage.addActor(background);

        this.container = buildContainer();
        stage.addActor(container);
    }

    //baut den container mit allen Elementen auf dem Endbildschirm
    private Table buildContainer()
    {

        Table container = new Table();
        container.setFillParent(true); //zentriert Hauptcontainer
        Label gameOverText = new Label("GAME OVER", skin);
        gameOverText.setFontScale(3); //skaliert Schriftgroesse x3
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

    //buttonText entweder "MENU" oder "PLAY AGAIN"
    private Table buildButton(final String buttonText)
    {
        menuButton = new TextButton(buttonText, skin);
        if(buttonText == "MENU") {
            menuButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    //setzt das Fenster auf das Hauptfenster
                    game.setScreen(new TITLE_SCREEN(game));
                }
            });

            }
        else {
            menuButton.addListener(new ClickListener() {
                public void clicked(InputEvent event, float x, float y) {
                    //setzt das Fenster auf das Menuefenster
                    game.setScreen(new GAME_SCREEN(game, player_image_source));
                }
            });
        }
        Table buttonStyleContainer = new Table(skin);
        buttonStyleContainer.add(menuButton).expandX().fillX();
        buttonStyleContainer.setBackground("window-c"); //setzt den Hintergund vom Knopf auf den Knopfhintergrund
        return buttonStyleContainer;
    }
    
    //wird pro frame aufgerufen
    public void render(float delta) {
        stage.draw();
    }


    public void dispose()
    {
        stage.clear();
        stage.dispose();
    }

}
