package model.exceptions;

import model.Coordinate;

// TODO: Auto-generated Javadoc
/**
 * The Class OutOfBoundsException.
 */
public class OutOfBoundsException extends Exception{
	
	/** The c. */
	private Coordinate c;
	
	/**
	 * Instantiates a new out of bounds exception.
	 *
	 * @param c the c
	 */
	public OutOfBoundsException(Coordinate c) {
		this.c = c;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return "ERROR: sponge bob square pants " + c;
	}
}
