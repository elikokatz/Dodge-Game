import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	Handler handler;
	// These booleans are used to save if the player has a status
	public static boolean speed, invur;
	private int timer_speed, velScale = 1;

	// Basic constructor
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
	}

	// Each game tick
	@Override
	public void tick() { 
		if ((x <= 0 && velX > 0) || (x >= Game.WIDTH - 30 && velX < 0) || (x < Game.WIDTH - 30 && x > 0)) {
			x += velX * velScale;
		}
		if ((y <= 0 && velY > 0) || (y >= Game.HEIGHT - 60 && velY < 0) || (y < Game.HEIGHT - 60 && y > 0)) {
			y += velY * velScale;
		}
		if (timer_speed > 0) {
			timer_speed--;
		}
		if (timer_speed == 0 && speed) {
			speed = false;
			velScale = 1;
		}
		collision();
	}
	
	// Used to check for collision with enemy/potions
	private void collision() {
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.enemy1) {
				// if the collision is with enemy1
				if (getBounds().intersects(tempObject.getBounds())) {
					HUD.HEALTH -= 2;
				}
			}
			if (tempObject.getID() == ID.health_potion) {
				// if the collision is with an health potion
				if (getBounds().intersects(tempObject.getBounds())) {
					if (HUD.HEALTH <= 30) {
						HUD.HEALTH += 70;
					} else {
						HUD.HEALTH = 100;
					}
					handler.removeObject(tempObject);
				}
			}
			if (tempObject.getID() == ID.speed_potion) {
				// if the collision is with a speed potion
				if (getBounds().intersects(tempObject.getBounds())) {
					speed = true;
					timer_speed = 100;
					velScale = 2;
					handler.removeObject(tempObject);
				}
			}
		}
		// Checks if the player's health is 0 (or below if his health got down really fast)
		if (HUD.HEALTH <= 0) {
			System.out.println("Level: " + Game.level);
			System.exit(0);
		}
	}

	// Renders the player ** used to show the enemy on the screen
	@Override
	public void render(Graphics g) { 
		g.setColor(Color.white);
		g.fillRect(x, y, 32, 32);
	}
	
	// Returns the rectangle bounds ** used to determine collision 
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}

}
