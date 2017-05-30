package Main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;

import GameState.GameStateManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class GameBoard extends JPanel implements Runnable{
	public final static int BOARD_WIDTH = 1150;
	public final static int BOARD_HEIGHT = 600;
	private Thread thread;
	private boolean running;
	private final int FPS = 60;
	private final int DELAY = 1000 / FPS;
	private BufferedImage image;
	private Graphics2D g2d;
	private GameStateManager gsm;
	
	public GameBoard() {
		initBoard();
	}
		
	private void initBoard() {
		this.setBackground(new Color(0, 0, 0));
		this.setFocusable(true);
		this.requestFocusInWindow();
		this.setDoubleBuffered(true);
		this.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
	}
	
	@Override
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			this.addKeyListener(new KeyListener() {
				@Override
				public void keyPressed(KeyEvent key) {
					gsm.keyPressed(key.getKeyCode());
				}

				@Override
				public void keyReleased(KeyEvent key) {
					gsm.keyReleased(key.getKeyCode());
				}

				@Override
				public void keyTyped(KeyEvent ke) {
					System.out.println(ke);
				}
			});
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
					gsm.mouseClicked(rect);
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
					gsm.mousePressed(rect);
				}
				
				@Override
				public void mouseReleased(MouseEvent e) {
					Rectangle rect = new Rectangle(e.getX(), e.getY(), 1, 1);
					gsm.mouseReleased(rect);
				}
	
			});
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(BOARD_WIDTH, BOARD_HEIGHT, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D) image.getGraphics();
		running = true;
		gsm = new GameStateManager();
	}
	
	// This method contain main game loop
	@Override
	public void run() {
		init();
		
		long start, elapsed, wait;
			
		while(running) {
			start = System.currentTimeMillis();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.currentTimeMillis() - start;
			wait = DELAY - elapsed;
			if(wait < 2) {
				wait = 2;
			}
			
			try {
				Thread.sleep(wait);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		gsm.update();
	}
	
	private void draw() {
		gsm.draw(g2d);
	}
	
	private void drawToScreen() {
		Graphics g = this.getGraphics();
		g.drawImage(image, 0, 0, BOARD_WIDTH, BOARD_HEIGHT, null);
		g.dispose();
	}
}
