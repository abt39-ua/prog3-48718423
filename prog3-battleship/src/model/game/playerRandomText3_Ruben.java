package model.game;

import model.RandomNumber;
import model.Side;
import model.exceptions.InvalidSizeException;

public class playerRandomText3_Ruben {
	public static void main(String [] args) throws InvalidSizeException {
		RandomNumber.resetRandomCounter(); // 
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
	}
}
