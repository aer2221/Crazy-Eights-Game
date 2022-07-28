/** Card.java
*   Author: Anna Reis
*   uni: aer2221
*   
*   
*   Models a typical playing card
*
*/
import java.lang.String; 

class Card{
    
    private char suit;
    private int rank;

    // Initializes a card instance
    public Card(char suit, int rank){
        this.suit=suit;
        this.rank=rank;
    }

    // Accessor for suit
    public char getSuit(){
        return suit;
    }
    
    // Accessor for rank
    public int getRank(){
        return rank;
    }

    // Returns a human readable form of the card
    public String toString(){
        if (rank==1||rank>10){
            return stringForRank() + " of " + stringForSuit();
        }
        else{
            return rank + " of " + stringForSuit();
        }
    }

    // To String for cards with ranks that are not numeric 
    public String stringForRank(){
        String[] extraRanks={"Ace", "Jack", "Queen", "King"};
        String r=null;
        if (rank==1){
            r=extraRanks[0];
        }
        if (rank==11){
            r=extraRanks[1];  
        }
        if (rank==12){
            r=extraRanks[2];
        }
        if (rank==13){
            r=extraRanks[3];
        }
        return r;
    }

    // Convert suit character into string format
    public String stringForSuit(){
       String[] strings= {"Clubs", "Diamonds", "Hearts", "Spades"};
       String s=null;
        if (suit=='c'){
            s=strings[0];
        }
        
        if (suit=='d'){
            s=strings[1];
        }

        if (suit=='h'){
            s=strings[2];
        }

        if (suit=='s'){
            s=strings[3];
        }
        return s;
    }
}