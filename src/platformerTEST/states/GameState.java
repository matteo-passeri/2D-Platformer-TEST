package platformerTEST.states;

import java.awt.Graphics;

import platformerTEST.Handler;
import platformerTEST.worlds.World;

public class GameState extends State{
	
	private World world;
	
	
	
	public GameState(Handler handler) {
		super(handler);
		world = new World(handler, "res/worlds/world1.txt");
		handler.setWorld(world);
		
	}

	@Override
	public void tick() {
		world.tick();
		
//		// moving map like space game
//		handler.getGameCamera().move(1, 0);
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	
}
