package main;

import entity.Entity;

public class CollisionManager {
	
	GPane gp;
	public CollisionManager(GPane gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidBoundary.x;
		int entityRightWorldX = entity.worldX + entity.solidBoundary.x + entity.solidBoundary.width;
		int entityTopWorldY = entity.worldY + entity.solidBoundary.y;
		int entityBottomWorldY = entity.worldY + entity.solidBoundary.y + entity.solidBoundary.height;
		
		int entityLeftCol = entityLeftWorldX/gp.tileSize;
		int entityRightCol = entityRightWorldX/gp.tileSize;
		int entityTopRow = entityTopWorldY/gp.tileSize;
		int entityBottomRow = entityBottomWorldY/gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collison == true || gp.tileM.tile[tileNum2].collison == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}

	public int checkObject(Entity entity, boolean player) {
		
		int index = 999;
		
		for(int i = 0; i < gp.obj.length; i++) {
			if(gp.obj[i] != null) {
				// get entity's collision box
				entity.solidBoundary.x = entity.worldX + entity.solidBoundary.x;
				entity.solidBoundary.y = entity.worldY + entity.solidBoundary.y;
				
				// get object's collison box
				gp.obj[i].solidBoundary.x = gp.obj[i].worldX + gp.obj[i].solidBoundary.x;
				gp.obj[i].solidBoundary.y = gp.obj[i].worldY + gp.obj[i].solidBoundary.y;
				
				switch(entity.direction ) {
				case "up": entity.solidBoundary.y -= entity.speed; break;
				case "down": entity.solidBoundary.y += entity.speed;	break;
				case "left": entity.solidBoundary.x -= entity.speed; break;
				case "right": entity.solidBoundary.x += entity.speed; break;
				}
				
				if(entity.solidBoundary.intersects(gp.obj[i].solidBoundary)) {
					if(gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					
					if(player == true) {
						index = i;
					}
				}
				
				entity.solidBoundary.x = entity.solidBoundaryDefaultX;
				entity.solidBoundary.y = entity.solidBoundaryDefaultY;
				gp.obj[i].solidBoundary.x = gp.obj[i].solidBoundaryDefaultX;
				gp.obj[i].solidBoundary.y = gp.obj[i].solidBoundaryDefaultY;
			}
		}
		
		return index;
	}
	
	public int checkEntity(Entity entity, Entity[] target) {
		int index = 999;
		
		for(int i = 0; i < target.length; i++) {
			if(target[i] != null) {
				// get entity's collision box
				entity.solidBoundary.x = entity.worldX + entity.solidBoundary.x;
				entity.solidBoundary.y = entity.worldY + entity.solidBoundary.y;
				
				// get object's collison box
				target[i].solidBoundary.x = target[i].worldX + target[i].solidBoundary.x;
				target[i].solidBoundary.y = target[i].worldY + target[i].solidBoundary.y;
				
				switch(entity.direction ) {
				case "up": entity.solidBoundary.y -= entity.speed; break;
				case "down": entity.solidBoundary.y += entity.speed; break;
				case "left": entity.solidBoundary.x -= entity.speed; break;
				case "right": entity.solidBoundary.x += entity.speed; break;
				}
				
				if(entity.solidBoundary.intersects(target[i].solidBoundary)) {
					if(target[i] != entity) {
						entity.collisionOn = true;
						index = i;
					}
				}
				entity.solidBoundary.x = entity.solidBoundaryDefaultX;
				entity.solidBoundary.y = entity.solidBoundaryDefaultY;
				target[i].solidBoundary.x = target[i].solidBoundaryDefaultX;
				target[i].solidBoundary.y = target[i].solidBoundaryDefaultY;
			}
		}
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		
		boolean playerContact = false;
		
		entity.solidBoundary.x = entity.worldX + entity.solidBoundary.x;
		entity.solidBoundary.y = entity.worldY + entity.solidBoundary.y;
		
		// get object's collison box
		gp.player.solidBoundary.x = gp.player.worldX + gp.player.solidBoundary.x;
		gp.player.solidBoundary.y = gp.player.worldY + gp.player.solidBoundary.y;
		
		switch(entity.direction ) {
		case "up": entity.solidBoundary.y -= entity.speed; break;
		case "down": entity.solidBoundary.y += entity.speed; break;
		case "left": entity.solidBoundary.x -= entity.speed; break;
		case "right": entity.solidBoundary.x += entity.speed; break;
		}
		
		if(entity.solidBoundary.intersects(gp.player.solidBoundary)) {
			entity.collisionOn = true;
			playerContact = true;
		}
		
		entity.solidBoundary.x = entity.solidBoundaryDefaultX;
		entity.solidBoundary.y = entity.solidBoundaryDefaultY;
		gp.player.solidBoundary.x = gp.player.solidBoundaryDefaultX;
		gp.player.solidBoundary.y = gp.player.solidBoundaryDefaultY;
		
		return playerContact;
	}
}