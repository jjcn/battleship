package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class ZShapedShipTest {

	@Test
	public void make_coords_test_1() {
		int width = 2;
		int height = 5;
		
		Coordinate c0 = new Coordinate(0, 0);
		HashSet<Coordinate> set0 = new HashSet<>();
		set0.add(new Coordinate(0, 0));
		set0.add(new Coordinate(1, 0));
		set0.add(new Coordinate(2, 0));
		set0.add(new Coordinate(2, 1));
		set0.add(new Coordinate(3, 1));
		set0.add(new Coordinate(4, 1));
		assertEquals(set0, ZShapedShip.makeCoords(c0, width, height, false)); // up
		
		Coordinate c1 = new Coordinate(1, 2);
		HashSet<Coordinate> set1 = new HashSet<>();
		set1.add(new Coordinate(1, 3));
		set1.add(new Coordinate(2, 3));
		set1.add(new Coordinate(3, 3));
		set1.add(new Coordinate(3, 2));
		set1.add(new Coordinate(4, 2));
		set1.add(new Coordinate(5, 2));
		assertEquals(set1, ZShapedShip.makeCoords(c1, width, height, true)); // down
	}

	@Test
	public void make_coords_test_2() {
		int width = 5;
		int height = 2;
		
		Coordinate c0 = new Coordinate(0, 0);
		HashSet<Coordinate> set0 = new HashSet<>();
		set0.add(new Coordinate(1, 0));
		set0.add(new Coordinate(1, 1));
		set0.add(new Coordinate(1, 2));
		set0.add(new Coordinate(0, 2));
		set0.add(new Coordinate(0, 3));
		set0.add(new Coordinate(0, 4));
		assertEquals(set0, ZShapedShip.makeCoords(c0, width, height, false)); // right
		
		Coordinate c1 = new Coordinate(1, 2);
		HashSet<Coordinate> set1 = new HashSet<>();
		set1.add(new Coordinate(1, 2));
		set1.add(new Coordinate(1, 3));
		set1.add(new Coordinate(1, 4));
		set1.add(new Coordinate(2, 4));
		set1.add(new Coordinate(2, 5));
		set1.add(new Coordinate(2, 6));
		assertEquals(set1, ZShapedShip.makeCoords(c1, width, height, true)); // left
	}

}
