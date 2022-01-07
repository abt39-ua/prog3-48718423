	//@author Alicia Baquero Tafalla
package model;

import model.exceptions.FighterIsDestroyedException;

// TODO: Auto-generated Javadoc
/**
 * The Class Fighter.
 */
public abstract class Fighter {
	
	/** The position. */
	private Coordinate position;
	
	/** The mother ship. */
	private Ship motherShip;
	
	/** The type. */
	//private String type;
	
	/** The velocity. */
	private int velocity;
	
	/** The attack. */
	private int attack;
	
	/** The shield. */
	private int shield;
	
	/** The id. */
	private int id;
	
	/** The next id. */
	private static int nextId=1;
	
	/**
	 * Instantiates a new fighter.
	 *
	 * @param mother the mother
	 */
	protected Fighter(Ship mother) {
		//this.type=type;
		this.motherShip=mother;
		velocity=100;
		attack=80;
		shield=80;
		position=null;
		
		id=nextId;
		nextId++;
	}
	
	/**
	 * Instantiates a new fighter.
	 *
	 * @param f the f
	 */
	protected Fighter(Fighter f) {
		//type= f.type;
		motherShip= f.motherShip;
		velocity = f.velocity;
		attack = f.attack;
		shield = f.shield;
		position = f.position;
		id = f.id;
	}
	
	/**
	 * Reset next id.
	 */
	public static void resetNextId() {
		nextId=1;
	}
	
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return getClass().getSimpleName();
	}
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Gets the velocity.
	 *
	 * @return the velocity
	 */
	public int getVelocity() {
		return velocity;
	}
	
	/**
	 * Gets the attack.
	 *
	 * @return the attack
	 */
	public int getAttack() {
		return attack;
	}
	
	/**
	 * Gets the shield.
	 *
	 * @return the shield
	 */
	public int getShield() {
		return shield;
	}
	
	/**
	 * Gets the side.
	 *
	 * @return the side
	 */
	public Side getSide() {
		return motherShip.getSide();
	}
	
	/**
	 * Gets the position.
	 *
	 * @return the position
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/**
	 * Gets the mother ship.
	 *
	 * @return the mother ship
	 */
	public Ship getMotherShip() {
		return motherShip;
	}
	
	/**
	 * Sets the position.
	 *
	 * @param p the new position
	 */
	public void setPosition(Coordinate p){
		position=p;
	}
	
	/**
	 * Adds the attack.
	 *
	 * @param at the at
	 */
	public void addAttack(int at) {
		this.attack+=at;
		if(this.attack<0) {
			this.attack=0;
		}
	}
	
	/**
	 * Adds the velocity.
	 *
	 * @param vel the vel
	 */
	public void addVelocity(int vel) {
		velocity+=vel;
		if(velocity<0)
			velocity=0;
	}
	
	/**
	 * Adds the shield.
	 *
	 * @param sh the sh
	 */
	public void addShield(int sh) {
		shield+=sh;
	}
	
	/**
	 * Checks if is destroyed.
	 *
	 * @return true, if is destroyed
	 */
	public boolean isDestroyed() {
		boolean destroyed=false;
		
		if(shield<=0) {
			destroyed=true;
		}
		
		return destroyed;
	}
	
	/**
	 * Gets the damage.
	 *
	 * @param n the n
	 * @param enemy the enemy
	 * @return the damage
	 */
	public int  getDamage(int n, Fighter enemy) {
		int sol= (n * attack) / 300; 
		
		return sol;
	}
	
	/**
	 * Fight.
	 *
	 * @param enemy the enemy
	 * @return the int
	 * @throws FighterIsDestroyedException the fighter is destroyed exception
	 */
	
	/**
	 * Fight.
	 *
	 * @param enemy the enemy
	 * @return the int
	 * @throws FighterIsDestroyed the fighter is destroyed
	 */
	public int fight(Fighter enemy) throws FighterIsDestroyedException{
		int cent=0, n=0, umbral;
		
		if(this.isDestroyed()) {
			throw new FighterIsDestroyedException(this);
		}
		
		if(enemy.isDestroyed()) {
			throw new FighterIsDestroyedException(enemy);
		}
		
		else {
			while(!enemy.isDestroyed() && !isDestroyed()) {
				n=RandomNumber.newRandomNumber(100);
				umbral=(100*velocity)/(velocity+enemy.velocity);
				
				if(n<umbral) {
					shield= shield-enemy.getDamage(100-n, this);
				}
				
				else {
					enemy.shield= enemy.shield-getDamage(n, enemy);
				}
			}
			
			if(isDestroyed()) {
				cent=-1;
			}
			
			else {
				cent=1;
			}
		}
		
		return cent;
	}
	
	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	/**
	 * Equals.
	 *
	 * @param obj the obj
	 * @return true, if successful
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fighter other = (Fighter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(" + getType() + " " + id + " " + getSide() + " " + position + " {" + velocity + "," + attack + "," + shield + "})");
		
		return sb.toString();
	}
	
    /**
     * Copy.
     *
     * @return the fighter
     */
    public abstract Fighter copy();
    
    /**
     * Gets the symbol.
     *
     * @return the symbol
     */
    public abstract char getSymbol();
    
    public int getValue() {
    	return attack+velocity;
    }
}
