package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleShipDisplayInfoTest {

	@Test
	public void test_get_info() {
		SimpleShipDisplayInfo<Character> ssdi = new SimpleShipDisplayInfo<>('s', 'x');
		Coordinate c = new Coordinate(0, 0);
		assertEquals(new Character('x'), ssdi.getInfo(c, true));
		assertEquals(new Character('s'), ssdi.getInfo(c, false));
	}

}
