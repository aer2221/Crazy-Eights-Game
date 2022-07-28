/** Player.java
*   Author: Anna Reis
*   uni: aer2221
*   
*   Player class as part of Crazy Eights
*   Models key actions and variables of a user/player
*   To be used with Game, Card, Deck classes
*
*/

import java.util.ArrayList;
import java.util.Scanner;

class Player{
    
    private ArrayList<Card> hand;
    private Scanner input;

    // Instantiate player hand and scanner 
    public Player(){
        hand=new ArrayList<Card>();
        input= new Scanner(System.in);
    }

    // Adds a card to the player's hand
    public void addCard(Card c){
        hand.add(c);
    }

    // Removes a card from the player's hand
    public void removeCard(Card c){
        hand.remove(c);
    }
   
    // Covers all the logic regarding a human player's turn
    public Card playsTurn(Deck deck){
        while (true){
            if (deck.canDeal()==true){ //Internal check for deck size
                System.out.println("Your hand is:" + "\n" + handToString());
                System.out.println("Type 'draw' to draw a card, or " + 
                "type the number next to the card you want to play.");
                String userString= input.nextLine();
                // Player draw mechanism
                if (userString.contains("draw")){
                    addCard(deck.deal());
                }
                else { //Player plays a card
                    int cardIndex=((Integer.parseInt(userString))-1);
                    Card userCard=hand.get(cardIndex);
                    return userCard;
                }
            }
            //If deck cannot deal, return null, which will end the game
            else{
                return null;
            }
        }
    }

    // Accessor for the players hand
    public ArrayList<Card> getHand(){
        return hand;
    }

    // Returns a printable string representing the player's hand
    public String handToString(){
        String handString="";
        int cardNum=1;
        for (int i=0;i<hand.size();i++){
            handString+= cardNum + "\t" + hand.get(i) + "\n";
            cardNum++;
        }
        return handString;
    }


} 
