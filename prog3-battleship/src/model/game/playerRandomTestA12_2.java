package model.game;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.Test;

import model.Fighter;
import model.RandomNumber;
import model.Side;
import model.exceptions.InvalidSizeException;

public class playerRandomTestA12_2 {
	ByteArrayOutputStream baos;
	PrintStream ps, old;
	
	@Test
	public void test() throws InvalidSizeException {
		standardIO2Stream(); // SALIDA AL BUFFER.
		RandomNumber.resetRandomCounter(); // 
		Fighter.resetNextId();
		GameBoard gb = new GameBoard(5);
		
		PlayerRandom player1 = new PlayerRandom(Side.IMPERIAL, 5);
		PlayerRandom player2 = new PlayerRandom(Side.REBEL, 5);
		player1.setBoard(gb);
		player1.initFighters();
		System.out.println("generados en initFighters: " + RandomNumber.getRandomNumberList());
	
		player2.setBoard(gb);
		player2.initFighters();
		System.out.println("generados en initFighters: " + RandomNumber.getRandomNumberList());
	
		
		System.out.println(player1.showShip());
		System.out.println(player2.showShip());
		System.out.println(gb.toString());
		
		for(int i = 1; i <= 10; i++) {
			System.out.println("jugada " + i);
			System.out.println("===============");
			System.out.println(player1.nextPlay());
			System.out.println(player1.showShip());
			System.out.println(gb.toString());
			System.out.println(RandomNumber.getRandomNumberList());

			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
			System.out.println(player2.nextPlay());
			System.out.println(player2.showShip());
			System.out.println(gb.toString());
			System.out.println(RandomNumber.getRandomNumberList());
		}
		String sout = Stream2StandardIO(); //Cambia salida standard a un Stream
		System.out.println(sout);
		String solution = readSolutionFromFile("files/playerRandomTestA12_2.out"); //Cambia salida de Stream a la consola
		compareLines(solution, sout, false);
	}

	/***************************
	 * METODOS DE APOYO
	 ***************************/
	/* Compara dos String línea a línea.
	 * Parámetros: la cadena esperada, la cadena resultado y true si queremos que considere los espacios 
	 * interiores de la cadena o false en caso contrario. 
	 */
		private void  compareLines(String expected, String result, boolean spaces) {
			String exp[]=expected.split("\n");
			String res[]=result.split("\n");
			boolean iguales = true;
			if (exp.length!=res.length) 
				fail("Cadena esperada de tamaño ("+exp.length+") distinto a la resultante ("+res.length+")");
			for (int i=0; i<exp.length && iguales; i++) {
				if (! exp[i].contains("ERROR:")) {
					if (spaces)
						assertEquals("linea "+i, exp[i].trim(),res[i].trim());
					else
						assertEquals("linea "+i, exp[i].replace(" ",""), res[i].replace(" ","")); 
				} 
				else if (! res[i].contains("ERROR:"))
						fail("Error: el resultado esperado debía contener en la línea "+i+" la cadena 'ERROR:'");
			}
		}
	
	
	//Redirección de la salida estandard de la consola a un Stream	
	private  void standardIO2Stream(){
		baos = new ByteArrayOutputStream();
		ps = new PrintStream(baos);
		// IMPORTANTE: Guarda el antiguo System.out!
		old = System.out;
		// Cambiamos en Java la salida estandar al nuevo stream
		System.setOut(ps);   
	}	
		
	//Volver a restaurar la salida standard a la consola devolviendo lo recogido en el stream
	private String Stream2StandardIO(){
		System.out.flush();
		System.setOut(old); //Reestablecemos la salida standard
		return (baos.toString());
	}
		
	
	//Lee la solución de un fichero y la devuelve en un String	
	private String readSolutionFromFile(String file) {
			Scanner sc=null;
			try {
					sc = new Scanner(new File(file));
			} catch (FileNotFoundException e) {
					System.out.println("File "+file+" not found");
					fail("Fichero "+file+" no encontrado");
			}
			StringBuilder sb = new StringBuilder();
			while (sc.hasNext()) 
				sb.append(sc.nextLine()+"\n");			
			sc.close();
			return (sb.toString());
		}
	

}
