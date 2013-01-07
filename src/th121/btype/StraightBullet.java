package th121.btype;

import org.newdawn.slick.geom.Vector2f;

public class StraightBullet extends AbstractBulletType{
	float dx, dy;
	
	public StraightBullet(int ID, float x, float y, float speed, float direction) {
		super(ID, x, y);
		super.direction = direction;
		setLinearDelta(direction, speed);
	}
	
	public void setLinearDelta(float direction, float speed) {
		direction %= 360;
		float dd = direction % 90;

		if (direction < 90) {
			dx = (float) Math.cos(Math.toRadians(dd)) * speed;
			dy = -(float) Math.sin(Math.toRadians(dd)) * speed;
		} else if (direction < 180) {
			dx = -(float) Math.sin(Math.toRadians(dd)) * speed;
			dy = -(float) Math.cos(Math.toRadians(dd)) * speed;
		} else if (direction < 270) {
			dx = -(float) Math.cos(Math.toRadians(dd)) * speed;
			dy = (float) Math.sin(Math.toRadians(dd)) * speed;
		} else {
			dx = (float) Math.sin(Math.toRadians(dd)) * speed;
			dy = (float) Math.cos(Math.toRadians(dd)) * speed;
		}
	}

	@Override
	public void move() {
		x += dx;
		y += dy;
	}
}
