import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -8921419424614180143L;

	// Game window Width, Height
	public static final int WIDTH = 1200, HEIGHT = 700;
	// The game loop thread
	private Thread thread;
	// Boolean to check if the game is currently running
	private boolean running = false;
	// Our handler ** used to handle all sorts of stuff in the game
	private Handler handler;
	// Our HUD (heads up display) ** used to show the health, and all other stuff like that
	private HUD hud;
	public static float timeElapsed = 0, enemies = 0, counter_money = 0, counter_health = 0, potions_health = 0,
			potions_speed = 0, potions_invur = 0;
	public static int level = 1, money = 0;
	// The random is used to spawn the enemy/potions in a random location
	private Random rnd = new Random();

	// Basic constructor, used to set everything from the start
	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game", this);
		hud = new HUD();
		handler.addObject(new Player(123, 321, ID.player, handler));
	}

	// Basic thread start
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	// Basic thread stop
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Our game loop, this loop will determine when each tick and rendering process will occur. * Algorithm found on the internet
	@Override
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
	}

	// Game tick, used to check/do stuff each tick the game is running
	private void tick() {
		handler.tick();
		hud.tick();
		// Time elapsed will be our variable to save how many ticks were from the start
		timeElapsed++;
		// Basically every 50 ticks, the money will increase by 1
		counter_money += 0.2;
		if (counter_money >= 10) {
			money++;
			counter_money = 0;
		}
		// Basically every 20 ticks, the health will go down, reason: so the player won't camp near the bounds of the window
		counter_health += 0.2;
		if (counter_health >= 4) {
			HUD.HEALTH--;
			counter_health = 0;
		}
		// will decide/do the spawning of enemies, potions etc.
		spawn();
	}

	private void spawn() {
		// Every 1000 ticks, an enemy will spawn
		if (timeElapsed / 1000 >= enemies) {
			enemies++;
			handler.addObject(new Enemy1(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.enemy1));
		}
		// Every 300 ticks, an health potion will spawn
		if (timeElapsed / 300 >= potions_health) {
			potions_health++;
			handler.addObject(new PotionHealth(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.health_potion));
		}
		// Every 800 ticks, a speed potion will spawn
		if (timeElapsed / 800 >= potions_speed) {
			potions_speed++;
			handler.addObject(new PotionSpeed(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.speed_potion));
		}
		// the level is determined by the amount of enemys, or every 1000 ticks is 1 level
		level = (int) enemies;
	}

	// Rendering of the actual screen the game will be on
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		handler.render(g);
		hud.render(g);
		g.dispose();
		bs.show();
	}

	// The main 
	public static void main(String args[]) {
		new Game();
	}

}
