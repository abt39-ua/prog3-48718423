package model.game.exceptions;

public class WrongFighterIdException extends Exception{
	private int id;
	public WrongFighterIdException(int id) {
		this.id = id;
	}
	public String getMessage() {
		return "ERROR: " + id; 
	}
}