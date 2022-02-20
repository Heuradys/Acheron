package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GPane;

public class OBJ_Crate extends Entity {
	public OBJ_Crate(GPane gp) {
		
		super(gp);
		name = "Crate";
		down1 = setup("/objects/crate", gp.tileSize, gp.tileSize);
	}
}
