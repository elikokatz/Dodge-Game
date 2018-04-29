import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy1 extends GameObject {

	// Constructor
	public Enemy1(int x, int y, ID id) {
		super(x, y, id);
		velX = 10;
		velY = 10;
	}

	// Each game tick
	@Override
	public void tick() {
		// Checks if the enemy hits the bound ** used to bounce the enemy off the bounds
		if (x >= Game.WIDTH - 40 || x <= 0) {
			velX *= -1;
		}
		if (y >= Game.HEIGHT - 40 || y <= 0) {
			velY *= -1;
		}
		// Moves the enemy using basic current location + velocity
		x += velX;
		y += velY;
	}

	// Renders the enemy ** used to show the enemy on the screen
	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 25, 25);
	}

	// Returns the rectangle bounds ** used to determine collision 
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}

}
