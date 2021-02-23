package edu.duke.sj320.battleship;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;

import org.junit.Test;

public class TextPlayerTest {
	String error_over_top = "That placement is invalid: the ship goes off the top of the board.\n";
	String error_over_bottom = "That placement is invalid: the ship goes off the bottom of the board.\n";
	String error_over_left = "That placement is invalid: the ship goes off the left of the board.\n";
	String error_over_right = "That placement is invalid: the ship goes off the right of the board.\n";
	String error_collision = "That placement is invalid: the ship overlaps another ship.\n";
	
	private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
	    BufferedReader input = new BufferedReader(new StringReader(inputData));
	    PrintStream output = new PrintStream(bytes, true);
	    Board<Character> board = new BattleShipBoard<Character>(w, h, 'X');
	    V1ShipFactory shipFactory = new V1ShipFactory();
	    return new TextPlayer("A", board, input, output, shipFactory);
	  }

	
    public void test_read_placement() throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
	    
	    String prompt = "Player A where do you want to place a Destroyer?";
		Placement[] expected = new Placement[3];
		expected[0] = new Placement(new Coordinate(1, 2), 'V');
		expected[1] = new Placement(new Coordinate(2, 8), 'H');
		expected[2] = new Placement(new Coordinate(0, 4), 'V');
		
		for (int i = 0; i < expected.length; i++) {
			Placement p = player.readPlacement(prompt);
			assertEquals(p, expected[i]); // did we get the right Placement back
			assertEquals(prompt + "\n", bytes.toString()); // should have
															// printed prompt
															// and newline
			bytes.reset(); // clear out bytes for next time around
		}
	}
    
    
	public void test_do_one_placement() throws IOException {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
	    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);
	    player.doOnePlacement("Destroyer", (p) -> new V1ShipFactory().makeDestroyer(p));		
	}
	
	String placement_str =  String.join("\n", "B2V", "C8H", //sub * 2
			"a4v", "a6v", "r0v", // destroyer * 3
			"e0h", "f0v", "h4h", // battleship * 3
			"k2h", "n9v"); // carrier * 2
	
	String placement_str_v2 =  String.join("\n", "B2V", "C8H", //sub * 2
			"a4v", "a6v", "r0v", // destroyer * 3
			"e0u", "f0d", "h4l", // battleship * 3
			"k2r", "n9u"); // carrier * 2
	
	@Test
	public void test_do_placement_phase_A() throws IOException {
		TextPlayer player1 = createTextPlayer(10, 20, placement_str, System.out);
		player1.doPlacementPhase();
	}
	
	@Test
	public void test_do_placement_phase_A_over_bottom() throws IOException {
		TextPlayer player1 = createTextPlayer(10, 20, placement_str, System.out);
		player1.doPlacementPhase();
	}
	
	@Test
	public void test_do_placement_phase_A_over_right() throws IOException {
		TextPlayer player1 = createTextPlayer(10, 20, placement_str, System.out);
		player1.doPlacementPhase();
	}
	
	@Test
	public void test_do_placement_phase_B() throws IOException {
		BufferedReader input = new BufferedReader(new StringReader(placement_str));
	    PrintStream output = new PrintStream(System.out, true);
	    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
	    V1ShipFactory shipFactory = new V1ShipFactory();
	    TextPlayer player2 = new TextPlayer("B", board, input, output, shipFactory);
		player2.doPlacementPhase();
	}
	
	@Test
	public void test_do_placement_phase_C() throws IOException {
		BufferedReader input = new BufferedReader(new StringReader(placement_str_v2));
	    PrintStream output = new PrintStream(System.out, true);
	    Board<Character> board = new BattleShipBoard<Character>(10, 20, 'X');
	    V2ShipFactory shipFactory2 = new V2ShipFactory();
		TextPlayer player3 = new TextPlayer("C", board, input, output, shipFactory2);
		player3.doPlacementPhase();
	}
	
}
