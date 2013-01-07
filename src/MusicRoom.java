

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.SelectTransition;

public class MusicRoom extends BasicGameState implements ComponentListener{
    private StateBasedGame game;
    private MouseOverArea backButton;
    private Image background;
    
    @Override
    public int getID() {
	// TODO Auto-generated method stub
	return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
	    throws SlickException {
	
	this.game = game;
	
	backButton = new MouseOverArea(container, new Image("resources/buttonback.jpg"), 50, 50, 250, 100, this);
	backButton.setNormalColor(new Color(1,1,1,0.4f));
	backButton.setMouseOverColor(new Color(1,1,1,0.9f));
	
	background = new Image("resources/musicroom.jpg");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
	    throws SlickException {
	background.draw();
	g.drawString("nothing here yet.  wanna help me choose music?", 100, 300);
	backButton.render(container, g);
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
	    throws SlickException {	
    }

    @Override
    public void componentActivated(AbstractComponent source) {
	game.enterState(1, new FadeOutTransition(Color.black, 500), new FadeInTransition(Color.black, 500));
    }

}
