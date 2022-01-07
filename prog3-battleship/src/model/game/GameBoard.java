//@author Alicia Baquero Tafalla
package model.game;

import java.util.Map;
import java.util.Set;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.InvalidSizeException;

// TODO: Auto-generated Javadoc
/**
 * The Class GameBoard.
 */
public class GameBoard extends Board{
	
	/**
	 * Instantiates a new game board.
	 *
	 * @param size the size
	 * @throws InvalidSizeException the invalid size exception
	 */
	public GameBoard(int size) throws InvalidSizeException {
		super(size);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		String s="";
		Coordinate c;
		Fighter f;
		
		s=s+"  ";
		for(int i=0; i<getSize();  i++) {
			s= s+i;
		}
		s=s+"\n  ";
		for(int k=0; k<getSize(); k++) {
			s=s+"-";
			
			if(k>=10) {
				s=s+"-";
			}
		}
		
		s=s+"\n";
		
		for(int h=0; h<getSize(); h++) {
			s= s+h + "|";
			for(int j=0; j<getSize(); j++) {
				c= new Coordinate(j, h);
				f=board.get(c);
				
				if(f==null) {
					s=s+" ";
				}
						
				else {
					s=s+f.getSymbol();
				}
			}
			if(h!=getSize()-1) {
				s= s+"\n";
			}
		}
		return s;
	}
	
	/**
	 * Num fighters.
	 *
	 * @param side the side
	 * @return the int
	 */
	public int numFighters(Side side) {
		int numbers = 0;
		
		for(Map.Entry<Coordinate, Fighter> f : board.entrySet()) {			
			if(f.getValue().getSide() == side) {
				numbers++;
			}
		}
		return numbers;
	}
}
