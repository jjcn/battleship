package edu.duke.sj320.battleship;

public class Placement {
	final Coordinate where;
	final char orientation;
	
	/**
	   * Constructs a Coordinate with a Coordinate object and a character for orientation
	   * 
	   * @param where is the Coordinate object
	   * @param orientation is the character for orientation
	   */	
	public Placement(Coordinate where, char orientation) {
		this.where = where;
		this.orientation = orientation;
	}
	
	/**
	   * Constructs a Coordinate with letter + digit + letter (e.g. A0V)
	   * 
	   * @param descr is the placement string
	   *
	   * @throws IllegalArgumentException if the string length is not 3
	   *								  or first not letter
	   *								  or second not digit
	   *                                  does not check orientation letter
	   */		
	public Placement(String descr) throws IllegalArgumentException {
		if (descr.length() != 3) {
			throw new IllegalArgumentException("The placement must be a letter (ignore case) followed by a digit (0-9) " +
												"and then an orientation (ignore case)");
		}
		if (descr.length() == 3) {
			if (Character.isLetter(descr[0]) == false || Character.isDigit(descr[1]) == false)
			throw new IllegalArgumentException("The placement must be a letter (ignore case) followed by a digit (0-9) " +
												"and then an orientation (ignore case)");
		}
		char orientationLetter = Character.toUpperCase(descr.charAt(2));
	    this.where = new Coordinate(descr.substring(0, 2));
	    this.orientation = orientationLetter;
	}

	public Coordinate getWhere() {
		return where;
	}

	public char getOrientation() {
		return orientation;
	}

	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(getClass())) {
			Placement p = (Placement) o;
			return where.equals(p.where) && orientation == p.orientation;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return where.toString() + ", " + orientation;
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
