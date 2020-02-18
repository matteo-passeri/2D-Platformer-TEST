package platformerTEST.entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import platformerTEST.Handler;

public abstract class Entity {
	
	public static final int DEFAULT_HEALTH = 3;
	protected Handler handler;
	protected float x, y;
	protected int width, height;
	protected int health;
	protected boolean active = true;
	
	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	// collision bounds
	protected Rectangle bounds;
	
	public Entity(Handler handler, float x, float y, int width, int height) {
		this.handler = handler;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		health = DEFAULT_HEALTH;
		
		// by default bounds are the same size of the image
		bounds = new Rectangle(0, 0, width, height);
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public abstract void die();
	
	public void hurt(int amt) {
		
		health -= amt;
		if (health <= 0) {
			active = false;
			die();
			}
	}
	
	//
	public boolean checkEntityCollisions(double xMove, double yMove) {
		for(Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if(e.equals(this))
				continue;
			if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xMove, yMove))) {
//				// collision with Entities DISABLE
//				return true;
//				if (e.isSolid())
					return true;
//				else
//					continue;
			}
		}
		return false;
	}
	
	public boolean isSolid() {
		return false;
	}
	
	public Rectangle getCollisionBounds(double xMove, double yMove) {
		return new Rectangle((int) (x + bounds.x + xMove), (int) (y + bounds.y + yMove), bounds.width, bounds.height);
	}
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
