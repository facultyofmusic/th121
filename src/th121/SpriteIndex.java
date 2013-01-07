package th121;

public class SpriteIndex {
	private int x, y, x2, y2;
	public SpriteIndex(int x, int y, int x2, int y2){
		this.setX(x);
		this.setY(y);
		this.setX2(x2);
		this.setY2(y2);
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}
	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}
	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}
	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int x2) {
		this.x2 = x2;
	}
	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}
}
