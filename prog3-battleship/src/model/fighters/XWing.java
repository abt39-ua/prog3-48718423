package model.fighters;

import model.Fighter;
import model.Ship;

// TODO: Auto-generated Javadoc
/**
 * The Class XWing.
 */
public class XWing extends Fighter{
	
	/**
	 * Instantiates a new x wing.
	 *
	 * @param mother the mother
	 */
	public XWing(Ship mother) {
		super(mother);
		addVelocity(10);
		addAttack(20);
	}
	
	/**
	 * Instantiates a new x wing.
	 *
	 * @param f the f
	 */
	private XWing(XWing f) {
		super(f);
	}
	
	/**
	 * Copy.
	 *
	 * @return the fighter
	 */
	public Fighter copy() {
		return new XWing(this);
	}
	
	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return 'X';
	}
}
