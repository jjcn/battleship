package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.HashSet;

import org.junit.Test;

public class RectangleShipTest {
	
	@Test
	public void make_coords_test() {
		Coordinate c1 = new Coordinate(1, 2);
		int width1 = 1;
		int height1 = 3;
		HashSet<Coordinate> set1 = new HashSet<>();
		set1.add(new Coordinate(1, 2));
		set1.add(new Coordinate(2, 2));
		set1.add(new Coordinate(3, 2));
		assertEquals(set1, RectangleShip.makeCoords(c1, width1, height1));
		
		Coordinate c2 = new Coordinate(1, 2);
		int width2 = 3;
		int height2 = 1;
		HashSet<Coordinate> set2 = new HashSet<>();
		set2.add(new Coordinate(1, 2));
		set2.add(new Coordinate(1, 3));
		set2.add(new Coordinate(1, 4));
		assertEquals(set2, RectangleShip.makeCoords(c2, width2, height2));
	}
	
	@Test
	public void test_get_coordinates() {
		Coordinate c = new Coordinate(0, 0);
		int width = 1;
		int height = 2;
		RectangleShip<Character> rs = new RectangleShip<>("ship", c, width, height, 's', '*');
		HashSet<Coordinate> expectedCoordinates = new HashSet<>();
		expectedCoordinates.add(new Coordinate(0, 0));
		expectedCoordinates.add(new Coordinate(1, 0));
		assertEquals(expectedCoordinates, rs.getCoordinates());
	}
	
	@Test
	public void test_occupies_coordinates1() {
		Coordinate c = new Coordinate(1, 0);
		int width = 3;
		int height = 2;
		RectangleShip<Character> rs = new RectangleShip<>("ship", c, width, height, 's', '*');
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(1, 0)));
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(1, 1)));
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(1, 2)));
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(2, 0)));
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(2, 1)));
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(2, 2)));
	}
	
	@Test
	public void test_occupies_coordinates2() {
		Coordinate c = new Coordinate(0, 1);
		int width = 1;
		int height = 1;
		RectangleShip<Character> rs = new RectangleShip<>("ship", c, width, height, 's', '*');
		assertEquals(true, rs.occupiesCoordinates(new Coordinate(0, 1)));
		assertNotEquals(true, rs.occupiesCoordinates(new Coordinate(0, 0)));
		assertNotEquals(true, rs.occupiesCoordinates(new Coordinate(0, 2)));
	}
	
	@Test
	public void test_is_sunk() {
		RectangleShip<Character> rs = new RectangleShip<>("ship", new Coordinate(0, 0), 1, 3, 's', '*');
		assertEquals(false, rs.isSunk());
		rs.recordHitAt(new Coordinate(0, 0));
		assertEquals(false, rs.isSunk());
		rs.recordHitAt(new Coordinate(1, 0));
		rs.recordHitAt(new Coordinate(2, 0));
		assertEquals(true, rs.isSunk());
	}
	
	@Test
	public void test_record_hit_at() {
		RectangleShip<Character> rs = new RectangleShip<>("ship", new Coordinate(0, 0), 1, 3, 's', '*');
		rs.recordHitAt(new Coordinate(0, 0));
		rs.recordHitAt(new Coordinate(1, 0));
		//assertThrows(IllegalArgumentException.class, rs.recordHitAt(new Coordinate(0, 1)));
	}
	
	@Test
	public void test_was_hit_at() {
		RectangleShip<Character> rs = new RectangleShip<>("ship", new Coordinate(0, 0), 1, 3, 's', '*');
		rs.recordHitAt(new Coordinate(0, 0));
		assertEquals(true, rs.wasHitAt(new Coordinate(0, 0)));
		assertEquals(false, rs.wasHitAt(new Coordinate(1, 0)));
		assertEquals(false, rs.wasHitAt(new Coordinate(2, 0)));
		//assertThrows(IllegalArgumentException.class, rs.wasHitAt(new Coordinate(0, 1)));
	}
	
	public void test_get_display_info_at() {
		RectangleShip<Character> rs = new RectangleShip<>("ship", new Coordinate(0, 0), 1, 3, 's', '*');
		rs.recordHitAt(new Coordinate(0, 0));
		assertEquals(new Character('*'), rs.getDisplayInfoAt(new Coordinate(0, 0), true));
		assertEquals(new Character('s'), rs.getDisplayInfoAt(new Coordinate(1, 0), true));
		assertEquals(new Character('s'), rs.getDisplayInfoAt(new Coordinate(0, 0), false));
		assertEquals(null, rs.getDisplayInfoAt(new Coordinate(1, 0), false));
		//assertThrows(IllegalArgumentException.class, rs.getDisplayInfoAt(new Coordinate(0, 1));
	}
	
	public void test_get_name() {
		RectangleShip<Character> rs1 = new RectangleShip<>("ship", new Coordinate(0, 0), 1, 3, 's', '*');
		assertEquals("ship", rs1.getName());
		RectangleShip<Character> rs2 = new RectangleShip<>("", new Coordinate(0, 0), 5, 5, 'd', '*');
		assertEquals("", rs2.getName());
	}
}
