package edu.duke.sj320.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {

	protected Ship<Character> createRectangleShip(Placement where, int w, int h, char letter, String name) {
		if (where.orientation == 'V') { // vertical
			return new RectangleShip<Character>(name, where.getWhere(), w, h, letter, '*');
		}
		else if (where.orientation == 'H') { // horizontal
			return new RectangleShip<Character>(name, where.getWhere(), h, w, letter, '*');
		}
		else {
			throw new IllegalArgumentException("Orientation must be a single letter V or H (ignore case)");
		}
	}
	
	protected Ship<Character> createTShapedShip(Placement where, int w, int h, char letter, String name) {
		if (where.orientation == 'U') {
			return new TShapedShip<Character>(name, where.getWhere(), w, h, letter, '*', false);
		}
		else if (where.orientation == 'D') {
			return new TShapedShip<Character>(name, where.getWhere(), w, h, letter, '*', true);
		}
		else if (where.orientation == 'R') {
			return new TShapedShip<Character>(name, where.getWhere(), h, w, letter, '*', false);
		}
		else if (where.orientation == 'L') {
			return new TShapedShip<Character>(name, where.getWhere(), h, w, letter, '*', true);
		}
		else {
			throw new IllegalArgumentException("Orientation must be a single letter U/D/R/L (ignore case)");
		}
	}
	
	protected Ship<Character> createZShapedShip(Placement where, int w, int h, char letter, String name) {
		if (where.orientation == 'U') {
			return new ZShapedShip<Character>(name, where.getWhere(), w, h, letter, '*', false);
		}
		else if (where.orientation == 'D') {
			return new ZShapedShip<Character>(name, where.getWhere(), w, h, letter, '*', true);
		}
		else if (where.orientation == 'R') {
			return new ZShapedShip<Character>(name, where.getWhere(), h, w, letter, '*', false);
		}
		else if (where.orientation == 'L') {
			return new ZShapedShip<Character>(name, where.getWhere(), h, w, letter, '*', true);
		}
		else {
			throw new IllegalArgumentException("Orientation must be a single letter U/D/R/L (ignore case)");
		}
	}
	
	@Override
	public Ship<Character> makeSubmarine(Placement where) {
		return createRectangleShip(where, 1, 2, 's', "Submarine");
	}

	@Override
	public Ship<Character> makeDestroyer(Placement where) {
		return createRectangleShip(where, 1, 3, 'd', "Destroyer");
	}
	
	@Override
	public Ship<Character> makeBattleship(Placement where) {
		return createTShapedShip(where, 3, 2, 'b', "Battleship");
	}

	@Override
	public Ship<Character> makeCarrier(Placement where) {
		return createZShapedShip(where, 2, 5, 'c', "Carrier");
	}

}
