//@author Alicia Baquero Tafalla
package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class TIEBomber.
 */
public class TIEBomber extends Fighter{
	
	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param mother the mother
	 */
	public TIEBomber(Ship mother) {
		super(mother);
		addVelocity(-30);
		addAttack(-50);
		addShield(35);
	}
	
	/**
	 * Instantiates a new TIE bomber.
	 *
	 * @param f the f
	 */
	private TIEBomber(TIEBomber f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new TIEBomber(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'b';
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
		if(enemy instanceof YWing || enemy instanceof XWing) {
			oldDamage /= 2;
		}
		else {
			if(enemy instanceof AWing) {
				oldDamage /= 3;
			}
		}
		return oldDamage;
	}
}
