package th121;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class SnowEffect {
	public SnowFlake[] snowFlakes;
	public static int snowLife = 100;
	
	public SnowEffect(int numOfFlakes){
		snowFlakes = new SnowFlake[numOfFlakes];
		for (int i = 0; i < snowFlakes.length; i++) {
			makeSnow(i, (int) (Math.random() * snowLife));
		}
	}
	
	public void render(Graphics g){
		//g.setDrawMode(Graphics.MODE_ADD);
		for (int i = 0; i < snowFlakes.length; i++) {
			snowFlakes[i].render(g);
			if (snowFlakes[i].life == 1){
				makeSnow(i, snowLife);
			}
		}
		g.setDrawMode(Graphics.MODE_NORMAL);
	}
	
	public void makeSnow(int i, int life) {
		float x = (float) (Math.random() * 640);
		float y = (float) (Math.random() * 480 + snowLife);
		
		snowFlakes[i] = new SnowFlake((float) (Math.random() * 640), (float) (Math.random() * 480 + 100),
				(float) (Math.random() * 4 - 2), (float) (Math.random()*0.6 + 0.2f), 0, (float) (Math.random()*0.5 + 0.5), life);
	}
	
	class SnowFlake{
		public int life;
		float x, y, rotation, angle, scale, alpha, dX, dY;
		
		public SnowFlake (float x, float y, float rotation, float scale, float dX, float dY, int life){
			this.x = x;
			this.y = y;
			this.dX = dX;
			this.dY = dY;
			this.rotation = rotation;
			this.scale = scale;
			this.life = life;
		}
		
		public void render(Graphics g){
			//g.drawOval(x, y, 30, 30);
			life--;
			
			angle += (rotation + 360) % 360;
			x += dX;
			y += dY;
			
			// set alpha
			if (life < 20){
				alpha -= 0.05f;
			}else if (alpha < 1){
				alpha += 0.05f;
			}
			
			Image snow = ERM.getImage("menu_snow.png");
			Image temp = snow.getScaledCopy(scale);
			temp.setRotation(angle);
			temp.setAlpha(alpha);
			g.drawImage(temp, x, y
					- snowLife * 2);
		}
	}
}
