package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlacementTest {
	@Test
	public void test_equals() {
		Placement p1 = new Placement("A0v");
	    Placement p2 = new Placement("A0V");
	    Placement p3 = new Placement(new Coordinate("A0"), 'V');
	    Placement p4 = new Placement("A0H");
	    assertEquals(p1, p2);
	    assertEquals(p1, p3);
	    assertEquals(p2, p3);
	    assertNotEquals(p1, p4);
	    assertNotEquals(p1, "A0v");
	}
	
	@Test
	public void test_to_string() {
		Placement p1 = new Placement("B2v");
	    Placement p2 = new Placement("B2V");
	    Placement p3 = new Placement("D4H");
	    Placement p4 = new Placement("Z9V");
		assertEquals(p1.toString(), "(1, 2), V"); 
		assertEquals(p2.toString(), "(1, 2), V");
		assertEquals(p3.toString(), "(3, 4), H");
		assertEquals(p4.toString(), "(25, 9), V");
	}
	
	@Test
	public void test_hashCode() {
		Placement p1 = new Placement("B2v");
	    Placement p2 = new Placement("B2V");
	    Placement p3 = new Placement("B2H");
	    Placement p4 = new Placement("C1V");
		assertEquals(p1.hashCode(), p2.hashCode());
		assertNotEquals(p1.hashCode(), p3.hashCode());
		assertNotEquals(p1.hashCode(), p4.hashCode());
	}
	
	@Test
	public void test_string_constructor_valid_cases() {
	    Placement p1 = new Placement("B3V");
	    Coordinate c1 = new Coordinate("B3");
	    assertEquals(c1, p1.getWhere());
	    assertEquals('V', p1.getOrientation());
	    
	    Placement p2 = new Placement("B3v");
	    Coordinate c2 = new Coordinate("B3");
	    assertEquals(c2, p2.getWhere());
	    assertEquals('V', p2.getOrientation());
	    
	    Placement p3 = new Placement("A0V");
	    Coordinate c3 = new Coordinate("A0");
	    assertEquals(c3, p3.getWhere());
	    assertEquals('V', p3.getOrientation());
	    
	    Placement p4 = new Placement("Z9H");
	    Coordinate c4 = new Coordinate("Z9");
	    assertEquals(c4, p4.getWhere());
	    assertEquals('H', p4.getOrientation());
	  }
	
	public void test_string_constructor_error_cases() {
	    assertThrows(IllegalArgumentException.class, () -> new Placement("000"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("AAA"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("50H"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("AAH"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("A0j"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("$0H"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("A&H"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("A0#"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("A"));
	    assertThrows(IllegalArgumentException.class, () -> new Placement("H"));
	  }
}
