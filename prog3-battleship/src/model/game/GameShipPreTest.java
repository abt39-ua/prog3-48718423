package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.Fighter;
import model.Ship;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.FighterNotInBoardException;
import model.exceptions.InvalidSizeException;
import model.exceptions.OutOfBoundsException;
import model.game.exceptions.WrongFighterIdException;

public class GameShipPreTest {

	GameShip gameShip;
	List<Fighter>fleet ;
	GameBoard gameBoard;
	
	@Before
	public void setUp() throws Exception {
		 gameShip = new GameShip("Lanzadera T-4a", Side.IMPERIAL);
		 Fighter.resetNextId();
	}

	/* Se comprueba que el constructor copia de GameShip es correcto y que
	   GameShip es una clase derivada de Ship (no se han duplicado los atributos)
	 */
	//TODO
	@Test
	public void testGameShip() {
		assertEquals ("Lanzadera T-4a", gameShip.getName());
		assertEquals (Side.IMPERIAL, gameShip.getSide());
		assertEquals (0,  gameShip.getWins());
		assertEquals (0,  gameShip.getLosses());
		fleet = (List<Fighter>) gameShip.getFleetTest();
		assertNotNull (fleet);
		assertTrue(gameShip instanceof Ship);
	}

	/* Se comprueba que isFleetDestroyed devuelve true si no hay cazas en
	 * la nave.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedEmpty() {
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	/* Añade fighters a un GameShip. Luego destruyelos todos y comprueba que 
	 * isFleetDestroyed devuelve true.
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedAllDestroyed() {
		gameShip.addFighters("2/XWing:3/YWing");
		fleet = (List<Fighter>) gameShip.getFleetTest();
		for(Fighter f : fleet) {
			f.addShield(-200);
		}
		assertTrue(gameShip.isFleetDestroyed());
	}
	
	
	/* Añade cazas a una nave. Destruye todos menos uno y comprueba que 
	 * isFleetDestroyed() devuelve false
	 */
	//TODO
	@Test
	public void testIsFleetDestroyedNotAllDestroyed() {
		gameShip.addFighters("2/XWing:3/YWing");
		fleet = (List<Fighter>) gameShip.getFleetTest();
		// no destruyo el primero
		for(int i = 1; i < fleet.size(); i++) {
			fleet.get(i).addShield(-200);
		}
		assertFalse(gameShip.isFleetDestroyed());
	}
	
	/* Se comprueba que se obtiene una lista vacía de una nave sin cazas. Luego se
	 * añaden cazas, se destruyen todos y se comprueba que se sigue devolviendo una
	 * lista vacía.
	 */
	@Test
	public void testGetFightersIdListEmpty() {
		List<Integer> l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");
		for (Fighter f : gameShip.getFleetTest()) {
			f.addShield(-300);
		}
		l = gameShip.getFightersId("");
		assertTrue (l.isEmpty());
	}

	/* Añade cazas a un Ship. Comprueba que al invocar a getFightersId("ship") 
	 * se devuelve una lista con los 'id' de todos los cazas del la nave. 
	 * Además comprueba que al invocar a getFightesId("board") se devuelve una lista 
	 * vacía.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty1()  {
		gameShip.addFighters("10/TIEFighter:35/TIEInterceptor:5/TIEBomber");	
		
		List<Integer> l = gameShip.getFightersId("ship");
		for(int i = 1; i < l.size(); i++) {
			assertEquals("comprobando ids", i, (int) l.get(i-1));
		}
		
		l = gameShip.getFightersId("board");
		assertEquals("tamaño de la lista", 0, l.size());
		
	}
	
	/* Se añaden cazas a un Ship. Se añaden todos a un tablero. Se comprueba que al invocar a  getFightersId("board") 
	 * se devuelve una lista con los id de todos los cazas del la nave.
	 * Además se comprueba que getFightersId("ship") devuelve una lista vacía
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty2() throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		gameShip.addFighters("2/TIEFighter:4/TIEInterceptor:3/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		for(int i = 1; i <= fleet.size(); i++) {
			try {
				gameShip.launch(i, new Coordinate(i-1, i-1), gameBoard);
			} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
				fail("al añadir los cazas");
			}
		}
		
		List<Integer> l = gameShip.getFightersId("board");
		for(int i = 1; i < l.size(); i++) {
			assertEquals("comprobando ids", i, (int) l.get(i-1));
		}
		
		
		System.out.println(gameBoard.toString());
	}
	
	/* Añade cazas a un Ship. Añade algunos a un tablero. Destruye otros. 
	 * Comprueba que al invocar a:
	 *  - getFightersId("board") se devuelve una lista solo con los que realmente
	 * 		están en el tablero. 
     *  - getFightersId("ship") se devuelve una lista solo con los que no están en el tablero y no están destruídos.
     *  - getFightersId("") se devuelve una lista con todos los no destruídos.
	 */
	//TODO
	@Test
	public void testGetFightersIdListNotEmpty3() 
			throws InvalidSizeException, FighterAlreadyInBoardException, OutOfBoundsException  {
		
		gameShip.addFighters("1/TIEFighter:6/TIEInterceptor:3/TIEBomber");	
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());

		for(int i = 1; i <= fleet.size()/2; i++) { // los 5 primeros al tablero
			try {
				gameShip.launch(i, new Coordinate(i-1, i-1), gameBoard);
			} catch (WrongFighterIdException | FighterAlreadyInBoardException | OutOfBoundsException e) {
				fail("al añadir los cazas");
			}
		}
		fleet.get(0).addShield(-1000);					// me cargo el primero
		fleet.get(fleet.size()-1).addShield(-1000);		// me cargo el ultimo
		
		System.out.println(gameBoard.toString());
		
		List<Integer> l1 = gameShip.getFightersId("board");
		System.out.println(l1);
		assertEquals("in board", "[2, 3, 4, 5]", l1.toString());
		List<Integer> l2 = gameShip.getFightersId("ship");
		System.out.println(l2);
		assertEquals("in ship", "[6, 7, 8, 9]", l2.toString());
		List<Integer> l3 = gameShip.getFightersId("");
		System.out.println(l3);
		assertEquals("alls", "[2, 3, 4, 5, 6, 7, 8, 9]", l3.toString());
		
		
	}
	
	/* Añade cazas a un GameShip e intenta poner uno, con launch, con una id que no existe. 
	 * Se comprueba que se lanza la excepción WrongFighterIdException y que no lo ha puesto.
	 * Luego destruye uno del GameShip e intenta ponerlo en el tablero. Comprueba que
	 * también se lanza la excepción WrongFighterIdException y que tampoco se ha puesto.
	 */
	//TODO
	@Test
	public void testLaunchWrongFighterIdException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		Coordinate c=new Coordinate(4,3);
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.launch(20, c, gameBoard);
			fail("ERROR: Debió lanzar la excepción WrongFighterIdException");
		} catch (WrongFighterIdException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
			assertNull(gameBoard.getFighter(c));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
		fleet.get(0).addShield(-1000);
		try {
			gameShip.launch(1, new Coordinate(0,0), gameBoard);
			assertNull(gameBoard.getFighter(new Coordinate(0,0)));
			assertNull(fleet.get(0).getPosition());
		}
		catch(WrongFighterIdException  e ) {
			
		}
		catch(FighterAlreadyInBoardException | OutOfBoundsException e) {
			fail("lanzas la excepcion que no es");
		}
	}
	
	
	/* Añaden cazas a un GameShip e intenta poner uno, con launch, en una coordenada
	 * fuera del tablero. 
	 * Comprueba que se lanza la excepción OutOfBoundsException.
	 */
	//TODO
	@Test
	public void testLaunchOutOfBoundsException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		gameBoard = new GameBoard(5);
		
		try {
			gameShip.launch(1, new Coordinate(5, 0), gameBoard);
			fail("no lanzas la excepcion out of bounds");
		} catch (WrongFighterIdException e) {
			
			fail("");
		} catch (FighterAlreadyInBoardException e) {
			fail("");
		} catch (OutOfBoundsException e) {
		}
		
	}
	
	/* Se añaden cazas a un GameShip y se pone a patrullar a uno de ellos en un
	 * tablero. Como no está en él, se comprueba que se lanza la excepción 
	 * FighterNotInBoardException y no otra y que el mensaje empieza con la
	 * cadena "ERROR:"
	 */
	@Test
	public void testPatrolNotInBoardException() throws InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		try {
			gameShip.patrol(13, gameBoard);
			fail("ERROR: Debió lanzar la excepción FighterNotInBoardException");
		} catch (FighterNotInBoardException e1) {
			assertTrue(e1.getMessage().startsWith("ERROR:"));
		} catch (Exception e2) {
			fail("ERROR: No debió lanzar la excepción "+e2.getClass().getSimpleName());
		}	
	}

	/* Añade cazas a un GameShip y pon a patrullar a uno con una id que
	 * no existe en el tablero. Como no está en él, comprueba que se lanza 
	 * la excepción WrongFighterIdException y no otra.
	 */
	//TODO
	@Test
	public void testPatrolWrongFighterIdException() throws InvalidSizeException {
		gameShip.addFighters("3/XWing");
		gameBoard = new GameBoard(5);
		try {
			gameShip.patrol(5, gameBoard);
			fail("no lanzaste la excepcion2");
		}
		catch(WrongFighterIdException e) {
			
		}
		catch(FighterNotInBoardException e) {
			fail("Se lanza not in board");
		}
	}
	
	/* Añade cazas a un GameShip y pon un TIEInterceptor en un tablero.
	 * Añade una mejora de 97 al caza del tablero. Comprueba que ya no está en
	 * el tablero y que el ataque ahora es de 133 y el escudo de 109.
	 */
	@Test
	public void testImproveFighter() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());
		
		gameShip.launch(5, new Coordinate(0,0),gameBoard);
		gameShip.improveFighter(5, 97, gameBoard);
		assertNull("no esta en el tablero", fleet.get(4).getPosition());
		assertEquals("escudo ok", fleet.get(4).getShield(), 109);
		assertEquals("ataque ok", fleet.get(4).getAttack(), 133);
		assertNull(gameBoard.getFighter(new Coordinate(0, 0)));
		
	}
	
	/* Se añaden cazas a un GameShip. Se intenta mejorar uno de los cazas del GameShip que
	 * no está en tablero alguno. Se comprueba que ha existido la mejora en dicho caza.
	 * Se intenta mejorar un id de un caza que no existe. Se comprueba que se lanza la excepción
	 * WrongFighterIdException y que lanza el mensaje con el inicio de 'ERROR:'
	 */
	//TODO
	@Test
	public void testImproveFighterExceptions() throws FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException {
		gameShip.addFighters("4/TIEFighter:3/TIEInterceptor:6/TIEBomber");
		fleet = gameShip.getFleetTest();
		gameBoard = new GameBoard(fleet.size());	
		try {
			gameShip.improveFighter(6, 97, gameBoard);
			// se comprueba que el caza se ha mejorado ----
			assertEquals("escudo ok", fleet.get(5).getShield(), 109);
			assertEquals("ataque ok", fleet.get(5).getAttack(), 133);

		} catch (WrongFighterIdException e) {
			fail("ERROR: No debió lanzar la excepción "+e.getClass().getSimpleName());
		}
		try {
			gameShip.improveFighter(0, 97, gameBoard);
			fail("el id del caza no existe");
		} catch (WrongFighterIdException e) {
		}
		try {
			gameShip.improveFighter(14, 97, gameBoard);
			fail("el id del caza no existe");
		} catch (WrongFighterIdException e) {
		}

		
	}
	
	/* Realiza las comprobaciones de los parámetros null en launch, patrol e improveFighter
	 * de GameShip */
	//TODO
	@Test
	public void testRequireNonNull() throws WrongFighterIdException, FighterAlreadyInBoardException, OutOfBoundsException, InvalidSizeException, FighterNotInBoardException  {
		
		try {
			gameShip.launch(2, null, new GameBoard(10));
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.launch(2, new Coordinate(3,2), null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.patrol(3, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			gameShip.improveFighter(3, 3, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		
		
		
		
		
	}

}
