package model.game;

import static org.junit.Assert.*;

import org.junit.Test;

import model.RandomNumber;
import model.Side;

public class PlayerRandomTestA12 {

	@Test
	public void test() {
		String s1 = "Ship [PlayerRandom IMPERIAL Ship 0/0] 1/TIEFighter\n"
				+ "(TIEFighter 1 IMPERIAL null {110,85,70})\n";
		
		String s2 = "Ship [PlayerRandom IMPERIAL Ship 0/0] \n";
		
		String s3 = "Ship [PlayerRandom IMPERIAL Ship 0/0] 4/TIEFighter:1/TIEBomber:3/TIEInterceptor\n"
				+ "(TIEFighter 2 IMPERIAL null {110,85,70})\n"
				+ "(TIEFighter 3 IMPERIAL null {110,85,70})\n"
				+ "(TIEFighter 4 IMPERIAL null {110,85,70})\n"
				+ "(TIEFighter 5 IMPERIAL null {110,85,70})\n"
				+ "(TIEBomber 6 IMPERIAL null {70,30,115})\n"
				+ "(TIEInterceptor 7 IMPERIAL null {145,85,60})\n"
				+ "(TIEInterceptor 8 IMPERIAL null {145,85,60})\n"
				+ "(TIEInterceptor 9 IMPERIAL null {145,85,60})\n";
		
		RandomNumber.resetRandomCounter(); // 
		GameShip ship;
		
		PlayerRandom player = new PlayerRandom(Side.IMPERIAL, 2);
		player.initFighters();
		ship = player.getGameShip();
		System.out.println(player.showShip());
		assertEquals(s1, player.showShip());	
		
		PlayerRandom player2 = new PlayerRandom(Side.IMPERIAL, 2);
		player2.initFighters();
		ship = player2.getGameShip();
		System.out.println(player2.showShip());
		assertEquals(s2, player2.showShip());
		
		PlayerRandom player3 = new PlayerRandom(Side.IMPERIAL, 5);
		player3.initFighters();
		ship = player3.getGameShip();
		System.out.println(player3.showShip());
		assertEquals(s3, player3.showShip());
		
	}

}
