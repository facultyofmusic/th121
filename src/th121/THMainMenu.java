package th121;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.state.StateBasedGame;

public class THMainMenu extends ScreenPage {
	ScreenObject start, extraStart, practiceStart, spellPractice, replay,
			statistics, musicRoom, help, options, exit, blackGradient,
			titleImage, startBackground, musicVol, sfxVol, keyConfig,
			fullScreen, defaultReset, whiteGradient, white, volumeInfo,
			fullScreenInfo, defaultInfo, testStage;
	ScreenObject[] menuList, optionList, optionInfoList;
	boolean startRender = false, enteringStart, quittingStart, enteringOptions,
			quittingOptions;
	int selectedMenuOption, selectedOptionOption, selectedStartOption;
	SnowEffect snow;

	/*
	 * 0 = menu 1 = start 2 3 4 5 6 7 8 = options 9
	 */
	int state = 0;

	/*
	 * 0 = choose difficulty 1 = choose character 2 = choose weapon
	 */
	int startState = 0;

	/*
	 * 0 = music volume 1 = sfx volume 2 = key configuration 3 = fullscreen 4 =
	 * default
	 */

	class Particle {
		float x, y;

		public Particle() {

		}

		public void draw(Graphics g) {
			y++;
			g.drawImage(
					ERM.getImage("menu_particle.png").getSubImage(64, 64, 64, 64),
					x, y);
		}
	}

	public THMainMenu() {
		// menu list objects
		start = new ScreenObject("menu_choices.png", 480, 150, 0, 1f);
		extraStart = new ScreenObject("menu_choices.png", 450, 182, 1, 0.6f);
		practiceStart = new ScreenObject("menu_choices.png", 430, 214, 2, 0.6f);
		spellPractice = new ScreenObject("menu_choices.png", 440, 246, 3, 0.6f);
		replay = new ScreenObject("menu_choices.png", 490, 278, 4, 0.6f);
		statistics = new ScreenObject("menu_choices.png", 470, 310, 5, 0.6f);
		musicRoom = new ScreenObject("menu_choices.png", 460, 342, 6, 0.6f);
		help = new ScreenObject("menu_choices.png", 500, 374, 7, 0.6f);
		options = new ScreenObject("menu_choices.png", 480, 406, 8, 0.6f);
		exit = new ScreenObject("menu_choices.png", 500, 438, 9, 0.6f);

		// menu list
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

		// option menu objects
		musicVol = new ScreenObject("menu_choices.png", 45, 300, 10, 1f);
		sfxVol = new ScreenObject("menu_choices.png", 45, 350, 11, 0.6f);
		keyConfig = new ScreenObject("menu_choices.png", 45, 400, 12, 0.6f);
		fullScreen = new ScreenObject("menu_choices.png", 45, 450, 13, 0.6f);
		defaultReset = new ScreenObject("menu_choices.png", 45, 500, 14, 0.6f);

		// option list
		optionList = new ScreenObject[5];
		optionList[0] = musicVol;
		optionList[1] = sfxVol;
		optionList[2] = keyConfig;
		optionList[3] = fullScreen;
		optionList[4] = defaultReset;
		for(int x = 0; x < optionList.length; x++){
			optionList[x].setDesAlpha(0);
		}

		// option infos
		volumeInfo = new ScreenObject("volumeInfo.png", 270, 200, -1, 0f);
		fullScreenInfo = new ScreenObject("fullScreenInfo.png", 270, 200, -1, 0f);
		defaultInfo = new ScreenObject("defaultInfo.png", 270, 200, -1, 0f);

		optionInfoList = new ScreenObject[5];
		optionInfoList[0] = fullScreenInfo;
		optionInfoList[1] = defaultInfo;
		optionInfoList[2] = volumeInfo;
		optionInfoList[3] = fullScreenInfo;
		optionInfoList[4] = defaultInfo;

		// others
		blackGradient = new ScreenObject("black_gradient.png", -50, 0, -1, 1f);
		blackGradient.setWidth(1);
		blackGradient.setHeight(640);

		whiteGradient = new ScreenObject("white_gradient.png", 100, 100, -1, 1f);
		whiteGradient.setWidth(250);
		whiteGradient.setHeight(40);
		whiteGradient.setX(20);
		whiteGradient.setY(500);
		

		white = new ScreenObject("white.png", 200, 200, -1, 1f);
		white.setX(270);
		white.setY(600);
		white.setWidth(320);
		white.setHeight(350);
		white.setAlpha(0.82f);
		
		testStage = new ScreenObject("test_stage.png", 180, 400, -1, 0f);

		// title
		titleImage = new ScreenObject("menu_title.png", 30, 50, -1, 0f);
		titleImage.setDesAlpha(1);
		titleImage.setAddModeWhenRender(true);

		// start background
		startBackground = new ScreenObject("menu_start_back.png", 0, 0, -1, 0f);

		snow = new SnowEffect(70);

		mainReset();
		Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
		setMenuAlpha();
	}

	public void enter(GameContainer container, StateBasedGame game)
			throws SlickException {
		// ERM.getMusic("menu_music").loop();
		startRender = true;
	}

	public void leave(GameContainer container, StateBasedGame game)
			throws SlickException {
	}

	public void mainReset() {
		startRender = true;
		for (int x = 0; x < menuList.length; x++) {
			menuList[x].setAlpha(x * -0.2f);
		}
	}

	@Override
	public void render(GameContainer container, Graphics g) {

		if (startRender) {


				//System.out.println("State = " + state);
				g.drawImage(ERM.getImage("menu_back.png"), 0, 0);
				startBackground.draw(g);
				blackGradient.draw(g);
				whiteGradient.draw(g);
				white.draw(g);
				

				// menu objects
				for (int x = 0; x < menuList.length; x++) {
					menuList[x].draw(g);
				}

				// options objects
				for (int x = 0; x < optionList.length; x++) {
					optionList[x].draw(g);
					optionInfoList[x].draw(g);
				}
				
				// in start
				startBackground.draw(g);
				testStage.draw(g);
				

				titleImage.draw(g);
				
			// snow
			snow.render(g);

		}// if (startRender)
	}

	void enterOptions() {
		blackGradient.setDesWidth(840);

		for (ScreenObject o : menuList) {
			o.setDesX(640);
		}

		titleImage.setDesX(40);
		titleImage.setDesY(30);
		titleImage.setDesWidth(200);
		titleImage.setDesHeight(50);

		white.setDesY(96);

		// optionList set
		musicVol.setDesY(100);
		sfxVol.setDesY(140);
		keyConfig.setDesY(180);
		fullScreen.setDesY(220);
		defaultReset.setDesY(260);
		for(int x = 0; x < optionList.length; x++){
			optionList[x].setDesAlpha(1);
		}

		enteringOptions = false;
		state = 8;
	}

	void enterStart() {
		titleImage.setDesX(400);
		titleImage.setDesY(30);
		titleImage.setDesWidth(200);
		titleImage.setDesHeight(50);
		
		testStage.setDesAlpha(1);
		testStage.setDesY(150);

		startBackground.setDesAlpha(1);
		enteringStart = false;
		state = 1;

	}

	void quitStart() {
		mainReset();
		
		System.out.println("Quitting start");

		// title
		titleImage.setDesX(30);
		titleImage.setDesY(50);
		titleImage.setDesWidth(454);
		titleImage.setDesHeight(119);

		testStage.setDesAlpha(0);
		testStage.setDesY(400);

		startBackground.setDesAlpha(0);
		quittingStart = false;
		state = 0;

	}

	void quitOptions() {
		start.setDesX(480);
		extraStart.setDesX(450);
		practiceStart.setDesX(430);
		spellPractice.setDesX(440);
		replay.setDesX(490);
		statistics.setDesX(470);
		musicRoom.setDesX(460);
		help.setDesX(500);
		options.setDesX(480);
		exit.setDesX(500);

		blackGradient.setDesWidth(1);
		white.setDesY(600);
		titleImage.setDesX(30);
		titleImage.setDesY(50);
		titleImage.setDesWidth(454);
		titleImage.setDesHeight(119);

		// optionList reset
		musicVol.setDesY(300);
		sfxVol.setDesY(350);
		keyConfig.setDesY(400);
		fullScreen.setDesY(450);
		defaultReset.setDesY(500);
		for(int x = 0; x < optionList.length; x++){
			optionList[x].setDesAlpha(0);
		}

		quittingOptions = false;
		state = 0;
	}

	@Override
	public void keyPressed(int code, char ch) {
		if (state == 0) {
			if (code == Input.KEY_UP) {
				selectedMenuOption--;
				ERM.sfx("select.se.ogg");
			}

			if (code == Input.KEY_DOWN) {
				selectedMenuOption++;
				ERM.sfx("select.se.ogg");
			}
			selectedMenuOption = (selectedMenuOption + menuList.length)
			% menuList.length;

			if (code == Input.KEY_Z) {
				ERM.sfx("ok.se.ogg");

				switch (selectedMenuOption) {
				case 0:
					enterStart();
					break;
				case 8:
					enterOptions();
					setOptionAlpha();
					break;
				case 9:
					THApp.app.exit();
				}
			}

			if (code == Input.KEY_X) {
				ERM.sfx("select.se.ogg");
				if (selectedMenuOption == 9) {
					System.exit(0);
				}
				selectedMenuOption = 9;
			}
		} else if (state == 1) {
			if (code == Input.KEY_X) {
				quitStart();
			}else if (code == Input.KEY_Z) {
				ERM.sfx("ok.se.ogg");
				THLoad.set("game", "data/teststage.rlist");
				THApp.setPage("loadingScreen");
			}
		} else if (state == 8) {
			if (code == Input.KEY_UP) {
				selectedOptionOption--;
				ERM.sfx("select.se.ogg");
			}

			if (code == Input.KEY_DOWN) {
				selectedOptionOption++;
				ERM.sfx("select.se.ogg");
			}
			selectedOptionOption = (selectedOptionOption + optionList.length)
			% optionList.length;

			if (code == Input.KEY_X) {
				System.out.println("Quit options");
				quitOptions();
			}
			if (state == 8)
				setOptionAlpha();
		}



		setMenuAlpha();
	}

	@Override
	public void keyReleased(int code, char ch) {

	}

	void setMenuAlpha() {
		// main menu
		for (int x = 0; x < menuList.length; x++) {
			menuList[x].setDesAlpha(0.6f);
		}
		menuList[selectedMenuOption].setDesAlpha(1);

	}
	
	void setOptionAlpha(){

		// options objects
		for (int x = 0; x < optionList.length; x++) {
			optionList[x].setDesAlpha(0.6f);
			optionInfoList[x].setDesAlpha(0);
			optionInfoList[x].setDesY(white.getDesY());
		}
		optionList[selectedOptionOption].setDesAlpha(1);
		optionInfoList[selectedOptionOption].setDesAlpha(1);
		whiteGradient.setDesY(optionList[selectedOptionOption].getDesY() - 4);
	}

	/**
	 * @see org.newdawn.slick.InputListener#mouseClicked(int, int, int, int)
	 */
	public void mouseClicked(int button, int x, int y, int clickCount) {
		start.setDesX(x);
		start.setDesY(y);
	}
}
