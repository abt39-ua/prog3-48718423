package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Coordinate;
import model.Fighter;
import model.Side;
import model.exceptions.FighterAlreadyInBoardException;
import model.exceptions.OutOfBoundsException;

public class PlayerFilePreTest {
	PlayerFile playerFile;
	GameShip gs;
	GameBoard gb;
	String inputFighter;
	BufferedReader br;
	Reader stringReader;
	PrintStream ps, old;
	ByteArrayOutputStream baos;
	final String kREBELGAMESHIP = "Ship [PlayerFile REBEL Ship 0/0] 10/AWing:25/XWing:7/YWing";
	final String kIMPERIALGAMESHIP = "Ship [PlayerFile IMPERIAL Ship 0/0] 155/TIEInterceptor:210/TIEBomber:170/TIEFighter";
	final String kSHOWSHIP ="Ship [PlayerFile REBEL Ship 0/0] 10/AWing:25/XWing:7/YWing\n" + 
			"(AWing 1 REBEL null {140,85,30})\n" + 
			"(AWing 2 REBEL null {140,85,30})\n" + 
			"(AWing 3 REBEL null {140,85,30})\n" + 
			"(AWing 4 REBEL null {140,85,30})\n" + 
			"(AWing 5 REBEL null {140,85,30})\n" + 
			"(AWing 6 REBEL null {140,85,30})\n" + 
			"(AWing 7 REBEL null {140,85,30})\n" + 
			"(AWing 8 REBEL null {140,85,30})\n" + 
			"(AWing 9 REBEL null {140,85,30})\n" + 
			"(AWing 10 REBEL null {140,85,30})\n" + 
			"(XWing 11 REBEL null {110,100,80})\n" + 
			"(XWing 12 REBEL null {110,100,80})\n" + 
			"(XWing 13 REBEL null {110,100,80})\n" + 
			"(XWing 14 REBEL null {110,100,80})\n" + 
			"(XWing 15 REBEL null {110,100,80})\n" + 
			"(XWing 16 REBEL null {110,100,80})\n" + 
			"(XWing 17 REBEL null {110,100,80})\n" + 
			"(XWing 18 REBEL null {110,100,80})\n" + 
			"(XWing 19 REBEL null {110,100,80})\n" + 
			"(XWing 20 REBEL null {110,100,80})\n" + 
			"(XWing 21 REBEL null {110,100,80})\n" + 
			"(XWing 22 REBEL null {110,100,80})\n" + 
			"(XWing 23 REBEL null {110,100,80})\n" + 
			"(XWing 24 REBEL null {110,100,80})\n" + 
			"(XWing 25 REBEL null {110,100,80})\n" + 
			"(XWing 26 REBEL null {110,100,80})\n" + 
			"(XWing 27 REBEL null {110,100,80})\n" + 
			"(XWing 28 REBEL null {110,100,80})\n" + 
			"(XWing 29 REBEL null {110,100,80})\n" + 
			"(XWing 30 REBEL null {110,100,80})\n" + 
			"(XWing 31 REBEL null {110,100,80})\n" + 
			"(XWing 32 REBEL null {110,100,80})\n" + 
			"(XWing 33 REBEL null {110,100,80})\n" + 
			"(XWing 34 REBEL null {110,100,80})\n" + 
			"(XWing 35 REBEL null {110,100,80})\n" + 
			"(YWing 36 REBEL null {80,70,110})\n" + 
			"(YWing 37 REBEL null {80,70,110})\n" + 
			"(YWing 38 REBEL null {80,70,110})\n" + 
			"(YWing 39 REBEL null {80,70,110})\n" + 
			"(YWing 40 REBEL null {80,70,110})\n" + 
			"(YWing 41 REBEL null {80,70,110})\n" + 
			"(YWing 42 REBEL null {80,70,110})";
	
	@Before
	public void setUp() throws Exception {
		inputFighter = "10/AWing:25/XWing:7/YWing\nlaunch 4 6 1\nexit";  
		stringReader = new StringReader(inputFighter);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.REBEL, br);
		gb = new GameBoard(10);
		gs = playerFile.getGameShip();
		Fighter.resetNextId();
	}

	/* 
	 * Se crea un PlayerFile (en el setUp) y se comprueba que el GameShip que se crea
	 * es del bando REBEL y que el nombre es correcto. Además se comprueba que el objeto
	 * playerRandom es una instancia de IPlayer.
	 */
	@Test
	public void testPlayerFile() {
		gs = playerFile.getGameShip();
		assertEquals(Side.REBEL,gs.getSide());
		assertEquals("PlayerFile REBEL Ship", gs.getName());
		assertTrue (playerFile instanceof IPlayer);
	}

	/* Se prueba initFighters() para playerFile. Para ello se comprueba que 
	 * el fleet del GameShip asociado tiene 42 cazas y que el ship es correcto 
	 * (coincide con kREBELGAMESHIP)
	 */
	@Test
	public void testInitFightersRebel() {
		playerFile.initFighters();
		gs = playerFile.getGameShip();
		assertEquals(42,gs.getFleetTest().size());
		compareLines(kREBELGAMESHIP, playerFile.getGameShip().toString());
	}
	
	/* Crea un PlayerFile Imperial. Invoca a initFighters() y
	 * comprueba que el fleet del GameShip asociado tiene 535 cazas y
	 * que el ship es correcto (coincide con kIMPERIALGAMESHIP)
	 */
	//TODO
	@Test
	public void testInitFightersImperial() {
		// hay que rellenar el buffer
		inputFighter = "155/TIEInterceptor:210/TIEBomber:170/TIEFighter\nlaunch 1 5\nlaunch 2 2\npatrol 2\nexit\n";
		stringReader = new StringReader(inputFighter);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.IMPERIAL, br);
		playerFile.initFighters();
		
		System.out.println(playerFile.getGameShip().toString());
		assertEquals("comprobando init en playerFile", kIMPERIALGAMESHIP, playerFile.getGameShip().toString());
	}

	
	/* Para un PlayerFile sin iniciar (sin cazas en la nave) se comprueba que
	 * isFleetDestroyed es true.
	 * Tras iniciar la flota, se comprueba ahora que es false. Se destruyen todos
	 * y se comprueba que devuelve true. 
	 */
	@Test
	public void testIsFleetDestroyed() {
		assertTrue (playerFile.isFleetDestroyed());
		playerFile.initFighters();
		assertFalse (playerFile.isFleetDestroyed());
		for (Fighter f: playerFile.getGameShip().getFleetTest()) {
			f.addShield(-300);
		}
		assertTrue (playerFile.isFleetDestroyed());
	}
	

	/* Se comprueba showShip para playerFile sin iniciarlo. Tras iniciarlo se comprueba
	 * que ahora coincide con kSHOWSHIP
	 */
	@Test
	public void testShowShip() {
		compareLines("Ship [PlayerFile REBEL Ship 0/0] ",playerFile.showShip());
		playerFile.initFighters();
		compareLines(kSHOWSHIP, playerFile.showShip());
	}

	/* Comprueba purgeFleet() con un PlayeFile con toda la flota de su nave intacta.
	 * El número de cazas debe coincidir con el que tenía antes de hacer purgeFleet.
	 * Se destruyen todos los cazas. Se ejecuta purgeFleet(). Se comprueba que ahora
	 * no existe ningún caza en la nave.
	 */
	//TODO
	@Test
	public void testPurgeFleet() {
		playerFile.initFighters();
		assertEquals(42,playerFile.getGameShip().getFleetTest().size());
		playerFile.purgeFleet();
		assertEquals(42,playerFile.getGameShip().getFleetTest().size());
		
		System.out.println(playerFile.getGameShip().getFleetTest().toString());
		System.out.println(playerFile.getGameShip().showFleet());
		
		for(Fighter f : playerFile.getGameShip().getFleetTest()) {
			f.addShield(-10000);
		}
		playerFile.purgeFleet();
		assertEquals(0, playerFile.getGameShip().getFleetTest().size());
		
		
	}
	
	/* Inicia playerFile con cazas en su nave. Añadele un tablero. 
	 * Invoca, para ese player, a nextPlay() una vez de tal forma que 
	 * añada un caza en el tablero. 
	 * Vuelve a invocar a nextPlay() para que lea exit y comprueba que 
	 * devuelve false.
	 * Comprueba que está en el tablero en esa posición.
	 */
	//TODO
	@Test
	public void testNextPlay() {
		String s = "10/AWing:25/XWing\nlaunch 4 6 1\nexit";
		stringReader = new StringReader(s);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.REBEL, br);
		playerFile.initFighters();
	
		playerFile.setBoard(gb);
		assertTrue (playerFile.nextPlay()); //Pone un caza en el tablero (id=1)
		assertFalse (playerFile.nextPlay()); //Sale con exit	
		
		List<Integer> listID = playerFile.getGameShip().getFightersId("board");
		assertEquals("hay uno", 1, listID.size());
		assertEquals("es el 1", 1, (int) listID.get(0));
		
		// usando el de board.
		assertEquals("comprobando posicion", new Coordinate(4, 6), gb.getFighter(new Coordinate(4, 6)).getPosition());
		
		// accediendo al de la nave.
		List<Fighter> fighters = playerFile.getGameShip().getFleetTest();
		assertEquals("comprobando posicion", new Coordinate(4, 6), fighters.get(0).getPosition());
		
	}

	/* Inicia playerFile con cazas en su nave. Añadele un tablero. 
	 * 
	 * Crea, para ese player, un String de órdenes de tal forma que:
	 *  -añada 10 AWing, 25 XWing y 7 YWing al GameShip.
	 *  -añade 3 cazas en el tablero (con cada una de las 3 posibilidades) y 
	 *   comprueba que así ha sido.
	 *  -mejora uno de ellos. Comprueba que la mejora se ha efectuado.
	 *  -pon a patrullar a uno en el tablero
	 *  -abandona la partida
	 *  Invoca, para ese player, a nextPlay() todas las veces necesarias para ejecutar todas
	 *  las opciones
	 * 
	 */
	//TODO
	@Test
	public void testNextPlay1() {
		inputFighter = "10/AWing:25/XWing:7/YWing\nlaunch 0 0\nlaunch 0 1 2\nlaunch 0 2 YWing\nimprove 1 50\nimprove 4 20\npatrol 11\npatrol 2\nexit";
		stringReader = new StringReader(inputFighter);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.REBEL, br);
		gs = playerFile.getGameShip();
		
		Fighter.resetNextId();	// esto se hace para que cuando se mezcle con otros test la variable se inicialice.
		playerFile.initFighters(); // addFighters con la primera linea
		playerFile.setBoard(gb);
		gs = playerFile.getGameShip();
		assertTrue(playerFile.nextPlay()); // hace un launch
		assertTrue(playerFile.nextPlay()); // ..
		assertTrue(playerFile.nextPlay()); // ..
		String sal = "  0123456789\n"
				+ "  ----------\n"
				+ "0|A         \n"
				+ "1|A         \n"
				+ "2|Y         \n"
				+ "3|          \n"
				+ "4|          \n"
				+ "5|          \n"
				+ "6|          \n"
				+ "7|          \n"
				+ "8|          \n"
				+ "9|          ";;
		
		System.out.println(gb.toString());
		System.out.println(gs.showFleet());
		
		List<Fighter> fighters = gs.getFleetTest();
		assertEquals("posicion bien 1", new Coordinate(0, 0), fighters.get(0).getPosition());
		assertEquals("posicion bien 2", new Coordinate(0, 1), fighters.get(1).getPosition());
		assertEquals("posicion bien 3", new Coordinate(0, 2), fighters.get(35).getPosition());
		assertEquals("son iguales", sal, gb.toString());
		
		
		assertTrue(playerFile.nextPlay()); // ..
		assertTrue(playerFile.nextPlay()); // ..
		
		System.out.println(gb.toString());
		System.out.println(gs.showFleet());
		
		
		System.out.println(fighters.get(0).toString());
		assertEquals("comprobando improve del 1", "(AWing 1 REBEL null {140,110,55})", fighters.get(0).toString());

		System.out.println(fighters.get(3).toString());
		assertEquals("comprobando improve del 4", "(AWing 4 REBEL null {140,95,40})", fighters.get(3).toString());

		assertTrue(playerFile.nextPlay()); // patrol falla.
		assertTrue(playerFile.nextPlay()); // patrol ok.
		
		System.out.println(gb.toString());
		
		
	}
	
	
	/* Tras iniciar playerFile con cazas y asignarles un tablero de 10x10, se invoca cinco veces a nextPlay() 
	 * que debe ejecutar tres instrucciones improve erróneas:
	 *  - con 1 número 
	 *  - con 3 números
	 *  - con qty NO menor que 100
	 *  - con un id inexistente.
	 *  - con una opción improve mal escrita: 'improves'
	 * Se comprueba que se emitieron los mensajes de error y que éstos empiezan con la 
	 * subcadena "ERROR:".
	 */
	@Test
	public void testNextPlayWithErrorsInImprove() {
		
		String s = "10/AWing:25/XWing:7/YWing\nimprove 9\nimprove 4 3 26\nimprove 4 100\nimprove 43 50\nimproves 4 23";
		stringReader = new StringReader(s);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.REBEL, br);
		gs = playerFile.getGameShip();
		playerFile.initFighters();
		playerFile.setBoard(gb);
		standardIO2Stream(); //Cambiamos la salida estandard a un nuevo stream
		for (int i=0; i<5; i++)
			assertTrue (playerFile.nextPlay()); 
		String serr = Stream2StandardIO(); //Restauramos la salida estandar a la consola.
		errorsControl(serr,5); //Comprobamos que serr contiene cinco líneas que empiezan con 'ERROR:'
	}

	/* Tras iniciar playerFile con cazas y asignarles un tablero de 10x10, se invoca cinco veces a nextPlay() 
	 * que debe ejecutar 6 instrucciones una launch correcta y 5 patrol erróneas:
	 *  - con ningún número. 
	 *  - con 2 números
	 *  - con un id que no está en el tablero.
	 *  - con un id inexistente.
	 *  - con una opción patrol mal escrita: 'patrols'
	 * Se comprueba que se emitieron los mensajes de error y que éstos empiezan con 
	 * la subcadena "ERROR:".
	 */
	//TODO
	@Test
	public void testNextPlayWithErrorsInPatrol() throws FighterAlreadyInBoardException, OutOfBoundsException {
		String s = "10/AWing\npatrol\npatrol 0 0\npatrol 11\npatrol 1\npatrols";
		stringReader = new StringReader(s);
		br = new BufferedReader(stringReader);
		playerFile = new PlayerFile(Side.REBEL, br);
		gs = playerFile.getGameShip();
		playerFile.initFighters();
		playerFile.setBoard(gb);
		
		standardIO2Stream(); //Cambiamos la salida estandard a un nuevo stream
		for (int i = 0; i < 5; i++)
			assertTrue (playerFile.nextPlay()); 
		String serr = Stream2StandardIO(); //Restauramos la salida estandar a la consola.
		errorsControl(serr,5); //Comprobamos que serr contiene cinco líneas que empiezan con 'ERROR:'
		
		System.out.println(serr);
		
	}
	
	/* Test de comprobación de los parámetros null en PlayerFile */
	@Test
	public void testRequireNonNull()  {
		
		try {
			new PlayerFile(null, br);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
		try {
			new PlayerFile(Side.REBEL, null);
			fail("ERROR: Debió lanzar NullPointerException");
		}catch (NullPointerException e) {}
	}

	/***************************
	 * METODOS DE APOYO
	 ***************************/
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
	
	//Redirección de la salida standard de la consola a un Stream	
	private  void standardIO2Stream(){
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		// Guarda el antiguo System.out!
		old = System.out;
		// Cambiamos en Java la salida standard al nuevo stream
		System.setOut(ps);   
	}
		
	//Volver a restaurar la salida standard a la consola
	private String Stream2StandardIO(){
		System.out.flush();
		System.setOut(old); //Reestablecemos la salida standard
		return (baos.toString());
	}
	
	//Se comprueba si serr contiene nlines que empiezan con "ERROR:"
	private void errorsControl(String serr, int nlines) {
		String verr[] = serr.split("\n");
		if (nlines != verr.length) fail ("ERROR: Número de líneas de error incorrectas "+verr.length);
		for (int i=0; i<nlines; i++) {
			assertTrue("Error en la línea "+i,verr[i].startsWith("ERROR:"));
		}
	}
	
}
