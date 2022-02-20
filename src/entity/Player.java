package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GPane;
import main.KeyHandler;
import main.Toolbox;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword;

public class Player extends Entity {
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public boolean attackCancel = false;
	
	
	public Player(GPane gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidBoundary = new Rectangle();
		solidBoundary.x = 23;
		solidBoundary.y = 12;
		solidBoundaryDefaultX = solidBoundary.x;
		solidBoundaryDefaultY = solidBoundary.y;
		solidBoundary.width = 20; //20;
		solidBoundary.height = 46; //46;
		
		attackArea.width = 40;
		attackArea.height = 40;
		
		setDefaultValues();
		getPlayerImage();
		getPlayerAttackImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 34; //starting pos on map
		worldY = gp.tileSize * 11;
		speed = 4;
		direction = "down";
		
		// player stats
		level = 1;
		maxHealth = 4;
		health = maxHealth;
		str = 1;
		dex = 1;
		xp = 0;
		nextLevelXP = 5;
		drachma =0;
		currentWeapon = new OBJ_Sword(gp);
		currentShield = new OBJ_Shield_Wood(gp);
		atk = getAttack();
		def = getDefense();
	}
	public int getAttack() {
		return atk = str*currentWeapon.attackValue;
	}
	public int getDefense() {
		return def = dex*currentShield.defenseValue;
	}
	
	
	public void getPlayerImage() {
		
		//TODO: Convert this God-awful spaghetti code into something with an array and for loops
		up1 = setup("/player/back/hood_back_1", gp.tileSize, gp.tileSize);
		up2 = setup("/player/back/hood_back_2", gp.tileSize, gp.tileSize);
		up3 = setup("/player/back/hood_back_3", gp.tileSize, gp.tileSize);
		up4 = setup("/player/back/hood_back_4", gp.tileSize, gp.tileSize);
		up5 = setup("/player/back/hood_back_5", gp.tileSize, gp.tileSize);
		up6 = setup("/player/back/hood_back_6", gp.tileSize, gp.tileSize);
		up7 = setup("/player/back/hood_back_7", gp.tileSize, gp.tileSize);
		up8 = setup("/player/back/hood_back_8", gp.tileSize, gp.tileSize);
			
		down1 = setup("/player/front/hood_front_1", gp.tileSize, gp.tileSize);
		down2 = setup("/player/front/hood_front_2", gp.tileSize, gp.tileSize);
		down3 = setup("/player/front/hood_front_3", gp.tileSize, gp.tileSize);
		down4 = setup("/player/front/hood_front_4", gp.tileSize, gp.tileSize);
		down5 = setup("/player/front/hood_front_5", gp.tileSize, gp.tileSize);
		down6 = setup("/player/front/hood_front_6", gp.tileSize, gp.tileSize);
		down7 = setup("/player/front/hood_front_7", gp.tileSize, gp.tileSize);
		down8 = setup("/player/front/hood_front_8", gp.tileSize, gp.tileSize);
			
		right1 = setup("/player/right/hood_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/player/right/hood_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/player/right/hood_right_3", gp.tileSize, gp.tileSize);
		right4 = setup("/player/right/hood_right_4", gp.tileSize, gp.tileSize);
		right5 = setup("/player/right/hood_right_5", gp.tileSize, gp.tileSize);
		right6 = setup("/player/right/hood_right_6", gp.tileSize, gp.tileSize);
		right7 = setup("/player/right/hood_right_7", gp.tileSize, gp.tileSize);
		right8 = setup("/player/right/hood_right_8", gp.tileSize, gp.tileSize);
			
		left1 = setup("/player/left/hood_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/player/left/hood_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/player/left/hood_left_3", gp.tileSize, gp.tileSize);
		left4 = setup("/player/left/hood_left_4", gp.tileSize, gp.tileSize);
		left5 = setup("/player/left/hood_left_5", gp.tileSize, gp.tileSize);
		left6 = setup("/player/left/hood_left_6", gp.tileSize, gp.tileSize);
		left7 = setup("/player/left/hood_left_7", gp.tileSize, gp.tileSize);
		left8 = setup("/player/left/hood_left_8", gp.tileSize, gp.tileSize);
	}
	
	public void getPlayerAttackImage() {
		
		//TODO: Convert this God-awful spaghetti code into something with an array and for loops
		a_up1 = setup("/player/attack/up/up_1", gp.tileSize, gp.tileSize*2);
		a_up2 = setup("/player/attack/up/up_2", gp.tileSize, gp.tileSize*2);
		a_up3 = setup("/player/attack/up/up_3", gp.tileSize, gp.tileSize*2);
		a_up4 = setup("/player/attack/up/up_4", gp.tileSize, gp.tileSize*2);
		a_up5 = setup("/player/attack/up/up_5", gp.tileSize, gp.tileSize*2);
		a_up6 = setup("/player/attack/up/up_6", gp.tileSize, gp.tileSize*2);
		a_up7 = setup("/player/attack/up/up_7", gp.tileSize, gp.tileSize*2);
		a_up8 = setup("/player/attack/up/up_8", gp.tileSize, gp.tileSize*2);
			
		a_down1 = setup("/player/attack/down/front_1", gp.tileSize, gp.tileSize*2);
		a_down2 = setup("/player/attack/down/front_2", gp.tileSize, gp.tileSize*2);
		a_down3 = setup("/player/attack/down/front_3", gp.tileSize, gp.tileSize*2);
		a_down4 = setup("/player/attack/down/front_4", gp.tileSize, gp.tileSize*2);
		a_down5 = setup("/player/attack/down/front_5", gp.tileSize, gp.tileSize*2);
		a_down6 = setup("/player/attack/down/front_6", gp.tileSize, gp.tileSize*2);
		a_down7 = setup("/player/attack/down/front_7", gp.tileSize, gp.tileSize*2);
		a_down8 = setup("/player/attack/down/front_8", gp.tileSize, gp.tileSize*2);
			
		a_right1 = setup("/player/attack/right/right_1", gp.tileSize*2, gp.tileSize);
		a_right2 = setup("/player/attack/right/right_2", gp.tileSize*2, gp.tileSize);
		a_right3 = setup("/player/attack/right/right_3", gp.tileSize*2, gp.tileSize);
		a_right4 = setup("/player/attack/right/right_4", gp.tileSize*2, gp.tileSize);
		a_right5 = setup("/player/attack/right/right_5", gp.tileSize*2, gp.tileSize);
		a_right6 = setup("/player/attack/right/right_6", gp.tileSize*2, gp.tileSize);
		a_right7 = setup("/player/attack/right/right_7", gp.tileSize*2, gp.tileSize);
		a_right8 = setup("/player/attack/right/right_8", gp.tileSize*2, gp.tileSize);
			
		a_left1 = setup("/player/attack/left/left_1", gp.tileSize*2, gp.tileSize);
		a_left2 = setup("/player/attack/left/left_2", gp.tileSize*2, gp.tileSize);
		a_left3 = setup("/player/attack/left/left_3", gp.tileSize*2, gp.tileSize);
		a_left4 = setup("/player/attack/left/left_4", gp.tileSize*2, gp.tileSize);
		a_left5 = setup("/player/attack/left/left_5", gp.tileSize*2, gp.tileSize);
		a_left6 = setup("/player/attack/left/left_6", gp.tileSize*2, gp.tileSize);
		a_left7 = setup("/player/attack/left/left_7", gp.tileSize*2, gp.tileSize);
		a_left8 = setup("/player/attack/left/left_8", gp.tileSize*2, gp.tileSize);
	}
	
	
	public void update() {
		
		if (attacking == true) {
			attack();
		}
		
		else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true 
				|| keyH.rightPressed == true || keyH.chatButtonPressed == true) {
			if(keyH.upPressed == true) {
				direction = "up";
			}
			else if(keyH.downPressed == true) {
				direction = "down";
			}
			else if(keyH.leftPressed == true) {
				direction = "left";
			}
			else if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			
			//collision checking
			collisionOn = false;
			gp.cManager.checkTile(this);
			
			//check obj
			int objIndex = gp.cManager.checkObject(this, true);
			objPickup(objIndex);
			
			//check npc
			
			int npcIndex = gp.cManager.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			//check mob
			int monsterIndex = gp.cManager.checkEntity(this, gp.monster);
			//interactNPC(monsterIndex);
			contactWithMob(monsterIndex);
			
			//check event
			gp.eHand.checkEvent();
			
			
			
			//if we aren't colliding with stuff, move the character
			if(collisionOn == false && gp.keyH.chatButtonPressed == false) {
				switch (direction) {
				case "up": worldY -= speed;break;
				case "down": worldY += speed;break;
				case "left": worldX -= speed;break;
				case "right": worldX += speed;break;
				}
			}
			if(keyH.chatButtonPressed && attackCancel == false) {
				gp.playSFX(4);
				attacking = true;
				spriteCounter = 0;
			}
			
			attackCancel = false;
			gp.keyH.chatButtonPressed = false;
			
			spriteCounter++;
			if(spriteCounter > 6) {
				spriteNum++;
				 if(spriteNum == 8) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}
	
	public void attack() {
		spriteCounter++;
		if(spriteCounter <=7) {
			spriteNum = 1;
			
			int currentWorldX = worldX;
			int currentWorldY = worldY;
			int solidBoundaryWidth = solidBoundary.width;
			int solidBoundaryHeight = solidBoundary.height;
			
			// a
			switch(direction) {
			case "up": worldY -= attackArea.height; break;
			case "down": worldY += attackArea.height; break;
			case "left": worldX -= attackArea.width; break;
			case "right": worldY += attackArea.width; break;
			}
			
			solidBoundary.width = attackArea.width;
			solidBoundary.height = attackArea.height;
			
			int monsterIndex = gp.cManager.checkEntity(this, gp.monster);
			damageMonster(monsterIndex);
			
			worldX = currentWorldX;
			worldY = currentWorldY;
			solidBoundary.width = solidBoundaryWidth;
			solidBoundary.height = solidBoundaryHeight;
			
		}
		if(spriteCounter > 7 && spriteCounter <=15) {
			spriteNum = 2;
		}
		if(spriteCounter > 15 && spriteCounter <=23) {
			spriteNum = 3;
		}
		if(spriteCounter > 23 && spriteCounter <=31) {
			spriteNum = 4;
		}
		if(spriteCounter > 31 && spriteCounter <=37) {
			//spriteNum = 5;
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}

		/*if(spriteCounter > 23 && spriteCounter <=27) {
			spriteNum = 6;
		}
		if(spriteCounter > 27 && spriteCounter <=31) {
			spriteNum = 7;
		}
		if(spriteCounter > 31 && spriteCounter <=35) {
			spriteNum = 8;
		}
		if(spriteCounter > 35 && spriteCounter <=39) {
			spriteNum = 1;
			spriteCounter = 0;
			attacking = false;
		}*/
	}

	public void contactWithMob(int index) {
		if (index != 999) {
			if(invuln == false) {
				
				int damage = gp.monster[index].atk - def;
				if(damage < 0) damage = 0;
				
				health -= 1;
				invuln = true;
			}
		}
	}
	
	public void damageMonster(int index) {
		if(index != 999) {
			if(gp.monster[index].invuln == false) {
				gp.playSFX(3);
				
				int damage = atk - gp.monster[index].def;
				if(damage < 0) damage = 0;
				
				gp.monster[index].health -= damage;
				
				gp.ui.addMessage(damage + " damage!");
				
				gp.monster[index].invuln = true;
				gp.monster[index].damageReact();
				
				if(gp.monster[index].health <= 0) {
					gp.monster[index].dying = true;
					gp.ui.addMessage("You killed the " + gp.monster[index].name + "!");
					gp.ui.addMessage("You gained " + gp.monster[index].xp + " xp");
					xp += gp.monster[index].xp;
					checkLevelUP();
				}
			}
		} else {
			//miss stuff
		}
	}
	
	public void checkLevelUP() {
		if(xp >= nextLevelXP) {
			//TODO: Figure out a better level curve
			level++;
			nextLevelXP = nextLevelXP*2;
			maxHealth += 2;
			str++;
			dex++;
			atk = getAttack();
			def = getDefense();
			
			//gp.playSFX(1);
			gp.gameState = gp.chatState;
			gp.ui.currentChat = "You are level " + level + " now!\n" + "You feel yourself become more familiar with the world.";
		}
	}

	public void objPickup(int index) {
		if(index != 999) { //999 = null more or less
		}
		
	}
	
	public void interactNPC(int index) {
		if(gp.keyH.chatButtonPressed == true ) {
			if(index != 999 ) { 
				attackCancel = true;
				gp.gameState = gp.chatState;
				gp.npc[index].speak();
			}
		}
	}
	public void render(Graphics2D g2) {
		
		BufferedImage image = null;
		
		int tempScreenX = screenX;
		int tempScreenY = screenY;
		//TODO: Fix this terrible management of spites
		switch(direction) {
		case "up":
			if(attacking == false) {
				if(spriteNum == 1) {image = up1;}
				if(spriteNum == 2) {image = up2;}
				if(spriteNum == 3) {image = up3;}
				if(spriteNum == 4) {image = up4;}
				if(spriteNum == 5) {image = up5;}
				if(spriteNum == 6) {image = up6;}
				if(spriteNum == 7) {image = up7;}
				if(spriteNum == 8) {image = up8;}
			}
			else if(attacking == true) {
				tempScreenY = screenY - gp.tileSize;
				if(spriteNum == 1) {image = a_up1;}
				if(spriteNum == 2) {image = a_up2;}
				if(spriteNum == 3) {image = a_up3;}
				if(spriteNum == 4) {image = a_up4;}
				if(spriteNum == 5) {image = a_up5;}
				if(spriteNum == 6) {image = a_up6;}
				if(spriteNum == 7) {image = a_up7;}
				if(spriteNum == 8) {image = a_up8;}
			}
			
			break;
		case "down":
			if(attacking == false) {
				if(spriteNum == 1) {image = down1;}
				if(spriteNum == 2) {image = down2;}
				if(spriteNum == 3) {image = down3;}
				if(spriteNum == 4) {image = down4;}
				if(spriteNum == 5) {image = down5;}
				if(spriteNum == 6) {image = down6;}
				if(spriteNum == 7) {image = down7;}
				if(spriteNum == 8) {image = down8;}
			}
			else if(attacking == true) {
				if(spriteNum == 1) {image = a_down1;}
				if(spriteNum == 2) {image = a_down2;}
				if(spriteNum == 3) {image = a_down3;}
				if(spriteNum == 4) {image = a_down4;}
				if(spriteNum == 5) {image = a_down5;}
				if(spriteNum == 6) {image = a_down6;}
				if(spriteNum == 7) {image = a_down7;}
				if(spriteNum == 8) {image = a_down8;}
			}
		
			break;
		case "left":
			if(attacking == false) {
				if(spriteNum == 1) {image = left1;}
				if(spriteNum == 2) {image = left2;}
				if(spriteNum == 3) {image = left3;}
				if(spriteNum == 4) {image = left4;}
				if(spriteNum == 5) {image = left5;}
				if(spriteNum == 6) {image = left6;}
				if(spriteNum == 7) {image = left7;}
				if(spriteNum == 8) {image = left8;}
			}
			else if(attacking == true) {
				tempScreenX = screenX - gp.tileSize;
				if(spriteNum == 1) {image = a_left1;}
				if(spriteNum == 2) {image = a_left2;}
				if(spriteNum == 3) {image = a_left3;}
				if(spriteNum == 4) {image = a_left4;}
				if(spriteNum == 5) {image = a_left5;}
				if(spriteNum == 6) {image = a_left6;}
				if(spriteNum == 7) {image = a_left7;}
				if(spriteNum == 8) {image = a_left8;}
			}
			
			break;
		case "right":
			if(attacking == false) {
				if(spriteNum == 1) {image = right1;}
				if(spriteNum == 2) {image = right2;}
				if(spriteNum == 3) {image = right3;}
				if(spriteNum == 4) {image = right4;}
				if(spriteNum == 5) {image = right5;}
				if(spriteNum == 6) {image = right6;}
				if(spriteNum == 7) {image = right7;}
				if(spriteNum == 8) {image = right8;}
			}
			else if(attacking == true) {
				if(spriteNum == 1) {image = a_right1;}
				if(spriteNum == 2) {image = a_right2;}
				if(spriteNum == 3) {image = a_right3;}
				if(spriteNum == 4) {image = a_right4;}
				if(spriteNum == 5) {image = a_right5;}
				if(spriteNum == 6) {image = a_right6;}
				if(spriteNum == 7) {image = a_right7;}
				if(spriteNum == 8) {image = a_right8;}
			}
			
			break;
		}
		
		g2.drawImage(image, tempScreenX, tempScreenY, null);
	}
}
