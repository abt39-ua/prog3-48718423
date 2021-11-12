package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class TIEInterceptor.
 */
public class TIEInterceptor extends Fighter{
	
	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param mother the mother
	 */
	public TIEInterceptor(Ship mother) {
		super(mother);
		addVelocity(45);
		addAttack(5);
		addShield(-20);
	}
	
	/**
	 * Instantiates a new TIE interceptor.
	 *
	 * @param f the f
	 */
	private TIEInterceptor(TIEInterceptor f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new TIEInterceptor(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'i';
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
		if(enemy instanceof YWing) {
			oldDamage *= 2;
		}
		else {
			if(enemy instanceof AWing) {
				oldDamage /= 2;
			}
		}
		return oldDamage;
	}
}
