package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import entity.Entity;
import main.GPane;

public class OBJ_Health extends Entity {

	public OBJ_Health(GPane gp) {
		
		super(gp);
		name = "Health";
		image = setup("/objects/health_full", gp.tileSize, gp.tileSize);
		image2 = setup("/objects/health_half", gp.tileSize, gp.tileSize);
		image3 = setup("/objects/health_blank", gp.tileSize, gp.tileSize);
	}
}
