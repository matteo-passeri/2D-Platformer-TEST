package platformerTEST.entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import platformerTEST.Handler;
import platformerTEST.entities.Entity;
import platformerTEST.gfx.Animation;
import platformerTEST.gfx.Assets;
import platformerTEST.inventory.Inventory;

public class Player extends Creature {

//	@SuppressWarnings("unused")
//	private boolean crouch;

//	// jump
//	private double jumpSpeed = 4;
//	private double currentJumpSpeed = jumpSpeed;
//
//	// fall
//	private double maxFallSpeed = 4;
//	private double currentFallSpeed = 0.1;

	private boolean hit = false;

	// attack anim
	Rectangle ar = new Rectangle();

	// Animations
	private Animation animAttackHit, animDown, animUp, animLeft, animRight;
	// AttackTimer
	private long lastAttackTimer, attackCooldown = 800, attackTimer = attackCooldown;
	// Inventory
	private Inventory inventory;

	private int direction = 1;

	public Player(Handler handler, float x, float y) {
		super(handler, x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);

		// player collision box
		bounds.x = 17;
		bounds.y = 12;
		bounds.width = 28;
		bounds.height = 46;

		// Animations
		animDown = new Animation(250, Assets.player_down);
		animUp = new Animation(250, Assets.player_up);
		animLeft = new Animation(250, Assets.player_left);
		animRight = new Animation(250, Assets.player_right);
		// anim attack
		animAttackHit = new Animation(250, Assets.animAttackHit);

		inventory = new Inventory(handler);

	}

	

	

	@Override
	public void tick() {
		// gravity
		gravityEngine();

//		// jumping
//		if (jumping = true) 
		jumping();

		crouch = false;

		// Animations
		animDown.tick();
		animUp.tick();
		animLeft.tick();
		animRight.tick();

		// Movement
		getInput();
		move();

		// camera
		handler.getGameCamera().centerOnEntity(this);

		// Attack
		checkAttacks();
		// attack anim
		animAttackHit.tick();

		// Inventory
		inventory.tick();
                
                fallingOutside();

	}
        
        private void fallingOutside() {         
            if(y > handler.getHeight() + 600) {
                    die();
            }
        }

	private void checkAttacks() {
		attackTimer += System.currentTimeMillis() - lastAttackTimer;
		lastAttackTimer = System.currentTimeMillis();

		if (attackTimer < attackCooldown)
			return;

		if (inventory.isActive())
			return;

		Rectangle cb = getCollisionBounds(0, 0);

		int arSize = 24;
		ar.width = arSize;
		ar.height = arSize;

		// create attackCollisionBox depending on which button u pushed
		if (handler.getKeyManager().aUp) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y - arSize;
		} else if (handler.getKeyManager().aDown) {
			ar.x = cb.x + cb.width / 2 - arSize / 2;
			ar.y = cb.y + cb.height;
		} else if (handler.getKeyManager().aLeft) {
			ar.x = cb.x - arSize;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else if (handler.getKeyManager().aRight) {
			ar.x = cb.x + cb.width;
			ar.y = cb.y + cb.height / 2 - arSize / 2;
		} else {
			return;
		}

		attackTimer = 0;

		// check attackBox with entitieBoxes
		for (Entity e : handler.getWorld().getEntityManager().getEntities()) {
			if (e.equals(this))
				continue;
			if (e.getCollisionBounds(0, 0).intersects(ar)) {
				hit = handler.getKeyManager().attacking;
				e.hurt(1);
				return;
			}
		}
	}

	@Override
	public void die() {
		System.out.println("You lose");
              
                System.exit(0);
	}

	private void getInput() {
		xMove = 0;
		yMove = 0;

		if (inventory.isActive())
			return;

		// jumping
		if ((handler.getKeyManager().jump || handler.getKeyManager().up) && !jumping && !falling) {
			jumping = true;
//			System.out.println("Jump is pressed is: " + jumping);
		}
		if (jumping)
			jumping();

		// player moving

//		if (handler.getKeyManager().down)
//			crouch = true;
//		if(handler.getKeyManager().keyReleased(keyEvent.VK_DOWN)
//			crouch = false;
		if (handler.getKeyManager().left)
			xMove = -speed;
		if (handler.getKeyManager().right)
			xMove = speed;
	}

	@Override
	public void render(Graphics g) {
		// draw player on map...according to camera
		g.drawImage(getCurrentAnimationFrame(), (int) (x - handler.getGameCamera().getxOffSet()),
				(int) (y - handler.getGameCamera().getyOffSet()), width, height, null);

		// Anim Attack
		if (hit == true) {
			g.drawImage(animAttackHit.getCurrentFrame(), (int) (ar.x - handler.getGameCamera().getxOffSet()),
					(int) (ar.y - handler.getGameCamera().getyOffSet()), null);
			hit = handler.getKeyManager().attacking;

//			  // checking attack box (wrongly (up))
//			  g.setColor(Color.RED);
//			  g.fillRect((int) (ar.x), 
//						(int) (ar.y), 
//						ar.width, ar.height);
//			  // ...
		}
		// checking collision BOX...
		g.setColor(Color.RED);
		g.drawRect((int) (x + bounds.x - handler.getGameCamera().getxOffSet()),
				(int) (y + bounds.y - handler.getGameCamera().getyOffSet()), bounds.width, bounds.height);
		// ...
	}

	public void postRender(Graphics g) {
		inventory.render(g);
	}

	private BufferedImage getCurrentAnimationFrame() {
		if (xMove != 0 || yMove != 0) {
			if (xMove < 0) {
				// Going Left
				direction = 2;
				return animLeft.getCurrentFrame();
			} else if (xMove > 0) {
				// Going Right
				direction = 3;
				return animRight.getCurrentFrame();
			} else if (yMove < 0) {
				// Going Up
				// TO DO - Change with jump animation
				direction = 0;
				return animUp.getCurrentFrame();
			} else {
				// Going Down
				// TO DO - Change with falling animation
				direction = 1;
				return animDown.getCurrentFrame();
			}
		} else
			return Assets.player_still[direction];
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

}
