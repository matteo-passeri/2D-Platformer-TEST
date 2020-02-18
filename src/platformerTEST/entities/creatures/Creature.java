package platformerTEST.entities.creatures;

import platformerTEST.Handler;
import platformerTEST.entities.Entity;
import platformerTEST.tile.Tile;

public abstract class Creature extends Entity {

	public static final float DEFAUL_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64, DEFAULT_CREATURE_HEIGHT = 64;

	protected boolean falling = false;
	protected boolean justLanded, jumping = false, crouch;

	protected float speed;
	protected double xMove;
	protected double yMove;

	// jump
	private double jumpSpeed = 4;
	private double currentJumpSpeed = jumpSpeed;

	// fall
	private double maxFallSpeed = 4;
	private double currentFallSpeed = 0.1;

	public Creature(Handler handler, float x, float y, int width, int height) {
		super(handler, x, y, width, height);

		speed = DEFAUL_SPEED;
		xMove = 0;
		yMove = 0;
	}

	public void move() {
		if (!checkEntityCollisions(xMove, 0f))
			moveX();
		if (!checkEntityCollisions(0f, yMove))
			moveY();
	}

	public void moveX() {
		if (xMove > 0) {
			// Moving right
			int tx = (int) (x + xMove + bounds.x + bounds.width) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = (tx * Tile.TILEWIDTH) - bounds.x - bounds.width - 1;
			}
		} else if (xMove < 0) {
			// moving left
			int tx = (int) (x + xMove + bounds.x) / Tile.TILEWIDTH;

			if (!collisionWithTile(tx, (int) (y + bounds.y) / Tile.TILEHEIGHT)
					&& !collisionWithTile(tx, (int) (y + bounds.y + bounds.height) / Tile.TILEHEIGHT)) {
				x += xMove;
			} else {
				x = tx * Tile.TILEWIDTH + Tile.TILEWIDTH - bounds.x;
			}
		}
	}

	public void moveY() {

		if (yMove < 0) {
			// moving up
			int ty = (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT;

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				y += yMove;
			} else {
				jumping = false;
				y = ty * Tile.TILEHEIGHT + Tile.TILEHEIGHT - bounds.y;
			}

		} else if (yMove >= 0) {
			// moving down or standing
			int ty = (int) ((y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT);

			if (!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, ty)
					&& !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, ty)) {
				// start gravity
				if (!jumping && !falling)
					falling = true;

				y += yMove;

//				System.out.println("Im moving down, falling is: " + falling);
//				System.out.println("and...yMove is: " + yMove);
//				System.out.println("and...y is: " + y);
			} else {
				y = (float) (ty * Tile.TILEHEIGHT - bounds.y - bounds.height - 0.9);
				falling = false;

//				System.out.println("I've just landed!!! - falling is: " + falling);
			}
		}
	}

	protected void gravityEngine() {
//		System.out.println("every tick, falling is: " + falling);

		// GRAVITY ENGINE
		// if NOT jumping - always falling
		if (!jumping && !falling) {
			falling = true;
		}

		// if falling
		if (falling) {
//			System.out.println(
//					"Im in falling, that is true...yMove is: " + yMove + " - currentFallSpeed is: " + currentFallSpeed);
			yMove += currentFallSpeed;

			if (currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += .1;
			}
			moveY();
		}

		// if NOT falling
		if (!falling) {
			currentFallSpeed = .1;
		}
	}

	protected void jumping() {
		// jumping
		if (jumping && !falling) {
			falling = false;
			yMove -= currentJumpSpeed;
			currentJumpSpeed -= .1;

//			System.out.println("Inside JUMPING IF, jumping is: " + jumping);
//			System.out.println("And currentJumpSpeed is: " + currentJumpSpeed);

			if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			moveY();
		}
//		System.out.println("Jump is: " + jumping);
	}

	protected boolean collisionWithTile(int x, int y) {
		return handler.getWorld().getTile(x, y).isSolid();
	}

	// Getters - Setters

	public double getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public double getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
