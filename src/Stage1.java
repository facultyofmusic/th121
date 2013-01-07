import org.lwjgl.opengl.GL11;

public class Stage1 implements Stage {
	private int bgCord = 0;

	@Override
	public void counterCall() {
		// TODO Auto-generated method stub

	}

	@Override
	public void drawBackground() {
		ERM.getImage("stage1a").bind();

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

		ERM.getImage("stage1b").bind();
		
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
