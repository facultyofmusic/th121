import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class MainMenu extends BasicGameState {
	ScreenObject start, extraStart, practiceStart, spellPractice, replay, statistics,
			musicRoom, help, options, exit;
	ScreenObject[] menuList;
	float titleX = 50, titleY = 30, titleA = 0, startAlpha = 0, startScale = 1.5f;
	boolean titleAP = true, startRender = false, enteringStart, quittingStart;
	int selectedMenuOption;
	StateBasedGame THSGS;
	GameContainer container;
	SnowEffect snow;
	
	/*
	 * 0 = menu
	 * 1 = start
	 */
	int state = 0;
	
	/*
	 * 0 = choose difficulty
	 * 1 = choose character
	 * 2 = choose weapon
	 */
	int startState = 0;

	class Particle {
		float x, y;

		public Particle() {

		}

		public void draw(Graphics g) {
			y++;
			g.drawImage(ERM.getImage("menu_particle").getSubImage(64, 64, 64,
					64), x, y);
		}
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.container = container;
		THSGS = game;

		start = new ScreenObject("menu_choices", 480, 150, 0, 1f);
		extraStart = new ScreenObject("menu_choices", 450, 182, 1, 0.6f);
		practiceStart = new ScreenObject("menu_choices", 430, 214, 2, 0.6f);
		spellPractice = new ScreenObject("menu_choices", 440, 246, 3, 0.6f);
		replay = new ScreenObject("menu_choices", 490, 278, 4, 0.6f);
		statistics = new ScreenObject("menu_choices", 470, 310, 5, 0.6f);
		musicRoom = new ScreenObject("menu_choices", 460, 342, 6, 0.6f);
		help = new ScreenObject("menu_choices", 500, 374, 7, 0.6f);
		options = new ScreenObject("menu_choices", 480, 406, 8, 0.6f);
		exit = new ScreenObject("menu_choices", 500, 438, 9, 0.6f);

		menuList = new ScreenObject[10];
		menuList[0] = start;
		menuList[1] = extraStart;
		menuList[2] = practiceStart;
		menuList[3] = spellPractice;
		menuList[4] = replay;
		menuList[5] = statistics;
		menuList[6] = musicRoom;
		menuList[7] = help;
		menuList[8] = options;
		menuList[9] = exit;

		snow = new SnowEffect(70);

		mainReset();
		Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
	}

	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		//ERM.getMusic("menu_music").loop();
		startRender = true;
	}

	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	public void mainReset() {
		titleA = 0;
		titleY = 30;
		titleX = 50;
		startRender = true;
		for (int x = 0; x < menuList.length; x++) {
			menuList[x].setAlpha(x * -0.2f);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {


		if (startRender) {

			if (state == 0) {
				//System.out.println("State = 0;");
				g.drawImage(ERM.getImage("menu_back"), 0, 0);
				
				// manipulate title image
				if (titleAP) {
					titleA += 0.005;
					if (titleA >= 1) {
						titleAP = false;
					}
				} else {
					titleA -= 0.005;
					if (titleA <= 0.5) {
						titleAP = true;
					}
				}

				if (titleY > 30) {
					titleY -= 0.2f;
				}

				g.setDrawMode(Graphics.MODE_ADD);
				g.drawImage(ERM.getImage("menu_title"), titleX, titleY,
						new Color(titleA, titleA, titleA));
				g.setDrawMode(Graphics.MODE_NORMAL);

				for (int x = 0; x < menuList.length; x++) {
					menuList[x].draw(g, selectedMenuOption == x);
				}
				
				if (enteringStart){
					Image startImage = ERM.getImage("menu_start_back");
					startImage.setAlpha(startAlpha += 0.1f);
					g.drawImage(startImage, 0, 0);
					if (startAlpha >= 1){
						mainReset();
						enteringStart = false;
						state = 1;
					}
				}
			}else if (state == 1){
				g.drawImage(ERM.getImage("menu_start_back"), 0, 0);
				
				if (quittingStart){
					g.drawImage(ERM.getImage("menu_back"), 0, 0);
					Image startImage = ERM.getImage("menu_start_back");
					startImage.setAlpha(startAlpha -= 0.1f);
					g.drawImage(startImage, 0, 0);
					if (startAlpha <= 0){
						quittingStart = false;
						state = 0;
					}
				}
			}
			// snow
			snow.render(g);
			
			
		}//if (startRender)
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
	}

	@Override
	public void keyPressed(int code, char ch) {
		if (state == 0) {
			if (code == Input.KEY_UP) {
				selectedMenuOption--;
				ERM.sfx("se_select00");
			}

			if (code == Input.KEY_DOWN) {
				selectedMenuOption++;
				ERM.sfx("se_select00");
			}
			
			if (code == Input.KEY_Z) {
				ERM.sfx("se_ok00");

				switch (selectedMenuOption) {
				case 0:
					String[] images = {"game_back"};
					THLoad.set(2, new String[0], images, new String[0], new String[0]);
					THSGS.enterState(-1, new FadeOutTransition(Color.black, 500), null);
					//enteringStart = true;
					break;
				case 9:
					THSGS.getContainer().exit();
				}
			}
			
			if (code == Input.KEY_X){
				ERM.sfx("se_select00");
				selectedMenuOption = 9;
			}
		}else if (state == 1){
			if (code == Input.KEY_X){
				quittingStart = true;
			}
		}
		

		selectedMenuOption = (selectedMenuOption + menuList.length)
				% menuList.length;
		setMenuAlpha();
	}
	
	void setMenuAlpha(){
		for(int x = 0; x < menuList.length; x++){
			menuList[x].setDesAlpha(0.6f);
		}
		menuList[selectedMenuOption].setDesAlpha(1);
	}


	/**
	 * @see org.newdawn.slick.InputListener#mouseClicked(int, int, int, int)
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
		start.setDesX(x);
		start.setDesY(y);
	}
}
