package main;

import entity.NPC_Bones;
import monster.MON_Scav;
import object.OBJ_Door;
import object.OBJ_Keycard;
import object.OBJ_SandStriders;

public class ObjectManager {
	
	GPane gp;
	
	public ObjectManager(GPane gp) {
		this.gp = gp;
	}
	
	public void initObject() {
		gp.obj[0] = new OBJ_SandStriders(gp);
		gp.obj[0].worldX = gp.tileSize*(23-1);
		gp.obj[0].worldY = gp.tileSize*(21-1);

	}
	
	public void initNPC() {
		gp.npc[0] = new NPC_Bones(gp);
		gp.npc[0].worldX = gp.tileSize*16;
		gp.npc[0].worldY = gp.tileSize*14;
	}
	
	public void initMonster() {
		gp.monster[0] = new MON_Scav(gp);
		gp.monster[0].worldX = gp.tileSize*4;
		gp.monster[0].worldY = gp.tileSize*29;
		gp.monster[1] = new MON_Scav(gp);
		gp.monster[1].worldX = gp.tileSize*5;
		gp.monster[1].worldY = gp.tileSize*29;
	}

}
