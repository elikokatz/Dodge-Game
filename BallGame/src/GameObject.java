import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x, y;
	protected ID id;
	protected int velX, velY;

	// Basic constructor
	public GameObject(int x, int y, ID id) {
		this.x = x;
		this.y = y;
		this.id = id;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();
	
	/*
	 * Getters and setters
	 */

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setID(ID id) {
		this.id = id;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public ID getID() {
		return this.id;
	}

	public int getVelX() {
		return this.velX;
	}

	public int getVelY() {
		return this.velY;
	}
}
