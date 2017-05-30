package Main;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JFrame;

public class MainGame extends JFrame {
	
	public MainGame() {
		initUi();
	}
	
	public void initUi() {
		this.add(new GameBoard());
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setTitle("SUPERGREATF#@$&!GAME");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
		
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainGame game = new MainGame();
				game.setVisible(true);
			}
		});
	}		

}
