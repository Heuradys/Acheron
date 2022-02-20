package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GPane;
import main.Toolbox;

public class TileManager {
	GPane gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GPane gp) {
		this.gp = gp;
		tile = new Tile[10]; //how many different tiles do we have
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/world03.txt");
	}
	
	public void getTileImage() {		
		// INDEX, FILE_NAME WITHOUT PNG, COLLISION
		setup(0, "earth", false);
		setup(1, "wall", true);
		setup(2, "water", true);
		setup(3, "grass", false);
		setup(4, "sand", false);
		setup(5, "sand", false);
		setup(6, "floor_01", false);
		setup(7, "wall_01", true);
	}
	
	public void setup(int index, String imagePath, boolean collision) {
		Toolbox tBox = new Toolbox();
		
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + imagePath + ".png"));
			tile[index].image = tBox.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
			tile[index].collison = collision;
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String map) {
		try {
			InputStream is = getClass().getResourceAsStream(map);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" "); //gets rid of the spaces in our map file.
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
					
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;
		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			
			//only draw what tiles we can see using math
			if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
			   worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
			   worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
			   worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}
			
			worldCol++;
			
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}
	}
}
