package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class TopLeftTile extends Tile {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public TopLeftTile(int id) {
		super(Assets.topLeftTile, id);
	}

	@Override
	public boolean isSolid() {
		return true;
	}
}
