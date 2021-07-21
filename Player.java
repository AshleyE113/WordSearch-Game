//A simple player class that simply sets and gets the player's name
public class Player {

    private String name; //Give it attributes

    public Player(String name){
        this.name = name;
    }

    public String setName(String name){
        return "Welcome to the game " + name + "!";

    }
    public String getName(){
        return this.name;
    }
    
    
}
