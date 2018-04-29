import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -8921419424614180143L;

	public static final int WIDTH = 1200, HEIGHT = 700;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	private HUD hud;
	public static float timeElapsed = 0, enemies = 0, counter_money = 0, counter_health = 0, potions_health = 0,
			potions_speed = 0, potions_invur = 0;
	public static int level = 1, money = 0;
	private Random rnd = new Random();

	public Game() {
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		new Window(WIDTH, HEIGHT, "Game", this);
		hud = new HUD();
		handler.addObject(new Player(123, 321, ID.player, handler));
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() { // game loop
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

	private void tick() {
		handler.tick();
		hud.tick();
		timeElapsed++;
		counter_money += 0.2;
		counter_health += 0.2;
		if (counter_money >= 10) {
			money++;
			counter_money = 0;
		}
		if (counter_health >= 4) {
			HUD.HEALTH--;
			counter_health = 0;
		}
		spawn();
	}

	private void spawn() {
		if (timeElapsed / 1000 >= enemies) {
			enemies++;
			handler.addObject(new Enemy1(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.enemy1));
		}
		if (timeElapsed / 300 >= potions_health) {
			potions_health++;
			handler.addObject(new PotionHealth(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.health_potion));
		}
		if (timeElapsed / 800 >= potions_speed) {
			potions_speed++;
			handler.addObject(new PotionSpeed(rnd.nextInt(WIDTH - 30), rnd.nextInt(HEIGHT - 40), ID.speed_potion));
		}
		level = (int) enemies;
	}

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

	public static void main(String args[]) {
		new Game();
	}

}
