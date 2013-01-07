

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class End extends BasicGameState{

    @Override
    public int getID() {
	// TODO Auto-generated method stub
	return 4;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game)
	    throws SlickException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g)
	    throws SlickException {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta)
	    throws SlickException {
	System.exit(0);	
    }

}
