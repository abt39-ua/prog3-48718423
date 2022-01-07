//@author Alicia Baquero Tafalla
package model.game.score;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.NoSuchElementException;

// TODO: Auto-generated Javadoc
/**
 * The Class Ranking.
 *
 * @param <ScoreType> the generic type
 */
public class Ranking<ScoreType extends Score<?>>{
	
	/** The score set. */
	private SortedSet<ScoreType> scoreSet;
	
	/**
	 * Instantiates a new ranking.
	 */
	public Ranking() {
		scoreSet= new TreeSet<>();
	}
	
	/**
	 * Adds the score.
	 *
	 * @param st the st
	 */
	public void addScore(ScoreType st) {
		scoreSet.add(st);
	}
	
	/**
	 * Gets the sorted ranking.
	 *
	 * @return the sorted ranking
	 */
	public SortedSet<ScoreType> getSortedRanking(){
		return scoreSet;
	}
	
	/**
	 * Gets the winner.
	 *
	 * @return the winner
	 */
	public ScoreType getWinner() {
		ScoreType f=null;
		try {
			f=scoreSet.first();
		}catch(NoSuchElementException e) {
			throw new RuntimeException("Error: " + e);
		}
		
		return f;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String s="";
		
		for(ScoreType st: scoreSet) {
			s+= " | " + st.toString();
		}
		
		s+=" | ";
		return s;
	}
}
