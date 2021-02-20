package edu.duke.sj320.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Player with text interface
 * 
 */
public class TextPlayer {
	String name; // player name
	final Board<Character> theBoard; // the board for play
	final BoardTextView view; // what to show the player
	
	final BufferedReader inputReader; // input from player
	final PrintStream out; // output to player
	final AbstractShipFactory<Character> shipFactory; // abstract factory that makes ships
	
	final ArrayList<String> shipsToPlace; // all ships available for use
	final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns; // for easy generation of Ship objects 
	
	int moveCount = 2;
	int scanCount = 1;
	
	public TextPlayer(String name, Board<Character> theBoard, 
		   BufferedReader inputReader, PrintStream out, AbstractShipFactory<Character> f) {
		this.name = name;
		this.theBoard = theBoard;
		this.view = new BoardTextView(theBoard);
		this.inputReader = inputReader;
		this.out = out;
		this.shipFactory = f;
		this.shipsToPlace = new ArrayList<String>();
		setupShipCreationList();
		this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
		setupShipCreationMap();
	}
	
	/**
	 * Helper that sets up shipsToPlace
	 */
	protected void setupShipCreationList() {
		shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
	    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
	    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
	    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
	}
	
	/**
	 * Helper that sets up shipCreationFns
	 */
	protected void setupShipCreationMap() {
		shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
		shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
		shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
		shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
	}
   
	/**
	 * Read a line of coordination from input.
	 * @param prompt is a String to prompt user input
	 * @return Coordinate object
	 * @throws IOException
	 * @throws EOFException
	 */
	public Coordinate readCoordinate(String prompt) throws IOException, EOFException {
	    out.println(prompt);
	    String s = inputReader.readLine();
	    if (s == null) {
	    	throw new EOFException("Reached End of File.");
	    }
	    return new Coordinate(s);
	}
	
	/**
	 * Read a line placement from input.
	 * @param prompt is a String to prompt user input
	 * @return Placement object
	 * @throws IOException
	 * @throws EOFException
	 */
	public Placement readPlacement(String prompt) throws IOException, EOFException {
	    out.println(prompt);
	    String s = inputReader.readLine();
	    if (s == null) {
	    	throw new EOFException("Reached End of File.");
	    }
	    return new Placement(s);
	}
	
	/**
	 * Do one placement on board.
	 * @param shipName is the name of ship
	 * @param createFn is an object, an apply method that takes a Placement and returns a Ship<Character>
	 * @throws IOException
	 */
	public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
		Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
		try {
			Ship<Character> s = createFn.apply(p);
			theBoard.tryAddShip(s);
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}	
		out.print(view.displayMyOwnBoard());
	}

	/**
	 * (a) display the starting (empty) board
	   (b) print the instructions message (from the README,
	       but also shown again near the top of this file)
	   (c) call doOnePlacement to place one ship
	 * @throws IOException
	 */
	public void doPlacementPhase() throws IOException {
		out.print(new BoardTextView(theBoard).displayMyOwnBoard());
		String instructions = "Player " + name + 
				": you are going to place the following ships (which are all\n" +
				"rectangular). For each ship, type the coordinate of the upper left\n" +
				"side of the ship, followed by either H (for horizontal) or V (for\n" +
				"vertical).  For example M4H would place a ship horizontally starting\n" +
				"at M4 and going to the right.  You have\n" +
				"\n" +
				"2 \"Submarines\" ships that are 1x2\n" + 
				"3 \"Destroyers\" that are 1x3\n" +
				"3 \"Battleships\" that are 1x4\n" +
				"2 \"Carriers\" that are 1x6\n";
		out.print(instructions);
		
		for (String shipName : shipsToPlace) {
			doOnePlacement(shipName, shipCreationFns.get(shipName));
		}
	}
	
	/**
	 * A player plays one attack.
	 * @param enemyBoard
	 * @param enemyView
	 * @throws IOException 
	 * @throws EOFException 
	 */
	public void playOneTurn(Board<Character> enemyBoard, BoardTextView enemyView,
			                String myHeader, String enemyHeader) throws EOFException, IOException {
		// 1. Display player A's ships and what hits/misses player A has already made.
		out.print(view.displayMyBoardWithEnemyNextToIt(enemyView, myHeader, enemyHeader));
		// 2. Prompt for the coordinate to fire at.  
		// If the coordinates are invalid, the game should prompt player A to enter
		// a valid choice. Then record this fire on enemyBoard.
		String firePrompt = String.format("Player %s: Choose a valid coordinate to fire at:\n", name);
		Coordinate c = readCoordinate(firePrompt);
		InBoundsRuleChecker<Character> ibrc = new InBoundsRuleChecker<>(null);
		while (ibrc.checkMyRule(new RectangleShip<Character>(c, 't', '*'), 
			   enemyBoard) != null) {
			out.print(ibrc.checkMyRule(new RectangleShip<Character>(c, 't', '*'), enemyBoard));
			c = readCoordinate(firePrompt);
		}
		enemyBoard.fireAt(c);
		// 3. Report the result.
		if (enemyBoard.whatIsAtForSelf(c) == null) {
			out.print("You missed!\n");
		}
		else {
			out.print(String.format("You hit a %s!\n", enemyBoard.getShipAt(c).getName()));
		}
	}
	
	public void doOneMove() throws EOFException, IOException {
		// 1. prompt for which a coordinate, and the player should be able to type any
        // coordinate which is a part of the ship they want to move
		String movePrompt = String.format("Player %s: Choose a valid coordinate to move your ship:\n", name);
		Coordinate c = readCoordinate(movePrompt);
		Ship<Character> ship = theBoard.getShipAt(c);
		// 2. The player then is prompted for a new placement
		String placementPrompt = String.format("Player %s: Enter a valid placement for your selected ship:\n", name);
		Placement p = readPlacement(placementPrompt);
		// 3. The ship is then moved according to the new placement
		theBoard.moveShip(ship, p);
	}
	
	/**
	 * Do one sonar scan.
	 * @param enemyBoard
	 * @throws EOFException
	 * @throws IOException
	 */
	public void doOneScan(Board<Character> enemyBoard) throws EOFException, IOException {
		// 1. prompts for the center coordinates of a sonar scan.
		String coordPrompt = String.format("Player %s: Choose a valid coordinate as the center of sonar scan:\n", name);
		Coordinate c = readCoordinate(coordPrompt);
		// 2. calculation
		HashMap<String, Integer> stats = new HashMap<>();
		stats.put("Submarine", 0);
		stats.put("Destroyer", 0);
		stats.put("Battleship", 0);
		stats.put("Carrier", 0);
		stats = enemyBoard.scan(stats, c);
		// 3. reports on the number of squares occupied by each type of ship in that region.
		for (HashMap.Entry<String, Integer> e : stats.entrySet()) {
			out.print(String.format("%s occupy %d squares\n", e.getKey(), e.getValue()));
		}
	}
	
	/**
	 * Prompt player 3 actions to choose from.
	 * @return Uppercase String of the player's selection
	 * @throws IOException
	 */
	public String selectAction() throws IOException {
		String firePrompt = " F Fire at a square\n";
		String movePrompt = moveCount <= 0 ? "" : String.format(" M Move a ship to another square (%d remaining)\n", moveCount);
		String scanPrompt = scanCount <= 0 ? "" : String.format(" S Sonar scan (%d remaining)\n", scanCount);
	
		String selectionPrompt = String.format( "Possible actions for Player %s:\n\n", name) +
			firePrompt + movePrompt + scanPrompt +
			String.format("\nPlayer %s, what would you like to do?\n", name);
		out.print(selectionPrompt);
	    String s = inputReader.readLine();
	    if (s == null) {
	    	throw new EOFException("Reached End of File.");
	    }
	    return s.toUpperCase();
	}

	/**
	 * Player does the corresponding action.
	 * @throws IOException 
	 * @throws EOFException 
	 */
	public void doAction(Board<Character> enemyBoard, BoardTextView enemyView,
            String myHeader, String enemyHeader, String action) throws EOFException, IOException {
		if (action.equals("F")) {
			playOneTurn(enemyBoard, enemyView, myHeader, enemyHeader);
		}
		else if (moveCount <= 0 && action.equals("M")) {
			doOneMove();
			moveCount--;
		}
		else if (scanCount <= 0 && action.equals("S")) {
			doOneScan(enemyBoard);
			scanCount--;
		}
	}
	
	/**
	 * Check if the player has lost the game.
	 * @return true, if lost;
	 * 		   false, otherwise.
	 */
	public boolean isLost() {
		return this.theBoard.checkLose();
	}
	
}
