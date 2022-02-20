package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GPane;

public class OBJ_Keycard extends Entity {
	public OBJ_Keycard(GPane gp) {
		
		super(gp);
		name = "Keycard";
		down1 = setup("/objects/keycard.png", gp.tileSize, gp.tileSize);
		solidBoundary.x = 4;
		solidBoundary.y = 4;
		solidBoundary.width = 24;
		solidBoundary.height = 24;
	}
}
