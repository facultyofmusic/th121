package tests;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;

public class TrailMain extends BasicGame{
	TrailEffect t;
	
	public TrailMain(){
		super("Trail Test");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		t = new TrailEffect(new Vector2f(100, 100), Color.white, 500);		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		t.update(delta, Mouse.getX(), -Mouse.getY()+ 480 );
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		t.render(g);
	}
	
	public static void main(String[] args) throws SlickException {
		AppGameContainer app = new AppGameContainer(new TrailMain());
		app.setDisplayMode(640, 480, false);
		app.setUpdateOnlyWhenVisible(true);

		//app.setFullscreen(true);
		app.setShowFPS(true);
		app.setAlwaysRender(false);
		app.setClearEachFrame(true);
		app.setTargetFrameRate(60);
		app.setSmoothDeltas(true);
		app.setMusicOn(true);
		app.setVerbose(true);
		app.setVSync(false);
		app.start();
	    }
}
