package mariodoodle.game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.LinkedList;

public class MD_GAME extends Game {
	private LinkedList<PLATFORM> platforms;
	//no camera like in libgdx tutorial

	public void create()
	{
		setScreen(new TITLE_SCREEN(this));
	}

	public void render()
	{
		super.render();
	}

	@Override
	public void dispose () {}
}
