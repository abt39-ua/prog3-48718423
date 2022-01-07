//@author Alicia Baquero Tafalla
package model;

import model.exceptions.NoFighterAvailableException;

import java.util.Objects;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class Ship.
 */
public class Ship {
	
	/** The name. */
	private String name;
	
	/** The wins. */
	private int wins;
	
	/** The losses. */
	private int losses;
	
	/** The side. */
	private Side side;
	
	/** The fleet. */
	protected ArrayList<Fighter> fleet;
	
	/**
	 * Instantiates a new ship.
	 *
	 * @param name the name
	 * @param side the side
	 */
	
	
	public Ship(String name, Side side) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(side);
		wins=0;
		losses=0;
		this.side=side;
		this.name=name;
		fleet=new ArrayList<Fighter>();
		
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the side.
	 *
	 * @return the side
	 */
	public Side getSide() {
		return side;
	}
	
	/**
	 * Gets the wins.
	 *
	 * @return the wins
	 */
	public int getWins() {
		return wins;
	}
	
	/**
	 * Gets the losses.
	 *
	 * @return the losses
	 */
	public int getLosses() {
		return losses;
	}
	
	/**
	 * Gets the fleet test.
	 *
	 * @return the fleet test
	 */
	public List<Fighter> getFleetTest(){
		return fleet;
	}
	
	/**
	 * Adds the fighters.
	 *
	 * @param fd the fd
	 */
	public void addFighters(String fd) {
		Objects.requireNonNull(fd);
		String [] trozos=fd.split(":");
		
		for(String trozo : trozos) {
			String [] trozos2= trozo.split("/");
			int cuantos = Integer.parseInt(trozos2[0]);
			
			for(int i = 1; i <= cuantos; i++) {
				Fighter f = FighterFactory.createFighter(trozos2[1], this);
				if(f!=null) {
					fleet.add(f);
				}
			}
			
		}
	}
	
	/**
	 * Update results.
	 *
	 * @param r the r
	 */
	public void updateResults(int r){
		switch(r) {
			case 1:
				wins++;
				break;
			case -1:
				losses++;
				break;
		}
	}
	
	/**
	 * Gets the first available fighter.
	 *
	 * @param t the t
	 * @return the first available fighter
	 * @throws NoFighterAvailableException the no fighter available exception
	 */
	public Fighter getFirstAvailableFighter(String t) throws NoFighterAvailableException{
		Objects.requireNonNull(t);
		Fighter first=null;
		
		for(int i=0; i<fleet.size() && first==null; i++) {
			if(!fleet.get(i).isDestroyed() && (t.equals(fleet.get(i).getType()) || t.isEmpty()) && fleet.get(i).getPosition()==null) {
					first=fleet.get(i);
					i--;
			}
		}
		
		if(first==null) {
			throw new NoFighterAvailableException(t);
		}
		return first;
	}
	
	/**
	 * Purge fleet.
	 */
	public void purgeFleet() {
		for(int i=0; i<fleet.size(); i++) {
			if(fleet.get(i).isDestroyed()) {
				fleet.remove(i);
				i--;
			}
		}
	}
	
	/**
	 * Show fleet.
	 *
	 * @return the string
	 */
	public String showFleet() {
		StringBuilder sb= new StringBuilder();
		
		for(Fighter f : fleet) {
			sb.append(f);
			
			if(f.isDestroyed())
				sb.append(" (X)");
			sb.append("\n");
		}
		
		return sb.toString();
	}
	
	/**
	 * My fleet.
	 *
	 * @return the string
	 */
	public String myFleet() {
		String s= "";
		int n=-1;
		ArrayList<String> tipos= new ArrayList<>();
		ArrayList<Integer> cantidad =new ArrayList<>();
		
		for(Fighter f :fleet) {
			if(!f.isDestroyed()) {
				n=tipos.indexOf(f.getType());
				
				if(n==-1) {
					tipos.add(f.getType());
					cantidad.add(1);
				}
				
				else {
					cantidad.set(n, cantidad.get(n)+1);
				}
			}
		}
		
		for(int i=0; i<tipos.size(); i++) {
			s+=cantidad.get(i) + "/" + tipos.get(i);
			
			if(i!=tipos.size() -1)
				s+= ":";
		}
		
		return s;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		return "Ship [" + name + " " + wins + "/" + losses + "] " + myFleet();
	} 
}
