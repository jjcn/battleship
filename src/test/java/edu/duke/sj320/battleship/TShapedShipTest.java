package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class TShapedShipTest {

	@Test
	public void make_coords_test_1() {
		Coordinate c0 = new Coordinate(0, 0);
		int width0 = 3;
		int height0 = 2;
		HashSet<Coordinate> set0 = new HashSet<>();
		set0.add(new Coordinate(0, 1));
		set0.add(new Coordinate(1, 0));
		set0.add(new Coordinate(1, 1));
		set0.add(new Coordinate(1, 2));
		assertEquals(set0, TShapedShip.makeCoords(c0, width0, height0, false)); //up
		
		Coordinate c1 = new Coordinate(1, 2);
		int width1 = 3;
		int height1 = 2;
		HashSet<Coordinate> set1 = new HashSet<>();
		set1.add(new Coordinate(1, 2));
		set1.add(new Coordinate(1, 3));
		set1.add(new Coordinate(1, 4));
		set1.add(new Coordinate(2, 3));
		assertEquals(set1, TShapedShip.makeCoords(c1, width1, height1, true)); // down
	}

	@Test
	public void make_coords_test_2() {
		Coordinate c0 = new Coordinate(0, 0);
		int width0 = 2;
		int height0 = 3;
		HashSet<Coordinate> set0 = new HashSet<>();
		set0.add(new Coordinate(0, 0));
		set0.add(new Coordinate(1, 0));
		set0.add(new Coordinate(1, 1));
		set0.add(new Coordinate(2, 0));
		assertEquals(set0, TShapedShip.makeCoords(c0, width0, height0, false)); // right
		
		Coordinate c1 = new Coordinate(1, 2);
		int width1 = 2;
		int height1 = 3;
		HashSet<Coordinate> set1 = new HashSet<>();
		set1.add(new Coordinate(2, 2));
		set1.add(new Coordinate(1, 3));
		set1.add(new Coordinate(2, 3));
		set1.add(new Coordinate(3, 3));
		assertEquals(set1, TShapedShip.makeCoords(c1, width1, height1, true)); // left
	}
}
