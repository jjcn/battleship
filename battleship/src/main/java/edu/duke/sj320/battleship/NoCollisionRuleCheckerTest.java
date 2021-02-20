package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class NoCollisionRuleCheckerTest {
	String error_collision = "That placement is invalid: the ship overlaps another ship.\n";
	
	@Test
	public void test_no_collision_rule_checker() {
		
		NoCollisionRuleChecker<Character> nocollision_rc = new NoCollisionRuleChecker<>(null);
		BattleShipBoard<Character> b = new BattleShipBoard<>(6, 6, nocollision_rc, 'X');
		b.tryAddShip(new RectangleShip<Character>(new Coordinate(2, 2), 't', '*'));
		b.tryAddShip(new RectangleShip<Character>(new Coordinate(5, 5), 't', '*'));
		V1ShipFactory f = new V1ShipFactory();
		
		Placement p11h = new Placement("b1h");
		Placement p11v = new Placement("b1v");
		Placement p20h = new Placement("c0h");
		Placement p30h = new Placement("d0h");
		Placement p02v = new Placement("a2v");
		Placement p32v = new Placement("d2v");
		Placement p23h = new Placement("c3h");
		Placement p45v = new Placement("e5v");
		
		Ship<Character> s1 = f.makeSubmarine(p11h);
		Ship<Character> s2 = f.makeSubmarine(p11v);
		assertEquals(null, nocollision_rc.checkMyRule(s1, b));
		assertEquals(null, nocollision_rc.checkMyRule(s2, b));
		
		Ship<Character> s3 = f.makeSubmarine(p20h);
		Ship<Character> s4 = f.makeDestroyer(p20h);
		Ship<Character> s5 = f.makeCarrier(p20h);
		assertEquals(null, nocollision_rc.checkMyRule(s3, b));
		assertEquals(error_collision, nocollision_rc.checkMyRule(s4, b));
		assertEquals(error_collision, nocollision_rc.checkMyRule(s5, b));
		
		Ship<Character> s6 = f.makeSubmarine(p30h);
		Ship<Character> s7 = f.makeDestroyer(p30h);
		assertEquals(null, nocollision_rc.checkMyRule(s6, b));
		assertEquals(null, nocollision_rc.checkMyRule(s7, b));
		
		Ship<Character> s8 = f.makeSubmarine(p02v);
		Ship<Character> s9 = f.makeDestroyer(p02v);
		assertEquals(null, nocollision_rc.checkMyRule(s8, b));
		assertEquals(error_collision, nocollision_rc.checkMyRule(s9, b));
		
		Ship<Character> s10 = f.makeBattleship(p32v);
		Ship<Character> s11 = f.makeBattleship(p23h);
		assertEquals(null, nocollision_rc.checkMyRule(s10, b));
		assertEquals(null, nocollision_rc.checkMyRule(s11, b));
		
		Ship<Character> s12 = f.makeSubmarine(p45v);
		Ship<Character> s13 = f.makeDestroyer(p45v);
		assertEquals(error_collision, nocollision_rc.checkMyRule(s12, b));
		assertEquals(error_collision, nocollision_rc.checkMyRule(s13, b));
	}

}
