package edu.duke.sj320.battleship;

import java.util.HashMap;

/**
 * The interface of a board to play "Battleship".
 *
 * @param <T> is the generic describing the implementation of board
 */
public interface Board<T> {
  public int getWidth();

  public int getHeight();
  
  public String tryAddShip(Ship<T> toAdd);
  
  public Ship<T> getShipAt(Coordinate where);
  /**
   * Display the status of a coordinate as it is seen by myself
   * @param where is the coordinate to check
   * @return display info it has at the coordinate
   */
  public T whatIsAtForSelf(Coordinate where);
  
  /**
   * Display the status of a coordinate as it is seen by enemy
   * @param where is the coordinate to check
   * @return display info it has at the coordinate
   */
  public T whatIsAtForEnemy(Coordinate where);
  
  public Ship<T> fireAt(Coordinate c);
  
  public boolean checkLose();
}

