package Sprite;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.imageio.ImageIO;

public class Gif extends Sprite implements Animated{
	private String name;
	private ArrayList<BufferedImage> images;
	private Iterator<BufferedImage> switcher;
	private int ONE_PERIOD;
	private int period;
	
	public Gif(int x, int y, String name, int one_period) {
		super(x, y);
		this.name = name;
		ONE_PERIOD = one_period;
		init();
	}
	
	public Gif(int one_period) {
		super(0, 0);
		this.ONE_PERIOD = one_period;
	}
	
	private void setImage(BufferedImage image) {
		img = image;
		width = img.getWidth();
		height = img.getHeight();
	}

	@Override
	protected void init() {
		period = 0;
		if(images == null) {
			images = new ArrayList<BufferedImage>();
		}
		else {
			images.clear();
		}
		try {
			File[] files = (new File(getClass().getResource("/Background/Gifs/" + name).toURI())).listFiles();
			for(File file : files) {	
				BufferedImage image = ImageIO.read(file);
				images.add(image);
			}
			switcher = images.iterator();
			setImage(switcher.next());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void animate() {
		if(period % ONE_PERIOD == 0) {
			try {
				if(switcher.hasNext()) {
					setImage(switcher.next());
				}
				else {
					switcher = images.iterator();
					setImage(switcher.next());
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		period++;
		
	}
	
	public void setName(String name) {
		this.name = name;
		init();
		switcher = images.iterator();
	}
}
