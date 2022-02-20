package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import entity.Entity;
import object.OBJ_Health;
import object.OBJ_Keycard;

public class UI {
	GPane gp;
	Graphics2D g2;
	Font hell_40;
	BufferedImage health_full, health_half, health_blank;
	//public boolean messageOn = false;
	//public String message = "";
	ArrayList<String> message = new ArrayList<>();
	ArrayList<Integer> messageCounter = new ArrayList<>();
	int messageFadeTime = 0;
	public boolean gameFinished = false;
	public String currentChat = "";
	public int commandNum = 0;
	
	double playTime;
	DecimalFormat dForm = new DecimalFormat("#0");
	
	Color fontColor = new Color(155, 86, 86);
	
	public UI(GPane gp) {
		this.gp = gp;
		
		hell_40 = new Font("Romanus", Font.PLAIN, 40);
		Entity health = new OBJ_Health(gp);
		health_full = health.image;
		health_half = health.image2;
		health_blank = health.image3;
		
	}
	
	public void addMessage(String msg) {
		message.add(msg);
		messageCounter.add(0);
	}
	
	public void render(Graphics2D g2) {
		this.g2 = g2;
		
		g2.setFont(hell_40);
		g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g2.setColor(new Color(155, 86, 86));
		
		
		if(gp.gameState == gp.titleState) {
			renderTitleScreen();
		}
		if(gp.gameState == gp.playState) {
			renderPlayerHealth();
			renderMessage();
		}
		if(gp.gameState == gp.pauseState) {
			renderPauseScreen();
		}
		
		if(gp.gameState == gp.chatState) {
			renderChatScreen();
		}
		if(gp.gameState == gp.charState) {
			renderCharScreen();
		}
		
	}
	
	public void renderPlayerHealth() {
		
		double hpScale = (double)gp.tileSize/gp.player.maxHealth;
		double hpBarVal = hpScale*gp.player.health;
		int healthX = gp.screenWidth-(gp.tileSize*16) + 16;
		int healthY = gp.screenHeight-(gp.tileSize) + 48;
		
		//g2.setColor(new Color(31, 31, 31));
		//g2.fillRect(healthX, healthY,  gp.tileSize,  gp.tileSize);
		g2.setColor(new Color(211, 0, 30));
		int healthY2 = healthY - (int)hpBarVal;
		//g2.fillRect(healthX, healthY2, gp.tileSize, (int)hpBarVal);
		g2.fillRoundRect(healthX, healthY2, gp.tileSize, (int)hpBarVal, 25, 25);
		
		//hpCount++;
		//if(hpCount > 600) {
		//	hpCount = 0;
		//	hpBarActive = false;
		//}
		
		/*int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int index = 0;
		
		while(index < gp.player.maxHealth/2) {
			g2.drawImage(health_blank,  x,  y, null);
			index++;
			x += gp.tileSize;
		}
		
		//reset stuff
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		index = 0;
		
		while (index < gp.player.health) {
			g2.drawImage(health_half,  x,  y,  null);
			index++;
			if(index < gp.player.health) {
				g2.drawImage(health_full,  x,  y,  null);
			}
			index++;
			x += gp.tileSize;
		}*/
	}

	public void renderMessage() {
		int messageX = gp.tileSize;
		int messageY = gp.tileSize*4;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));
		
		for(int i = 0; i < message.size(); i++) {
			if(message.get(i) != null) {
				g2.setColor(Color.black);
				g2.drawString(message.get(i), messageX+2, messageY+2);
				g2.setColor(Color.white);
				g2.drawString(message.get(i), messageX, messageY);
				
				int counter = messageCounter.get(i) + 1;
				messageCounter.set(i, counter);
				messageY += 50;
				
				if(messageCounter.get(i) > 140) {
					message.remove(i);
					messageCounter.remove(i);
				}
			}
		}
	}
	
	public void renderTitleScreen() {
		// main menu title name
		
		g2.setColor(new Color(200, 200, 200));
		g2.fillRect(0,  0,  gp.screenWidth, gp.screenHeight);
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,64F));
		String text = "The Wilds of Acheron";
		int x = getXForCenterText(text);
		int y = gp.tileSize*3;
		
		
		//testing shadow
		g2.setColor(new Color(61, 61, 61));
		g2.drawString(text, x+3, y+3);
		
		//main
		g2.setColor(new Color(200, 105, 105));
		g2.drawString(text, x, y);
		
		// draw centered image
		x = gp.screenWidth/2 - (gp.tileSize*2)/2;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down3, x, y, gp.tileSize*2, gp.tileSize*2, null);
		
		// mm
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 36F));
		g2.setColor(new Color(61, 61, 61));
		
		text = "NEW GAME";
		x = getXForCenterText(text);
		y += gp.tileSize*3.5;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.setColor(new Color(200, 105, 105));
			g2.drawString("x", x-gp.tileSize, y - 7);
			g2.setColor(new Color(61, 61, 61));
		}
		
		text = "LOAD GAME";
		x = getXForCenterText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 1) {
			g2.setColor(new Color(200, 105, 105));
			g2.drawString("x", x-gp.tileSize, y - 7);
			g2.setColor(new Color(61, 61, 61));
		}
		
		text = "QUIT GAME";
		x = getXForCenterText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		if(commandNum == 2) {
			g2.setColor(new Color(200, 105, 105));
			g2.drawString("x", x-gp.tileSize, y - 7);
			g2.setColor(new Color(61, 61, 61));
		}
	}

	public void renderPauseScreen() {
		
		g2.setFont(g2.getFont().deriveFont(50F));
		String text = "Paused";
		int x = getXForCenterText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y - gp.tileSize);
	}
	
	public void renderChatScreen() {
		// window
		int x = gp.tileSize*2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - (gp.tileSize*4);
		int height = gp.tileSize*4;
		
		renderSubwindow(x, y, width, height);
		
		g2.setColor(new Color(61, 61, 61));
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		
		x += gp.tileSize;
		y += gp.tileSize;
		
		for(String line : currentChat.split("\n")) {
			g2.drawString(line, x, y);
			y += 40;
		}
		
	}
	
	public void renderCharScreen() {
		final int frameX = gp.tileSize - (gp.tileSize/2);
		final int frameY = gp.tileSize- (gp.tileSize/2);
		final int frameWidth = gp.tileSize*14;
		final int frameHeight = gp.tileSize*10;
		renderSubwindow(frameX, frameY, frameWidth, frameHeight);
		
		//text
		g2.setColor(Color.white);
		g2.setFont(g2.getFont().deriveFont(32f));
		
		int textX = frameX + 32;
		int textY = frameY + gp.tileSize - 16;
		final int lineHeight = 48;
		
		//names
		g2.drawString("Level", textX, textY);
		textY+=lineHeight;
		g2.drawString("Health", textX, textY);
		textY+=lineHeight;
		g2.drawString("Strength", textX, textY);
		textY+=lineHeight;
		g2.drawString("Dexterity", textX, textY);
		textY+=lineHeight;
		g2.drawString("Attack", textX, textY);
		textY+=lineHeight;
		g2.drawString("Defense", textX, textY);
		textY+=lineHeight;
		g2.drawString("Exp", textX, textY);
		textY+=lineHeight;
		g2.drawString("Next Level", textX, textY);
		textY+=lineHeight;
		g2.drawString("Drachma", textX, textY);
		textY+=lineHeight + 20;
		g2.drawString("Weapon", textX, textY);
		textY+=lineHeight + 16;
		g2.drawString("Shield", textX, textY);
		textY+=lineHeight;
		
		//vals
		int tailX = (frameX + frameWidth) - 32;
		textY = frameY + gp.tileSize - 16;
		String val;
		
		val = String.valueOf(gp.player.level);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.health + "/" + gp.player.maxHealth);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.str);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.dex);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.atk);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.def);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.xp);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.nextLevelXP);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		val = String.valueOf(gp.player.drachma);
		textX = getXForAlignRight(val,tailX);
		g2.drawString(val, textX, textY);
		textY+=lineHeight;
		
		g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY-14, null);
		textY += gp.tileSize;
		g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-14, null);
		textY += gp.tileSize;
	}
	
	public void renderSubwindow(int x, int y, int width, int height)
	{
		Color c = new Color(61, 61, 61, 200); //61 61 61
		g2.setColor(c);
		g2.fillRoundRect(x, y, width, height, 25, 25);
		
		c = new Color(0,0,0, 200); //200 200 200
		g2.setColor(c);
		
		g2.setStroke(new BasicStroke(5));
		g2.fillRoundRect(x+5, y+5, width-10, height-10, 25, 25);
	}

	
	
	public int getXForCenterText(String text) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = gp.screenWidth /2 - length/2;
		return x;
	}
	public int getXForAlignRight(String text, int tailX) {
		int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int x = tailX - length;
		return x;
	}
}
