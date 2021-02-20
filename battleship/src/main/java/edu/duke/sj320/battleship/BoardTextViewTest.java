package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import org.junit.Test;

public class BoardTextViewTest {
	@Test
	  public void test_invalid_board_size() {
	    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20, 'X');
	    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27, 'X');
	    //you should write two assertThrows here
	    //assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(11, 20));
	    //assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard(10, 27));
	  }
	
	/**
	 * Helper that checks if an empty board meets our expected one 
	 * @param w is the width of board
	 * @param h is the height of board
	 * @param expectedHeader is the expected header of board
	 * @param expectedBody is the expected body of board
	 */
	private void emptyBoardHelper(int w, int h, String expectedHeader, String expectedBody){
	    Board<Character> b = new BattleShipBoard<Character>(w, h, 'X');
	    BoardTextView view = new BoardTextView(b);
	    assertEquals(expectedHeader, view.makeHeader());
	    String expected = expectedHeader + expectedBody + expectedHeader;
	    assertEquals(expected, view.displayMyOwnBoard());
	  }
	
	@Test
	public void test_display_empty_2by2() {
		String expectedHeader = "  0|1\n";
		String expectedBody = "A  |  A\n" + 
							  "B  |  B\n";
		emptyBoardHelper(2, 2, expectedHeader, expectedBody);
	}
	
	@Test
	public void test_display_empty_3by2() {
		String expectedHeader = "  0|1|2\n";
		String expectedBody = "A  | |  A\n" + 
							  "B  | |  B\n";
		emptyBoardHelper(3, 2, expectedHeader, expectedBody);
	}
	
	@Test
	public void test_display_empty_3by5() {
		String expectedHeader = "  0|1|2\n";
		String expectedBody = "A  | |  A\n"+
						      "B  | |  B\n"+
						      "C  | |  C\n"+
						      "D  | |  D\n"+
						      "E  | |  E\n";
		emptyBoardHelper(3, 5, expectedHeader, expectedBody);
	}
	
	/**
	 * Helper that checks if a board with ships meets our expected one 
	 * @param w is the width of board
	 * @param h is the height of board
	 * @param expectedHeader is the expected header of board
	 * @param expectedBody is the expected body of board
	 */
	private void boardHelper(Board<Character> b, Ship<Character> ship, String expectedHeader, String expectedBody){
	    b.tryAddShip(ship);
		BoardTextView view = new BoardTextView(b);
	    assertEquals(expectedHeader, view.makeHeader());
	    String expected = expectedHeader + expectedBody + expectedHeader;
	    assertEquals(expected, view.displayMyOwnBoard());
	}
	
	@Test
	public void test_display_1by1() {
		Board<Character> b = new BattleShipBoard<Character>(1, 1, 'X');
		String expectedHeader = "  0\n";
		
		RectangleShip<Character> bs1 = new RectangleShip<>(new Coordinate(0, 0), 's', '*');
		String expectedBody = "A s A\n";
		boardHelper(b, bs1, expectedHeader, expectedBody);
	}
	
	@Test
	public void test_display_3by4() {
		Board<Character> b = new BattleShipBoard<Character>(3, 4, 'X');
		String expectedHeader = "  0|1|2\n";
		
		// 1st ship
		RectangleShip<Character> bs1 = new RectangleShip<>(new Coordinate(1, 1), 's', '*');
		String expectedBody = "A  | |  A\n"+
						      "B  |s|  B\n"+
						      "C  | |  C\n"+
						      "D  | |  D\n";	
		boardHelper(b, bs1, expectedHeader, expectedBody);
		// 2nd ship
		RectangleShip<Character> bs2 = new RectangleShip<>(new Coordinate(1, 2), 's', '*');
		expectedBody =        "A  | |  A\n"+
						      "B  |s|s B\n"+
						      "C  | |  C\n"+
						      "D  | |  D\n";	
		boardHelper(b, bs2, expectedHeader, expectedBody);
		// 3rd ship
		RectangleShip<Character> bs3 = new RectangleShip<>(new Coordinate(3, 2), 's', '*');
		expectedBody =        "A  | |  A\n"+
						      "B  |s|s B\n"+
						      "C  | |  C\n"+
						      "D  | |s D\n";	
		boardHelper(b, bs3, expectedHeader, expectedBody);
	}
	
	@Test
	public void test_display_enemy_board() {
		Board<Character> b = new BattleShipBoard<Character>(4, 3, 'X');
		V2ShipFactory f = new V2ShipFactory();
		Ship<Character> s1 = f.makeSubmarine(new Placement("b0h"));
		Ship<Character> d1 = f.makeDestroyer(new Placement("a3v"));
		b.tryAddShip(s1);
		b.tryAddShip(d1);
		
		String myView =
			      "  0|1|2|3\n" +
			      "A  | | |d A\n" +
			      "B s|s| |d B\n" +
			      "C  | | |d C\n" +
			      "  0|1|2|3\n";
			    //make sure we laid things out the way we think we did.
		assertEquals(myView, new BoardTextView(b).displayMyOwnBoard());
		
		b.fireAt(new Coordinate(0, 0));
		b.fireAt(new Coordinate(1, 0));
		b.fireAt(new Coordinate(2, 3));	
		String my_view_after_fire =
			      "  0|1|2|3\n" +
			      "A  | | |d A\n" +
			      "B *|s| |d B\n" +
			      "C  | | |* C\n" +
			      "  0|1|2|3\n";
			    //make sure we laid things out the way we think we did.
		assertEquals(my_view_after_fire, new BoardTextView(b).displayMyOwnBoard());
		String enemy_view_after_fire =
			      "  0|1|2|3\n" +
			      "A X| | |  A\n" +
			      "B s| | |  B\n" +
			      "C  | | |d C\n" +
			      "  0|1|2|3\n";
			    //make sure we laid things out the way we think we did.
		assertEquals(enemy_view_after_fire, new BoardTextView(b).displayEnemyBoard());
	}
	
	@Test
	public void test_displayMyBoardWithEnemyNextToIt() {
		Board<Character> b1 = new BattleShipBoard<Character>(4, 3, 'X');
		V2ShipFactory f = new V2ShipFactory();
		Ship<Character> s1 = f.makeSubmarine(new Placement("b0h"));
		Ship<Character> d1 = f.makeDestroyer(new Placement("a3v"));
		b1.tryAddShip(s1);
		b1.tryAddShip(d1);
		b1.fireAt(new Coordinate(0, 0));
		b1.fireAt(new Coordinate(1, 0));
		b1.fireAt(new Coordinate(2, 3));	
		Board<Character> b2 = b1;
		
		String myHeader = "Your ocean";
		String enemyHeader = "Player B's ocean";
		String expected =
				 "     Your ocean               Player B's ocean\n"+
				 "  0|1|2|3                    0|1|2|3\n" +
				 "A  | | |d A                A X| | |  A\n" +
				 "B *|s| |d B                B s| | |  B\n" +
				 "C  | | |* C                C  | | |d C\n" +
				 "  0|1|2|3                    0|1|2|3\n";
		assertEquals(expected, new BoardTextView(b1).displayMyBoardWithEnemyNextToIt(
				new BoardTextView(b2), myHeader, enemyHeader));
	}
	
}
