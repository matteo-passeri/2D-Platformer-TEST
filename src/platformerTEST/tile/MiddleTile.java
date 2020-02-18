package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class MiddleTile extends Tile{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public MiddleTile(int id) {
		super(Assets.middleTile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
