import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;


public class ScreenObject {
	private String sheetID;
	private float x, y, alpha, width, height, desX, desY, desAlpha, desWidth, desHeight, srcX = -1, srcY, srcX2, srcY2;
	private boolean addModeWhenRender;
	private int sheetIndex;
	
	public ScreenObject(String sheetID, float x, float y, int sheetIndex){
		this(sheetID, x, y, sheetIndex, 1);
	}
	
	public ScreenObject(String sheetID, float x, float y, int sheetIndex, float alpha){
		this(sheetID, x, y, -1, -1, sheetIndex, alpha, false);
	}
	
	public ScreenObject(String sheetID, float x, float y, float width, float height, int sheetIndex, float alpha){
		this(sheetID, x, y, width, height, sheetIndex, alpha, false);
	}
	
	public ScreenObject(String sheetID, float x, float y, float width, float height, int sheetIndex, boolean addModeWhenRender){
		this(sheetID, x, y, width, height, sheetIndex, 1, addModeWhenRender);
	}
	
	public ScreenObject(String sheetID, float x, float y, float width, float height, int sheetIndex, float alpha, boolean addModeWhenRender){
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
		this.addModeWhenRender = addModeWhenRender;
		this.sheetIndex = sheetIndex;
	}
	
	public void loadIndex(){
		SpriteIndex si = IndexChart.getIndex(sheetID, sheetIndex);
		this.srcX = si.getX();
		this.srcY = si.getY();
		this.srcX2 = si.getX2();
		this.srcY2 = si.getY2();
	}
	
	public void setAlpha(float a){
		alpha = a;
	}
	
	public void setDesAlpha(float a){
		desAlpha = a;
	}
	
	public void setX(float newX){
		x = newX;
	}
	
	public void setDesX(float newX){
		desX = newX;
	}
	
	public void setY(float newY){
		y = newY;
	}
	
	public void setDesY(float newY){
		desY = newY;
	}
	
	public void setWidth(float newWidth){
		width = newWidth;
	}
	
	public void setDesWidth(float newWidth){
		desWidth = newWidth;
	}
	
	public void setHeight(float newHeight){
		height = newHeight;
	}
	
	public void setDesHeight(float newHeight){
		desHeight = newHeight;
	}
	
	public void draw(Graphics g, boolean selected){
		if (srcX == -1){
			loadIndex();
		}
		
		if (alpha < desAlpha){
			if (desAlpha - alpha < 0.05f){
				alpha = desAlpha;
			}else{
				alpha+= 0.05f;
			}
		}else if (alpha > desAlpha){
			if (alpha - desAlpha < 0.025f){
				alpha = desAlpha;
			}else{
				alpha-= 0.025f;
			}
		}
		
		if (x < desX){
			if (desX - x < 0.05f){
				x = desX;
			}else{
				x+= (desX-x)/3;
			}
		}else if (x > desX){
			if (x - desX < 0.05f){
				x = desX;
			}else{
				x-= (x-desX)/3;
			}
		}
		
		if (y < desY){
			if (desY - y < 0.05f){
				y = desY;
			}else{
				y+= (desY - y) / 3;
			}
		}else if (y > desY){
			if (y - desY < 0.05f){
				y = desY;
			}else{
				y-= (y-desY)/3;
			}
		}
		
		//g.drawRect(x, y, 10, 10);
		if (alpha > 0)
			g.drawImage(ERM.getImage(sheetID), x, y, x + srcX2 - srcX, y + srcY2 - srcY, srcX, srcY, srcX2, srcY2, new Color(alpha, alpha, alpha, alpha));
	}
}
