/**
 * Main game
 */

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class DanmakuMain extends BasicGameState{
	public static ArrayList<Bullet> bullets;
	private static CallableRenderer renderer;
	
	@Override
	public int getID() {
		return 2;
	}

	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		renderer = new CallableRenderer();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		renderer.call();
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}
	
	class CallableRenderer extends SlickCallable {
		float a = 0, b = 0, c = 0, d = 0;
		float bgCord = 0;

		@Override
		protected void performGLOperations() throws SlickException {
			// super.leaveSafeBlock();
			// super.enterSafeBlock();
			drawBackground();
			drawBullets();
			drawEffects();
		}

		public void drawEffects() {
		}

		public void drawBackground() {
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
			ERM.getImage("game_back").bind();
			
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
			GL11.glScissor(30, 480 - 15 - 450, 385, 450);

			// g.setClip(30, 15, 385, 450);
		}

		public void drawBullets() {
			// Bind Texture
			ERM.getImage("bullet_main").bind();

			Iterator<Bullet> it = bullets.iterator();

			// //////////////////////////////////////////////////////////////

			while (it.hasNext()) {
				Bullet bullet = it.next();

				if (bullet.x < 0 || bullet.x > 450 || bullet.y < 0
						|| bullet.y > 500) {
					it.remove();
					continue;
				}

				GL11.glTranslatef(bullet.x, bullet.y, 0);
				GL11.glRotatef(180, 0, 1, 0);

				GL11.glRotatef(bullet.degrees - 90, 0.0f, 0.0f, 1.0f);

				GL11.glBegin(GL11.GL_QUADS);
				{
					GL11.glTexCoord2f(4 * 0.0625f,
							5 * 0.0625f); // Map
					GL11.glVertex2f(8, 8); // draw
					// POINT TWO
					GL11.glTexCoord2f(3 * 0.0625f,
							5 * 0.0625f); //
					GL11.glVertex2f(-8, 8); // draw point

					GL11.glTexCoord2f(3 * 0.0625f,
							4 * 0.0625f); // Map
					GL11.glVertex2f(-8, -8); // draw point at

					GL11.glTexCoord2f(4 * 0.0625f,
							4 * 0.0625f); //
					GL11.glVertex2f(8, -8); // draw point
					// /////////////
				}
				GL11.glEnd();

				GL11.glRotatef(-bullet.degrees + 90, 0.0f, 0.0f, 1.0f);

				GL11.glRotatef(-180, 0, 1, 0);
				GL11.glTranslatef(-bullet.x, -bullet.y, 0);

				bullet.move();

//				if (bullet.ID < 3) {
//					bullet.degrees = (bullet.degrees + 0.5f) % 360;
//				} else {
//					bullet.degrees = (bullet.degrees - 0.5f + 360) % 360;
//				}

			}
		}

		/**
		 * Sets rendering mode
		 * 
		 * @param mode
		 */
		public void setDrawMode(int mode) {
			SGL GL = Renderer.get();

			if (mode == Graphics.MODE_NORMAL) {
				GL.glEnable(SGL.GL_BLEND);
				GL.glColorMask(true, true, true, true);
				GL.glBlendFunc(SGL.GL_SRC_ALPHA, SGL.GL_ONE_MINUS_SRC_ALPHA);
			}
			if (mode == Graphics.MODE_ALPHA_MAP) {
				GL.glDisable(SGL.GL_BLEND);
				GL.glColorMask(false, false, false, true);
			}
			if (mode == Graphics.MODE_ALPHA_BLEND) {
				GL.glEnable(SGL.GL_BLEND);
				GL.glColorMask(true, true, true, false);
				GL.glBlendFunc(GL11.GL_DST_ALPHA, GL11.GL_ONE_MINUS_DST_ALPHA);
			}
			if (mode == Graphics.MODE_COLOR_MULTIPLY) {
				GL.glEnable(SGL.GL_BLEND);
				GL.glColorMask(true, true, true, true);
				GL.glBlendFunc(GL11.GL_ONE_MINUS_SRC_COLOR, GL11.GL_SRC_COLOR);
			}
			if (mode == Graphics.MODE_ADD) {
				GL.glEnable(SGL.GL_BLEND);
				GL.glColorMask(true, true, true, true);
				GL.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
			}
			if (mode == Graphics.MODE_SCREEN) {
				GL.glEnable(SGL.GL_BLEND);
				GL.glColorMask(true, true, true, true);
				GL.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);
			}
		}
	}

}
