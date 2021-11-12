package model.exceptions;

import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterNotInBoardException.
 */
public class FighterNotInBoardException extends Exception{
	
	/** The f. */
	private Fighter f;
	
	/**
	 * Instantiates a new fighter not in board exception.
	 *
	 * @param f the f
	 */
	public FighterNotInBoardException(Fighter f) {
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
