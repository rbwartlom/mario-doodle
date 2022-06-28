package mariodoodle.game;

import com.badlogic.gdx.Game;

/*
Startet das Spiel durch erstellen eines neuen Game Screens
 */

public class MD_GAME extends Game {

	//setzt das Standardenster auf das Menuefenster
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
