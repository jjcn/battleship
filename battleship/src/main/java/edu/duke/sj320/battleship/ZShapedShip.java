package edu.duke.sj320.battleship;

import java.util.HashSet;

public class ZShapedShip<T> extends BasicShip<T> {
	final String name;
	
	@Override
	public String getName() {
		return name;
	}
	
	/**
	 * This method generates a HashSet including all the coordinates of a Z-shaped ship.
	 * @param upperLeft
	 * @param width
	 * @param height
	 * @param isFlipped
	 * @return
	 */
	static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height, boolean isFlipped) {
		HashSet<Coordinate> ans = new HashSet<>();
		if (width <= height) { 
			if (isFlipped) { // "Down"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (i == 0 && j >= height / 2) { // first column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i != 0 && i != width - 1 && j == height / 2) { // middle columns
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i == width - 1 && j <= height / 2) { // last column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
					}
				}
			}
			else { // "Up"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (i == 0 && j <= height / 2) { // first column
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i != 0 && i != width - 1 && j == height / 2) { // middle columns
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (i == width - 1 && j >= height / 2) { // last column
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
						if (j == 0 && i <= width / 2) { // first row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j != 0 && j != height - 1 && i == width / 2) { // middle rows
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j == height - 1 && i >= width / 2) { // last row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
					}
				}
			}
			else { // "Right"
				for (int i = 0; i < width; i++) {
					for (int j = 0; j < height; j++) {
						if (j == 0 && i >= width / 2) { // first row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j != 0 && j != height - 1 && i == width / 2) { // middle rows
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
						if (j == height - 1 && i <= width / 2) { // last row
							ans.add(new Coordinate(upperLeft.getRow() + j, upperLeft.getColumn() + i));
						}
					}
				}
			}
		}
		return ans;
	}
	
	public ZShapedShip(String name, Coordinate upperLeft, int width, int height,
			           ShipDisplayInfo<T> myDisplayInfo, ShipDisplayInfo<T> enemyDisplayInfo,
			           boolean isFlipped) {
		super(makeCoords(upperLeft, width, height, isFlipped), myDisplayInfo, enemyDisplayInfo);
		this.name = name;
	}

	public ZShapedShip(String name, Coordinate upperLeft, int width, int height,
					  T data, T onHit,
					  boolean isFlipped) {
		this(name, upperLeft, width, height, 
		     new SimpleShipDisplayInfo<T>(data, onHit),
		     new SimpleShipDisplayInfo<T>(null, data),
		     isFlipped);
	}

}
