package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class V1ShipFactoryTest {	
	/**
	 * Ship checking helper 
	 *
	 * @param testShip
	 * @param expectedName
	 * @param expectedLetter
	 * @param expectedLocs
	 */
	private void checkShip(Ship<Character> testShip, String expectedName,
            Character expectedLetter, Coordinate... expectedLocs) { // ... means 0 or more Coordinates
		assertEquals(expectedName, testShip.getName());
		for (Coordinate c : expectedLocs) {
			assertEquals(expectedLetter, testShip.getDisplayInfoAt(c, true));
		}
		for (Coordinate c : expectedLocs) {
			assertEquals(true ,testShip.occupiesCoordinates(c));
		}
		
	}
	
	V1ShipFactory f = new V1ShipFactory();
	Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
	Placement h0_0 = new Placement(new Coordinate(0, 0), 'H');
	
	@Test
	public void test_make_submarine() {
		Ship<Character> dst1 = f.makeSubmarine(v1_2);
		checkShip(dst1, "Submarine", 's', new Coordinate(1, 2), new Coordinate(2, 2));
		
		Ship<Character> dst2 = f.makeSubmarine(h0_0);
		checkShip(dst2, "Submarine", 's', new Coordinate(0, 0), new Coordinate(0, 1));
	}
	
	@Test
	public void test_make_destroyer() {
		Ship<Character> dst1 = f.makeDestroyer(v1_2);
		checkShip(dst1, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
		
		Ship<Character> dst2 = f.makeDestroyer(h0_0);
		checkShip(dst2, "Destroyer", 'd', new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2));
	}

	@Test
	public void test_make_battleship() {
		Ship<Character> dst1 = f.makeBattleship(v1_2);
		checkShip(dst1, "Battleship", 'b', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2), new Coordinate(4, 2));
		
		Ship<Character> dst2 = f.makeBattleship(h0_0);
		checkShip(dst2, "Battleship", 'b', new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(0, 3));
	}
	
	@Test
	public void test_make_carrier() {
		Ship<Character> dst1 = f.makeCarrier(v1_2);
		checkShip(dst1, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2), 
				 new Coordinate(4, 2), new Coordinate(5, 2), new Coordinate(6, 2));
		
		Ship<Character> dst2 = f.makeCarrier(h0_0);
		checkShip(dst2, "Carrier", 'c', new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), 
				new Coordinate(0, 3), new Coordinate(0, 4), new Coordinate(0, 5));
	}
}
