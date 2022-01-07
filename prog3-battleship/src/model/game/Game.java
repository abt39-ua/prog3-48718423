//@author Alicia Baquero Tafalla
package model.game;

import java.util.Objects;
import model.exceptions.InvalidSizeException;
import model.Side;

// TODO: Auto-generated Javadoc
/**
 * The Class Game.
 */
public class Game {
	
	/** The rebel. */
	private IPlayer rebel;
	
	/** The imperial. */
	private IPlayer imperial;
	
	/** The board. */
	private GameBoard board;
	
	/** The Constant BOARD_SIZE. */
	private final static int BOARD_SIZE = 10;
	
	/**
	 * Instantiates a new game.
	 *
	 * @param imperial the imperial
	 * @param rebel the rebel
	 */
	public Game(IPlayer imperial, IPlayer rebel) {
		Objects.requireNonNull(imperial);
		Objects.requireNonNull(rebel);
		this.rebel = rebel;
		this.imperial = imperial;
		
		try {
			board=new GameBoard(BOARD_SIZE);
			rebel.setBoard(board);
			imperial.setBoard(board);
		}catch(InvalidSizeException e){
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * Gets the game board.
	 *
	 * @return the game board
	 */
	public GameBoard getGameBoard() {
		return board;
	}
	
	/**
	 * Play.
	 *
	 * @return the side
	 */
	public Side play() {
		Side ganador = null;
		boolean seguir;
		
		imperial.initFighters();
		rebel.initFighters();
		seguir = true;
		do {
			System.out.println("BEFORE IMPERIAL");
			System.out.println(board.toString());
			System.out.println();
			System.out.println(imperial.showShip());
			System.out.println(rebel.showShip());
			System.out.print("IMPERIAL(" + board.numFighters(Side.IMPERIAL) + "): "); 
			seguir = imperial.nextPlay();
			if(!seguir) {
				ganador = Side.REBEL;
			}
			else {
				System.out.println("AFTER IMPERIAL, BEFORE REBEL");
				System.out.println(board.toString());
				System.out.println();
				System.out.println(imperial.showShip());
				System.out.println(rebel.showShip());
				if(!imperial.isFleetDestroyed() && !rebel.isFleetDestroyed()) {
					System.out.print("REBEL(" + board.numFighters(Side.REBEL) + "): ");
					seguir = rebel.nextPlay();
					if(!seguir) {
						ganador = Side.IMPERIAL;
					}
					else {
						System.out.println("AFTER REBEL");
						System.out.println(board.toString());
						System.out.println();
						System.out.println(imperial.showShip());
						System.out.println(rebel.showShip());
						imperial.purgeFleet();
						rebel.purgeFleet();
					}
				}
			}
		}while(seguir && !imperial.isFleetDestroyed() && !rebel.isFleetDestroyed());
		imperial.purgeFleet();
		rebel.purgeFleet();		
		if(seguir) {
			if(imperial.isFleetDestroyed()) {
				ganador = Side.REBEL;
			}
			else{
				ganador = Side.IMPERIAL;
			}
		}
		return ganador;
	}
}