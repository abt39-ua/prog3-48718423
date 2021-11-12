package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class TIEFighter.
 */
public class TIEFighter extends Fighter{
	
	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param mother the mother
	 */
	public TIEFighter(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(5);
		addShield(-10);
	}
	
	/**
	 * Instantiates a new TIE fighter.
	 *
	 * @param f the f
	 */
	private TIEFighter(Fighter f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new TIEFighter(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'f';
	}
}
