//@author Alicia Baquero Tafalla
package model.game.score;

import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class Score.
 *
 * @param <T> the generic type
 */
public abstract class Score<T> implements Comparable<Score<T>> {
	
	/** The score. */
	protected int score;
	
	/** The side. */
	private Side side;
	
	/**
	 * Instantiates a new score.
	 *
	 * @param side the side
	 */
	public Score(Side side) {
		this.side=side;
		score=0;
	}
	
	/**
	 * Compare to.
	 *
	 * @param sc the sc
	 * @return the int
	 */
	public int compareTo(Score<T> sc) {
		int n=0;
		
		if(score > sc.score) {
			n=-1;
		}
		
		else {
			if(score < sc.score) {
				n=1;
			}
			
			else {
				n=side.compareTo(sc.side);
			}
		}
		return n;
	}
	
	/**
	 * Gets the score.
	 *
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Player " + side + ": " +score;
	}
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	public abstract void score(T sc);
}
