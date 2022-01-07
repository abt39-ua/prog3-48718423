package model;

import java.util.Objects;
/*
import model.fighters.AWing;
import model.fighters.TIEBomber;
import model.fighters.TIEFighter;
import model.fighters.TIEInterceptor;
import model.fighters.XWing;
import model.fighters.YWing;
*/
// TODO: Auto-generated Javadoc
/**
 * A factory for creating Fighter objects.
 */
public class FighterFactory {
	
	/**
	 * Creates a new Fighter object.
	 *
	 * @param type the type
	 * @param ship the ship
	 * @return the fighter
	 */
	public static Fighter createFighter(String type, Ship ship) {
		Objects.requireNonNull(type);
		Objects.requireNonNull(ship);
		try {
			return  (Fighter) Class.forName("model.fighters." + type).getConstructor(Ship.class).newInstance(ship);
		} catch (Exception e) {
			return null;
		}	
	}
	/**
	 * Creates a new Fighter object.
	 *
	 * @param type the type
	 * @param mother the mother
	 * @return the fighter
	 */
	
	/*
	public static Fighter createFighter(String type, Ship mother) {
		Fighter f = null;
		switch(type) {
		case "TIEFighter":
				f = new TIEFighter(mother);
			break;
		case "TIEBomber":
				f = new TIEBomber(mother);
				break;
		case "TIEInterceptor":
				f = new TIEInterceptor(mother);
				break;
		case "AWing":
				f = new AWing(mother);
				break;
		case "XWing":
				f = new XWing(mother);
				break;
		case "YWing":
				f = new YWing(mother);
				break;
		}
		return f;
	}
	*/
}
