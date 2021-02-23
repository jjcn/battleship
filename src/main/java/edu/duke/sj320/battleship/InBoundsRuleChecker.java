package edu.duke.sj320.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {
	
	public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
		super(next);
	}

	@Override
	protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
		for (Coordinate c : theShip.getCoordinates()) {
			int row = c.getRow();
			int column = c.getColumn();
			if (row < 0) {
				return "That placement is invalid: the ship goes off the top of the board.\n";
			}
			else if (row >= theBoard.getHeight()) {
				return "That placement is invalid: the ship goes off the bottom of the board.\n";
			}
			else if (column < 0) {
				return "That placement is invalid: the ship goes off the left of the board.\n";
			}
			else if (column >= theBoard.getWidth()) {
				return "That placement is invalid: the ship goes off the right of the board.\n";
			}
		}
		return null;
	}
}
