import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

	LinkedList<GameObject> object = new LinkedList<GameObject>();

	//  Calls each tick 
	public void tick() {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).tick();
		}
	}

	// Calls each render
	public void render(Graphics g) {
		for (int i = 0; i < object.size(); i++) {
			object.get(i).render(g);
		}
	}

	// Adds an object to enable ticking and rendering
	public void addObject(GameObject object) {
		this.object.add(object);
	}

	// Removes an object
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
}
