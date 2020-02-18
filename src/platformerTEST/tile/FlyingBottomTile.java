package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class FlyingBottomTile extends Tile{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public FlyingBottomTile(int id) {
		super(Assets.flyingBottomTile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}


