// Questions: what is enum?

package TicTacToe.src.TicTactoe;
// Since we're in a new folder, it'll create a package to avoid naming conflicts

public class Game {
    // For the board, we could create a 2d array or a 1d array board (1x9 array)    

    // public static final String O = "O";
    // public static final String X = "X"; 
    // final means it cant' be changed. Convention is that such instances should be written in capital letters
    

    // If you print out the game object, it'll print tictactoe.Game@5fcf34f --> which is a hexadecimal version of the reference variable
    //      The reason it's not the actual address is because the Object has a default toString() method, which we can Override 
    //      We will use a StringBuilder object to append more strings to the object


    // Since we have a 1d array of length 9, we sholdn't put "/n" for i%3 == 0 but instead i%3 == 2 because 0%3 = 0 so it'll do a new line after the first element instead of every third

    public static void main(String[] args) {        
        StringBuilder sb = new StringBuilder();
        sb.append("e");
        return sb.toString();
        // can't return anything within the main method since this is the entry point for a program. The JVM simply executes code from within the main method and terminates program when it reaches the end 
    }
}


// JUnits test: It's convention to store test files in a diff ffolder. 
// On VSCode click on flask on left side, then click on enable java tests. Then choose Junit
//      Click on lib. You’ll see two new jar files have been added for testing. Make a folder called test and subfolder within test called TicTacToe, and put these jar files in there. 
//      Within this, make a new file called GameTest.Java, and the first line should be @Test (auto adds import org.junit.Test;)
//      assertsEquals