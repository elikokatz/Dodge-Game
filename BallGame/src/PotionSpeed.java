import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PotionSpeed extends GameObject {

	public PotionSpeed(int x, int y, ID id) {
		super(x, y, id);
		velX = 0;
		velY = 0;
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 25, 25);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}
}
