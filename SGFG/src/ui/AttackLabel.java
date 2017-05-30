package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Sprite.Gif;
import Text.AttackScore;

public class AttackLabel {
	private AttackScore attackScore;
	private Gif gif;
	private final int LIMIT = 175;
	private int animateCount;
	private boolean visible;
	
	public AttackLabel() {
		gif = new Gif(7);
		attackScore = new AttackScore();
		animateCount = 0;
		visible = false;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
		if(visible) {
			animateCount = 0;
		}
	}
	
	public void draw(Graphics2D g2d, JPanel board) {
		g2d.drawImage(gif.getImage(), gif.getX(), gif.getY(), board);
		gif.animate();
		if(attackScore.isVisible()) {
			g2d.setColor(new Color(255, 255, 255));
			g2d.setFont(new Font("Arial", Font.PLAIN, 20));
			g2d.drawString(attackScore.getMessage(), attackScore.getX(), attackScore.getY());
			attackScore.move();
		}
	}
	
	public void initAttackScore(int x, int y, int score) {
		attackScore.setVisible(true);
		attackScore.setX(x);
		attackScore.setY(y);
		String _score = Integer.toString(score * -1);
		attackScore.setMessage(_score);
	}
	
	public void initGif(int x, int y, String name) {
		gif.setX(x);
		gif.setY(y);
		gif.setName(name);
	}
	
	public void update() {
		animateCount++;
		if(animateCount == LIMIT) {
			visible = false;
		}
	}
}
