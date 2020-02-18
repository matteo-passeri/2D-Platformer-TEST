package platformerTEST.gfx;

import java.awt.Font;
import java.awt.image.BufferedImage;

public class Assets {
	
	private static final int width = 64, height = 64;
	
	public static Font font28;
	
	public static BufferedImage[] player_still;
	public static BufferedImage chopedTree, tree1, tree2, rock, flyingMiddleTile, flyingBottomTile, emptyTile, topRightTile, middleTile, topMiddleTile, topLeftTile, player_down_stand, player_up_stand, player_left_stand, player_right_stand;
	public static BufferedImage wood;
	public static BufferedImage[] animAttackHit, player_down, player_up, player_left, player_right;
//	public static BufferedImage[] btn_start;
	public static BufferedImage inventoryScreen;
	
	public static void init() {
		font28 = FontLoader.loadFont("res/fonts/slkscr.ttf", 28);
		
		SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage("/texture/sheet.png"));
		
		inventoryScreen = ImageLoader.loadImage("/texture/inventoryScreen.png");
		
//		wood = sheet.crop(width*2, height *6, width, height);
		
		animAttackHit = new BufferedImage[1];
//		animAttackHit[0] = sheet.crop(width, height *6, width, height);
		
//		btn_start = new BufferedImage[2];
//		btn_start[0] = sheet.crop(width *8, height *4, width*2, height);
//		btn_start[1] = sheet.crop(width *8, height *5, width*2, height);
		
		// Player assets
		player_down = new BufferedImage[2];
		player_up = new BufferedImage[2];
		player_left = new BufferedImage[2];
		player_right = new BufferedImage[2];
		player_still = new BufferedImage[4];
		
		player_still[0] = sheet.crop(width, height *5, width, height);
		player_still[1] = sheet.crop(width, height *4, width, height);
		player_still[2] = sheet.crop(width*4, height *4, width, height);
		player_still[3] = sheet.crop(width*4, height *5, width, height);
		
		
		player_down[0] = sheet.crop(0, height *4, width, height);
		player_down[1] = sheet.crop(width *2, height *4, width, height);
		
		player_up[0] = sheet.crop(0, height *5, width, height);
		player_up[1] = sheet.crop(width *2, height *5, width, height);
		
		player_left[0] = sheet.crop(width *3, height *4, width, height);
		player_left[1] = sheet.crop(width *5, height *4, width, height);
		
		player_right[0] = sheet.crop(width *3, height *5, width, height);
		player_right[1] = sheet.crop(width *5, height *5, width, height);
		
		
		// Tiles assets
		emptyTile = ImageLoader.loadImage("/texture/tiles/0.png");
		topLeftTile = ImageLoader.loadImage("/texture/tiles/1.png");
		topMiddleTile = ImageLoader.loadImage("/texture/tiles/2.png");
		topRightTile = ImageLoader.loadImage("/texture/tiles/3.png");
		middleTile = ImageLoader.loadImage("/texture/tiles/5.png");
		flyingBottomTile = ImageLoader.loadImage("/texture/tiles/9.png");
		flyingMiddleTile = ImageLoader.loadImage("/texture/tiles/14.png");
		
		
		// Items assets
		rock = ImageLoader.loadImage("/texture/object/Stone.png");
		chopedTree = ImageLoader.loadImage("/texture/object/Tree_1.png");
		tree1 = ImageLoader.loadImage("/texture/object/Tree_2.png");
		tree2 = ImageLoader.loadImage("/texture/object/Tree_3.png");

	}

}
