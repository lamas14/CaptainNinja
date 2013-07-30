package Entity.Enemies;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Entity.Animation;
import Entity.Enemy;
import TileMap.TileMap;

public class Wolf extends Enemy {

	private BufferedImage[] sprites;

	public Wolf(TileMap tm) {

		super(tm);

		moveSpeed = 2;
		maxSpeed = 2;
		fallSpeed = 0.2;
		maxFallSpeed = 10;

		width = 30;
		height = 30;

		cwidth = 20;
		cheight = 20;

		health = maxHealth = 8;
		damage = 1;

		// load Sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass()
					.getResourceAsStream("/Sprites/Enemies/wolf.gif"));

			sprites = new BufferedImage[5];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width,
						height);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(50);

		right = true;
		facingRight = true;
	}

	private void getNextPosition() {

		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}

		if (falling) {
			dy += fallSpeed;
		}
	}
	
	
	
	@Override
	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}
		
		
		// if it hits a wall, go other direction
		if (right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		}
		else if(left && dx == 0){
			right = true;
			left = false;
			facingRight = true;
		}
		
		// update animation
		animation.update();
	}

	
	
	public void draw(Graphics2D g) {

		setMapPosition();

		super.draw(g);

	}

}
