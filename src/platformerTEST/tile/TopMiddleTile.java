package platformerTEST.tile;

import platformerTEST.gfx.Assets;

public class TopMiddleTile extends Tile{
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public TopMiddleTile(int id) {
		super(Assets.topMiddleTile, id);
	}
	
	@Override
	public boolean isSolid() {
		return true;
	}

}
