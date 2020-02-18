package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class FlyingMiddleTile extends Tile{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public FlyingMiddleTile(int id) {
		super(Assets.flyingMiddleTile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}

