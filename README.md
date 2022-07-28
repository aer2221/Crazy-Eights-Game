
Anna Reis
Crazy Eights Game

*****Instructions for using my software, step-by-step*****

Note: Print statements given during the playing of the game should help with using the software

1. User must play a card that matches the suit of the current suit or the rank of the up card.
2. However, if up card is an 8, the user must match the current suit only. It cannot play a card based on whether it matches up the card.
3. If user cannot play any of its cards, user can draw by typing 'draw'.
4. After drawing, the user can decide to play the card just drawn (so long as it is playable), or the user can draw again.
5. The user wins once they have played all their cards or if, once the deck is empty and no more cards can be drawn, the user has less cards than the computer player.


*****Explanation of design decisions*****

Here I will explain my reasoning behind some of the helper methods I created.

1. startingDeal()
I created this so that the first deal (where each player is given 7 cards to start) can be a separate move that isn't accidentally repeated in another part of the game. I also overloaded the startPlayer() method, which actually deals 7 cards to the player hand and computer hand, that is called within startingDeal(). I overloaded this because the action of dealing 7 cards will be fairly similar for both a user player and computer player, but the exact mechanics will be different based on the parameter (player vs computer) passed in to startPlayer(). 
	
2. suitToString()
Although I am aware that other toString methods exist in classes like the card class or deck class, I had to create this suitToString() method within the Game class because currentSuit is private and cannot be acted upon by other classes. This method is used when printing out the currentSuit to the player each turn. 

3. printCurrentValues() 
This compartmentalizes the action of printing various information to the user and ensures this process does not have to coded into the playerTurn() or computerTurn() methods. 

4. playable()
This separate helper method can be used by both the computer and player because computer and player are obliged to follow the same rules. This method ensures the computer and player cannot play a card that violates the rules, such as playing a card that does not match the suit or rank of the up card or playing a card that doesn't match currentSuit after an 8 was played. This method prevents "cheating," i.e. playing invalid cards. 

5. playerTurn()
a. I had to create this method in the first place because certain checks like playable cannot be performed and certain variables like currentSuit and faceup cannot be accessed by the Player class. I decided to create this method rather than including these checks in the play() method in order to group all of the relevant player actions together and not have them overwhelm the play() method. 
b. I also incorporated a check to see if playerCard is null. I added this because in the later development stages of the game I realized that the user could draw past the end of the deck within its move and specifically draw null cards, which technically shouldn't be possible. The game is supposed to end as soon as the deck is empty. To solve this problem, I coordinated between playerTurn() and the Player class so that if the deck cannot deal, the Player class' playsTurn() method will return null. I then check for this possibility in the playerTurn() class. If playsTurn() does not return null, the player move will continue as normal. If playsTurn() does return null, faceup will become playerCard (making it null), and faceup will be returned, ending the player move and sending it back to the play() method, where faceup being null will end the game immediately. TLDR, this check acknowledges the possibility that the game could end within a player's turn rather than waiting for the player to "finish" their turn to check if the game should end. 

6. playerMoves() and computerMoves() 
These separate methods ensure that actions such as removing a card from a player's hand, changing faceup, changing the currentSuit do not happen until after playerCard/computerCard is deemed playable. 

7. playerEightCheck() and computerEightCheck() 
These methods are called after the player or computer plays their desired card. They are separate to ensure that methods such as the play() method do not become too long and because they serve a unique and different purpose that justifies having a separate method. 

8. computerDraw()
Similar to the player problem mentioned before, I wanted to ensure that the computer could not draw null cards and that if the deck became empty during its move, the game would immediately end. To solve this problem, I did much the same as I did in the playerTurn() method; if the deck (cards) still can deal, then the computer can draw. Otherwise, faceup becomes null, the method returns faceup, and due to the gameOver check in the play() method, the game will immediately end. 

9. gameOver()
I created this separately to compartmentalize end-game formalities such as print statements and calculating game winner if the game ends and neither player has played all of the cards in their hand. 
