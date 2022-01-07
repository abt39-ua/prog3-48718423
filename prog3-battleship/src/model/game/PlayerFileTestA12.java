package model.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import model.Fighter;
import model.RandomNumber;
import model.Side;
import model.exceptions.InvalidSizeException;

public class PlayerFileTestA12 {

	@Test
	public void test() throws IOException, InvalidSizeException{
		 Fighter.resetNextId();
		 RandomNumber.resetRandomCounter(); // 
		String salida1 = "Ship [PlayerFile IMPERIAL Ship 0/0] \n";
		String salida2 = "Ship [PlayerFile IMPERIAL Ship 0/0] 3/XWing:2/YWing\n"
				+ "(XWing 1 IMPERIAL null {110,100,80})\n"
				+ "(XWing 2 IMPERIAL null {110,100,80})\n"
				+ "(XWing 3 IMPERIAL null {110,100,80})\n"
				+ "(YWing 4 IMPERIAL null {80,70,110})\n"
				+ "(YWing 5 IMPERIAL null {80,70,110})\n";
		String salida3 = "Ship [PlayerFile IMPERIAL Ship 0/0] 3/XWing:2/YWing\n"
				+ "(XWing 1 IMPERIAL [0,0] {110,100,80})\n"
				+ "(XWing 2 IMPERIAL [0,2] {110,100,80})\n"
				+ "(XWing 3 IMPERIAL null {110,100,80})\n"
				+ "(YWing 4 IMPERIAL null {80,70,110})\n"
				+ "(YWing 5 IMPERIAL [0,1] {80,70,110})\n";
		String salida4 = "  0123456789\n"
				+ "  ----------\n"
				+ "0|X         \n"
				+ "1|Y         \n"
				+ "2|X         \n"
				+ "3|          \n"
				+ "4|          \n"
				+ "5|          \n"
				+ "6|          \n"
				+ "7|          \n"
				+ "8|          \n"
				+ "9|          ";
		String salida5 = "Ship [PlayerFile IMPERIAL Ship 0/0] 3/XWing:2/YWing\n"
				+ "(XWing 1 IMPERIAL null {110,125,105})\n"
				+ "(XWing 2 IMPERIAL [0,2] {110,100,80})\n"
				+ "(XWing 3 IMPERIAL null {110,149,130})\n"
				+ "(YWing 4 IMPERIAL null {80,70,110})\n"
				+ "(YWing 5 IMPERIAL [0,1] {80,70,110})\n"
				+ "";
		
		String salida6 = "  0123456789\n"
				+ "  ----------\n"
				+ "0|          \n"
				+ "1|Y         \n"
				+ "2|X         \n"
				+ "3|          \n"
				+ "4|          \n"
				+ "5|          \n"
				+ "6|          \n"
				+ "7|          \n"
				+ "8|          \n"
				+ "9|          ";
		
		StringBuilder buf = new StringBuilder();
		
		GameBoard gb = new GameBoard(10);
		buf.append("3/XWing:2/YWing\nlaunch 0 0\nlaunch 0 1 5\nlaunch 0 2 XWing\n");
		buf.append("improve 6 50\nimprove 5 120\nimprove 1 50\nimprove 3 99\n");
		BufferedReader in = new BufferedReader(new StringReader(buf.toString()));
		
		PlayerFile pf = null;
		
		pf = new PlayerFile(Side.IMPERIAL, in);
		pf.setBoard(gb);
		
		System.out.println(pf.showShip());
		assertEquals(salida1, pf.showShip());
		
		pf.initFighters(); // lee y crea y los fighteres.
		
		System.out.println(pf.showShip());
		assertEquals(salida2, pf.showShip());
		
		assertTrue(pf.nextPlay()); // primero disponible
		assertTrue(pf.nextPlay()); // el del id 5
		assertTrue(pf.nextPlay()); // primro disponible del XWing
		
		
		System.out.println(pf.showShip());
		assertEquals(salida3, pf.showShip());
		
		System.out.println(gb.toString());
		assertEquals(salida4, gb.toString());
		
		// WrongID >100 detablero
		

		assertTrue(pf.nextPlay()); // primero disponible
		assertTrue(pf.nextPlay()); // el del id 5
		assertTrue(pf.nextPlay()); // primro disponible del XWing
		assertTrue(pf.nextPlay()); // primro disponible del XWing
		
		assertEquals(salida5, pf.showShip());
		assertEquals(salida6, gb.toString());
		System.out.println(pf.showShip());
		System.out.println(gb.toString());
		
		
	}
}
