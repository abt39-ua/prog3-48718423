//@author Alicia Baquero Tafalla
package model.game;

import java.util.List;
import java.util.Objects;
import java.util.ArrayList;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;
import model.game.score.DestroyedFightersScore;
import model.game.score.WinsScore;

// TODO: Auto-generated Javadoc
/**
 * The Class GameShip.
 */
public class GameShip extends Ship{
	private WinsScore winsScore;
	private DestroyedFightersScore destroyedFightersScore;
	
	/**
	 * Instantiates a new game ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	public GameShip(String name, Side side) {
		super(name, side);
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
		winsScore = new WinsScore(side);
		destroyedFightersScore = new DestroyedFightersScore(side);
	}
	
	public WinsScore getWinsScore() {
		return winsScore;
	}
	
	public DestroyedFightersScore getDestroyedFightersScore(){
		return destroyedFightersScore;
	}

	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	public boolean isFleetDestroyed() {
		boolean destroyed = true;
		
		for(Fighter f: fleet) {
			if(!f.isDestroyed()) {
				destroyed=false;
			}
		}
		
		return destroyed;
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param id the id
	 * @return the fighter
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	private Fighter getFighter(int id)  throws WrongFighterIdException {
		Fighter f = null;
		
		for(Fighter t:fleet) {
			if(!t.isDestroyed() && t.getId()==id) {
				f=t;
			}
		}
		
		if(f==null) {
			throw new WrongFighterIdException(id);
		}
		
		return f;
	}
	
	/**
	 * Gets the fighters id.
	 *
	 * @param where the where
	 * @return the fighters id
	 */
	public List<Integer> getFightersId(String where){
		List<Integer> lista = new ArrayList<>();
		for(Fighter f : fleet) {
			if(!f.isDestroyed()) {
				switch(where){
					case "board":
						if(f.getPosition()!=null) {
							lista.add(f.getId());
						}
						break;
					case "ship":
						if(f.getPosition()==null) {
							lista.add(f.getId());
						}
						break;
					default:
						lista.add(f.getId());
						break;
				}
			}
		}
		return lista;
	}
	
	/**
	 * Launch.
	 *
	 * @param id the id
	 * @param c the c
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public void launch(int id, Coordinate c, Board b) throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException{
		Objects.requireNonNull(b);
		Objects.requireNonNull(c);
		Fighter f=getFighter(id);
		b.launch(c, f);
	}
	
	/**
	 * Patrol.
	 *
	 * @param id the id
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public void patrol(int id, Board b) throws WrongFighterIdException, FighterNotInBoardException{
		Objects.requireNonNull(b);
		Objects.requireNonNull(id);
		Fighter f=getFighter(id);
		b.patrol(f);
	}
	
	/**
	 * Improve fighter.
	 *
	 * @param id the id
	 * @param qty the qty
	 * @param b the b
	 * @throws WrongFighterIdException the wrong fighter id exception
	 */
	public void improveFighter(int id, int qty, Board b) throws WrongFighterIdException{
		Objects.requireNonNull(b);
		Fighter f= getFighter(id);
		
		try {
			b.removeFighter(f);
		}catch(FighterNotInBoardException e) {}
		
		f.addAttack(qty/2);
		f.addShield(qty-qty/2);
	}
}
