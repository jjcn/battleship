# A console app battleship game.  
  
The game supports 2 players playing against each other on a 20 * 10 text-based grid:  
  
## 1. Ship Placement phase.  
Player A places his ships first, while player B closes her eyes.  
The game displays a blank board (as above), and the ship placement begins.  
  
Each player has 8 ships:  
  
2 "Submarines" ships that are 1x2   
3 "Destroyers" that are 1x3  
3 "Battleships" that are 1x4  
2 "Carriers" that are 1x6  
  
The game then prompts player A for each ship, e.g.,  
Player A where do you want to place a Submarine?  
  
If the location is invalid (collides with another ship, results in a ship going off the grid, etc),  
it explains the problem to the player, and asks them to place again.  
  
## 2. Battle Phase
Turns alternate between each player, with player A going first.  
First, display the board to player A.   
    
A * indicates a hit portion of the player's own ship, and X indicates a miss on an enemy ship.  

Player A is then prompted for the coordinate to fire at.  
If the coordinates are invalid, the game should prompt player A to enter a valid choice.  
The game will then report the result.  
  
Game then hands the control over to B, and continues to alternate until one player has no more ships.  
When one player has no more ships, the other player wins.  
  
The game then prints a message stating who won, and exits.  
