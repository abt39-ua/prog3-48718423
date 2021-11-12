//@author Alicia Baquero Tafalla
package model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import javax.management.RuntimeErrorException;

import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterIsDestroyedException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

// TODO: Auto-generated Javadoc
/**
 * The Class Board.
 */
public class Board {
	
	/** The size. */
	private int size;
	
	/** The board. */
	private Map<Coordinate,Fighter> board;
	
	/**
	 * Instantiates a new board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException the invalid size exception
	 */
	public Board(int size) throws InvalidSizeException{
		if(size<5) {
			throw new InvalidSizeException(size);
		}
		this.size=size;
		board=new HashMap<Coordinate, Fighter>();
	}
	
	/**
	 * Gets the fighter.
	 *
	 * @param c the c
	 * @return the fighter
	 */
	public Fighter getFighter(Coordinate c) {
		Objects.requireNonNull(c);
		Fighter fighter=board.get(c	);
		
		if(fighter!=null) {
			return fighter.copy();
		}
		
		return null;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Removes the fighter.
	 *
	 * @param f the f
	 * @return true, if successful
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public void removeFighter(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		Coordinate position1 = f.getPosition();
		
		if(position1==null) {
			throw new FighterNotInBoardException(f);
		}
		
		if(board.get(position1)==null) {
			throw new FighterNotInBoardException(f);
		}
		if(!board.get(position1).equals(f)){
			throw new FighterNotInBoardException(f);

		}
		
		board.remove(position1);
		f.setPosition(null);
	}
	
	/**
	 * Inside.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public boolean inside(Coordinate c) {
		boolean in=false;
		
		if(c!=null) {
			if(c.getX()>=0 && c.getY()>=0 && c.getX()<size && c.getY()<size) {
				in=true;
			}
		}
		return in;
	}
	
	/**
	 * Gets the neighborhood.
	 *
	 * @param c the c
	 * @return the neighborhood
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public Set<Coordinate> getNeighborhood(Coordinate c) throws OutOfBoundsException{
		Objects.requireNonNull(c);
		Set<Coordinate> vecinas= new TreeSet<>();
		
		if(!inside(c)) {
			throw new OutOfBoundsException(c);
		}
		
		for(int a=c.getX()-1; a<=c.getX()+1; a++) {
			for(int b=c.getY()-1; b<=c.getY()+1; b++) {
				if(a==c.getX() && b==c.getY()){}
				else{
					if(inside(new Coordinate(a,b)))
						vecinas.add(new Coordinate(a,b));
				}
			}
		}
		return vecinas;
	}
	


	
	/**
	 * Launch.
	 *
	 * @param c the c
	 * @param f the f
	 * @return the int
	 * @throws FighterAlreadyInBoardException the fighter already in board exception
	 * @throws OutOfBoundsException the out of bounds exception
	 */
	public int launch(Coordinate c, Fighter f) throws FighterAlreadyInBoardException, OutOfBoundsException{
		Objects.requireNonNull(c);
		Objects.requireNonNull(f);
		Fighter enemy;
		int n=0;
		
		if(board.containsValue(f)) {
			throw new FighterAlreadyInBoardException(f);
		}
		
		if(!inside(c)) {
			throw new OutOfBoundsException(c);
		}
		
		if(!board.containsKey(c)) {
			board.put(c, f);
			f.setPosition(c);
		}
		else {
			enemy=board.get(c);
				
			if(f.getSide()!=enemy.getSide()) {
				try {
					n=f.fight(enemy);
				}
				catch(FighterIsDestroyedException e) {
					throw new RuntimeException(e.getMessage());
				}

				if(n==1) {
					f.getMotherShip().updateResults(1);
					enemy.getMotherShip().updateResults(-1);
					board.put(c, f);
					enemy.setPosition(null);
				}
					
				else {
					enemy.getMotherShip().updateResults(1);
					f.getMotherShip().updateResults(-1);
				}
			}
			
		}

		return n;
	}
	
	/**
	 * Patrol.
	 *
	 * @param f the f
	 * @throws FighterNotInBoardException the fighter not in board exception
	 */
	public void patrol(Fighter f) throws FighterNotInBoardException{
		Objects.requireNonNull(f);
		Fighter fighter;
		Coordinate position= f.getPosition();
		Set<Coordinate> neigbors=null;
		int n;
		
		if(position==null || board.get(position)==null || !board.get(position).equals(f)) {
			throw new FighterNotInBoardException(f);
		}
		if(position!=null) {
			Fighter fposition= board.get(position);
			
			if(f==fposition) {
				try {
					neigbors=getNeighborhood(position);
				}catch(OutOfBoundsException e) {
					throw new RuntimeException(e.getMessage());
				}
				
				for(Coordinate cor:neigbors) {	
					fighter=board.get(cor);
					
					if(fighter!=null && fighter.getSide()!=f.getSide()){
						try{
							n=f.fight(fighter);
						}
						catch(FighterIsDestroyedException e) {
							throw new RuntimeException(e);
						}
						
						if(n!=1) {
							f.getMotherShip().updateResults(-1);
							fighter.getMotherShip().updateResults(1);
							board.remove(f.getPosition());
							f.setPosition(null);
							break;
						}
						
						else {
							f.getMotherShip().updateResults(1);
							fighter.getMotherShip().updateResults(-1);
							fighter.setPosition(null);
							board.remove(cor);
						}
					}
				}
			}
		}
	}
}
