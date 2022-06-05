package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;

public class GAME_SCREEN extends ScreenAdapter {

    private MD_GAME game;
    private Stage stage;
    private String player_image_source;

    private PLAYER player;
    private LinkedList<PLATFORM> platforms;
    private int score;

    GAME_SCREEN(MD_GAME game, String player_image_source)
    {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        this.player_image_source = player_image_source;
    }

    @Override
    public void show() {
        player = new PLAYER("badlogic.jpg");
        platforms = new LinkedList<PLATFORM>();


    }

    public void update(float delta)
    {
        int mouseX = Gdx.input.getX();
        player.update(mouseX);
        //calculate gravity in player
        //move player from position of mouse
    }

    public void render(float delta) {
        update(delta);
        ScreenUtils.clear(0, 0, 0, 1);
        game.batch.begin();

        game.batch.draw(player.getTexture(), player.getX(), 20);
        /* for (int i = 0; i < platforms.size(); i++)
        {
            PLATFORM currentPlatform = platforms.get(i);
            game.batch.draw(currentPlatform.getImage(), currentPlatform.getX(), currentPlatform.getY());
        } */
        game.batch.end();
    }

    //use: player.isTouching(platform)
    public boolean isTouching(PLATFORM p)
    {
        return true;
    }

    public void hide()
    {
        game.batch.dispose();
    }

}
