package th121;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;


public class THSplash extends ScreenPage{
	private Image backgroundImage;
	private Image black;
	
	public THSplash(){
		try{
			backgroundImage = new Image("data/splash.png");
			black = new Image("data/black.png");
			black.setAlpha(0);
		}catch(SlickException ex){
			ex.printStackTrace();
		}
		ERM.set("data/main_menu.rlist");
		backgroundImage.setAlpha(0);
		LoadingIndicator.reset();
	}

	@Override
	public void render(GameContainer container, Graphics g)
			{
		g.clear();
		backgroundImage.draw(0, 0);
		if (backgroundImage.getAlpha() < 1.0){
			backgroundImage.setAlpha(backgroundImage.getAlpha() + 0.01f);
		}else{			
			LoadingIndicator.draw(g);

			LoadingIndicator.draw(g);
			if (ERM.isDoneLoading()) {
				black.setAlpha(black.getAlpha() + 0.02f);

				if (black.getAlpha() >= 1) {
					THApp.setPage("mainMenu");
				}

				g.drawImage(black, 0, 0, container.getWidth(), container.getHeight(), 
						0, 0, 4, 4);
			} else {
				ERM.loadNext();
			}
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
