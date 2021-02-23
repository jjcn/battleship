package edu.duke.sj320.battleship;

import static org.junit.Assert.*;
import org.junit.Test;

public class CoordinateTest {
	@Test
	public void test_equals() {
		Coordinate c1 = new Coordinate(1, 2);
		Coordinate c2 = new Coordinate(1, 2);
		Coordinate c3 = new Coordinate(1, 3);
		Coordinate c4 = new Coordinate(3, 2);
		assertEquals(c1, c1); // equals should be reflexive
		assertEquals(c1, c2); // different objects but same contents
		assertNotEquals(c1, c3); // different contents
		assertNotEquals(c1, c4);
		assertNotEquals(c3, c4);
		assertNotEquals(c1, "(1, 2)"); // different types
	}

	@Test
	public void test_to_string() {
		Coordinate c1 = new Coordinate(1, 2);
		Coordinate c2 = new Coordinate(1, 2);
		Coordinate c3 = new Coordinate(1, 3);
		Coordinate c4 = new Coordinate(3, 2);
		assertEquals(c1.toString(), "(1, 2)"); 
		assertEquals(c2.toString(), "(1, 2)");
		assertEquals(c3.toString(), "(1, 3)");
		assertEquals(c4.toString(), "(3, 2)");
	}
	
	@Test
	public void test_hashCode() {
		Coordinate c1 = new Coordinate(1, 2);
		Coordinate c2 = new Coordinate(1, 2);
		Coordinate c3 = new Coordinate(0, 3);
		Coordinate c4 = new Coordinate(2, 1);
		assertEquals(c1.hashCode(), c2.hashCode());
		assertNotEquals(c1.hashCode(), c3.hashCode());
		assertNotEquals(c1.hashCode(), c4.hashCode());
	}
	
	@Test
	public void test_string_constructor_valid_cases() {
	    Coordinate c1 = new Coordinate("B3");
	    assertEquals(1, c1.getRow());
	    assertEquals(3, c1.getColumn());
	    Coordinate c2 = new Coordinate("D5");
	    assertEquals(3, c2.getRow());
	    assertEquals(5, c2.getColumn());
	    Coordinate c3 = new Coordinate("A9");
	    assertEquals(0, c3.getRow());
	    assertEquals(9, c3.getColumn());
	    Coordinate c4 = new Coordinate("Z0");
	    assertEquals(25, c4.getRow());
	    assertEquals(0, c4.getColumn());

	  }
		
	  @Test
	  public void test_string_constructor_error_cases() {
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("00"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("AA"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("@0"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("[0"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A/"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A:"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A"));
	    assertThrows(IllegalArgumentException.class, () -> new Coordinate("A12"));
	  }

	  @Test
	  public void test_manhattan() {
		  Coordinate c0 = new Coordinate(0, 0);
		  Coordinate c1 = new Coordinate(1, 0);
		  Coordinate c2 = new Coordinate(-1, -4);
		  Coordinate c3 = new Coordinate(-2, 2);
		  Coordinate c4 = new Coordinate(2, -5);
		  assertEquals(1, c0.manhattan(c1));
		  assertEquals(5, c0.manhattan(c2));
		  assertEquals(4, c0.manhattan(c3));
		  assertEquals(7, c0.manhattan(c4));

		  assertEquals(6, c1.manhattan(c2));
		  assertEquals(5, c1.manhattan(c3));
		  assertEquals(6, c1.manhattan(c4));
		  
		  assertEquals(7, c2.manhattan(c3));
		  assertEquals(4, c2.manhattan(c4));

		  assertEquals(11, c3.manhattan(c4));
	  }

}
