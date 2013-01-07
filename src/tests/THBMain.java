package tests;
import java.util.ArrayList;
import java.util.Iterator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.opengl.SlickCallable;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.pbuffer.FBOGraphics;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;

public class THBMain extends BasicGame {
	private SpriteSheet sheet;
	private Image image, back, bg, bg2, black;
	private ArrayList<Bullet> bullets;
	private SlickCallable Callable;
	private long counter = 0;
	private float dAngle = 0, dDelta = 0;
	private boolean deltaIncreasing = false;
	public static AppGameContainer app;
	public static int CLIP_X, CLIP_Y, CLIP_WIDTH, CLIP_HEIGHT;

	private class Bullet {
		float x, y, degrees, speed;
		int ID;

		public Bullet(float x, float y, float degrees, float speed, int ID) {
			this.x = x;
			this.y = y;
			this.degrees = degrees;
			this.speed = speed;
			this.ID = ID;
		}

		public void move() {
			float dx, dy, dd = degrees % 90;

			if (degrees < 90) {
				dx = (float) Math.cos(Math.toRadians(dd)) * speed;
				dy = (float) Math.sin(Math.toRadians(dd)) * speed;

				x += dx;
				y -= dy;
			} else if (degrees < 180) {
				dx = (float) Math.sin(Math.toRadians(dd)) * speed;
				dy = (float) Math.cos(Math.toRadians(dd)) * speed;

				x -= dx;
				y -= dy;
			} else if (degrees < 270) {
				dx = (float) Math.cos(Math.toRadians(dd)) * speed;
				dy = (float) Math.sin(Math.toRadians(dd)) * speed;

				x -= dx;
				y += dy;
			} else {
				dx = (float) Math.sin(Math.toRadians(dd)) * speed;
				dy = (float) Math.cos(Math.toRadians(dd)) * speed;

				x += dx;
				y += dy;
			}
		}
	}

	public void counterCall() {
		counter++;

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
		
		if (counter % 2 == -1){
			for(int x = 0; x < 360; x += 60){
				bullets.add(new Bullet(220, 120,
						(x + ((dAngle += dDelta)) % 360 + 360)%360, 2f, 3));
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

		if (counter % 1 == 0) {
			for (int x = 0; x < 15; x += 50) {
				bullets.add(new Bullet(220, 120,
						(float) (counter * 2 + 120 + x) % 360, 2.6f, 1));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 2 + 240 + x) % 360, 2.6f, 1));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 2 + 60 + x) % 360, 2.6f, 1));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 2 + 180 + x) % 360, 2.6f, 1));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 2 + 300 + x) % 360, 2.6f, 1));

				bullets.add(new Bullet(220, 120, (float) counter * 5 % 360 + x,
						3.6f, 2));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 18 + 120 + x) % 360, 3.6f, 2));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 18 + 240 + x) % 360, 3.6f, 2));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 18 + 60 + x) % 360, 3.6f, 2));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 18 + 180 + x) % 360, 3.6f, 2));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 18 + 300 + x) % 360, 3.6f, 2));

				bullets.add(new Bullet(220, 120, (float) counter * 9 % 360 + x,
						4.6f, 3));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 9 + 120 + x) % 360, 4.6f, 3));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 9 + 240 + x) % 360, 4.6f, 3));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 9 + 60 + x) % 360, 4.6f, 3));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 9 + 180 + x) % 360, 4.6f, 3));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 9 + 300 + x) % 360, 4.6f, 3));

				bullets.add(new Bullet(220, 120, (float) counter * 3 % 360 + x,
						3f, 4));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 3 + 120 + x) % 360, 3f, 4));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 3 + 240 + x) % 360, 3f, 4));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 3 + 60 + x) % 360, 3f, 4));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 3 + 180 + x) % 360, 3f, 4));
				bullets.add(new Bullet(220, 120,
						(float) (counter * 3 + 300 + x) % 360, 3f, 4));
				bullets.add(new Bullet(220, 120, (float) counter * 2 % 360 + x,
						3f, 4));
			}
		}
	}

	class CallableRenderer extends SlickCallable {
		float a = 0, b = 0, c = 0, d = 0;
		float bgCord = 0;

		@Override
		protected void performGLOperations() throws SlickException {
			// drawBackground3D();
			// super.leaveSafeBlock();
			// super.enterSafeBlock();
			drawBackground();
			drawBullets();
			drawEffects();
		}

		public void drawEffects() {
		}

		public void drawBackground3D() {
			// Bind Texture
			Texture tex = bg2.getTexture(); // get Texture ref from Image
			tex.bind(); // binds the texture

			GL11.glMatrixMode(GL11.GL_PROJECTION);
			GL11.glLoadIdentity();
			GL11.glFrustum(-1.0, 1, -1.0, 1.0, 5, 100);
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
			GL11.glTranslatef(0, 0, -5.01f);

			// Draw Quad
			GL11.glBegin(SGL.GL_QUADS); // beings GL mode to draw quads
			// TRI-1
			// POINT ONE
			GL11.glTexCoord2f(0.0f, 0.0f);
			GL11.glVertex3f(-1.0f, -1.0f, 0);

			// POINT TWO
			GL11.glTexCoord2f(1.0f, 0.0f);
			GL11.glVertex3f(1.0f, -1.0f, 0);

			// POINT THREE
			GL11.glTexCoord2f(1.0f, 1.0f);
			GL11.glVertex3f(1.0f, -1.0f, -15);

			// POINT FOUR
			GL11.glTexCoord2f(0.0f, 1.0f);
			GL11.glVertex3f(-1.0f, -1.0f, -15);

			GL11.glEnd(); // END GL mode to draw Quads
		}

		public void drawBackground() {
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
			back.bind();
			
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
			GL11.glScissor(CLIP_X, app.getHeight() - CLIP_Y - CLIP_HEIGHT, CLIP_WIDTH, CLIP_HEIGHT);

			// g.setClip(30, 15, 385, 450);
			
			if (counter < 100) {
				Color c = Color.white;
				c.a = counter / 100.0f;
				c.bind();
			}

			bg.bind();

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

			bg2.bind();
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

		public void drawBullets() {
			// Bind Texture
			image.bind();

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

	public THBMain() {
		super("SimpleTest");
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		// initialize sheet
		image = new Image("data/general2.png", Color.black);
		back = new Image("data/app_back.png");
		//dbg = new FBOGraphics(back);
		bg = new Image("data/back.png");
		bg2 = new Image("data/back2.png");
		Callable = new CallableRenderer();
		bullets = new ArrayList<Bullet>();
		black = new Image("data/black.png");


		Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		counterCall();
		Callable.call();
		g.drawString("" + bullets.size(), 100, 100);
	}

	public void keyPressed(int key, char c) {
		System.out.println("CODE: " + key + "  CHAR: " + c);
	}

	public static void main(String[] args) {
		try {
			app = new AppGameContainer(new ScalableGame(new THBMain(), 640, 480, true));
			app.setDisplayMode(640, 480, false);
			CLIP_X = (int)(app.getWidth()*0.046875);
			CLIP_Y = (int)(app.getHeight() * 0.03125);
			CLIP_WIDTH = (int) (app.getWidth()*0.6015625);
			CLIP_HEIGHT = (int)(app.getHeight() * 0.9375);
			app.setShowFPS(true);
			app.setAlwaysRender(true);
			app.setClearEachFrame(false);
			app.setTargetFrameRate(60);
			app.setSmoothDeltas(false);
			app.setMusicOn(true);
			app.setVerbose(true);
			app.setVSync(true);
			app.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
}