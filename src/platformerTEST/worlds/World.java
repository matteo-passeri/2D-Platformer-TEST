package platformerTEST.worlds;

import java.awt.Graphics;

import platformerTEST.Handler;
import platformerTEST.entities.EntityManager;
import platformerTEST.entities.creatures.Player;
import platformerTEST.entities.statics.Rock;
import platformerTEST.items.ItemManager;
import platformerTEST.tile.Tile;
import platformerTEST.utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height;
	private int spawnX, spawnY;
	private int[][] theTiles;
	// Entities
	private EntityManager entityManager;
	// Items
	private ItemManager itemManager; 
	
	
	public World(Handler handler, String path) {
		this.handler = handler;
		entityManager = new EntityManager(handler, new Player(handler, 100, 100));
		itemManager = new ItemManager(handler);
		
		
		// temporally entities code
		entityManager.addEntity(new Rock(handler, 0, 770));
		// ...temporally entities code

		
		loadWorld(path);
		
		entityManager.getPlayer().setX(spawnX);
		entityManager.getPlayer().setY(spawnY);
	}
	
	public void tick() {
		itemManager.tick();
		entityManager.tick();
	}
	
	public void render(Graphics g) {
		// draw just the tiles in the camera
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffSet() / Tile.TILEWIDTH);
		int xEnd = (int) Math.min(width,  (handler.getGameCamera().getxOffSet() + handler.getWidth()) / Tile.TILEWIDTH + 1);
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffSet() / Tile.TILEHEIGHT);
		int yEnd = (int) Math.min(height,  (handler.getGameCamera().getyOffSet() + handler.getHeight()) / Tile.TILEHEIGHT + 1);
		
		for(int y = yStart; y < yEnd; y++) {
			for(int x = xStart; x < xEnd; x++) {
				getTile(x,y).render(g, (int) (x * Tile.TILEWIDTH - handler.getGameCamera().getxOffSet()), 
						(int)(y * Tile.TILEHEIGHT - handler.getGameCamera().getyOffSet()));
			}
		}
		
		// Items
		itemManager.render(g);
		
		// Entites
		entityManager.render(g);
	}
	
	public Tile getTile(int x, int y) {
		// if player go outside map, game fake is on gras Tile to not crash
		if(x < 0 || y < 0 || x >= width || y >= height)
			return Tile.topMiddleTile;
		
		//
		Tile t = Tile.tiles[theTiles[x][y]];
		if(t == null)
			return Tile.emptyTile;
		return t;
	}
	
	private void loadWorld(String path) {
		String file = Utils.loadFileAsString(path);
		
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		
		theTiles = new int[width][height];
		for(int y = 0; y < height; y++) {
			for(int x = 0; x < width; x++) {
				theTiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
			}
		}
	}
	
	// G & S
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

}
