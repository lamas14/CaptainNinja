package GameState;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Audio.AudioPlayer;
import Entity.Enemy;
import Entity.Explosion;
import Entity.HUD;
import Entity.Player;
import Entity.Enemies.Wolf;
import Main.GamePanel;
import TileMap.Background;
import TileMap.TileMap;

public class Level2State extends GameState {

	private TileMap tileMap;
	private Background bg;

	private Player player;

	private ArrayList<Enemy> enemies;

	private HUD hud;
	
	private AudioPlayer bgMusic;

	private ArrayList<Explosion> explosions;

	public Level2State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		tileMap = new TileMap(30);
		tileMap.loadTiles("/Tilesets/ninjatileset.gif");
		tileMap.loadMap("/Maps/level1-2.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);

		bg = new Background("/Backgrounds/levelbg1.gif", 0.04);

		player = new Player(tileMap);
		player.setPosition(100, 100);
		
		explosions = new ArrayList<Explosion>();
		
		populateEnemies();

		hud = new HUD(player);

		bgMusic = new AudioPlayer("/Music/level1music.mp3");
		bgMusic.play();

	}

	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();

		Wolf w;

		Point[] points = new Point[] { new Point(200,200), new Point(600, 80),
				new Point(1400, 100), new Point(1360, 100),
				new Point(1960, 80) };
		for (int i = 0; i < points.length; i++) {
			w = new Wolf(tileMap);
			w.setPosition(points[i].x, points[i].y);
			enemies.add(w);
		}
	}

	public void update() {
		
		if(player.isDead()){
			bgMusic.stop();
			gsm.setState(GameStateManager.GAMEOVERSTATE);
		}
		
		if(player.getx() + 11 == 4680){
			bgMusic.stop();
			gsm.setState(GameStateManager.MENUSTATE);
		}
		
		// update player
		player.update();
		tileMap.setPosition(GamePanel.WIDTH / 2 - player.getx(),
				GamePanel.HEIGHT / 2 - player.gety());

		// set background
		bg.setPosition(tileMap.getx(), tileMap.gety());

		// attack enemies
		player.checkAttack(enemies);

		// update all enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}
		
		
		// update all explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
	}

	public void draw(Graphics2D g) {

		// draw bg
		bg.draw(g);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int)tileMap.getx(), (int)tileMap.gety());
			explosions.get(i).draw(g);
		}

		// draw HUD
		hud.draw(g);

	}

	public void keyPressed(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(true);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(true);
		if (k == KeyEvent.VK_UP)
			player.setUp(true);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(true);
		if (k == KeyEvent.VK_W)
			player.setJumping(true);
		if (k == KeyEvent.VK_E)
			player.setGliding(true);
		if (k == KeyEvent.VK_R)
			player.setScratching();
		if (k == KeyEvent.VK_F)
			player.setFiring();
	}

	public void keyReleased(int k) {
		if (k == KeyEvent.VK_LEFT)
			player.setLeft(false);
		if (k == KeyEvent.VK_RIGHT)
			player.setRight(false);
		if (k == KeyEvent.VK_UP)
			player.setUp(false);
		if (k == KeyEvent.VK_DOWN)
			player.setDown(false);
		if (k == KeyEvent.VK_W)
			player.setJumping(false);
		if (k == KeyEvent.VK_E)
			player.setGliding(false);

	}

}
