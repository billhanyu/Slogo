# Lab 8, Fluxx Design

### Names: Chalena Maess-Scholl (cm296), Charles Xu (cx15), Ryan Anders (dra17), Bill Yu(hy103) (SLogo Team 07)

## Primary Classes
	+ Card
		+ Rules
			+ Play, Draw, Hand Limits, Keeper Limits, Bonus, Other
		+ Keepers
		+ Creepers
		+ Goals
		+ Actions
	+ Player 
		+ Hand - each player has a hand that contains cards
		+ Visible - visible cards that each player has layed out in front of them
	+ Game - class that keeps track of current rules, goals, deck of cards left to be picked from and discard pile




## Use Cases
1. a player plays a Goal card, changing the current goal, and wins the game.
   - Game chooses the player
   - Player is presented with a list of Cards in Hand
   - Player chooses the Goal card
   - Game.Goal is updated to the new Goal played by the player
   - If any Player's Visible contains the Goal objects, that player wins
2. a player plays an action card, allowing him to choose cards from another player's hand and play them
   - Game chooses the player
   - Player is presented with a list of Cards in Hand
   - Player chooses the Action card
   - Player is presented with a list of Players, chooses one
   - Player is presented with a list of Cards in that player's Hand
   - Player chooses a card, it is placed in his own Hand
3. A player plays a rule card, adding to the current srules to set a hand-size limit, requiring all players to immediately drop cards from their hands if necessary
   - Game chooses the player
   - Player is presented with a list of Cards in Hand
   - Player chooses the Rule card
   - Game.Rule.HandLimit is updated to the Rule card's specifications
   - A check is performed on all Players' Hand
      - If hand contains too many cards, that Player is prompted to discard cards
   - Discarded cards go to Game.Discard
4. A player plays a rule card, changing the current rule from play 1 to play all, requiring the players to play more cards this turn
   - Game chooses the player
   - Player is presented with a list of Cards in Hand
   - Player chooses the Rule card
   - Game.Rule.Play is updated to the Rule card's specifications, play all
5. A player plays a card, fulfilling the current Ungoal, and everyone loses the game.
   - Game chooses the player
   - Player is presented with a list of Cards in Hand
   - Player chooses a card
   - Card is added to Player.Visible
   - Game performs a check, comparing Player.Visible to Game.Ungoal
      - If Player.Visible contains Game.Ungoal, everyone loses
6. A new theme for the game is designed, creating a different set of Rule, Keeper, and Creeper cards
   - User chooses card type
   - User inputs card's name, effects
   - Card is added to Game.Deck
		
	

