/** Deck.java
*   Author: Anna Reis
*   uni: aer2221
*   
*   Models a typical deck of playing cards
*   To be used with Card class
*
*/

class Deck{

    private Card[] deck;
    private int top;

    // constructs a default Deck
    public Deck(){
        char[] suits={'c', 'd', 'h', 's'};
        deck= new Card[52];
        top=0;
        int deckIndex=0;
        for (int i=0; i<4;i++){
            int rank=1;
            char suit=suits[i];
            for (int j=0;j<13;j++){
                Card c = new Card(suit,rank);
                rank++;
                deck[deckIndex]=c;
                deckIndex++;
            }
        } 
    }

    // Deals the top card off the deck
    // If no cards left to deal, returns null value
    public Card deal(){
        top++;
        if (top>52){
            return null;
        }
        return deck[top-1];
    }


    // Returns true provided there is a card left in the deck to deal
    public boolean canDeal(){
        if (top<52){
            return true;
        }
        else{
            return false;
        }
    }

    // Shuffles the deck 10000 times
    public void shuffle(){
        for (int i=0;i<10000;i++){
            int random1= (int)(Math.random()*51);
            Card temp1=deck[random1];
            int random2=(int)(Math.random()*51);
            Card temp2=deck[random2];
            deck[random2]=temp1;
            deck[random1]=temp2;
        }
    }

    // Returns a string representation of the whole deck
    public String toString(){
        String deckString="";
        for (int i=0;i<deck.length;i++){
            deckString+= deck[i] + "\n";
        }
        return deckString;
    }
    
}