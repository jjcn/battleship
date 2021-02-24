package edu.duke.sj320.battleship;

public class Coordinate {
	private final int row;
	private final int column;
	
	/**
	   * Constructs a Coordinate with row and column numbers
	   * 
	   * @param r is the digit for row
	   * @param c is the digit for column
	   */	
	public Coordinate(int r, int c) {
		this.row = r;
		this.column = c;
	}
	
	/**
	   * Constructs a Coordinate with letter + digit (e.g. A0)
	   * 
	   * @param descr is the coordinate string
	   *
	   * @throws IllegalArgumentException if the string length is not 2
	   *                                  or first is not letter 
	   *                                  or second is not digit  
	   */								  
	public Coordinate(String descr) {
		if (descr.length() != 2) {
			throw new IllegalArgumentException("The coordinate must be a letter (ignore case) followed by a digit (0-9)");
		}
		char rowLetter = Character.toUpperCase(descr.charAt(0));
		if (rowLetter < 'A' || rowLetter > 'Z') {
			throw new IllegalArgumentException("The coordinate must start with a letter (ignore case)");
		}
		char columnDigit = descr.charAt(1);
		if (columnDigit < '0' || columnDigit > '9') {
	    	throw new IllegalArgumentException("The coordinate must end with a digit (0-9)");
	    }
	    this.row = rowLetter - 'A';
	    this.column = Character.getNumericValue(columnDigit);
	}
	
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o.getClass().equals(getClass())) {
			Coordinate c = (Coordinate) o;
			return row == c.row && column == c.column;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
}
