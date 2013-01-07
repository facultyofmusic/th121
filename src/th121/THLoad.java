/**
 * Copyright (c) 2010 L.A.N.E.X Doujin team.
 * 
 * TH Loading Screen
 * 
 * A loading screen that uses deferred loading to cope with the ERM.
 * 
 * @author Daniel Gen li
 */
package th121;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class THLoad extends ScreenPage {
	private static float alpha = 0;
	static String stateToEnter;
	static Image black;

	public THLoad() {
		try {
			black = new Image("data/images/black.png");
		} catch (SlickException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Sets up the loading sequence
	 * 
	 * @param state
	 *            The state to enter after loading is done.
	 * @param resourceListFile
	 *            The file to tell ERM what to load
	 */
	public static void set(String state, String listAddress) {
		LoadingIndicator.reset();
		stateToEnter = state;
		alpha = 0;
		ERM.set(listAddress);
	}

	/**
	 * Renders, but at the same time loads
	 */
	@Override
	public void render(GameContainer container, Graphics g) {
		g.clear();
		LoadingIndicator.draw(g);

		g.drawImage(black, 0, 0, container.getWidth(), 460, 0, 0, 4, 4);
		g.clear();

		LoadingIndicator.draw(g);
		if (ERM.isDoneLoading()) {
			g.setColor(new Color(0, 0, 0, alpha += 0.02));

			if (alpha > 1) {
				System.out.println("Entering " + stateToEnter);
				THApp.setPage(stateToEnter);
			}

			g.fillRect(0, 0, THApp.app.getWidth(), THApp.app.getHeight());
		} else {
			ERM.loadNext();
		}
	}

	@Override
	public void keyPressed(int key, char c) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(int key, char c) {
		// TODO Auto-generated method stub

	}

}
