package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class V2ShipFactoryTest {

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
	
	V2ShipFactory f = new V2ShipFactory();
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

	Placement u1_2 = new Placement(new Coordinate(1, 2), 'U');
	Placement d0_0 = new Placement(new Coordinate(0, 0), 'D');
	Placement r0_0 = new Placement(new Coordinate(0, 0), 'R');
	Placement l0_0 = new Placement(new Coordinate(0, 0), 'L');
	
	@Test
	public void test_make_battleship() {
		Ship<Character> dst1 = f.makeBattleship(u1_2);
		checkShip(dst1, "Battleship", 'b', new Coordinate(1, 3), new Coordinate(2, 2), new Coordinate(2, 3), new Coordinate(2, 4));
		
		Ship<Character> dst2 = f.makeBattleship(d0_0);
		checkShip(dst2, "Battleship", 'b', new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), new Coordinate(1, 1));
		
		Ship<Character> dst3 = f.makeBattleship(r0_0);
		checkShip(dst3, "Battleship", 'b', new Coordinate(0, 0), new Coordinate(1, 0), new Coordinate(2, 0), new Coordinate(1, 1));
		
		Ship<Character> dst4 = f.makeBattleship(l0_0);
		checkShip(dst4, "Battleship", 'b', new Coordinate(1, 0), new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(2, 1));
	}
	
	@Test
	public void test_make_carrier() {
		Ship<Character> dst1 = f.makeCarrier(u1_2);
		checkShip(dst1, "Carrier", 'c', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2), 
				 new Coordinate(3, 3), new Coordinate(4, 3), new Coordinate(5, 3));
		
		Ship<Character> dst2 = f.makeCarrier(d0_0);
		checkShip(dst2, "Carrier", 'c', new Coordinate(0, 1), new Coordinate(1, 1), new Coordinate(2, 1), 
				new Coordinate(2, 0), new Coordinate(3, 0), new Coordinate(4, 0));
		
		Ship<Character> dst3 = f.makeCarrier(r0_0);
		checkShip(dst3, "Carrier", 'c', new Coordinate(1, 0), new Coordinate(1, 1), new Coordinate(1, 2), 
				new Coordinate(0, 2), new Coordinate(0, 3), new Coordinate(0, 4));
		
		Ship<Character> dst4 = f.makeCarrier(l0_0);
		checkShip(dst4, "Carrier", 'c', new Coordinate(0, 0), new Coordinate(0, 1), new Coordinate(0, 2), 
				new Coordinate(1, 2), new Coordinate(1, 3), new Coordinate(1, 4));
	}
}
