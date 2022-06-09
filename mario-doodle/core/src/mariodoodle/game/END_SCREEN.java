package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class END_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private Stage stage;
    Skin skin;

    private TextButton playAgainButton;
    private TextButton menuButton;

    END_SCREEN(MD_GAME game)
    {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
    }

    public void show()
    {
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));

    }

    public void render(float delta) {
        update(delta);
        stage.addActor(new TextButton("start", skin));
        stage.draw();

    }
    public void update (float delta)
    {

    }

    public void hide(){
        stage.dispose();
    }

}
