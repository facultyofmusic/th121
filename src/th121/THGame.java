package th121;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import th121.btype.*;
import th121.stage.TestStage;

public class THGame extends ScreenPage{
	public static THCallableRenderer renderer;
	public static ArrayList<AbstractBulletType> bulletList;
	public static THStage currentStage = new TestStage();
	
	public THGame(){
		renderer = new THCallableRenderer();
		bulletList = new ArrayList<AbstractBulletType>();
	}

	@Override
	public void render(GameContainer container, Graphics g) {
		// PROCESSING
		currentStage.counterCall(this);
		
		g.clear();
		g.setColor(Color.white);
		g.drawString("IN GAME!!!", 100, 100);
		try {
			renderer.call();
			THCallableRenderer.leaveSafeBlock();
		} catch (SlickException ex) {
			ex.printStackTrace();
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
