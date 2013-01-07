package th121;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


abstract class ScreenPage {
	private ScreenPage instance;
	
	public abstract void render(GameContainer container, Graphics g);
	
	/**
	 * @see org.newdawn.slick.InputListener#keyPressed(int, char)
	 */
	public abstract void keyPressed(int key, char c);

	/**
	 * @see org.newdawn.slick.InputListener#keyReleased(int, char)
	 */
	public abstract void keyReleased(int key, char c);
}
