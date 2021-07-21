/*
Ashley Sarah Eliassaint

Intro to Computer Science

ase330

3/19/21
This program allows the user to play 12 different word searches of their choosing by themselves or with a group of friends.
Whether the game consists of one or more people, the player will use a simple player class to set and get their name while
the GameBoardClass handles creating the board and running the single-player and multiplayer games.

*/
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class WordSearch {

    public static void main(String[] args){
     Scanner scan = new Scanner(System.in);
     Scanner scan2 = new Scanner(System.in);
     Scanner scan3 = new Scanner(System.in);
     System.out.println("Welcome to the Word Search Game! What is your name?");
     String p_name = scan.nextLine();
     Player mine = new Player(p_name);
     System.out.println(mine.setName(p_name));

     System.out.println("Are you playing alone or with friends? Enter Yes or No!");
     String choice = scan.nextLine();

     if (choice.equals("Yes")|| choice.equals("yes")){
        System.out.println("How many people, including you, will be playing?");
        int num_of_players = scan.nextInt();

        Player[] player_names = new Player[num_of_players];
        player_names[0] = mine;//In multi player turn, have a loop for each player so they can have a turn. Then whoever guess the most words wins.
        //Make an array to track the score?
        System.out.println("You'll be Player 1!");
        for (int i = 1; i < player_names.length; i++){
            System.out.println("Put their names in one at a time! The person you're entering right now will be Player " + (i+1) + "!");
            String p_name_2 = scan2.nextLine();
            player_names[i] = new Player(p_name_2);

        }
        System.out.println("Which board would you like to play? Choose between 1 and 12 then type in 'board_Num.csv! Ex: 'board_1.csv'.");
        String b_name = scan3.nextLine();

        GameBoardClass playGame = new GameBoardClass(b_name);
        String[][] for_game = playGame.boardCreator(b_name);
        System.out.println(playGame.displayBoard(for_game));
        System.out.println("The game is ON!");
        playGame.multiPlayerTurn(player_names);

     }
     else if (choice.equals("no") || choice.equals("No")){ //In an "else-if" to avoid an error with the multiplayer quit action
        System.out.println("Alrighty! Let's continue!");
        System.out.println("Which board would you like to play? Choose between 1 and 12 then type in 'board_Num.csv! Ex: 'board_1.csv'.");
        String b_name = scan.nextLine();

        GameBoardClass playGame = new GameBoardClass(b_name);
        String[][] for_game = playGame.boardCreator(b_name);

        System.out.println(playGame.displayBoard(for_game));
        System.out.println("The game is ON!");
        playGame.playerTurn();
     }




       
        
        
        

      


        
    }
}
