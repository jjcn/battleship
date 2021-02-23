package edu.duke.sj320.battleship;

import java.util.HashMap;

public abstract class BasicShip<T> implements Ship<T> {
	/**
	 * HashMap holding the coordinates and whether they are hit of a ship.
	 * 
	 * For a coordinate c,

	   if myPieces.get(c)  is null , c is not part of this ship;
	   if myPieces.get(c)  is false, c is part of this ship and has not been hit;
	   if myPieces.get(c)  is true , c is part of this ship and has been hit.
	 */
	HashMap<Coordinate, Boolean> myPieces;
	protected ShipDisplayInfo<T> myDisplayInfo;
	protected ShipDisplayInfo<T> enemyDisplayInfo;
	
	/**
	 * initialize myPieces to have each Coordinate in where mapped
       to false.
       
	 * @param where is an iterable of Coordinate objects
	 */
	public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
		myPieces = new HashMap<Coordinate, Boolean>();
		for (Coordinate c: where) {
			myPieces.put(c, false);
		}
		this.myDisplayInfo = myDisplayInfo;
		this.enemyDisplayInfo = enemyDisplayInfo;
	}
	
	@Override
	public Iterable<Coordinate> getCoordinates() {
		return myPieces.keySet();
	}
	
	@Override
	public boolean occupiesCoordinates(Coordinate where) {
		for (Coordinate c: myPieces.keySet()) {
			if (c.equals(where)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check if Coordinate c is part of this ship (in myPieces).
	   If not, throw an IllegalArgumentException.
	 * @param c is the coordinate to check
	 */
	protected void checkCoordinateInThisShip(Coordinate c) throws IllegalArgumentException {
		if (myPieces.get(c) == null) {
			throw new IllegalArgumentException("Given coordinate is not a part of this ship");
		}
	}
	
	@Override
	public boolean isSunk() {
		boolean isSunk = true;
		for (boolean b : myPieces.values()) {
			if (b != true) {
				isSunk = false;
			}
		}
		return isSunk;
	}

	@Override
	public void recordHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		myPieces.put(where, true);
	}

	@Override
	public boolean wasHitAt(Coordinate where) {
		checkCoordinateInThisShip(where);
		return myPieces.get(where);
	}

	@Override
	public T getDisplayInfoAt(Coordinate where, boolean myShip) {
		checkCoordinateInThisShip(where);
		boolean isHit = wasHitAt(where);
		if (myShip) {
			return myDisplayInfo.getInfo(where, isHit);
		}
		return enemyDisplayInfo.getInfo(where, isHit);
	}

}
