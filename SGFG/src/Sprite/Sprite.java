package Sprite;

import javax.imageio.ImageIO;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Sprite {
	protected int x, y;
	protected int width, height;
	protected boolean visible;
	protected BufferedImage img;
	
	public Sprite(int x, int y) {
		this.x = x;
		this.y = y;
		visible = true;
	}
	
	protected void setImage(String fileName) {
		try {
			img = ImageIO.read(this.getClass().getResourceAsStream(fileName));
			width = img.getWidth(null);
			height = img.getHeight(null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage() {
		return img;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	protected abstract void init();
}
