package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class InBoundsRuleCheckerTest {
	String error_over_top = "That placement is invalid: the ship goes off the top of the board.\n";
	String error_over_bottom = "That placement is invalid: the ship goes off the bottom of the board.\n";
	String error_over_left = "That placement is invalid: the ship goes off the left of the board.\n";
	String error_over_right = "That placement is invalid: the ship goes off the right of the board.\n";
	
	@Test
	public void test_check_my_rule() {
		
		InBoundsRuleChecker<Character> inbounds_rc = new InBoundsRuleChecker<>(null);
		BattleShipBoard<Character> b = new BattleShipBoard<>(5, 5, inbounds_rc, 'X');
		V1ShipFactory f = new V1ShipFactory();
		
		Placement p00h = new Placement("a0h");
		Placement p00v = new Placement("a0v");
		Placement p04v = new Placement("a4v");
		Placement p41h = new Placement("e1h");
		Placement p21v = new Placement("c1v");
		Placement p44h = new Placement("e4h");
		
		Ship<Character> sub1 = f.makeSubmarine(p44h);
		Ship<Character> sub2 = f.makeSubmarine(p21v);	
		assertEquals(error_over_right, inbounds_rc.checkMyRule(sub1, b));
		assertEquals(null, inbounds_rc.checkMyRule(sub2, b));
		
		Ship<Character> d1 = f.makeDestroyer(p00h);
		Ship<Character> d2 = f.makeDestroyer(p00v);
		Ship<Character> d3 = f.makeDestroyer(p21v);
		assertEquals(null, inbounds_rc.checkMyRule(d1, b));
		assertEquals(null, inbounds_rc.checkMyRule(d2, b));
		assertEquals(null, inbounds_rc.checkMyRule(d3, b));
		
		Ship<Character> b1 = f.makeBattleship(p21v);
		Ship<Character> b2 = f.makeBattleship(p04v);
		Ship<Character> b3 = f.makeBattleship(p41h);
		assertEquals(error_over_bottom, inbounds_rc.checkMyRule(b1, b));
		assertEquals(null, inbounds_rc.checkMyRule(b2, b));
		assertEquals(null, inbounds_rc.checkMyRule(b3, b));
		
		Ship<Character> c1 = f.makeCarrier(p00v);
		Ship<Character> c2 = f.makeCarrier(p41h);
		assertEquals(error_over_bottom, inbounds_rc.checkMyRule(c1, b));
		assertEquals(error_over_right, inbounds_rc.checkMyRule(c2, b));
		
		// test for over_left and over_top
		Placement overLeft = new Placement(new Coordinate(0, -1), 'H');
		Placement overTop = new Placement(new Coordinate(-1, 0), 'V');
		Ship<Character> sub3 = f.makeSubmarine(overLeft);
		Ship<Character> sub4 = f.makeSubmarine(overTop);	
		assertEquals(error_over_left, inbounds_rc.checkMyRule(sub3, b));
		assertEquals(error_over_top, inbounds_rc.checkMyRule(sub4, b));
	}

}
