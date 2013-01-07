package th121;

import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

import th121.btype.*;


public class THCallableRenderer extends SlickCallable {
	float a = 0, b = 0, c = 0, d = 0;
	float bgCord = 0;

	@Override
	protected void performGLOperations() throws SlickException {
		
		THGame.currentStage.drawBackground();
		drawBullets(THGame.bulletList);
	}

	public void drawBullets(ArrayList <AbstractBulletType> bulletList) {
		// Bind Texture
		ERM.getImage("bullet_main.png").bind();

		Iterator<AbstractBulletType> it = bulletList.iterator();

		while (it.hasNext()) {
			AbstractBulletType bullet = it.next();

			if (bullet.x < 0 || bullet.x > 450 || bullet.y < 0
					|| bullet.y > 500) {
				it.remove();
				continue;
			}

			GL11.glTranslatef(bullet.x, bullet.y, 0);
			GL11.glRotatef(180, 0, 1, 0);

			GL11.glRotatef(bullet.direction - 90, 0.0f, 0.0f, 1.0f);

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

			GL11.glRotatef(-bullet.direction + 90, 0.0f, 0.0f, 1.0f);

			GL11.glRotatef(-180, 0, 1, 0);
			GL11.glTranslatef(-bullet.x, -bullet.y, 0);

			bullet.move();
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
