package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GPane;
import main.Toolbox;

public class Entity {
	
	GPane gp;
	
	
	
	public BufferedImage 
	up1, up2, up3, up4, up5, up6, up7, up8,
	down1, down2, down3, down4, down5, down6, down7, down8,
	left1, left2, left3, left4, left5, left6, left7, left8,
	right1, right2, right3, right4, right5, right6, right7, right8;
	
	public BufferedImage 
	a_up1, a_up2, a_up3, a_up4, a_up5, a_up6, a_up7, a_up8,
	a_down1, a_down2, a_down3, a_down4, a_down5, a_down6, a_down7, a_down8,
	a_left1, a_left2, a_left3, a_left4, a_left5, a_left6, a_left7, a_left8,
	a_right1, a_right2, a_right3, a_right4, a_right5, a_right6, a_right7, a_right8;
	
	public BufferedImage image, image2, image3;
	public Rectangle solidBoundary = new Rectangle(0, 0, 64, 64);
	public Rectangle attackArea = new Rectangle(0, 0, 0, 0);
	public int solidBoundaryDefaultX, solidBoundaryDefaultY;
	public boolean collision = false;
	String chats[] = new String[20];
	
	//STATE:
	public int worldX, worldY;
	public String direction = "down";
	public int spriteNum = 1;
	int chatIndex = 0;
	
	public boolean collisionOn = false;
	public boolean invuln = false;
	public boolean attacking = false;
	public boolean alive = true;
	public boolean dying = false;
	public boolean hpBarActive = false;
	
	//counters
	public int spriteCounter = 0;
	public int actionLockCounter = 0;
	public int invulnCount;
	public int deathCount;
	public int hpCount = 0;
	
	// attribs
	public int entType; // 0 = player 1 = npc 2= mob
	public String name;
	public int speed;
	public int maxHealth;
	public int health;
	public int level;
	public int str;
	public int dex;
	public int atk;
	public int def;
	public int xp;
	public int nextLevelXP;
	public int drachma;
	
	// "player" items
	public Entity currentWeapon;
	public Entity currentShield;
	
	//item attribs
	public int attackValue;
	public int defenseValue;
	
	

	public Entity(GPane gp) {
		this.gp = gp;
	}
	
	public void setAction() {
		
	}
	public void damageReact() {
		
	}
	
	public void speak() {
		if(chats[chatIndex] == null) {
			chatIndex = 0;
		}
		gp.ui.currentChat = chats[chatIndex];
		chatIndex++;
		
		switch(gp.player.direction) {
		case "up":
			direction = "down";
			break;
		case "down":
			direction = "up";
			break;
		case "left":
			direction = "right";
			break;
		case "right":
			direction = "left";
			break;
		}
	}
	
	public void update() {
		setAction();
		
		collisionOn = false;
		gp.cManager.checkTile(this);
		gp.cManager.checkObject(this, false);
		gp.cManager.checkEntity(this,  gp.npc);
		gp.cManager.checkEntity(this,  gp.monster);
		boolean contactPlayer = gp.cManager.checkPlayer(this);	
		
		if(this.entType == 2 && contactPlayer) {
			//if the entity is a mob, and touches the player, run this
			if(gp.player.invuln == false) {
				
				int damage = atk - gp.player.def;
				if(damage < 0) damage = 0;
				
				gp.player.health -=damage;
				gp.player.invuln = true;
			}
		}
		
		
		if(collisionOn == false) {
			switch (direction) {
			case "up": worldY -= speed;break;
			case "down": worldY += speed;break;
			case "left": worldX -= speed;break;
			case "right": worldX += speed;break;
			}
		}
		spriteCounter++;
		if(spriteCounter > 6) {
			spriteNum++;
			 if(spriteNum == 8) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
		if(invuln) {
			invulnCount++;
			if(invulnCount > 40) {
				invuln = false;
				invulnCount = 0;
			}
		}
	}
	
	public BufferedImage setup(String imagePath, int width, int height) {
		Toolbox tBox = new Toolbox();
		BufferedImage scaledImage = null;
		
		try {
			scaledImage = ImageIO.read(getClass().getResource(imagePath + ".png"));
			scaledImage = tBox.scaleImage(scaledImage, width, height);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return scaledImage;
	}

	public void render(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		
		//only draw what tiles we can see using math
		if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
		   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
		   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
		   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			switch(direction) {
			case "up":
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
				if(spriteNum == 5) {image = up5;}
				if(spriteNum == 6) {image = up6;}
				if(spriteNum == 7) {image = up7;}
				if(spriteNum == 8) {image = up8;}
				break;
			case "down":
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				if(spriteNum == 5) {image = down5;}
				if(spriteNum == 6) {image = down6;}
				if(spriteNum == 7) {image = down7;}
				if(spriteNum == 8) {image = down8;}
				break;
			case "left":
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				if(spriteNum == 5) {image = left5;}
				if(spriteNum == 6) {image = left6;}
				if(spriteNum == 7) {image = left7;}
				if(spriteNum == 8) {image = left8;}
				break;
			case "right":
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				if(spriteNum == 5) {image = right5;}
				if(spriteNum == 6) {image = right6;}
				if(spriteNum == 7) {image = right7;}
				if(spriteNum == 8) {image = right8;}
				break;
			}
			
			// mon hp bar
			if(entType == 2 && hpBarActive) {
				double hpScale = (double)gp.tileSize/maxHealth;
				double hpBarVal = hpScale*health;
				
				g2.setColor(new Color(31, 31, 31));
				g2.fillRect(screenX-1, screenY-16,  gp.tileSize+2,  12);
				g2.setColor(new Color(211, 0, 30));
				g2.fillRect(screenX,  screenY - 15, (int)hpBarVal, 10);
				
				hpCount++;
				if(hpCount > 600) {
					hpCount = 0;
					hpBarActive = false;
				}
			}
			
			
			if(invuln) {
				hpBarActive = true;
				changeAlpha(g2,0.4f);
			}
			if(dying) {
				dyingAnim(g2);
			}
			
			if(gp.keyH.checkDrawtime) {
				g2.setColor(Color.RED);
				g2.drawRect(screenX + solidBoundary.x, screenY + solidBoundary.y, solidBoundary.width, solidBoundary.height);
			}
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			changeAlpha(g2,1f);
			
		}
	}
	public void dyingAnim(Graphics2D g2) {
		deathCount++;
		int i = 5;
		if(deathCount <= i) { changeAlpha(g2, 0f);}
		if(deathCount > i && deathCount <= i*2) { changeAlpha(g2, 1f); }
		if(deathCount > i*2 && deathCount <= i*3) { changeAlpha(g2, 0f); }
		if(deathCount > i*3 && deathCount <= i*4) { changeAlpha(g2, 1f); }
		if(deathCount > i*4 && deathCount <= i*5) { changeAlpha(g2, 0f); }
		if(deathCount > i*5 && deathCount <= i*6) { changeAlpha(g2, 1f); }
		if(deathCount > i*6) {
			dying = false;
			alive = false;
		}
	}
	public void changeAlpha(Graphics2D g2, float alpha) {
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
	}
}
