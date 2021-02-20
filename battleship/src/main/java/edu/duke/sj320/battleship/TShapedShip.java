package edu.duke.sj320.battleship;

import java.util.HashSet;

public class TShapedShip<T> extends BasicShip<T> {
	final String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This method generates a HashSet including all the coordinates of a T-shaped ship.
	 * @param upperLeft is the upper-left coordinate of the ship
	 * @param width is the width of the ship
	 * @param height is the height of the ship
	 * @param isFlipped is whether the ship is flipped along the axis
	 * 
	 * @return a HashSet of Coordinates of the ship
	 */
	static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height, boolean isFlipped) {
		HashSet<Coordinate> ans = new HashSet<>();
		if (width >= height) {
			if (isFlipped) { // "Down"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (j == 0) { // first row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j != 0 && i == width / 2) { // if not first row, only the middle of a row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}					
					}
				}
			}
			else { // "Up"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (j != height - 1 && i == width / 2) { // if not the last row, only the middle of a row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j == height - 1) { // last row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
					}
				}
			}
		}
		else { 
			if (isFlipped) { // "Left"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (i != width - 1 && j == height / 2) { // if not the last column, only the middle of a column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i == width - 1) { // last column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
					}
				}		
			}
			else { // "Right"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (i == 0) { // first column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i != 0 && j == height / 2) { // if not the first column, only the middle of a column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}		
					}
				}
			}
		}
		return ans;
	}
	
	public TShapedShip(String name, Coordinate upperLeft, int width, int height,
			           ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo,
			           boolean isFlipped) {
		super(makeCoords(upperLeft, width, height, isFlipped), myDisplayInfo, enemyDisplayInfo);
		this.name = name;
	}

	public TShapedShip(String name, Coordinate upperLeft, int width, int height,
			           T data, T onHit,
			           boolean isFlipped) {
		this(name, upperLeft, width, height, 
		     new SimpleShipDisplayInfo<T>(data, onHit),
		     new SimpleShipDisplayInfo<T>(null, data),
		     isFlipped);
	}

}
