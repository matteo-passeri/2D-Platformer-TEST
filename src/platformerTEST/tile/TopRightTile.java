package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class TopRightTile extends Tile {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public TopRightTile(int id) {
		super(Assets.topRightTile, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
