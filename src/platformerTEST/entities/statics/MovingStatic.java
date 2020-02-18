package platformerTEST.entities.statics;

import java.awt.Graphics;

import platformerTEST.Handler;
import platformerTEST.gfx.Assets;
import platformerTEST.items.Item;

public class MovingStatic extends StaticEntity {
	private int leftBound, rightBound;
	private int move = 1;
//	private int id;

	public MovingStatic(Handler handler, float x, float y, int width, int height, int rightBound, int leftBound) {
		super(handler, x, y, width, height);
		this.rightBound = rightBound;
		this.leftBound = leftBound;
	}

	@Override
	public boolean isSolid() {
		return true;
	}

	@Override
	public void tick() {
//		System.out.println("ITS TICKING");
		if(x + width - handler.getGameCamera().getxOffSet() >= rightBound - handler.getGameCamera().getxOffSet() && move!= -1) {
			move *= -1;
		}
		if(x - handler.getGameCamera().getxOffSet() >= leftBound - handler.getGameCamera().getxOffSet() && move!= 1) {
			move *= -1;
		}
		x += move;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.topMiddleTile, (int) (x - handler.getGameCamera().getxOffSet()),
				(int) (y - handler.getGameCamera().getyOffSet()), width, height, null);

	}

	@Override
	public void die() {
		handler.getWorld().getItemManager().addItem(Item.rockItem.createNew((int) x, (int) y));
	}

}