import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy1 extends GameObject {

	public Enemy1(int x, int y, ID id) {
		super(x, y, id);
		velX = 10;
		velY = 10;
	}

	@Override
	public void tick() {
		if (x >= Game.WIDTH - 40 || x <= 0) {
			velX *= -1;
		}
		if (y >= Game.HEIGHT - 40 || y <= 0) {
			velY *= -1;
		}
		x += velX;
		y += velY;
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 25, 25);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 25, 25);
	}

}
