package Items;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import FileReader.DescriptionReader;
import Sprite.Sprite;
import Units.Unit;

public abstract class Item extends Sprite implements Cloneable{
	protected String basePath;
	protected String name;
	protected String bigImage;
	protected ArrayList<String> description;
	protected Effector effector;
	
	public Item(int x, int y, String name) {
		super(x, y);
		this.name = name;
		initBasePath();
		init();
	}	

	@Override
	protected void init() {
		setImage("/Items/" + basePath + "/" + name + ".png");
		bigImage = "/Items/" + basePath + "/" + name + "_big.png";
		readDescription();
	}
	
	protected abstract void initBasePath();
	
	public void effect(Unit unit) {
		effector.effect(unit);
	}
	
	private void readDescription() {
		InputStream in = getClass().getResourceAsStream("/Descriptions/" + name + ".txt");
		description = DescriptionReader.read(in);
	}
	
	public void setEffector(Effector effector) {
		this.effector = effector;
	}
	
	public String getName() {
		return name;
	}
	
	public ArrayList<String> getDescription() {
		return description;
	}
	
	public BufferedImage getBigImage() {
		try {
			return ImageIO.read(this.getClass().getResourceAsStream(bigImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
