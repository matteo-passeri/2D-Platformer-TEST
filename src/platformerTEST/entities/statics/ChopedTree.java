package platformerTEST.entities.statics;

import java.awt.Graphics;

import platformerTEST.Handler;
import platformerTEST.gfx.Assets;
import platformerTEST.items.Item;
import platformerTEST.tile.Tile;

public class ChopedTree extends StaticEntity {
	public ChopedTree(Handler handler, float x, float y) {
		super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT *2);
		
		bounds.x = 10;
		bounds.y = (int) (height / 1.5f);
		bounds.width = width - 20;
		bounds.height = (int) (height - height / 1.5f);
	}

	@Override
	public void tick() {
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.chopedTree, (int) (x - handler.getGameCamera().getxOffSet()), (int) (y - handler.getGameCamera().getyOffSet()), width, height, null);
		
	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.woodItem.createNew((int) x, (int) y));
	}

}
