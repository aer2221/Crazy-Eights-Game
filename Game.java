/** Game.java
*   Author: Anna Reis
*   uni: aer2221
*   
*   Game class for playing crazy eights in commandline
*   To be used with Player, Card, Deck classes
*
*/


import java.util.Scanner;
import java.util.ArrayList;

class Game{

    private char currentSuit; 
    private Card faceup; 
    private Scanner input;
    private Player p1;
    private ArrayList<Card> compHand;
    private Deck cards;
    
    // Sets up the Game object for play
    public Game(){
        // Creates a deck, shuffles it
        cards=new Deck();
        cards.shuffle();
        // Instantiates player, scanner, and computer hand
        p1= new Player();
        input=new Scanner(System.in);
        compHand=new ArrayList<Card>();
        // Deals cards to player hand and computer hand
        startingDeal();
        // Sets faceup and currentSuit 
        faceup= cards.deal();
        currentSuit=faceup.getSuit();
        System.out.println("Welcome to Crazy Eights!");
    }

    // Performs initial/starting deal of 7 cards to each player
    public void startingDeal(){
        startPlayer(p1);
        startPlayer(compHand);
    }

    // Starting deal to user player
    public void startPlayer(Player user){
        for (int i=0;i<7;i++){
            user.addCard(cards.deal());
        }
    }

    //Starting deal to computer 
    public void startPlayer(ArrayList<Card> user){
        for (int i=0;i<7;i++){
            compHand.add(cards.deal());
        }
    }

    // Necessary for printing currentSuit
     private String suitToString(char suit){
        String[] strings= {"Clubs", "Diamonds", "Hearts", "Spades"};
        String suitString="";
        if (suit=='c'){
            suitString=strings[0];
        }
        if (suit=='d'){
            suitString=strings[1];
        }
        if (suit=='h'){
            suitString=strings[2];
        }
        if (suit=='s'){
            suitString=strings[3];
        }
        return suitString;
    }

    // Print statements used at the start of every player turn 
    public void printCurrentValues(){
        System.out.println("\n");
        System.out.println("**The up card is " + faceup.toString() + " **");
        System.out.println("**The current suit is " + 
        suitToString(currentSuit) + " **");
        System.out.println("\n");
    }

    // Plays a game of crazy eights. 
    // Returns true to continue playing and false to stop playing
    public boolean play(){
        while (true){
            printCurrentValues();
            playerTurn();
            // Check if game should end after player turn
            if (cards.canDeal()==false || p1.getHand().size()==0 || 
            compHand.size()==0 || faceup==null){ 
                return gameOver();
            }
            else{
                // If player plays an 8, perform special operations
                playerEightCheck();
                computerTurn(); 
                // Check if game should end after computer turn
                if (cards.canDeal()==false || p1.getHand().size()==0 || 
                compHand.size()==0 || faceup==null){
                    return gameOver();
                }
            }
        }
    }

    // Check if player/computer card can be played
    private boolean playable(Card c){
        if (c.getSuit()!= currentSuit &&
        c.getRank()!= faceup.getRank() && 
        c.getRank()!=8 || faceup.getRank()==8 && 
        c.getSuit() != currentSuit && 
        c.getRank()!=8 ){
            return false;
        }
        else{
            return true;
        }
    }

    // Incorporates card from Player class into Game class
    // Coordinates with Player class' playsTurn() method
    // If playsTurn is null (no more cards to draw), playerTurn returns null
    private Card playerTurn(){
        while (true){
            Card playerCard=p1.playsTurn(cards);
            // If deck is not empty, proceed as normal 
            if (playerCard != null){
                // If card is not playable, do not proceed
                if (playable(playerCard)==false){
                    System.out.println("Sorry, that card is invalid." +
                    " Please try again.");
                }
                // If card is playable, continue with rest of player move
               else{
                    playerMoves(playerCard);
                    return playerCard;
                }
            }
            // If playsTurn is null (i.e. deck is empty), make faceup null
            // This will end the game immediately once deck is empty 
            else{
                faceup=playerCard;
                return faceup;
            }
        }
    }

    // Rest of player move
    // Only accessed once player's desired card is deemed playable
    private void playerMoves(Card c){
        faceup=c;
        currentSuit=c.getSuit();
        p1.removeCard(c);
        System.out.println("You played the " + c);
    }

    // After player plays a card, check if it is an 8
    // Changes currentSuit to be desired suit of player 
    private void playerEightCheck(){
        if (faceup.getRank()==8){
               System.out.println("You played an 8! What would you like " + 
                "the new suit to be? (c, h, d, s)");
                String userString= input.nextLine();
                currentSuit=userString.charAt(0);
        }
    }

    // Covers all the logic of a computer move
    private Card computerTurn(){
        //Plays first available card that matches suit or rank
         for (int i=0;i<compHand.size();i++){
             Card currentCard=compHand.get(i);
             // If card is playable, proceed with rest of computer move
             if (playable(currentCard)==true){
                 computerMoves(currentCard); 
                 return currentCard;
            }
        }
        // If computer cannot play any of its card, draw
        return computerDraw();
    }

    // Method for computer to draw cards if none of its cards are playable 
    private Card computerDraw(){
        while (true){
            // Check to see if more cards can be drawn
            // If yes, proceed with drawing and/or playing card
            if (cards.canDeal()==true){
                Card newCard= cards.deal();
                System.out.println("The computer drew a card.");
                //Check if card is playable
                if (playable(newCard)==true){
                    computerMoves(newCard);
                    return newCard;
                }
                // If not playable, add to computer's hand
                else{
                    compHand.add(newCard);
                }
            }
            // If no more cards can be drawn, make faceup null and return it
            // This will immediately end the game once deck is empty 
            else{
                faceup=null;
                return faceup;
            }
        }
    }

    // If computer's card is deemed playable, proceed with playing the card
    private void computerMoves(Card c){
        System.out.println("Computer plays " + 
        c.toString());
        faceup=c;
        currentSuit=c.getSuit();
        computerEightCheck();
        compHand.remove(c);
    }

    // If computer's move is deemed to be an 8, perform special 8 operations
    private void computerEightCheck(){
        if (faceup.getRank()==8){
            // Will make suit match the first card in computer hand 
            currentSuit=compHand.get(0).getSuit();
            System.out.println("Computer played an 8! New suit is: "+
            suitToString(currentSuit));
        }
    }            

    // Covers the logic of ending a game
    // Prints end-game statements
    // Calculates winner 
    // Asks if user wants to play again
    private boolean gameOver(){
        endGameStatements();
        System.out.println("Would you like to play again? y/n");
        String userString=input.nextLine();
        if (userString.contains("y")){
            // this will create new game and play again
            return true; 
        }
        else{
            //ends game permanently 
            return false;
        }
    }

    // Formalities and print statements necessary to ending game
    private void endGameStatements(){
        System.out.println("The game has ended!");
        if (cards.canDeal()==false || faceup==null){
            System.out.println("The deck ran out of cards.");
            calculateGameWinner();
        }
        if (p1.getHand().size()==0){
            System.out.println("You won!");
        }
        if (compHand.size()==0){
            System.out.println("The computer played all its cards. " + 
            "Computer wins!");
        }
    }

    //Calculates game winner if neither player played all their cards
    private void calculateGameWinner(){
        if (compHand.size()>p1.getHand().size()){
            System.out.println("Because you ended with less cards, " + 
            "you win! Congrats!");
        }
        else{
            System.out.println("Because the computer ended with less cards"+ 
            ", you lose. Better luck next time!");
        }
    }


}