import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PotionHealth extends GameObject {

	// Basic constructor
	public PotionHealth(int x, int y, ID id) {
		super(x, y, id);
		// We don't want the potion to move around
		velX = 0;
		velY = 0;
	}

	// No need for anything to happen with the potion each tick
	@Override
	public void tick() {

	}

	// Renders the potion into the screen
	@Override
	public void render(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, 25, 25);
	}

	// Returns the rectangle bounds ** used to determine collision 
	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}
}
