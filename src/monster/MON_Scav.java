package monster;

import java.util.Random;

import entity.Entity;
import main.GPane;



public class MON_Scav extends Entity {
	GPane gp;

	public MON_Scav(GPane gp) {
		super(gp);
		this.gp = gp;
		
		entType = 2;
		name = "Scav";
		speed = 2;
		maxHealth = 4;
		health = maxHealth;
		atk = 2;
		def = 0;
		xp = 4;
		
		solidBoundary.x = 23;
		solidBoundary.y = 12;
		solidBoundary.width = 20; //20;
		solidBoundary.height = 46; //46;
		solidBoundaryDefaultX = solidBoundary.x;
		solidBoundaryDefaultY = solidBoundary.y;
		
		getImage();
	}
	
	public void setAction() {
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
			Random random = new Random();
			int i = random.nextInt(100)+1;
			
			if(i <= 25) {
				direction = "up";
			} 
			if(i > 25 && i <= 50) {
				direction = "down";
			}
			if(i > 50 && i <= 75) {
				direction = "left";
			}
			if(i > 75 && i <= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}

	public void damageReact() {
		actionLockCounter = 0;
		direction = gp.player.direction;
	}
	
	public void getImage() {
		up1 = setup("/monster/scav/back/hood_back_1", gp.tileSize, gp.tileSize);
		up2 = setup("/monster/scav/back/hood_back_2", gp.tileSize, gp.tileSize);
		up3 = setup("/monster/scav/back/hood_back_3", gp.tileSize, gp.tileSize);
		up4 = setup("/monster/scav/back/hood_back_4", gp.tileSize, gp.tileSize);
		up5 = setup("/monster/scav/back/hood_back_5", gp.tileSize, gp.tileSize);
		up6 = setup("/monster/scav/back/hood_back_6", gp.tileSize, gp.tileSize);
		up7 = setup("/monster/scav/back/hood_back_7", gp.tileSize, gp.tileSize);
		up8 = setup("/monster/scav/back/hood_back_8", gp.tileSize, gp.tileSize);
			
		down1 = setup("/monster/scav/front/hood_front_1", gp.tileSize, gp.tileSize);
		down2 = setup("/monster/scav/front/hood_front_2", gp.tileSize, gp.tileSize);
		down3 = setup("/monster/scav/front/hood_front_3", gp.tileSize, gp.tileSize);
		down4 = setup("/monster/scav/front/hood_front_4", gp.tileSize, gp.tileSize);
		down5 = setup("/monster/scav/front/hood_front_5", gp.tileSize, gp.tileSize);
		down6 = setup("/monster/scav/front/hood_front_6", gp.tileSize, gp.tileSize);
		down7 = setup("/monster/scav/front/hood_front_7", gp.tileSize, gp.tileSize);
		down8 = setup("/monster/scav/front/hood_front_8", gp.tileSize, gp.tileSize);
			
		right1 = setup("/monster/scav/right/hood_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/monster/scav/right/hood_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/monster/scav/right/hood_right_3", gp.tileSize, gp.tileSize);
		right4 = setup("/monster/scav/right/hood_right_4", gp.tileSize, gp.tileSize);
		right5 = setup("/monster/scav/right/hood_right_5", gp.tileSize, gp.tileSize);
		right6 = setup("/monster/scav/right/hood_right_6", gp.tileSize, gp.tileSize);
		right7 = setup("/monster/scav/right/hood_right_7", gp.tileSize, gp.tileSize);
		right8 = setup("/monster/scav/right/hood_right_8", gp.tileSize, gp.tileSize);
			
		left1 = setup("/monster/scav/left/hood_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/monster/scav/left/hood_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/monster/scav/left/hood_left_3", gp.tileSize, gp.tileSize);
		left4 = setup("/monster/scav/left/hood_left_4", gp.tileSize, gp.tileSize);
		left5 = setup("/monster/scav/left/hood_left_5", gp.tileSize, gp.tileSize);
		left6 = setup("/monster/scav/left/hood_left_6", gp.tileSize, gp.tileSize);
		left7 = setup("/monster/scav/left/hood_left_7", gp.tileSize, gp.tileSize);
		left8 = setup("/monster/scav/left/hood_left_8", gp.tileSize, gp.tileSize);
	}

}
