package edu.duke.sj320.battleship;

import java.util.function.Function;

/**
 * This class handles textual display of a Board (i.e., converting it to a
 * string to show to the user). It supports two ways to display the Board: one
 * for the player's own board, and one for the enemy's board.
 */
public class BoardTextView {
	/**
	 * The Board to display
	 */
	private final Board<Character> toDisplay;

	/**
	 * Constructs a BoardView, given the board it will display.
	 * 
	 * @param toDisplay is the Board to display
	 * @throws IllegalArgumentException if the board is larger than 10x26.
	 */
	public BoardTextView(Board<Character> toDisplay) {
		this.toDisplay = toDisplay;
		if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
			throw new IllegalArgumentException(
					"Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
		}
	}
	
	/**
	 * This makes the header line, e.g. 0|1|2|3|4\n
	 * 
	 * @return the String that is the header line for the given board
	 */
	String makeHeader() {
		StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
		String sep = ""; // start with nothing to separate, then switch to | to separate
		for (int column = 0; column < toDisplay.getWidth(); column++) {
			ans.append(sep);
			ans.append(column);
			sep = "|";
		}
		ans.append("\n");
		return ans.toString();
	}
	
	/**
	 * This makes the body starting and ending in capitals, 
	 * e.g. A  | |  A\n
	 *      B  | |  B\n
	 * 
	 * @param getSquareFn is the function that takes a coordinate 
	 *                    and outputs a character based on who is viewing
	 * @return the String that is the body rows of the given board
	 */
	String makeBody(Function<Coordinate, Character> getSquareFn) {
		StringBuilder ans = new StringBuilder("");
		String sep = "|";
		char row_char = 'A';
		for (int row = 0; row < toDisplay.getHeight(); row++) {
			ans.append(row_char);
			ans.append(" ");
			
			for (int column = 0; column < toDisplay.getWidth(); column++) {
				char squareView = getSquareFn.apply(new Coordinate(row, column)) == null?
						      ' ' : getSquareFn.apply(new Coordinate(row, column));
				ans.append(squareView);
				if (column != toDisplay.getWidth() - 1) {
					ans.append(sep);
				}
			}
			
			ans.append(" ");
			ans.append(row_char);
			ans.append("\n");
			row_char++;
		}
		return ans.toString();
	}
	
	/**
	 * This makes a board, e.g. 
	 *                             0|1|2\n
	 *                           A  | |  A\n
	 *                           B  | |  B\n
	 *                             0|1|2\n
	 * @param view is the view of the board
	 * @return the String that is the text display for the given board
	 */
	String makeBoard(BoardTextView view, Function<Coordinate, Character> getSquareFn) {
		StringBuilder ans = new StringBuilder("");
		String header = view.makeHeader();
		String body = view.makeBody(getSquareFn);
		ans.append(header);
		ans.append(body);
		ans.append(header);
		return ans.toString();
	}
	
	protected String displayAnyBoard(Function<Coordinate, Character> getSquareFn) {	
		BoardTextView view = new BoardTextView(toDisplay);
		return makeBoard(view, getSquareFn);
	}
	
	public String displayMyOwnBoard() {
	    return displayAnyBoard((c) -> toDisplay.whatIsAtForSelf(c));
	}
	
	public String displayEnemyBoard() {
		return displayAnyBoard((c) -> toDisplay.whatIsAtForEnemy(c));
	}
	
	/**
	 * create a whitespace of certain length
	 * @param length
	 * @return
	 */
	public String createWhiteSpace(int length) {
		StringBuilder ans = new StringBuilder("");
		for (int i = 0; i < length; i++) {
			ans.append(" ");
		}
		return ans.toString();
	}
	
	/**
	 * Display my board's myView, and enemy board's enemy view next to each other.
	 * @param enemyView is the enemy board's view
	 * @param myHeader is the header for my board
	 * @param enemyHeader is the header for enemy board
	 * @return the display of two boards placed together
	 */
	public String displayMyBoardWithEnemyNextToIt(BoardTextView enemyView, String myHeader, String enemyHeader) {
		StringBuilder ans = new StringBuilder("");
		
		String [] myLines = this.displayMyOwnBoard().split("\n");
		String [] enemyLines = enemyView.displayEnemyBoard().split("\n");
		int width = toDisplay.getWidth();
		String headerSep = createWhiteSpace(2 * width - myHeader.length() + 17);
		String firstAndLastSep = createWhiteSpace(18);
		String bodySep = createWhiteSpace(16);
		
		ans.append(createWhiteSpace(5));
		ans.append(myHeader);
		ans.append(headerSep);
		ans.append(enemyHeader);
		ans.append("\n");
		for (int i = 0; i < myLines.length; i++) {
			ans.append(myLines[i]);
			if (i == 0 || i == myLines.length - 1) {
				ans.append(firstAndLastSep);
			}
			else {
				ans.append(bodySep);			
			}
			ans.append(enemyLines[i]);
			ans.append("\n");
		}
		
		return ans.toString();
	}
	
}