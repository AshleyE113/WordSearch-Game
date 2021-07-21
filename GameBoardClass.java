import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;


public class GameBoardClass {
    private static String boardName;
    private StringBuilder displayBoard;
    private String[][] gameBoard;
    private String[] answers;
    private int binarySearch;

    public GameBoardClass(String boardName){
        this.boardName = boardName;
        this.displayBoard = displayBoard;
        this.gameBoard = gameBoard;
        this.answers = answers;
        this.binarySearch = binarySearch;
    }

    public String displayBoard(String[][] grid) {
        StringBuilder boarder = new StringBuilder(); //StringBuilder, strings that can be overriden
        String section = "+-----";
        //Puts everything together
        boarder.append(printHeaderLine(grid));
        boarder.append(printDashedLine(grid, section));
        boarder.append(printGrid(grid, section));
        
        return boarder.toString();
    }

	///*
    public StringBuilder printHeaderLine(String[][] grid) {
        StringBuilder boarder = new StringBuilder(); //creates a new Str builder
        
        boarder.append("       "); //Puts spaces into the char grid
        for (int i = 0; i < grid.length; i++) {
            boarder.append("     ");
        }
        
        boarder.append(System.lineSeparator());
        return boarder;
    }
    //*/
    ///*
    public StringBuilder printGrid(String[][] grid, String section) {
        StringBuilder boarder = new StringBuilder();
        
        for (int i = 0; i < grid.length; i++) {
            boarder.append("   "); //Formats the row labels
            boarder.append(" ");
            for (int j = 0; j < grid[i].length; j++) {
                boarder.append("+  ");
                boarder.append(grid[i][j]); //Puts in the empty cells
                boarder.append("  ");
            }
            
            boarder.append("+");
            boarder.append(System.lineSeparator());
            boarder.append(printDashedLine(grid, section));
        }
        
        return boarder;
    }
    //*/
    //Prints the dashed lines.
    ///*
    public StringBuilder printDashedLine(String[][] grid, String section) {
        StringBuilder boarder = new StringBuilder();
        
        boarder.append("    ");
        for (int i = 0; i < grid.length; i++) {
            boarder.append(section);
        }
        
        boarder.append("+");
        boarder.append(System.lineSeparator());
        
        return boarder;
    }

    public String[][] boardCreator(String boardName){

        String firstRow = "";
        String data1 = "";
        String[] str_letters;
        String[][] Error = new String[1][1];

        Error[0][0] = "not found";

        //if (board_name.matches("board1")){
        String fileName = boardName;
        File file = new File(fileName);
        try{
            Scanner inputStream = new Scanner(file);
            firstRow = inputStream.next();
            while (inputStream.hasNext()){
                data1 += inputStream.next() + ",";
            }
            //System.out.println("FR: " + firstRow);
            //System.out.println("Data1: " + data1);

 
            String[] first_r = firstRow.split(",");
            str_letters = data1.split(",");

            int r_num = Integer.parseInt(first_r[0]);
            int c_num = Integer.parseInt(first_r[1]);
            int ans_num = first_r.length-2;
            int row_c = 0;
            int col_c = 0;
            answers = new String[ans_num];
            ///*
            for (int i = 2; i < first_r.length; i++){
                answers[i-2] = first_r[i]; 
                         
            }
            
            for(int i = 0; i < answers.length; i++){
                System.out.print(i + ": " + answers[i] + " ");
            }//*/

            String[][] gboard = new String[Integer.parseInt(first_r[0])][Integer.parseInt(first_r[1])];
            for (int r = 0; r < str_letters.length; r++){
                    row_c = (int)(Math.floor(r / r_num));
                    col_c = (int)(Math.abs(r-c_num * row_c));
                    gboard[row_c][col_c] = str_letters[r]; 

                    //System.out.print(gboard[row_c][col_c]);
            }

                inputStream.close();
                return gboard;
            } catch (FileNotFoundException e){
                return Error;
            }

    }

    public void playerTurn(){

        boolean foundAll = false;
        boolean endGame = false;
        int remaining = 0;
        Scanner player_ans = new Scanner(System.in);
        System.out.println("This board has " + answers.length + " words you need to find!\nGood Luck!");

        ArrayList<String> ansList =  new ArrayList<String>();//Arrays.asList(answers);
        ArrayList<String> input_ans = new ArrayList<String>();
        for(String ans: answers){
            ansList.add(ans);
        }
        
        while ((!foundAll) && (!endGame)){
            System.out.println("Put in 1 word at a time! If you want to quit, enter 'q' or 'Q'!" );
            String guess = player_ans.nextLine();
            input_ans.add(guess);
            if (guess.equals("q") || guess.equals("Q")){
                endGame = true;
            } else{
                Collections.sort(ansList); //Sorts the answers for the binary search
                int result = binarySearch(ansList, guess);
                System.out.println("Let's see what's up!");
                if (result == -1){ 
                    System.out.println("The word you entered isn't an answer! Try again!");

                }else{
                    remaining++;
                    //System.out.println("Rem: " + remaining);
                    System.out.println("Congrats! You found a word! You have " + (answers.length - remaining) + " words to go!");
                    if (remaining == answers.length){
                        System.out.println("You completed the word search! Good for you!"); //Fixed
                        foundAll = true;
                    }
                }
            }
            if (endGame){
                System.out.println("Alrighty, enjoy your day!");
            }
            if (foundAll){
                System.out.print("Congrats on completing the puzzle! You're a champ!");
            }
        } 
    

    }
    public void multiPlayerTurn(Object[] players){
        //Quit works
        int[] player_scores = new int[players.length]; //Will keep track of player scores
        int winner_index = 0; //Will get the winner at the end
        int while_i = 0;
        boolean foundAll = false;
        boolean endGame = false;
        int remaining = 0;
        Scanner player_ans = new Scanner(System.in);
        System.out.println("Hello players! This board has " + answers.length + " words you need to find!\nGood Luck!");

        ArrayList<String> ansList =  new ArrayList<String>();
        ArrayList<String> input_ans = new ArrayList<String>();
        for(String ans: answers){
            ansList.add(ans);
        }

        while ((!foundAll) && (!endGame)){

            while (while_i <= players.length){// Set it <= so it wouldn't skip the last player's turn when while_i is reset to 0
                System.out.println("It's Player " + (while_i+1) + "'s turn!");
                System.out.println("Points: " + player_scores[while_i]);
                System.out.println("Put in 1 word at a time! When you want to end your turn, type in 'e' or 'E'! If you want to quit, enter 'q' or 'Q'!" );
                String guess = player_ans.nextLine();
                input_ans.add(guess);

                if ((!guess.equals("q")) && (!guess.equals("Q"))){

                    Collections.sort(ansList);
                    int result = binarySearch(ansList, guess);
                    System.out.println("The results are in...");

                    if (result != -1){
                        remaining++;
                        player_scores[while_i]++;
                        System.out.println("Congrats! You found a word! Now " + (answers.length - remaining) + " words remain!");
                        
                        if (remaining == answers.length){
                            System.out.println("Congrats on completing the word search! Good for you!");
                            foundAll = true;
                            break;
                        }
                    }else if ((!guess.equals("q")) && (!guess.equals("Q")) && (!guess.equals("e")) && (!guess.equals("E"))) {
                        System.out.println("Yikes! The word entered isn't an answer! Try again!");                        
                    }
                    if (guess.equals("e") || guess.equals("E")){
                        System.out.println("Alright! Next player, you can go!");
                        while_i++;
                    }
                    if (while_i == players.length && (!foundAll) && (!endGame)){ 
                        while_i = 0; //iterates over the players until they quit or solve the word search
                    }

                } else{
                    endGame = true;
                    break; //forces us out of the loop
                }
            }
        }

        if (endGame){
            System.out.println("Sad to see you guys go! Enjoy the rest of your day!");
        }

        if (foundAll){
            //System.out.println("FA!");
            for (int i = 0; i < player_scores.length; i++){
                int max = player_scores[i];
                if (player_scores[i] > max){
                    max = player_scores[i];
                    winner_index = i;
                }
            }

            System.out.println("The winner is...\nPlayer " + (winner_index+1) + "! Congrats!");
            System.out.println("Enjoy your day!");
        }
    }

    public static int binarySearch(ArrayList<String> boardAnswers, String player_guess){
        int low = 0;
        int high = boardAnswers.size() - 1;
        int mid = (low + high) / 2;
    
        while (low <= high && !boardAnswers.get(mid).equalsIgnoreCase(player_guess)){ //Allows leeway with answers, meaning 'About' == 'about'
            if (boardAnswers.get(mid).compareTo(player_guess) < 0 ){
                //System.out.println("In CompareTO cond.");
                low = mid + 1; 
            } else{
                //System.out.println("In Else!");

                high = mid - 1;
            }
            mid = (low + high) / 2; 
    
            //
            if (low > high){
                //System.out.println("Return neg");

                mid = -1;
            }
        }
        return mid;
    }
}