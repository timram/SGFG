package ui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Main.GameBoard;

public class InfoPopUp extends PopUp {
	private String text;
	private BufferedImage image;
	private BufferedImage panel;
	private int x;
	private int y;
	private int timer;
	
	public InfoPopUp() {
		
		try {
			panel = ImageIO.read(this.getClass().getResourceAsStream("/UserInterface/popUpInfo.png"));
			x = GameBoard.BOARD_WIDTH / 2  - panel.getWidth() / 2;
			y = 20;
			timer = 500;
			setVisible(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void draw(Graphics2D g2d, JPanel board) {
		g2d.drawImage(panel, x, y, board);
		if(image != null) {
			g2d.drawImage(image,  x + panel.getWidth()/2 - image.getWidth()/2, y + 10, board);	
		}
		if(text != null && text.length() != 0) {
			g2d.drawString(text, x + 10,  y + image.getHeight() + 50);
		}
		updateTimer();
	}
	
	private void updateTimer() {
		timer--;
		if(timer <= 0) {
			setVisible(false);
			image = null;
			text = "";
		}
	}
	
	public void resetTimer() {
		timer = 250;
	}
	
	public void resetTimer(int timer) {
		this.timer = timer;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public void setText(String text) {
		this.text = text;
	}
}
