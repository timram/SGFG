package TileMap;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Sprite.Gif;

public class Background {
	protected BufferedImage image;
	protected ArrayList<Gif> gifs;
	
	public Background(String name) {
		try {
			image = ImageIO.read(this.getClass().getResourceAsStream(name));
			initGifs();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void initGifs() {
		gifs = new ArrayList<Gif>();
		gifs.add(new Gif(250, 15, "Dragon", 10));
		gifs.add(new Gif (860, -20, "RainbowDash", 7));
		gifs.add(new Gif (450, 150, "Title", 10));
		gifs.add(new Gif (-10, 66, "Lightning", 15));
	}
		
	public void draw(Graphics2D g2d) {
		g2d.drawImage(image, 0, 0, null);
		for(Gif gif : gifs) {
			g2d.drawImage(gif.getImage(), gif.getX(), gif.getY(), null);
			gif.animate();
		}
	}
}
