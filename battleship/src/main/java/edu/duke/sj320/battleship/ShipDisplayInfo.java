package edu.duke.sj320.battleship;

public interface ShipDisplayInfo<T> {
	public T getInfo(Coordinate where, boolean hit);
}
