package edu.duke.sj320.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {

	public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
		super(next);
	}

	@Override
	protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
		for (Coordinate c : theShip.getCoordinates()) {
			if (theBoard.whatIsAtForSelf(c) != null) {
				return "That placement is invalid: the ship overlaps another ship.\n";
			}
		}
		return null;
	}

}
