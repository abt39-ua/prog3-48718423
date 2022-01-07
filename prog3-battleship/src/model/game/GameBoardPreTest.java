package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import model.Board;
import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.NoFighterAvailableException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameBoardPreTest {

	GameBoard gameBoard;
	GameShip gameShip;
	final String kEXAMPLEBOARD = "  0123456789\n" + 
								 "  ----------\n" + 
								 "0|   A    AX\n" + 
								 "1|          \n" + 
								 "2|    X     \n" + 
								 "3|       A  \n" + 
								 "4|          \n" + 
								 "5|   A      \n" + 
								 "6| i Y      \n" + 
								 "7|    Y b X \n" + 
								 "8|          \n" + 
								 "9|          ";
	final String kEMPTYBOARD = "   0123456789\n" + 
							   "   ----------\n" + 
							   " 0|          \n" + 
							   " 1|          \n" + 
							   " 2|          \n" + 
							   " 3|          \n" + 
							   " 4|          \n" + 
							   " 5|          \n" + 
							   " 6|          \n" + 
							   " 7|          \n" + 
							   " 8|          \n" + 
							   " 9|          ";

								  
	@Before
	public void setUp() throws Exception {
		gameBoard = new GameBoard(10);
		gameShip = new GameShip("Tydirium", Side.REBEL);
		Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameBoard es correcto y que
	   GameBoard es una clase derivada de Board.
	 */
	@Test
	public void testGameBoard() {
		assertEquals (10, gameBoard.getSize());
		assertTrue(gameBoard instanceof Board );
	}

	/* Se comprueba que en un tablero vacío el número de Fighters de ambos bandos
	 * es 0
	 */
	@Test
	public void testNumFightersInEmptyBoard() {
		gameShip.addFighters("10/AWing:35/XWing:5/YWing");
		assertEquals(0,gameBoard.numFighters(Side.IMPERIAL));
		assertEquals(0,gameBoard.numFighters(Side.REBEL));
	}

	/* Se añaden a un GameShip varios cazas rebeldes. Todos ellos se posicionan sobre un 
	 * tablero. Se comprueba que numFighters para los rebeldes coincide con el número de
	 * cazas del GameShip.
	 */
	@Test
	public void testNumFighters1() throws FighterAlreadyInBoardException, OutOfBoundsException {
		gameShip.addFighters("7/AWing:6/XWing:2/YWing");
		int i=0;
		int j=0;
		for (Fighter f : gameShip.getFleetTest()) {
			if (i==gameBoard.getSize()) {
				i=0; j+=3;
			}
			gameBoard.launch(new Coordinate(i,j), f);
			i++;
			
		}
		assertEquals(15,gameBoard.numFighters(Side.REBEL));
	}
	
	/* Se añaden cazas rebeldes a un GameShip rebelde y cazas imperiales a un GameShip imperial.
	 * Se posiciona una parte de los cazas rebeldes e imperiales en un tablero. 
	 * Se comprueba que el numFighers para los cazas rebeldes e imperiales coinciden respectivamente
	 * con los que están en el tablero.
	 */
	//TODO
	@Test
	public void testNumFighters2() throws FighterAlreadyInBoardException, OutOfBoundsException {
		
		
		gameShip.addFighters("7/AWing:6/XWing:2/YWing");
		GameShip gameImperialShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		gameImperialShip.addFighters("3/TIEBomber:9/TIEInterceptor:2/TIEFighter");
		
		try {
			for(int i = 1; i <= 3; i++) {
				gameShip.launch(gameShip.getFightersId("ship").get(0), new Coordinate(0, i), gameBoard);
			}
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
			fail("efe lanzando fisters");
		}
		assertEquals("en el tablero rebel", 3, gameBoard.numFighters(Side.REBEL));
		
		
		try {
			for(int i = 1; i <= 4; i++) {
				gameImperialShip.launch(gameImperialShip.getFightersId("ship").get(0), new Coordinate(1, i), gameBoard);
			}
		} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
			fail("efe lanzando fisters");
		}
		assertEquals("en el tablero rebel", 4, gameBoard.numFighters(Side.IMPERIAL));
		
		System.out.println(gameBoard.toString());
		String salida = "  0123456789\n"
				+ "  ----------\n"
				+ "0|          \n"
				+ "1|Ab        \n"
				+ "2|Ab        \n"
				+ "3|Ab        \n"
				+ "4| i        \n"
				+ "5|          \n"
				+ "6|          \n"
				+ "7|          \n"
				+ "8|          \n"
				+ "9|          ";
		
		assertEquals("El tablero en realidad", salida, gameBoard.toString());
	}
	
	/* Se prueba toString para un tablero de 15x15 vacío
	 * 
	 */
	@Test
	public void testToStringAnEmptyBoard()  {
		
		compareLines(kEMPTYBOARD, gameBoard.toString());
	}
	
	/* Crea dos GameShip: uno imperial y otro rebelde. Añade los cazas suficientes en
	 * cada uno de ellos para luego ponerlos en el tablero como en el ejemplo del enunciado.
	 * Se añaden los cazas del ejemplo del enunciado en un tablero 10x10. Se comprueba que la salida
	 * es correcta: gameBoard.toString() debe coincidir con la constante String kEXAMPLEBOARD.
	 * Para compararlos usa el método auxiliar compareLines del final.
	 * compareLines(kEXAMPLEBOARD, gameBoard.toString());
	 */
	//TODO
	@Test
	public void testToStringExample() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		GameBoard gb = new GameBoard(10);
		GameShip gsi = new GameShip("irene ship", Side.IMPERIAL);
		GameShip gsr = new GameShip("jose ship", Side.REBEL);
		gsr.addFighters("4/AWing:3/XWing:2/YWing");
		gsi.addFighters("1/TIEInterceptor:1/TIEBomber");
		
		Coordinate [] coordinatesRebel = {
				new Coordinate(3, 0),
				new Coordinate(8, 0),
				new Coordinate(7, 3),
				new Coordinate(3, 5),
				new Coordinate(9, 0),
				new Coordinate(4, 2),
				new Coordinate(8, 7),
				new Coordinate(3, 6),
				new Coordinate(4, 7)
		};
		Coordinate [] coordinatesImperial = {
				new Coordinate(1, 6),
				new Coordinate(6, 7)
		};
		Fighter f;
		for(Coordinate c : coordinatesRebel) {
			try {
				f = gsr.getFirstAvailableFighter("");
				gb.launch(c, f);
			} catch (NoFighterAvailableException e) {
				fail("Aqui no tienes que lanzar nada, colocando rebels");
			}
		}
		for(Coordinate c : coordinatesImperial) {
			try {
				f = gsi.getFirstAvailableFighter("");
				gb.launch(c, f);
			} catch (NoFighterAvailableException e) {
				fail("Aqui no tienes que lanzar nada, colocando imperials");
			}
		}
		System.out.println(gb.toString());
		assertEquals("tus huevos", kEXAMPLEBOARD, gb.toString());
		
	}
	@Test
	public void testToStringExample2Way() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		GameBoard gb = new GameBoard(10);
		GameShip gsi = new GameShip("irene ship", Side.IMPERIAL);
		GameShip gsr = new GameShip("jose ship", Side.REBEL);
		gsr.addFighters("4/AWing:3/XWing:2/YWing");
		gsi.addFighters("1/TIEInterceptor:1/TIEBomber");
		
		Coordinate [] coordinatesRebel = {
				new Coordinate(3, 0),
				new Coordinate(8, 0),
				new Coordinate(7, 3),
				new Coordinate(3, 5),
				new Coordinate(9, 0),
				new Coordinate(4, 2),
				new Coordinate(8, 7),
				new Coordinate(3, 6),
				new Coordinate(4, 7)
		};
		Coordinate [] coordinatesImperial = {
				new Coordinate(1, 6),
				new Coordinate(6, 7)
		};
		Fighter f;
		int id = 1;
		for(Coordinate c : coordinatesRebel) {
			try {
				gsr.launch(id++, c, gb);
			} 
			catch (WrongFighterIdException e) {
				fail("Aqui no tienes que lanzar WrongFighterId");
			}
		}
		for(Coordinate c : coordinatesImperial) {
			try {
				gsi.launch(id++, c, gb);
			} 
			catch (WrongFighterIdException e) {
				fail("Aqui no tienes que lanzar WrongFighterId");
			}
		}
		System.out.println(gb.toString());
		assertEquals("tus huevos", kEXAMPLEBOARD, gb.toString());
		
		String salida = "Ship [irene ship 0/0] 1/TIEInterceptor:1/TIEBomber\n"
				+ "Ship [jose ship 0/0] 4/AWing:3/XWing:2/YWing";
		
		System.out.println(gsi.toString());
		System.out.println(gsr.toString());
		assertEquals("estado naves", salida, gsi.toString() + "\n" +  gsr.toString());
		
		String salidaRebel = 	"(AWing 1 REBEL [3,0] {140,85,30})\n"
								+ "(AWing 2 REBEL [8,0] {140,85,30})\n"
								+ "(AWing 3 REBEL [7,3] {140,85,30})\n"
								+ "(AWing 4 REBEL [3,5] {140,85,30})\n"
								+ "(XWing 5 REBEL [9,0] {110,100,80})\n"
								+ "(XWing 6 REBEL [4,2] {110,100,80})\n"
								+ "(XWing 7 REBEL [8,7] {110,100,80})\n"
								+ "(YWing 8 REBEL [3,6] {80,70,110})\n"
								+ "(YWing 9 REBEL [4,7] {80,70,110})\n";
		
		String salidaImperial = "(TIEInterceptor 10 IMPERIAL [1,6] {145,85,60})\n"
								+ "(TIEBomber 11 IMPERIAL [6,7] {70,30,115})\n";
		System.out.println(gsi.showFleet());
		System.out.println(gsr.showFleet());
		assertEquals("showFleet imperial", salidaImperial, gsi.showFleet());
		assertEquals("showFleet rebel", salidaRebel, gsr.showFleet());
		
		
	}

	
	
	
	
	
	
	
	/*************************
	 * FUNCIONES AUXILIARES
	 * 
	 *************************/
	//Compara dos String línea a línea
	private void  compareLines(String expected, String result) {
		String exp[]=expected.split("\n");
		String res[]=result.split("\n");
		boolean iguales = true;
		if (exp.length!=res.length) 
			fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
		for (int i=0; i<exp.length && iguales; i++) {
				 assertEquals("linea "+i, exp[i].trim(),res[i].trim());
		}
	}
}
