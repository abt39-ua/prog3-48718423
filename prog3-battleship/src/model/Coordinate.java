//@author Alicia Baquero Tafalla
package model;

import java.util.Objects; 
// TODO: Auto-generated Javadoc
import java.util.TreeSet;
import java.util.Set;


// TODO: Auto-generated Javadoc
/**
 * The Class Coordinate.
 */
public class Coordinate implements Comparable<Coordinate> {
	public int compareTo(Coordinate c) {
		int dx=x-c.x;
		int dy=y-c.y;
		
		if(dx!=0) {
			return dx;
		}
		else {
			return dy;
		}
	}
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
	
	/**
	 * Compare to.
	 *
	 * @param c the c
	 * @return the int
	 */
	/*
	public int compareTo(Coordinate c) {
		int d;
		
		if(x<c.x)
			d=-1;
		else {
			if(x>c.x)
				d=1;
			else {
				if(y<c.y)
					d=-1;
				else {
					if(y>c.y)
						d=1;
					else
						d=0;
				}
			}
		}
		return d;
	}
	*/
	/**
	 * Gets the neighborhood.
	 *
	 * @return the neighborhood
	 */
	
	public Set<Coordinate> getNeighborhood(){
		Set<Coordinate> vecinas= null;
		vecinas = new TreeSet<>();
		
		for(int a=x-1; a<=x+1; a++) {
			for(int b=y-1; b<=y+1; b++) {
				if(a==x && b==y) {}
				else {
					vecinas.add(new Coordinate(a,b));
				}
			}
		}
		return vecinas;
	}
}
