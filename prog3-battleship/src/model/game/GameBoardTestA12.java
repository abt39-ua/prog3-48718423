package model.game;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import model.Coordinate;
import model.FighterFactory;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;

public class GameBoardTestA12 {

	@Test
	public void test() {
		String resultado = "  0123456789\n"
				+ "  ----------\n"
				+ "0|          \n"
				+ "1|   X      \n"
				+ "2| ibA      \n"
				+ "3|   Y      \n"
				+ "4|  f       \n"
				+ "5|          \n"
				+ "6|          \n"
				+ "7|          \n"
				+ "8|          \n"
				+ "9|          ";
		GameShip gs = new GameShip("my shit", Side.REBEL);
		try {
			GameBoard gb = new GameBoard(10);
			gb.launch(new Coordinate(3, 1), FighterFactory.createFighter("XWing", gs));
			gb.launch(new Coordinate(3, 2), FighterFactory.createFighter("AWing", gs));
			gb.launch(new Coordinate(3, 3), FighterFactory.createFighter("YWing", gs));
			gb.launch(new Coordinate(2, 4), FighterFactory.createFighter("TIEFighter", gs));
			gb.launch(new Coordinate(2, 2), FighterFactory.createFighter("TIEBomber", gs));
			gb.launch(new Coordinate(1, 2), FighterFactory.createFighter("TIEInterceptor", gs));
			System.out.println(gb.toString());
			System.out.println(gb);
			assertEquals(resultado, gb.toString());
		}
		catch(InvalidSizeException  e) {
		}
		catch( FighterAlreadyInBoardException e) {
		}
		catch(OutOfBoundsException e) {
		}
	}

}
