package edu.duke.sj320.battleship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;

public class BattleShipBoard<T> implements Board<T> {
	private final int width;
	private final int height;
	final ArrayList<Ship<T>> myShips;
	private final PlacementRuleChecker<T> placementChecker;
	HashSet<Coordinate> enemyMisses;
	final T missInfo;
	
	 /**
	   * Constructs a BattleShipBoard with the specified width
	   * and height
	   * @param w is the width of the newly constructed board.
	   * @param h is the height of the newly constructed board.
	   * @param placementChecker is a checker for placement rules. Work like a linked list.
	   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
	   */
	public BattleShipBoard(int w, int h, PlacementRuleChecker<T> placementChecker, T missInfo) {
		if (w <= 0) {
		  throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
		}
	    if (h <= 0) {
	      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
	    }
		this.width = w;
		this.height = h;
		this.myShips = new ArrayList<>();
		this.placementChecker = placementChecker;
		this.enemyMisses = new HashSet<Coordinate>();
		this.missInfo = missInfo;	
	}
	
	public BattleShipBoard(int w, int h, T missInfo) {
	    this(w, h, new NoCollisionRuleChecker<>(new InBoundsRuleChecker<T>(null)), missInfo);
	}
		
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	/**
	 * Try adding a ship to the board.
	 * Check for validity of placement.
	 * If valid, place the ship on board and return true.
	 * If not, the ship is not placed on board and return false.
	 * 
	 * @param toAdd is the ship to put on board
	 * @return null, if placement is valid;
	 *         any non-null String: a description of what is wrong, 
	 *         suitable to show the user, if invalid
	 */
	public String tryAddShip(Ship<T> toAdd) {
		if (placementChecker.checkMyRule(toAdd, this) == null) {
			myShips.add(toAdd);
			return null;
		}
		return "Fails to add ship on board.\n";
	}
	
	/**
	 * Get the ship occupying the coordinate
	 * @param where is the coordinate to check
	 * @return the ship, if one is found at the coordinate;
	 * 		   null, if not found.
	 */
	public Ship<T> getShipAt(Coordinate where) {
		for (Ship<T> s : myShips) {
			if (s.occupiesCoordinates(where)) {
				return s;
			}
		}
		return null;
	}
	
	/**
	 * This method takes a Coordinate, and sees which (if any) Ship
       occupies that coordinate. 
       
	 * @param where is the coordinate to check
	 * @param isSelf is whether the board belongs to myself
	 * @return displayInfo it has at those coordinates, if one is found
               missInfo, if none is found and board does not belong to myself
               null, if none is found and otherwise
	 */
	protected T whatIsAt(Coordinate where, boolean isSelf) {
		for (Ship<T> s : myShips) {
			if (s.occupiesCoordinates(where)) { // if there is a ship
				return s.getDisplayInfoAt(where, isSelf);
			}
		}
		if (isSelf == false) { // if is enemy board
			if (enemyMisses.contains(where)) { // check for misses
				return missInfo;
			}
		}
		return null;
	}
	
	public T whatIsAtForSelf(Coordinate where) {
		return whatIsAt(where, true);
	}
	
	public T whatIsAtForEnemy(Coordinate where) {
	    return whatIsAt(where, false);
	}
	
	/**
	 * Shoot at a coordinate on the board.
	 * 
	 * Search for any ship that occupies Coordinate c,
	   If one is found, that Ship is "hit" by the attack 
	   and should record it, then return this ship.
	   If no ships are at this coordinate, record the miss 
	   in the enemyMisses HashSet that we just made,
	   and return null.

	 * @param c is the aim coordinate
	 * @return
	 */
	public Ship<T> fireAt(Coordinate c) {
		if (whatIsAtForSelf(c) != null) { // if a ship is hit
			for (Ship<T> s : myShips) {
				if (s.occupiesCoordinates(c)) {
					s.recordHitAt(c);
					return s;
				}
			}
		}
		enemyMisses.add(c);
		return null;
	}
	
	/**
	 * Move the ship to a new position.
	 * @param c is the Coordinate, which is part of the ship
	 * @param p is the new placement for ship
	 * @return true, if the move is valid
	 *         false, if not
	 */
	public boolean moveShip(Ship<T> ship, Placement p) { 
		// TODO
		return false;
	}
	
	/**
	 * Make a search area with a center.
	 * @param c is the center of search area
	 * @return the area to search
	 */
	private HashSet<Coordinate> makeSearchArea(Coordinate c) {
		HashSet<Coordinate> ans = new HashSet<>();
		for (int i = c.getRow() - 3; i <= c.getRow() + 3; i++) {
			for (int j = c.getColumn() - 3; i <= c.getColumn() + 3; i++) {
				if (i >= 0 && i < height && j >= 0 && j < width) { // check if in board
					Coordinate coord = new Coordinate(i, j);
					if (c.manhattan(coord) < 4) { // check distance
						ans.add(coord);
					}
				}
			}
		}
		return ans;
	}
	
	/**
	 * Get the corresponding number of squares ships occupy.
	 * @param stats is the statistics
	 * @param c is the coordinate to check
	 * @return a mapping from ship name to the number squares it occupies
	 */
	public HashMap<String, Integer> scan(HashMap<String, Integer> stats, Coordinate c) {
		for (Coordinate toSearch : makeSearchArea(c)) {
			int count = stats.get(this.getShipAt(toSearch).getName());
			stats.put(this.getShipAt(toSearch).getName(), count + 1);
		}
		return stats;
	}
	
	/**
	 * Check if the player loses a game by having all ships sunk.
	 * @return true, if lose;
	 *         false, if not.
	 */
	public boolean checkLose() {
		for (Ship<T> s : myShips) {
			if (s.isSunk() == false) {
				return false;
			}
		}
		return true;
	}
}
