/** CrazyEights.java
*   Anna Reis
*   uni: aer2221
*
*   Do not modify this one
*   
*   Plays a game of crazy eights in commandline
*   Keeps playing while the game play method returns true
*
*/

class CrazyEights{
    public static void main(String[] args){
        boolean keepPlaying = true;

        // Loop to play again or not 
        while(keepPlaying){
            Game g = new Game();
            keepPlaying = g.play();
        }
    }
}