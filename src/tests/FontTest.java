package tests;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class FontTest extends BasicGame{
	AngelCodeFont THFont;

	public FontTest() {
		super("Font test");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		THFont.drawString(100, 100, "Hello World! 1234567890");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		THFont = new AngelCodeFont("data/font/THFont2.fnt", new Image("data/font/THFont2_0.png"));
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String args[]){
		try {
			AppGameContainer app = new AppGameContainer(new FontTest());
			app.setDisplayMode(640, 480, false);
			app.setShowFPS(true);
			app.setAlwaysRender(true);
			app.setClearEachFrame(false);
			app.setSmoothDeltas(true);
			app.setMusicOn(true);
			app.setVerbose(true);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}
	
}
