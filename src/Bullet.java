class Bullet {
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
