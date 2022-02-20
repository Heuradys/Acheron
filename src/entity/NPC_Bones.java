package entity;

import java.util.Random;

import main.GPane;

public class NPC_Bones extends Entity{

	public NPC_Bones(GPane gp) {
		super(gp);
		
		direction = "down";
		speed = 1;
		entType = 1;
		
		solidBoundary.x = 23;
		solidBoundary.y = 12;
		solidBoundaryDefaultX = solidBoundary.x;
		solidBoundaryDefaultY = solidBoundary.y;
		solidBoundary.width = 20; //20;
		solidBoundary.height = 46; //46;
		
		getImage();
		initChat();
	}
	
	public void initChat() {
		chats[0] = "Well met Traveler!";
		chats[1] = "The ruins of Caer Sidi are a sight to behold.";
		chats[2] = "Three-score hundreds once stood in these walls,\n"
				+ "now all is silent.";
		chats[3] = "Annwn and Pwyll bless you."; 
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
	
	public void speak() {
		super.speak();
	}
	
	public void getImage() {
		up1 = setup("/npc/back/hood_back_1", gp.tileSize, gp.tileSize);
		up2 = setup("/npc/back/hood_back_2", gp.tileSize, gp.tileSize);
		up3 = setup("/npc/back/hood_back_3", gp.tileSize, gp.tileSize);
		up4 = setup("/npc/back/hood_back_4", gp.tileSize, gp.tileSize);
		up5 = setup("/npc/back/hood_back_5", gp.tileSize, gp.tileSize);
		up6 = setup("/npc/back/hood_back_6", gp.tileSize, gp.tileSize);
		up7 = setup("/npc/back/hood_back_7", gp.tileSize, gp.tileSize);
		up8 = setup("/npc/back/hood_back_8", gp.tileSize, gp.tileSize);
			
		down1 = setup("/npc/front/hood_front_1", gp.tileSize, gp.tileSize);
		down2 = setup("/npc/front/hood_front_2", gp.tileSize, gp.tileSize);
		down3 = setup("/npc/front/hood_front_3", gp.tileSize, gp.tileSize);
		down4 = setup("/npc/front/hood_front_4", gp.tileSize, gp.tileSize);
		down5 = setup("/npc/front/hood_front_5", gp.tileSize, gp.tileSize);
		down6 = setup("/npc/front/hood_front_6", gp.tileSize, gp.tileSize);
		down7 = setup("/npc/front/hood_front_7", gp.tileSize, gp.tileSize);
		down8 = setup("/npc/front/hood_front_8", gp.tileSize, gp.tileSize);
			
		right1 = setup("/npc/right/hood_right_1", gp.tileSize, gp.tileSize);
		right2 = setup("/npc/right/hood_right_2", gp.tileSize, gp.tileSize);
		right3 = setup("/npc/right/hood_right_3", gp.tileSize, gp.tileSize);
		right4 = setup("/npc/right/hood_right_4", gp.tileSize, gp.tileSize);
		right5 = setup("/npc/right/hood_right_5", gp.tileSize, gp.tileSize);
		right6 = setup("/npc/right/hood_right_6", gp.tileSize, gp.tileSize);
		right7 = setup("/npc/right/hood_right_7", gp.tileSize, gp.tileSize);
		right8 = setup("/npc/right/hood_right_8", gp.tileSize, gp.tileSize);
			
		left1 = setup("/npc/left/hood_left_1", gp.tileSize, gp.tileSize);
		left2 = setup("/npc/left/hood_left_2", gp.tileSize, gp.tileSize);
		left3 = setup("/npc/left/hood_left_3", gp.tileSize, gp.tileSize);
		left4 = setup("/npc/left/hood_left_4", gp.tileSize, gp.tileSize);
		left5 = setup("/npc/left/hood_left_5", gp.tileSize, gp.tileSize);
		left6 = setup("/npc/left/hood_left_6", gp.tileSize, gp.tileSize);
		left7 = setup("/npc/left/hood_left_7", gp.tileSize, gp.tileSize);
		left8 = setup("/npc/left/hood_left_8", gp.tileSize, gp.tileSize);
	}

}
