package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class BattleShipBoardTest {
	@Test
	public void test_width_and_height() {
		Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
	    assertEquals(10, b1.getWidth());
	    assertEquals(20, b1.getHeight());
	}
	
	/**
	 * Helper that checks if all coordinates in the BattleShipBoard have the right value.
	 * @param b
	 * @param expected
	 */
	private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expected) {
		for (int i = 0; i < b.getWidth(); i++) {
			for (int j = 0; j < b.getHeight(); j++) {
				assertEquals(expected[i][j], b.whatIsAtForSelf(new Coordinate(i, j)));
			}
		}
	}
	
	@Test
	public void test_empty_board() {
		BattleShipBoard<Character> b = new BattleShipBoard<>(3, 3, 'X');
		Character[][] expected = {{null, null, null}, {null, null, null}, {null, null, null}};
		checkWhatIsAtBoard(b, expected);
	}
	
	@Test
	public void test_board_with_ships() {
		BattleShipBoard<Character> b = new BattleShipBoard<>(3, 3, 'X');
		
		RectangleShip<Character> bs1 = new RectangleShip<>(new Coordinate(0, 0), 's', '*');
		assertEquals(null, b.tryAddShip(bs1));
		Character[][] expected1 = {{'s', null, null}, {null, null , null}, {null, null, null}};
		checkWhatIsAtBoard(b, expected1);
		
		RectangleShip<Character> bs2 = new RectangleShip<>(new Coordinate(2, 0), 's', '*');
		assertEquals(null, b.tryAddShip(bs2));
		Character[][] expected2 = {{'s', null, null}, {null, null , null}, {'s', null, null}};
		checkWhatIsAtBoard(b, expected2);
		
		RectangleShip<Character> bs3 = new RectangleShip<>(new Coordinate(1, 2), 's', '*');
		assertEquals(null, b.tryAddShip(bs3));
		Character[][] expected3 = {{'s', null, null}, {null, null , 's'}, {'s', null, null}};
		checkWhatIsAtBoard(b, expected3);
	}
	
	/**
	 * test_board_with_ships_with_rule_checker() helper
	 * @return
	 */
	public Board<Character> makeTestBoard() {
		BattleShipBoard<Character> b = new BattleShipBoard<>(6, 6, 'X');
		V1ShipFactory f = new V1ShipFactory();
		b.tryAddShip(new RectangleShip<>(new Coordinate(2, 2), 't', '*'));
		b.tryAddShip(f.makeBattleship(new Placement("b4v")));
		return b;
	}
	
	@Test
	public void test_board_with_ships_with_rule_checker() {
		String try_add_fail = "Fails to add ship on board.\n";
		V1ShipFactory f = new V1ShipFactory();
		
		Placement p10h = new Placement("b0h");			
		Ship<Character> s1 = f.makeSubmarine(p10h);
		Ship<Character> s2 = f.makeBattleship(p10h);
		Ship<Character> s3 = f.makeCarrier(p10h);
		assertEquals(null, makeTestBoard().tryAddShip(s1));
		assertEquals(null, makeTestBoard().tryAddShip(s2));
		assertEquals(try_add_fail, makeTestBoard().tryAddShip(s3));
		
		Placement p20v = new Placement("c0v");	
		Ship<Character> s4 = f.makeBattleship(p20v);
		Ship<Character> s5 = f.makeCarrier(p20v);
		assertEquals(null, makeTestBoard().tryAddShip(s4));
		assertEquals(try_add_fail, makeTestBoard().tryAddShip(s5)); // can't check inbounds rule
		
		Placement p42h = new Placement("e2h");	
		Ship<Character> s6 = f.makeBattleship(p42h);
		Ship<Character> s7 = f.makeCarrier(p42h);
		assertEquals(try_add_fail, makeTestBoard().tryAddShip(s6));
		assertEquals(try_add_fail, makeTestBoard().tryAddShip(s7));
	}
	
	@Test  
	public void test_fire_at() {
		BattleShipBoard<Character> b = new BattleShipBoard<>(6, 6, 'X');
		V1ShipFactory f = new V1ShipFactory();
		Ship<Character> s1 = new RectangleShip<>(new Coordinate(2, 2), 't', '*');
		Ship<Character> s2 = f.makeBattleship(new Placement("b4v"));
		b.tryAddShip(s1);
		b.tryAddShip(s2);
		assertEquals(null, b.fireAt(new Coordinate(1, 3)));
		assertSame(s1, b.fireAt(new Coordinate(2, 2)));
		assertTrue(s1.isSunk());
		assertSame(s2, b.fireAt(new Coordinate(1, 4)));
		assertFalse(s2.isSunk());
	}
	
	@Test
	public void test_what_is_at_for_enemy() {
		Board<Character> b = makeTestBoard();
		b.fireAt(new Coordinate(2, 2));
		b.fireAt(new Coordinate(2, 3));
		b.fireAt(new Coordinate(3, 4));
		assertEquals(new Character('t'), b.whatIsAtForEnemy(new Coordinate(2, 2))); // hit
		assertEquals(new Character('X'), b.whatIsAtForEnemy(new Coordinate(2, 3))); //miss
		assertEquals(new Character('b'), b.whatIsAtForEnemy(new Coordinate(3, 4))); //hit
		assertEquals(null, b.whatIsAtForEnemy(new Coordinate(4, 4))); // not hit
	}
	
	/*
	@Test
	  public void test_invalid_dimensions() {
	    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
	    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
	    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
	    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
	  }
	 */
}
