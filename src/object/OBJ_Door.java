package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GPane;

public class OBJ_Door extends Entity {
	public OBJ_Door(GPane gp) {
		
		super(gp);
		name = "Door";
		down1 = setup("/objects/door", gp.tileSize, gp.tileSize);
		collision = true;
		
	}
	
}
