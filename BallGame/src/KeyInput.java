import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	// These are used to prevent a bug that stops the player from moving when moving to a direction, then instantly to the other one
	private boolean wPressed, sPressed, dPressed, aPressed;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	// When a key is pressed
	public void keyPressed(KeyEvent e) {
		// Transforms the key pressed to an int value
		int key = e.getKeyCode();
		// loops through all the objects to see which one is the player
		for (int i = 0; i < handler.object.size(); i++) { 
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.player) {
				// Up ( - y )
				if (key == KeyEvent.VK_W) { 
					wPressed = true;
					tempObject.setVelY(-5);
				}
				// Down ( + y )
				if (key == KeyEvent.VK_S) { 
					sPressed = true;
					tempObject.setVelY(5);
				}
				// Right ( + x )
				if (key == KeyEvent.VK_D) { 
					dPressed = true;
					tempObject.setVelX(5);
				}
				// Left ( - x )
				if (key == KeyEvent.VK_A) { 
					aPressed = true;
					tempObject.setVelX(-5);
				}
			}
		}
	}

	// When a key is released
	public void keyReleased(KeyEvent e) {
		// Transforms the key pressed to an int value
		int key = e.getKeyCode();
		// loops through all the objects to see which one is the player
		for (int i = 0; i < handler.object.size(); i++) { 
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.player) {
				// Up ( - y )
				if (key == KeyEvent.VK_W) { 
					wPressed = false;
					if (sPressed) {
						tempObject.setVelY(5);
					} else {
						tempObject.setVelY(0);
					}
				}
				// Down ( + y )
				if (key == KeyEvent.VK_S) { 
					sPressed = false;
					if (wPressed) {
						tempObject.setVelY(-5);
					} else {
						tempObject.setVelY(0);
					}
				}
				// Right ( + x )
				if (key == KeyEvent.VK_D) { 
					dPressed = false;
					if (aPressed) {
						tempObject.setVelX(-5);
					} else {
						tempObject.setVelX(0);
					}
				}
				// Left ( - x )
				if (key == KeyEvent.VK_A) { 
					aPressed = false;
					if (dPressed) {
						tempObject.setVelX(5);
					} else {
						tempObject.setVelX(0);
					}
				}
			}
		}
	}
}
