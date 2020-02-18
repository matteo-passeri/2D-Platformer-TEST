package platformerTEST.tile;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class Tile {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	// STATIC TILES
	
	public static Tile[] tiles = new Tile[256];
	public static Tile emptyTile = new EmptyTile(0);
	public static Tile topLeftTile = new TopLeftTile(1);
	public static Tile topMiddleTile = new TopMiddleTile(2);
	public static Tile topRightTile = new TopRightTile(3);
	public static Tile middleTile = new MiddleTile(5);
	public static Tile flyingBottomTile = new FlyingBottomTile(9);
	public static Tile flyingMiddleTile = new FlyingMiddleTile(14);

		
	// CLASS
	
	public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
	
	protected BufferedImage texture;
	protected final int id;
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g, int x, int y) {
		g.drawImage(texture,  x,  y, TILEWIDTH, TILEHEIGHT, null);
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public int getId() {
		return id;
	}

}
