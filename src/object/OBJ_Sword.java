package object;

import entity.Entity;
import main.GPane;

public class OBJ_Sword extends Entity {

	public OBJ_Sword(GPane gp) {
		super(gp);

		name = "Ruined Hope";
		down1 = setup("/objects/sword_ruined_hope", gp.tileSize, gp.tileSize);
		attackValue = 1;
	}
	
}
