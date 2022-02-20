package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GPane  extends JPanel implements Runnable {
	
	// screen settings go here
	final int origTileSize = 32; //16x16 tiles 
	final int scale = 2;
	
	public final int tileSize = origTileSize * scale; //scales it up so the user can actually see stuff
	public final int maxScreenCol = 24; //
	public final int maxScreenRow = 12; //gives us a 4:3 ratio
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	int framesPerSecond = 60;
	
	
	TileManager tileM = new TileManager(this);
	public KeyHandler keyH = new KeyHandler(this);
	Sound sound = new Sound();
	public CollisionManager cManager = new CollisionManager(this);
	public ObjectManager oManager = new ObjectManager(this);
	public UI ui = new UI(this);
	public EventHandler eHand = new EventHandler(this);
	Thread gameThread;
	
	public int gameState;
	public final int titleState = 0;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int chatState = 3;
	public final int charState = 4;
	
	
	
	// Entity & obj
	public Player player = new Player(this, keyH);
	public Entity obj[] = new Entity[10]; //amount of objects we want to display at the same time;
	public Entity npc[] = new Entity[10];
	public Entity monster[] = new Entity[20];
	ArrayList<Entity> entityList = new ArrayList<>();
	
	public GPane() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true); //does fancy stuff to make render performance better.
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void initGame() {
		oManager.initObject();
		oManager.initNPC();
		oManager.initMonster();
		//playMusic(2);
		gameState = titleState;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start(); //runs the run method
	}

	@Override
	public void run() {
		
		double drawInt = 1000000000/framesPerSecond;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime) / drawInt;
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				
				repaint();
				delta--;
				drawCount++;
			}
			
			if(timer >= 1000000000) {
				System.out.print("FPS: " + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
		}
	}
	
	public void update() {
		
		if(gameState == playState) {
			player.update();
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					if(monster[i].alive && monster[i].dying == false) {
						monster[i].update();
					} if (monster[i].alive == false) {
						monster[i] = null;
					}
				}
			}
		}
		if(gameState == pauseState) {
			
		}
	}
	
	public void paintComponent(Graphics g) { // built in fuction w/ java <3
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		// debug
		long drawStart = 0;
		if(keyH.checkDrawtime) {
			drawStart = System.nanoTime();
		}
		
		// main menu
		if(gameState == titleState) {
			ui.render(g2);
		}
		
		// others
		else {
			// tile
			tileM.render(g2);
			
			// entity added to list
			entityList.add(player);
			
			for(int i = 0; i < npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i < obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			
			for(int i = 0; i < monster.length; i++) {
				if(monster[i] != null) {
					entityList.add(monster[i]);
				}
			}
			
			//sort
			Collections.sort(entityList, new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.worldY, e2.worldY);
					return result;
				}
				
			});
			
			//render the ents!
			for(int i = 0; i < entityList.size(); i++) {
				entityList.get(i).render(g2);
			}
			// empty ent list
				entityList.clear();	
			//ui
			ui.render(g2);
		}
		
		
		//DEBUG DISPLAY
		if(keyH.checkDrawtime) {
			long drawEnd = System.nanoTime();
			long passed = drawEnd - drawStart;
			
			g2.setFont(new Font("Arial", Font.PLAIN, 20));
			
			int x = 10;
			int y = 400;
			int lineH = 20;
			g2.setColor(new Color(254, 225, 184));
			g2.drawString("World X: " + player.worldX, x, y);
			y+=lineH;
			g2.drawString("World Y: " + player.worldY, x, y);
			y+=lineH;
			g2.drawString("Column: " + (player.worldX + player.solidBoundary.x)/tileSize + 
					" Row: " + (player.worldY + player.solidBoundary.y)/tileSize, x, y);
			y+=lineH;
			
			g2.drawString("Draw time: " + passed, x, y);
		}
		
		
		g2.dispose();
	}
	
	public void playMusic(int index) {
		sound.setfile(index);
		sound.play();
		sound.loop();
	}
	
	public void stopMusic() {
		sound.stop();
	}
	
	public void playSFX(int index) {
		sound.setfile(index);
		sound.play();
	}
}
