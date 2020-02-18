package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class EmptyTile extends Tile{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public EmptyTile(int id) {
		super(Assets.emptyTile, id);
	}

}
