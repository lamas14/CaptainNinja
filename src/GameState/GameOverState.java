package GameState;


import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;

public class GameOverState extends GameState {
	
	
	private int currentChoice = 0;
	private String[] options = {
		"Continue",
		"Exit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	
	public GameOverState(GameStateManager gsm) {
		
		this.gsm = gsm;
		titleColor = new Color(128, 0, 0);
		titleFont = new Font("Eras Bold ITC", Font.PLAIN, 28);
			
		font = new Font("Eras Light ITC", Font.BOLD, 18);
		
	}
	
	public void init() {}
	
	public void update() {
	}
	
	public void draw(Graphics2D g) {
		
		// draw black screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Game Over", 80 , 80);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 120, 140 + i * 20);
		}
		
	}
	
	private void select() {
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.getPreviousState());
		}
		if(currentChoice == 1) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
}










