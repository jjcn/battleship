package edu.duke.sj320.battleship;

/**
 * Subclass we make is-A PlacementRuleChecker
   and also has-A PlacementRuleChecker (next, which is inherits).
 *
 * @param <T>
 */
public abstract class PlacementRuleChecker<T> {
    private final PlacementRuleChecker<T> next;

    public PlacementRuleChecker(PlacementRuleChecker<T> next) {
        this.next = next;
    }
    
    /**
     * Subclasses will override this method to specify how they check their own rule.
     * 
     * @param theShip
     * @param theBoard
     * @return null, if the rule pass;
     *         a String indicating the problem, if the rule not pass.
     */
    protected abstract String checkMyRule(Ship<T> theShip, Board<T> theBoard);
    
    /**
     * Subclasses will generally NOT override this method
     * @param theShip
     * @param theBoard
     * @return null, if rule passes;
     *         non-null String, if not.
     */
	public String checkPlacement(Ship<T> theShip, Board<T> theBoard) {
		// if we fail our own rule: stop the placement is not legal
		if (checkMyRule(theShip, theBoard) != null) {
			return "That placement is invalid.\n";
		}
		// other wise, ask the rest of the chain.
		if (next != null) {
			return next.checkPlacement(theShip, theBoard);
		}
		// if there are no more rules, then the placement is legal
		return null;
	}
 }
