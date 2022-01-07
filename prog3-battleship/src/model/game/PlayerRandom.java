//@author Alicia Baquero Tafalla
package model.game;

import java.util.List;
import java.util.Objects;

import model.Coordinate;
import model.RandomNumber;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerRandom.
 */
public class PlayerRandom implements IPlayer{
	
	/** The board. */
	private GameBoard board;
	
	/** The ship. */
	private GameShip ship;
	
	/** The num fighters. */
	private int numFighters;
	
	/**
	 * Instantiates a new player random.
	 *
	 * @param side the side
	 * @param numFighters the num fighters
	 */
	public PlayerRandom(Side side, int numFighters) {
		Objects.requireNonNull(side);
		this.numFighters=numFighters;
		ship=new GameShip("PlayerRandom "+ side+ " Ship", side);
		board=null;
	}
	
	/**
	 * Sets the board.
	 *
	 * @param g the new board
	 */
	public void setBoard(GameBoard g) {
		Objects.requireNonNull(g);
		board=g;
	}
	
	/**
	 * Gets the game ship.
	 *
	 * @return the game ship
	 */
	public GameShip getGameShip() {
		return ship;
	}
	
	/**
	 * Inits the fighters.
	 */
	public void initFighters() {
		StringBuilder builder = new StringBuilder();
		String[] IMPERIALtypes = {"TIEFighter", "TIEBomber", "TIEInterceptor"};
		String[] REBELtypes = {"XWing", "YWing", "AWing"};
		String[] types = (this.ship.getSide() == Side.IMPERIAL)? IMPERIALtypes:REBELtypes;
		
		
			for(int i = 0; i < types.length; i++) {
				int num = RandomNumber.newRandomNumber(numFighters);
				if(num != 0) {
					if(builder.length() > 0) {
						builder.append(":");
					}
					
					builder.append(num + "/" + types[i]);
				}
			}
		
		if(builder.length() > 0) {
			ship.addFighters(builder.toString());
		}
		
	}
	
	/**
	 * Checks if is fleet destroyed.
	 *
	 * @return true, if is fleet destroyed
	 */
	public boolean isFleetDestroyed() {
		return ship.isFleetDestroyed();
	}
	
	/**
	 * Show ship.
	 *
	 * @return the string
	 */
	public String showShip() {
		String ss= ship.toString() + "\n" + ship.showFleet();
		return ss;
	}
	
	/**
	 * Purge fleet.
	 */
	public void purgeFleet() {
		ship.purgeFleet();
	}

	/**
	 * Next play.
	 *
	 * @return true, if successful
	 */
	public boolean nextPlay() {
		boolean exit = true;
		int option = RandomNumber.newRandomNumber(100);
		List<Integer> ids;
		int pos, idGet, x, y;
		
		if(option == 99) {
			exit = false; 
		}
		
		else{
			if(option >= 85) { 
				ids = ship.getFightersId("");
			}
			else {
				if(option >= 25) { 
					ids = ship.getFightersId("ship");
				}
				else { 
					ids = ship.getFightersId("board");
				}
			}
			
			if(ids.isEmpty()) {
				System.out.println("ERROR: lista vacía");
			}
			
			else {
				try {
					pos = RandomNumber.newRandomNumber(ids.size());
					idGet = ids.get(pos);
					
					if(option >= 85) {
						ship.improveFighter(idGet, option, board);
					}
					
					else {
						if(option < 25) {
							ship.patrol(idGet, board);
						}
						
						else {
							x = RandomNumber.newRandomNumber(board.getSize());
							y = RandomNumber.newRandomNumber(board.getSize());
							ship.launch(idGet, new Coordinate(x, y), board);
						}
					}
				}
				
				catch(OutOfBoundsException | FighterNotInBoardException | FighterAlreadyInBoardException | WrongFighterIdException e) {
					throw new RuntimeException(e);
				}
			}

		}
		return exit;
	}
}
