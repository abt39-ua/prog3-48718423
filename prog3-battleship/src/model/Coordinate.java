//@author Alicia Baquero Tafalla
package model;

import java.util.Objects; 
/*
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
*/

/**
 * The Class Coordinate.
 */
public class Coordinate {
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	
	/**
	 * Instantiates a new coordinate.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Coordinate(int x, int y) {
		this.x=x;
		this.y=y;
	}

	/**
	 * Instantiates a new coordinate.
	 *
	 * @param c the c
	 */
	public Coordinate(Coordinate c) {
		this.x=c.x;
		this.y=c.y;
	}
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * Adds the.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the coordinate
	 */
	public Coordinate add(int x, int y) {
		Coordinate nueva=new Coordinate(this.x + x, this.y +y);
		return nueva;
	}

	/**
	 * Adds the.
	 *
	 * @param c the c
	 * @return the coordinate
	 */
	public Coordinate add(Coordinate c) {
		Coordinate nueva=new Coordinate(this.x + c.x, this.y + c.y);
		return nueva;
	}
	 
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	public int hashCode() {
		return Objects.hash(x,y);
	}	

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	public boolean equals(Object obj) {
		if(this==obj)
			return true;
		if(obj==null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		return x==other.x && y==other.y;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "[" + x + "," + y + "]";
	}
	
}
