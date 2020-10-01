package model;

import java.util.Arrays;

public class Coordinate {
	
	private int [] components;  	//vector de enteros
	
	public Coordinate(int a, int b) {
		components= new int[2];		//se reserva el espacio para los componentes
		components[0]=a;
		components[1]=b;
	}
	
	public Coordinate(Coordinate x) {
		components= new int[x.components.length];		//se reserva el espacio para los componentes
		for(int i=0; i<x.components.length; i++)
			components[i]= x.components[i];
	}

	public int get(int a) {
		if(a>=0 && a<components.length)		//el valor debe ser mayor que 0 y menor que la longitud del vector
			return components[a];		//se devuelve el valor en dicha posición
		
		else		//si no cumple estos requisitos llama a error
			System.err.println("Error in Coordinate.get, component" + a + "is out of range");
		
		return -1;
	}
	
	public Coordinate add (Coordinate x) {
		Coordinate a0= new Coordinate(this);		//crea un nuevo objeto a0
		
		for(int i=0; i<x.components.length; i++) {
			a0.set(i, a0.get(i)+ x.get(i));			//le introduce los valores resultantes de la suma a a0
		}
		return a0;
	}
	
	public void set(int c, int x) {		//de un objeto creado modifica el valor
		if(c>=0 && c<components.length)
				components[c]=x;
		else
			System.err.println("Error in Coordinate.set, component" + c + "is out of range");
	}
	
	public Coordinate subtract(Coordinate x) {
		Coordinate a0= new Coordinate(this);
		
		for(int i=0; i<x.components.length; i++)
			a0.set(i, a0.get(i)- x.get(i));		//devuelve los valores tras realizar la resta
		
		return a0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(components);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (!Arrays.equals(components, other.components))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "(" + components[0] + ", " + components[1] + ")";	//devuelve los valores del objeto
	}
	
	
	

}
