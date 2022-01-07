//@author Alicia Baquero Tafalla
package model.game.score;

import model.Side;
import model.Fighter;

// TODO: Auto-generated Javadoc
/**
 * The Class DestroyedFightersScore.
 */
public class DestroyedFightersScore extends Score<Fighter>{
	
	/**
	 * Instantiates a new destroyed fighters score.
	 *
	 * @param side the side
	 */
	public DestroyedFightersScore(Side side) {
		super(side);
	}
	
	/**
	 * Score.
	 *
	 * @param fg the fg
	 */
	public void score(Fighter fg) {
		if(fg!=null)
			score+=fg.getValue();
	}
}
