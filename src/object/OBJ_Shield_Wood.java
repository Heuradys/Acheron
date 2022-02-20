package object;

import entity.Entity;
import main.GPane;

public class OBJ_Shield_Wood extends Entity{

	public OBJ_Shield_Wood(GPane gp) {
		super(gp);
		name = "Wooden Shield";
		down1 = setup("/objects/shield_wood", gp.tileSize, gp.tileSize);
		defenseValue = 1;
	}

}
