package edu.duke.sj320.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
	TextPlayer player1;
	TextPlayer player2;
	
	public App(TextPlayer player1, TextPlayer player2) {
		this.player1 = player1;
		this.player2 = player2;
	}
	
	/**
	 * Do the entire placement phase for players.
	 * @throws IOException
	 */
	public void doPlacementPhase() throws IOException {
		player1.doPlacementPhase();
		player2.doPlacementPhase();
	}
	
	/**
	 * Do the entire attacking phase for players.
	 * Let player1 play a turn, then see if player 2 has lost. 
	 * Then let player 2 play a turn and see if player 1 has lost.
	 * Repeat this until one player has lost, then report the outcome.
	 * @throws IOException
	 */
	public void doAttackingPhase() throws IOException {
		String winMsg = "You win the game!";
		String myHeader = "Your ocean";
		String p1EnemyHeader = String.format("Player %s's ocean", player2.name);
	    String p2EnemyHeader = String.format("Player %s's ocean", player1.name);
	    while (!player1.isLost() && !player2.isLost()) {
	    	String action1 = player1.selectAction();
	    	player1.doAction(player2.theBoard, player2.view, myHeader, p1EnemyHeader, action1);
	        if (player2.isLost()) {
	            player1.out.println(winMsg);
	            break;
	        }
	        
	        String action2 = player2.selectAction();
	    	player2.doAction(player1.theBoard, player1.view, myHeader, p2EnemyHeader, action2);
	        if (player1.isLost()) {
	            player2.out.println(winMsg);
	            break;
	        }
	    }
	}
	
	public static void main(String[] args) throws IOException {
		Board<Character> b1 = new BattleShipBoard<Character>(10, 20, 'X');
		Board<Character> b2 = new BattleShipBoard<Character>(10, 20, 'X');
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		V2ShipFactory factory = new V2ShipFactory();
		TextPlayer p1 = new TextPlayer("A", b1, input, System.out, factory);
		TextPlayer p2 = new TextPlayer("B", b2, input, System.out, factory);
		App app = new App(p1, p2);
		app.doPlacementPhase();
		app.doAttackingPhase();
	}
	
}
