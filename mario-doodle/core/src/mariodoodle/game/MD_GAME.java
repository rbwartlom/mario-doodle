package mariodoodle.game;

import com.badlogic.gdx.Game;

/*
Startet das Spiel durch erstellen eines neuen Game Screens
 */

//Artem
public class MD_GAME extends Game {

	public void create()
	{
		setScreen(new TITLE_SCREEN(this));
	}

	public void render()
	{
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
