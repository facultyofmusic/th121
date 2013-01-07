package th121.btype;

import org.newdawn.slick.geom.Vector2f;

public abstract class AbstractBulletType {
	public float x, y, direction;
	public int ID, counter;
	
	public AbstractBulletType(int ID, float x, float y){
		this.x = x;
		this.y = y;
		this.ID = ID;
	}
	
	public abstract void move();
}
