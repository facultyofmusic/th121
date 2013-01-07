package th121.stage;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import th121.ERM;
import th121.THApp;
import th121.THGame;
import th121.THStage;
import th121.btype.*;

public class TestStage implements THStage{
	int sMasterC;
	boolean deltaIncreasing = true;
	float dDelta = 0;
	float dAngle = 0;
	int bgCord = 0;
	int cc = 0;
	
	
	public void counterCall(THGame game) {
		ArrayList <AbstractBulletType> bulletList = game.bulletList;
		
		sMasterC++;

		if (deltaIncreasing){
			dDelta += 0.02f;
			if (dDelta > 5){
				deltaIncreasing = false;
			}
		}else{
			dDelta -= 0.02f;
			if (dDelta < -5){
				deltaIncreasing = true;
			}
		}
		
		if (sMasterC % 2 == 1){
			for(int x = 0; x < 360; x += 60){
				bulletList.add(new StraightBullet(3, 220, 120, 2f,
						(x + ((dAngle += dDelta)) % 360 + 360)%360));
//				bullets.add(new Bullet(220, 120,
//						((x + ((dAngle)) % 360 + 360))%360, 3.2f, 3));
//				bullets.add(new Bullet(220, 120,
//						((x + ((dAngle)) % 360 + 360))%360, 3.4f, 3));
//				bullets.add(new Bullet(220, 120,
//						((x + ((dAngle)) % 360 + 360)*1.5f)%360, 3.6f, 3));
//				bullets.add(new Bullet(220, 120,
//						(x + ((360 - dAngle)) % 360 + 360)%360, 2.8f, 3));
			}
		}

		if (sMasterC % 1 == -1) {
			for (int x = 0; x < 15; x += 50) {
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 2 + 120 + x) % 360, 2.6f, 1));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 2 + 240 + x) % 360, 2.6f, 1));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 2 + 60 + x) % 360, 2.6f, 1));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 2 + 180 + x) % 360, 2.6f, 1));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 2 + 300 + x) % 360, 2.6f, 1));

				bulletList.add(new StraightBullet(220, 120, (float) sMasterC * 5 % 360 + x,
						3.6f, 2));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 18 + 120 + x) % 360, 3.6f, 2));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 18 + 240 + x) % 360, 3.6f, 2));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 18 + 60 + x) % 360, 3.6f, 2));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 18 + 180 + x) % 360, 3.6f, 2));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 18 + 300 + x) % 360, 3.6f, 2));

				bulletList.add(new StraightBullet(220, 120, (float) sMasterC * 9 % 360 + x,
						4.6f, 3));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 9 + 120 + x) % 360, 4.6f, 3));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 9 + 240 + x) % 360, 4.6f, 3));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 9 + 60 + x) % 360, 4.6f, 3));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 9 + 180 + x) % 360, 4.6f, 3));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 9 + 300 + x) % 360, 4.6f, 3));

				bulletList.add(new StraightBullet(220, 120, (float) sMasterC * 3 % 360 + x,
						3f, 4));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 3 + 120 + x) % 360, 3f, 4));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 3 + 240 + x) % 360, 3f, 4));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 3 + 60 + x) % 360, 3f, 4));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 3 + 180 + x) % 360, 3f, 4));
				bulletList.add(new StraightBullet(220, 120,
						(float) (sMasterC * 3 + 300 + x) % 360, 3f, 4));
				bulletList.add(new StraightBullet(220, 120, (float) sMasterC * 2 % 360 + x,
						3f, 4));
			}
		}
	}
	
	
	public void drawBackground() {
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		ERM.getImage("game_background.png").bind();
		
		GL11.glBegin(GL11.GL_QUADS);
		{
			// left
			GL11.glTexCoord2f(0, 0); // Map
			GL11.glVertex2f(0, 0); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0, 0.937f); //
			GL11.glVertex2f(0, 480); // draw point

			GL11.glTexCoord2f(0.0612f, 0.937f); // Map
			GL11.glVertex2f(30, 480); // draw

			GL11.glTexCoord2f(0.0612f, 0); //
			GL11.glVertex2f(30, 0); // draw point
			
			
			// up
			GL11.glTexCoord2f(0, 0.937f); // Map
			GL11.glVertex2f(30, 0); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0, 0.9686f); //
			GL11.glVertex2f(30, 15); // draw point

			GL11.glTexCoord2f(0.75f, 0.9686f); // Map
			GL11.glVertex2f(415, 15); // draw

			GL11.glTexCoord2f(0.75f, 0.937f); //
			GL11.glVertex2f(415, 0); // draw point
			
			
			
			// down
			GL11.glTexCoord2f(0, 0.9686f); // Map
			GL11.glVertex2f(30, 465); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0, 1f); //
			GL11.glVertex2f(30, 480); // draw point

			GL11.glTexCoord2f(0.75f, 1f); // Map
			GL11.glVertex2f(415, 480); // draw

			GL11.glTexCoord2f(0.75f, 0.9686f); //
			GL11.glVertex2f(415, 465); // draw point

			
			
			// right
			GL11.glTexCoord2f(0.06125f, 0f); // Map
			GL11.glVertex2f(415, 0); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0.06125f, 0.937f); //
			GL11.glVertex2f(415, 480); // draw point

			GL11.glTexCoord2f(0.5f, 0.937f); // Map
			GL11.glVertex2f(640, 480); // draw

			GL11.glTexCoord2f(0.5f, 0f); //
			GL11.glVertex2f(640, 0); // draw point
		}
		GL11.glEnd();
		
		
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		GL11.glScissor(THApp.CLIP_X, THApp.app.getHeight() - THApp.CLIP_Y - THApp.CLIP_HEIGHT, THApp.CLIP_WIDTH, THApp.CLIP_HEIGHT);

		// g.setClip(30, 15, 385, 450);
		
		if (sMasterC < 100) {
			Color c = Color.white;
			c.a = sMasterC / 100.0f;
			c.bind();
		}

		ERM.getImage("teststage_back.png").bind();

		// stable layer
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0 * 0.0625f, 0 * 0.0625f); // Map
			GL11.glVertex2f(0, 0); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0 * 0.0625f, 16 * 0.0625f); //
			GL11.glVertex2f(0, 500); // draw point

			GL11.glTexCoord2f(16 * 0.0625f, 16 * 0.0625f); // Map
			GL11.glVertex2f(480, 500); // draw

			GL11.glTexCoord2f(16 * 0.0625f, 0 * 0.0625f); //
			GL11.glVertex2f(480, 0); // draw point
		}
		GL11.glEnd();

		ERM.getImage("teststage_back_2.png").bind();
		bgCord -= 1;
		if (bgCord == -256) {
			bgCord = 0;
		}

		// second layer
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glTexCoord2f(0, 0); // Map
			GL11.glVertex2f(bgCord, bgCord); // draw point at

			// POINT TWO
			GL11.glTexCoord2f(0, 4); //
			GL11.glVertex2f(bgCord, 1024 + bgCord); // draw point

			GL11.glTexCoord2f(3, 4); // Map
			GL11.glVertex2f(768 + bgCord, 1024 + bgCord); // draw

			GL11.glTexCoord2f(3, 0); //
			GL11.glVertex2f(768 + bgCord, bgCord); // draw point
		}
		GL11.glEnd();
	}
}
