package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import Main.GameBoard;

import TileMap.Background;
import TileMap.CreditsPage;

public class MenuState extends GameState {
	
	private Background background; 
	private CreditsPage creditsPage;
	private final String[] menuBar = {
			"Start Game",
			"Credits",
			"Quit"
	};
	private int currentChoice;
	private Color titleColor;
	private Font titleFont;
	private Font font;
	private int fontSize = 25;
	private int titleFontSize = 30;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
	}

	@Override
	public void init() {	
		if(!initialized) {
			try {
				background = new Background("/Background/menuBackground.png");
				creditsPage = new CreditsPage("/Background/CreditsPage.png");
				titleColor = new Color(255, 0, 0);
				titleFont = new Font("Arial", Font.PLAIN, titleFontSize);
				font = new Font("Arial", Font.PLAIN, fontSize);
				currentChoice = 0;
				initialized = true;
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update() {}

	@Override
	public void draw(Graphics2D g2d) {
		if(creditsPage.isVisible()) {
			drawCredits(g2d);
		} else {
			drawMenu(g2d);
		}
	}
	
	private void drawMenu(Graphics2D g2d) {
		background.draw(g2d);
		int centerX = GameBoard.BOARD_WIDTH / 2;
		int centerY = GameBoard.BOARD_HEIGHT / 2;
		g2d.setColor(titleColor);
		g2d.setFont(titleFont);
		g2d.setFont(font);
		for(int i = 0 ; i < menuBar.length; i++) {
			if(i == currentChoice) {
				g2d.setColor(Color.RED);
			}
			else {
				g2d.setColor(Color.BLACK);
			}
			g2d.drawString(menuBar[i], centerX - (menuBar[0].length() * fontSize) / 2, (centerY - 30) + i * 45);
		}
	}
	
	private void drawCredits(Graphics2D g2d) {
		creditsPage.draw(g2d);
	}

	private void select() {
		if(currentChoice == 0) {
			gsm.setState("Arena");
		}
		if(currentChoice == 1) {
			creditsPage.setVisible(true);
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	@Override
	public void keyPressed(int key) {
		if(creditsPage.isVisible()) {
			handleCreditsPageKey(key);
		} else {
			handleMenuKey(key);
		}
	}
	
	private void handleMenuKey(int key) {
		if(key == KeyEvent.VK_ENTER) {
			select();
		}
		if(key == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice < 0) {
				currentChoice = menuBar.length - 1;
			}
		}
		if(key == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == menuBar.length) {
				currentChoice = 0;
			}
		}
	}
	
	private void handleCreditsPageKey(int key) {
		if(key == KeyEvent.VK_ESCAPE) {
			creditsPage.setVisible(false);
		}
	}

	@Override
	public void keyReleased(int key) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(Rectangle mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(Rectangle mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(Rectangle mouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
