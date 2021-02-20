package edu.duke.sj320.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T> {
	final String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This method generates a HashSet including all the coordinates of a rectangular ship.
	 * 
	 * @param upperLeft is the upper-left coordinate of the ship
	 * @param width is the width of the ship
	 * @param height is the height of the ship
	 * @return a HashSet of Coordinates of the ship
	 */
	static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height) {
		HashSet<Coordinate> ans = new HashSet<>();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
			}
		}
		return ans;
	}
	
	/**
	 * Constructor: make the right coordinate set, and pass them up to the
       BasicShip constructor.
	 * @param upperLeft is the upper-left coordinate of the ship
	 * @param width is the width of the ship
	 * @param height is the height of the ship
	 */
	public RectangleShip(String name, Coordinate upperLeft, int width, int height, 
		                 ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo) {
		super(makeCoords(upperLeft, width, height), myDisplayInfo, enemyDisplayInfo);
		this.name = name;
	}
	
	public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
		this(name, upperLeft, width, height, 
			 new SimpleShipDisplayInfo<T>(data, onHit),
			 new SimpleShipDisplayInfo<T>(null, data));
	}

	public RectangleShip(Coordinate upperLeft, T data, T onHit) {
		this("testship", upperLeft, 1, 1, data, onHit);
	}

}
