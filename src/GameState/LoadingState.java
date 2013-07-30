package GameState;


import java.awt.*;
import java.awt.event.KeyEvent;

import Main.GamePanel;

public class LoadingState extends GameState {
	
	
	private Font titleFont;
	
	private Font font;
	
	private int level;
	
	public LoadingState(GameStateManager gsm) {
		
		this.gsm = gsm;
		titleFont = new Font("Eras Bold ITC", Font.PLAIN, 28);
			
		font = new Font("Eras Light ITC", Font.BOLD, 18);
		init();
	}
	
	public void init() {
		level = GameStateManager.getPreviousState() + 1;
	}
	
	public void update() {
	}
	
	public void draw(Graphics2D g) {
		
		// draw black screen
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// draw title
		g.setColor(Color.WHITE);
		g.setFont(titleFont);
		g.drawString("LOADING", 90 , 80);
		
		
		// draw menu options
		g.setFont(font);
		g.drawString("Level " + level, 130, 140);
		
		
		g.drawString("Press ENTER to start", 80, 170);
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			if(level <= GameStateManager.NUMGAMESTATES)
				gsm.setState(level);
			else
				gsm.setState(GameStateManager.MENUSTATE);
		}
	}
	public void keyReleased(int k) {}
	
}










