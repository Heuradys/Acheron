package main;

import java.awt.Rectangle;

public class EventHandler {
	GPane gp;
	EventR eventRect[][];
	
	int prevEventX, prevEventY;
	boolean canTouchEvent = true;
	
	public EventHandler(GPane gp) {
		this.gp = gp;
		
		eventRect = new EventR[gp.maxWorldCol][gp.maxWorldRow];
		
		int col = 0;
		int row = 0;
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			eventRect[col][row] = new EventR();
			eventRect[col][row].x = 31;
			eventRect[col][row].y = 31;
			eventRect[col][row].width = 2;
			eventRect[col][row].height = 2;
			eventRect[col][row].eventRDefaultX = eventRect[col][row].x;
			eventRect[col][row].eventRDefaultY = eventRect[col][row].y;
			
			col++;
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
	
	public void checkEvent() {
		//checks if the player is more than one "tile" away from alst event
		int xDistance = Math.abs(gp.player.worldX - prevEventX);
		int yDistance = Math.abs(gp.player.worldY - prevEventY);
		int distance = Math.max(xDistance, yDistance);
		if(distance > gp.tileSize) {
			canTouchEvent = true;
		}
		
		if (canTouchEvent) {
			if(trigger((23-1), 21-1, "any") == true) {
				sandStriders((23-1), 21-1, gp.chatState);
			}
		}
		
	}
	
	public boolean trigger(int col, int row, String reqDirection) {
		
		boolean triggered = false;
		
		gp.player.solidBoundary.x = gp.player.worldX + gp.player.solidBoundary.x;
		gp.player.solidBoundary.y = gp.player.worldY + gp.player.solidBoundary.y;
		eventRect[col][row].x = col*gp.tileSize + eventRect[col][row].x;
		eventRect[col][row].y = row*gp.tileSize + eventRect[col][row].y;
		
		if(gp.player.solidBoundary.intersects(eventRect[col][row]) && eventRect[col][row].eventDone == false) {
			if(gp.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
				triggered = true;
				
				prevEventX = gp.player.worldX;
				prevEventY = gp.player.worldY;
			}
		}
		
		gp.player.solidBoundary.x = gp.player.solidBoundaryDefaultX;
		gp.player.solidBoundary.y = gp.player.solidBoundaryDefaultY;
		eventRect[col][row].x = eventRect[col][row].eventRDefaultX;
		eventRect[col][row].y = eventRect[col][row].eventRDefaultY;
		
		return triggered;
	}
	public void curse(int col, int row, int gameState) {
		gp.gameState = gameState;
		gp.ui.currentChat = "The item is cursed and damages you...";
		gp.player.health -= 1;
		eventRect[col][row].eventDone = true;
		canTouchEvent = false;
	}
	
	public void sandStriders(int col, int row, int gameState) {
		
		if(gp.keyH.chatButtonPressed == true) {
			gp.gameState = gameState;
			
			gp.ui.currentChat = "You have unearthed a set of Sidian Sand Striders.\n"
					+ "Although the Fall happened long ago,\n"
					+ "They still look brand new.\n"
				    + "You can now walk twice as fast.";
			gp.obj[0] = null;
			gp.player.speed +=2;
			eventRect[col][row].eventDone = true;
			canTouchEvent = false;
		}
	}
	
}
