package edu.duke.sj320.battleship;

public class V1ShipFactory implements AbstractShipFactory<Character> {
	/**
	 * Creates a Ship object.
	 * 
	 * @param where is the placement of the ship
	 * @param w is the width of the ship
	 * @param h is the height of the ship
	 * @param letter is the letter indicating the ship
	 * @param name is the name of the ship
	 * @throws IllegalArgumentException when the orientation is not supported
	 * @return
	 */
	protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name) throws IllegalArgumentException {
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
	 
	@Override
	public Ship<Character> makeSubmarine(Placement where) {
		return createShip(where, 1, 2, 's', "Submarine");
	}

	@Override
	public Ship<Character> makeDestroyer(Placement where) {
		return createShip(where, 1, 3, 'd', "Destroyer");
	}
	
	@Override
	public Ship<Character> makeBattleship(Placement where) {
		return createShip(where, 1, 4, 'b', "Battleship");
	}

	@Override
	public Ship<Character> makeCarrier(Placement where) {
		return createShip(where, 1, 6, 'c', "Carrier");
	}

}
