package model.exceptions;

import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterIsDestroyedException.
 */
public class FighterIsDestroyedException extends Exception{
	
	/** The f. */
	private Fighter f;
	
	/**
	 * Instantiates a new fighter is destroyed exception.
	 *
	 * @param f the f
	 */
	public FighterIsDestroyedException(Fighter f) {
		this.f = f;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: ass not found " + f;
	}
}
