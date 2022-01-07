//@author Alicia Baquero Tafalla
package model.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class InvalidSizeException.
 */
@SuppressWarnings("serial")
public class InvalidSizeException extends Exception {

    /** The size. */
    private int size;
    
    /**
     * Instantiates a new invalid size exception.
     *
     * @param size the size
     */
    public InvalidSizeException(int size) {
        this.size = size;
    }
    
    /**
     * Gets the message.
     *
     * @return the message
     */
    public String getMessage() {
        return "ERROR: Informative message with " + size;
    }
}