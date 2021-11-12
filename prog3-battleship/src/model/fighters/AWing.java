package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class AWing.
 */
public class AWing extends Fighter{
	
	/**
	 * Instantiates a new a wing.
	 *
	 * @param mother the mother
	 */
	public AWing(Ship mother) {
		super(mother);
		addVelocity(40);
		addAttack(5);
		addShield(-50);
	}
	
	/**
	 * Instantiates a new a wing.
	 *
	 * @param f the f
	 */
	private AWing(AWing f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new AWing(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'A';
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
			oldDamage *= 2;
		}
		return oldDamage;
	}
}
