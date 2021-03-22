# A console app battleship game.

The game supports 2 players playing against each other on a 20 * 10 text-based grid:

  0|1|2|3|4|5|6|7|8|9
A  | | | | | | | | |  A
B  | | | | | | | | |  B
C  | | | | | | | | |  C
D  | | | | | | | | |  D
E  | | | | | | | | |  E
F  | | | | | | | | |  F
G  | | | | | | | | |  G
H  | | | | | | | | |  H
I  | | | | | | | | |  I
J  | | | | | | | | |  J
K  | | | | | | | | |  K
L  | | | | | | | | |  L
M  | | | | | | | | |  M
N  | | | | | | | | |  N
O  | | | | | | | | |  O
P  | | | | | | | | |  P
Q  | | | | | | | | |  Q
R  | | | | | | | | |  R
S  | | | | | | | | |  S
T  | | | | | | | | |  T
  0|1|2|3|4|5|6|7|8|9

## 1. Ship Placement phase. 
Player A places his ships first, while player B closes her eyes.
The game displays a blank board (as above), and the ship placement begins.

Each player has 8 ships:

2 "Submarines" ships that are 1x2 
3 "Destroyers" that are 1x3
3 "Battleships" that are 1x4
2 "Carriers" that are 1x6

The game then prompts player A for each ship, e.g., \\
Player A where do you want to place a Submarine?

If the location is invalid (collides with another ship, results in a ship going off the grid, etc), 
it explains the problem to the player, and asks them to place again.

## 2. Battle Phase
Turns alternate between each player, with player A going first.  
First, display the board to player A. 

Player A's turn:
     Your ocean                           Player B's ocean
  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9   
A s| | | | | | | |c|  A                A  | | | | | | | | |  A
B s| |d| | | | | |c|  B                B  | | | | | | | | |  B
C  | |*| | | | | |c|  C                C  | |X| | | | | | |  C
D  | |d| | | | | |c|  D                D  |X|d|d| | | | | |  D
E  | | | | | | | |c|  E                E  | |X| | | | | | |  E
F  | |d| | | | | |c|  F                F  | | | | | | | | |  F
G  | |d| | | |b| | |  G                G  | | | | | | | | |  G
H  | |d| | | |b| | |  H                H  | | | | | | | | |  H
I  | | | | | |b| | |  I                I  | | | | | | | | |  I
J  | | | | | |b| | |  J                J  | | |X| | | | | |  J
K c|c|c|c|c|c| | | |  K                K  | | | | | | | | |  K
L  | | | | | | | | |  L                L  | | | |X| | | | |  L
M  | | | |s|s| | | |  M                M  | | | | | | | | |  M
N  | | | | | | | | |  N                N  | | | | | | | | |  N
O  | | | | | |b| | |  O                O  | | | | |s|s| | |  O
P  | | | | | |b| | |  P                P  | | | | | | | | |  P
Q  | | | | | |b| | |  Q                Q  | | | | | | | | |  Q
R  | | | | | |b| | |  R                R  | | | | | | | | |  R
S  | | | | | | | | |  S                S  | | | | | | | | |  S
T d|d|d| | | | | | |  T                T  | | | | | | | | |  T
  0|1|2|3|4|5|6|7|8|9                    0|1|2|3|4|5|6|7|8|9
  
A * indicates a hit portion of the player's own ship, and X indicates a miss on an enemy ship.

Player A is then prompted for the coordinate to fire at. 
If the coordinates are invalid, the game should prompt player A to enter a valid choice. 
The game will then report the result.

Game then hands the control over to B, and continues to alternate until one player has no more ships.
When one player has no more ships, the other player wins.

The game then prints a message stating who won, and exits.
