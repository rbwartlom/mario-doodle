package mariodoodle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

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
        platforms = new LinkedList<PLATFORM>();
    }

    @Override
    public void show() {
        player = new PLAYER(player_image_source);

        //generate start platform
        for(int i = 20; i < Gdx.graphics.getHeight();) {
            PLATFORM newPlatform = new PLATFORM(i);
            platforms.add(newPlatform);

            i+= newPlatform.getHeight();
            i+= newPlatform.getPower()*10;
        }

        int screenHeight = 0;


    }

    public void update(float delta)
    {
        player.setX(Gdx.input.getX() - player.getWidth()/2);
        stage.act();
        System.out.println(player.getY());

        //if player position is higher than 60%
            //(platform management)
            //move platforms down by player difference to 60% point
            //check if any platform is lower than 20% (maybe also only lowest plat)
                //if yes delete it & add new platform to the top


        for (int i = 0; i < platforms.size(); i++)
        {

        }
        //(check if player is touching and boost)
        //get old posY before act
        //loop through platforms
            //check if it's y is in player range
                //if yes check if platform x is touching player
                    //if yes boost player buy platform power




        //detects if player reached bottom of screen
        if(player.getY() <= 0)
        {
            game.setScreen(new END_SCREEN(game));
        }


        //move player from position of mouse
    }

    public void render(float delta) {
        update(delta);

        ScreenUtils.clear(20, 0, 0, 1);

        //fills stage
        stage.addActor(player);
        for (int i = 0; i < platforms.size(); i++)
        {
            stage.addActor(platforms.get(i));

        }
        stage.draw();
        /*
        game.batch.begin();

        game.batch.draw(player.getSprite(), player.getX(), player.getY());
        for (int i = 0; i < platforms.size(); i++)
        {
            PLATFORM currentPlatform = platforms.get(i);
            game.batch.draw(currentPlatform.getImage(), currentPlatform.getX(), currentPlatform.getY());
        }
        game.batch.end();
         */
    }


    //use: player.isTouching(platform)
    public boolean isTouching(PLATFORM p)
    {
        return true;
    }

    public void end()
    {


    }
    public void hide()
    {
        //game.batch.dispose();
        stage.dispose();
    }

}
