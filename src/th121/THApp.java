package th121;

import java.util.concurrent.ArrayBlockingQueue;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;

public class THApp extends BasicGame implements ComponentListener{
	public static int CLIP_X, CLIP_Y, CLIP_WIDTH, CLIP_HEIGHT;
	public static AppGameContainer app;
	private static THSplash splash;
	private static THMainMenu mainMenu;
	private static THLoad loadingScreen;
	private static THGame game;
	public static ScreenPage currentPage;
	private static float alpha = 1;
	private static float musicVol = 1, sfxVol = 1;
	//public static THConsole c;
	public static THInternalConsole THC;
	boolean showConsole = false;
	

	public THApp(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		THC = THInternalConsole.getInstance(container, this);
		THC.append("initializing pages...");
		splash = new THSplash();
		mainMenu = new THMainMenu();
		loadingScreen = new THLoad();
		game = new THGame();
		
		currentPage = splash;
		
		THC.append("pages done.");
		THC.newl();
	}

	@Override
	public void update(GameContainer container, int delta) {
	}

	public static void setPage(String id) {
		THC.append("setting current page to: " + id);
		if (id.equals("splash")) {
			currentPage = splash;
		} else if (id.equals("mainMenu")) {
			currentPage = mainMenu;
		} else if (id.equals("loadingScreen")) {
			currentPage = loadingScreen;
		} else if (id.equals("game")) {
			currentPage = game;
		} 
		alpha = 1;
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		//g.scale(1f, 1f);
		
		currentPage.render(container, g);

		if (alpha > 0) {
			g.setColor(new Color(0, 0, 0, alpha -= 0.025));
			g.fillRect(0, 0, THApp.app.getWidth(), THApp.app.getHeight());
		}
		
		if (showConsole)
			THC.render(container, g);
	}

	/**
	 * @see org.newdawn.slick.InputListener#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (c == '`'){
			THC.setFocus(!showConsole);
			showConsole = !showConsole;
		}
		currentPage.keyPressed(key, c);
	}

	/**
	 * @see org.newdawn.slick.InputListener#keyReleased(int, char)
	 */
	public void keyReleased(int key, char c) {
		currentPage.keyReleased(key, c);
	}

	public static void main(String args[]) {
		try {
			app = new AppGameContainer(new ScalableGame(new THApp(
					"Touhou Project Doujin 12.1"), 640, 480, true));
			
			app.setDisplayMode(640, 480, false);
			//app.setDisplayMode(960, 720, false);
			//app.setDisplayMode(1024, 768, false);
			
			CLIP_X = (int) (app.getWidth() * 0.046875);
			CLIP_Y = (int) (app.getHeight() * 0.03125);
			CLIP_WIDTH = (int) (app.getWidth() * 0.6015625);
			CLIP_HEIGHT = (int) (app.getHeight() * 0.9375);
			app.setShowFPS(false);
			app.setAlwaysRender(false);
			app.setUpdateOnlyWhenVisible(true);
			app.setClearEachFrame(false);
			app.setTargetFrameRate(60);
			app.setSmoothDeltas(true);
			app.setMusicOn(true);
			app.setVerbose(true);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void componentActivated(AbstractComponent source) {
		// TODO Auto-generated method stub
		
	}
}
