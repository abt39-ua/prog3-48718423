package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class YWing.
 */
public class YWing extends Fighter{
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param mother the mother
	 */
	public YWing(Ship mother) {
		super(mother);
		addVelocity(-20);
		addAttack(-10);
		addShield(30);
	}
	
	/**
	 * Instantiates a new y wing.
	 *
	 * @param f the f
	 */
	private YWing(YWing f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new YWing(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'Y';
	}
	
	/**
	 * Gets the damage.
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	public int getDamage(int n, Fighter enemy) {
		int oldDamage = super.getDamage(n, enemy);
		if(enemy instanceof TIEBomber) {
			oldDamage /= 2;
		}
		else {
			if(enemy instanceof TIEFighter || 
					enemy instanceof TIEBomber) {
				oldDamage /= 3;
			}
		}
		return oldDamage;
	}
}
