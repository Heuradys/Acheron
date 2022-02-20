package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GPane;

public class OBJ_SandStriders extends Entity {

	public OBJ_SandStriders(GPane gp) {
		super(gp);
		
		name = "Sand Striders";
		down1 = setup("/objects/sandstriders", gp.tileSize, gp.tileSize);
	}
}
