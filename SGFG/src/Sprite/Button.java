package Sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Button extends Sprite{
	private BufferedImage defaultBtn;
	private BufferedImage pressedBtn;
	private String name;
	
	public Button(int x, int y, String name) {
		super(x, y);
		this.name = name;
		try {
			defaultBtn = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/Buttons/" + this.name + ".png"));
			pressedBtn = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/Buttons/" + this.name + "_pressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}

	private void setImage(BufferedImage image) {
		img = image;
		width = img.getWidth();
		height = img.getHeight();
	}
	
	@Override
	protected void init() {
		setImage(defaultBtn);
		setVisible(false);
	}
	
	public void press() {
		setImage(pressedBtn);
	}
	
	public void unpress() {
		setImage(defaultBtn);
	}
	
	public String getName() {
		return name;
	}
}
