import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private boolean wPressed, sPressed, dPressed, aPressed;

	public KeyInput(Handler handler) {
		this.handler = handler;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) { // loops through all the objects to see which one is the
															// player
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.player) {
				// key events
				if (key == KeyEvent.VK_W) { // up ( - y )
					wPressed = true;
					tempObject.setVelY(-5);
				}
				if (key == KeyEvent.VK_S) { // down ( + y )
					sPressed = true;
					tempObject.setVelY(5);
				}
				if (key == KeyEvent.VK_D) { // right ( + x )
					dPressed = true;
					tempObject.setVelX(5);
				}
				if (key == KeyEvent.VK_A) { // left ( - x )
					aPressed = true;
					tempObject.setVelX(-5);
				}
			}
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for (int i = 0; i < handler.object.size(); i++) { // loops through all the objects to see which one is the
															// player
			GameObject tempObject = handler.object.get(i);
			if (tempObject.getID() == ID.player) {
				// key events
				if (key == KeyEvent.VK_W) { // up ( - y )
					wPressed = false;
					if (sPressed) {
						tempObject.setVelY(5);
					} else {
						tempObject.setVelY(0);
					}
				}
				if (key == KeyEvent.VK_S) { // down ( + y )
					sPressed = false;
					if (wPressed) {
						tempObject.setVelY(-5);
					} else {
						tempObject.setVelY(0);
					}
				}
				if (key == KeyEvent.VK_D) { // right ( + x )
					dPressed = false;
					if (aPressed) {
						tempObject.setVelX(-5);
					} else {
						tempObject.setVelX(0);
					}
				}
				if (key == KeyEvent.VK_A) { // left ( - x )
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
