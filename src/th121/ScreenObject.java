package th121;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class ScreenObject {
	private String sheetID;
	private float x, y, alpha, width, height, desX, desY, desAlpha, desWidth,
			desHeight, srcX = -1, srcY, srcX2, srcY2, moveRate = 6;
	private boolean addModeWhenRender, straightMovement = false;
	private int sheetIndex;

	public ScreenObject(String sheetID, float x, float y, int sheetIndex) {
		this(sheetID, x, y, sheetIndex, 1);
	}

	public ScreenObject(String sheetID, float x, float y, int sheetIndex,
			float alpha) {
		this(sheetID, x, y, -1, -1, sheetIndex, alpha, false);
	}

	public ScreenObject(String sheetID, float x, float y, float width,
			float height, int sheetIndex) {
		this(sheetID, x, y, width, height, sheetIndex, 1, false);
	}

	public ScreenObject(String sheetID, float x, float y, float width,
			float height, int sheetIndex, float alpha) {
		this(sheetID, x, y, width, height, sheetIndex, alpha, false);
	}

	public ScreenObject(String sheetID, float x, float y, float width,
			float height, int sheetIndex, boolean addModeWhenRender) {
		this(sheetID, x, y, width, height, sheetIndex, 1, addModeWhenRender);
	}

	public ScreenObject(String sheetID, float x, float y, float width,
			float height, int sheetIndex, float alpha, boolean addModeWhenRender) {
		this.sheetID = sheetID;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.desX = x;
		this.desY = y;
		this.desAlpha = alpha;
		this.desWidth = width;
		this.desHeight = height;
		this.alpha = alpha;
		this.setAddModeWhenRender(addModeWhenRender);
		this.sheetIndex = sheetIndex;
	}

	public void loadIndex() {
		if (sheetIndex == -1) {
			Image img = ERM.getImage(sheetID);
			this.srcX = 0;
			this.srcY = 0;
			this.srcX2 = img.getWidth() - 1;
			this.srcY2 = img.getHeight() - 1;
		} else {
			SpriteIndex si = ERM.getIndex(sheetID + ".ssic", sheetIndex);

			this.srcX = si.getX();
			this.srcY = si.getY();
			this.srcX2 = si.getX2();
			this.srcY2 = si.getY2();
		}
		if (width == -1)
			this.width = srcX2 - srcX;

		if (height == -1)
			this.height = srcY2 - srcY;

		if (desWidth == -1)
			this.desWidth = width;

		if (desHeight == -1)
			this.desHeight = height;
	}

	public void setAlpha(float a) {
		alpha = a;
		desAlpha = a;
	}

	public float getDesAlpha() {
		return desAlpha;
	}

	public void setDesAlpha(float a) {
		desAlpha = a;
	}

	public float getX() {
		return x;
	}

	public void setX(float newX) {
		x = newX;
		desX = newX;
	}

	public float getDesX() {
		return desX;
	}

	public void setDesX(float newX) {
		desX = newX;
	}

	public float getY() {
		return y;
	}

	public void setY(float newY) {
		y = newY;
		desY = y;
	}

	public float getDesY() {
		return desY;
	}

	public void setDesY(float newY) {
		desY = newY;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float newWidth) {
		width = newWidth;
		desWidth = newWidth;
	}

	public float getDesWidth() {
		return desWidth;
	}

	public void setDesWidth(float newWidth) {
		desWidth = newWidth;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float newHeight) {
		height = newHeight;
		desHeight = newHeight;
	}

	public float getDesHeight() {
		return desHeight;
	}

	public void setDesHeight(float newHeight) {
		desHeight = newHeight;
	}

	/**
	 * @param moveRate
	 *            the moveRate to set
	 */
	public void setMoveRate(float moveRate) {
		this.moveRate = moveRate;
	}

	/**
	 * @return the moveRate
	 */
	public float getMoveRate() {
		return moveRate;
	}

	public void draw(Graphics g) {
		if (srcX == -1) {
			loadIndex();
		}

		if (alpha < desAlpha) {
			if (desAlpha - alpha < 0.07f) {
				alpha = desAlpha;
			} else {
				alpha += 0.07f;
			}
		} else if (alpha > desAlpha) {
			if (alpha - desAlpha < 0.05f) {
				alpha = desAlpha;
			} else {
				alpha -= 0.05f;
			}
		}

		// X and Y modification
		if (x < desX) {
			if (desX - x < 0.05f) {
				x = desX;
			} else {
				if (straightMovement) {
					x++;
				} else {
					x += (desX - x) / moveRate;
				}
			}
		} else if (x > desX) {
			if (x - desX < 0.05f) {
				x = desX;
			} else {
				if (straightMovement) {
					x--;
				} else {
					x -= (x - desX) / moveRate;
				}
			}
		}

		if (y < desY) {
			if (desY - y < 0.05f) {
				y = desY;
			} else {
				if (straightMovement) {
					y++;
				} else {
					y += (desY - y) / moveRate;
				}
			}
		} else if (y > desY) {
			if (y - desY < 0.05f) {
				y = desY;
			} else {
				if (straightMovement) {
					y--;
				} else {
					y -= (y - desY) / moveRate;
				}
			}
		}

		if (width < desWidth) {
			if (desWidth - width < 0.05f) {
				width = desWidth;
			} else {
				if (straightMovement) {
					width++;
				} else {
					width += (desWidth - width) / moveRate;
				}
			}
		} else if (width > desWidth) {
			if (width - desWidth < 0.05f) {
				width = desWidth;
			} else {
				if (straightMovement) {
					width--;
				} else {
					width -= (width - desWidth) / moveRate;
				}
			}
		}

		if (height < desHeight) {
			if (desHeight - height < 0.05f) {
				height = desHeight;
			} else {
				if (straightMovement) {
					height++;
				} else {
					height += (desHeight - height) / moveRate;
				}
			}
		} else if (height > desHeight) {
			if (height - desHeight < 0.05f) {
				height = desHeight;
			} else {
				if (straightMovement) {
					height--;
				} else {
					height -= (height - desHeight) / moveRate;
				}
			}
		}

		// g.drawRect(x, y, 10, 10);
		if (addModeWhenRender) {
			g.setDrawMode(Graphics.MODE_ADD);
		}

		if (alpha > 0)
			g.drawImage(ERM.getImage(sheetID), x, y, x + width, y + height,
					srcX, srcY, srcX2, srcY2, new Color(alpha, alpha, alpha,
							alpha));
		g.setDrawMode(Graphics.MODE_NORMAL);
	}

	/**
	 * @param addModeWhenRender
	 *            the addModeWhenRender to set
	 */
	public void setAddModeWhenRender(boolean addModeWhenRender) {
		this.addModeWhenRender = addModeWhenRender;
	}

	/**
	 * @return the addModeWhenRender
	 */
	public boolean isAddModeWhenRender() {
		return addModeWhenRender;
	}
}
