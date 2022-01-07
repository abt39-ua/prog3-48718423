package model.game;

import model.RandomNumber;
import model.Side;
import model.exceptions.InvalidSizeException;

public class playerRandomText2_Ruben {
	public static void main(String [] args) throws InvalidSizeException {
		RandomNumber.resetRandomCounter(); // 
		GameShip ship;
		GameBoard gb = new GameBoard(10);
		
		PlayerRandom player = new PlayerRandom(Side.IMPERIAL, 5);
		player.setBoard(gb);
		player.initFighters();
		System.out.println("generados en initFighters: " + RandomNumber.getRandomNumberList());
		
		ship = player.getGameShip();
		System.out.println(player.showShip());
		
		
		for(int i = 1; i <= 10; i++) {
			System.out.println("jugada " + i);
			System.out.println("===============");
			System.out.println(player.nextPlay());
			System.out.println(player.showShip());
			System.out.println(gb.toString());
			System.out.println(RandomNumber.getRandomNumberList());
		}
	}
}
