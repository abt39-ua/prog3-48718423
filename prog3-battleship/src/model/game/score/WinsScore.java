package model.game.score;

import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class WinsScore.
 */
public class WinsScore extends Score<Integer>{
	
	/**
	 * Instantiates a new wins score.
	 *
	 * @param side the side
	 */
	public WinsScore(Side side) {
		super(side);
	}
	
	/**
	 * Score.
	 *
	 * @param sc the sc
	 */
	public void score(Integer sc) {
		if(sc !=null && sc==1)
			score++;
	}
}
