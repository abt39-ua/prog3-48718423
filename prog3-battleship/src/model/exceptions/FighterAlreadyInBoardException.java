package model.exceptions;

import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class FighterAlreadyInBoardException.
 */
public class FighterAlreadyInBoardException extends Exception{
	
	/** The f. */
	private Fighter f;
	
	/**
	 * Instantiates a new fighter already in board exception.
	 *
	 * @param f the f
	 */
	public FighterAlreadyInBoardException(Fighter f) {
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
