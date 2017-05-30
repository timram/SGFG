package Sprite;

import Main.GameBoard;

public class GameLabel extends Sprite implements IMoveable{	
	private int initX;
	private int initY;
	private String lightLabel;
	private String darkLabel;
	private double SPEED = 30;
	
	public GameLabel(int x, int y) {
		super(x, y);
		init();
	}
	
	@Override
	protected void init() {
		setVisible(false);
		initX = x;
		initY = y;
		lightLabel = "LightTurn";
		darkLabel = "DarkTurn";
	}
	
	private void initLabel(String name) {
		setImage("/Labels/" + name + ".png");
		x = initX;
		y = initY;
		SPEED = 15;
		setVisible(true);
	}
	
	public void initLightLabel() {
		initLabel(lightLabel);
	}
	
	public void initDarkLabel() {
		initLabel(darkLabel);
	}

	@Override
	public void move() {
		x += (int)SPEED;
		if(x < GameBoard.BOARD_WIDTH / 2) {
			if(SPEED > 5) {
				SPEED -= 0.5;
			}
		}
		else {
			if(SPEED < 30) {
				SPEED += 0.5;
			}
		}
		if(x > GameBoard.BOARD_WIDTH) {
			setVisible(false);
		}
		
	}
}
