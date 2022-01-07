//@author Alicia Baquero Tafalla
package model.game;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

import model.Coordinate;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

// TODO: Auto-generated Javadoc
/**
 * The Class PlayerFile.
 */
public class PlayerFile implements IPlayer{
	
	/** The ship. */
	private GameShip ship;
	
	/** The board. */
	private GameBoard board;
	
	/** The br. */
	private BufferedReader br;
	
	/**
	 * Instantiates a new player file.
	 *
	 * @param side the side
	 * @param br the br
	 */
	public PlayerFile(Side side, BufferedReader br) {
		Objects.requireNonNull(side);
		Objects.requireNonNull(br);
		ship=new GameShip("PlayerFile " + side + " Ship", side);
		this.br=br;
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
		String s;
		
		try {
			s=br.readLine();
			ship.addFighters(s);
		}catch(IOException e) {
			throw new RuntimeException(e.getMessage());
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
		String s="";
		
		s=ship.toString()+ "\n"+ ship.showFleet();
		
		return s;
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
		boolean next=true;
		int id, qty, x, y;
		String [] trozo;
		String linea;
		
		try {
			linea=br.readLine();
			trozo=linea.split(" ");
			
			switch(trozo[0]) {
				case "exit":
					next=false;
					break;
					
				case "improve":
					if(trozo.length !=3) {
						System.out.println("ERROR: NOT ENOUGH PIECES");
					}
					else {
						id=Integer.parseInt(trozo[1]);
						qty=Integer.parseInt(trozo[2]);
						
						if(qty>=100) {
							System.out.println("ERROR: CAN NOT IMPROVE");
						}
						
						else {
							try {
								ship.improveFighter(id, qty, board);
							}catch(WrongFighterIdException e) {
								System.out.println(e.getMessage());
							}
						}
					}
					break;
					
				case "patrol":
					if(trozo.length !=2) {
						System.out.println("ERROR: to many arguments");
					}
					else {
						id=Integer.parseInt(trozo[1]);
						
						try {
							ship.patrol(id, board);
						}catch(WrongFighterIdException | FighterNotInBoardException e) {
							System.out.println(e.getMessage());
						}
					}
					break;
					
					case "launch":
						try {
						if(trozo.length==3) {
							x=Integer.parseInt(trozo[1]);
							y=Integer.parseInt(trozo[2]);
							board.launch(new Coordinate(x, y), ship.getFirstAvailableFighter(""));
								
						}
						
						if(trozo.length==4) {
							x=Integer.parseInt(trozo[1]);
							y=Integer.parseInt(trozo[2]);
							try {
								id=Integer.parseInt(trozo[3]);
								ship.launch(id, new Coordinate(x, y), board);
							}catch(NumberFormatException e){
								board.launch(new Coordinate(x, y), ship.getFirstAvailableFighter(trozo[3]));
							}
								
						}
							
						else {
							System.out.println("ERROR: TO MANY ARGUMENTS");
						}
						}catch(FighterAlreadyInBoardException | WrongFighterIdException |  OutOfBoundsException | NoFighterAvailableException el) {
							System.out.println(el.getMessage());
							}
						
						break;
					
				default:
					System.out.println("ERROR: "+ trozo[0]+ " unknown command.");
			}
			
			
		}catch(IOException e) {}
		
		return next;
	}
}
