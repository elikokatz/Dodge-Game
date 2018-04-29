import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {

	public static int HEALTH = 100;
	private String level, money;

	public void tick() {

	}

	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(30, 30, 500, 32);
		g.setColor(Color.blue);
		g.fillRect(30, 30, HEALTH * 5, 32);
		g.setColor(Color.white);
		level = "Level: " + Game.level;
		money = "Money: " + Game.money;
		g.setFont(new Font("TimesRoman", Font.BOLD, 40));
		g.drawString(level, 30, 100);
		g.drawString(money, 30, 150);
		if (Player.speed) {
			g.drawString("Speed", 30, 200);
		}
	}
}
