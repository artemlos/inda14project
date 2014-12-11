import java.util.Random;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes. (modified by Artem Los 2014.12.10)
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    
	private Room outside, D, E, F, TekHogUnderground, D2, D3, D3_1, DCompLab;
	
	private static final int MAX_TWEETS = 20;
	private int tweetCount = MAX_TWEETS;
	
	
    private void createRooms()
    {
//        Room outside, theater, pub, lab, office;


    	
    	Person Jockum  = new Person("Jockum", x-> JockumResponse(x));
    	
    	Person Armada = new Person("Armada", x-> "Please work for Armada!");
    	
    	Person Jonas = new Person("Jonas", x-> JonasResponse(x));
    	
    	outside = new Room("outside the main entrace of KTH");
    	D = new Room("in the D building");
    	D2 = new Room("on the second floor in D building.");
    	D3 = new Room("on the third florr in D building.");
    	D3_1 = new Room("in the lecture hall D1 where we've just started with single variable calculus. You are late.");
    	
    	DCompLab = new Room("in the computer laboratory.");
    	E = new Room("in the E building");
    	F = new Room("in the F building");
    	TekHogUnderground = new Room("at Tekniska Högskolan Underground station");
    	
    	D3_1.setPerson(Jockum);
    	outside.setPerson(Armada);
    	E.setPerson(Jonas);
    	D.setPerson(Armada);
    	
    	outside.setExit("down", TekHogUnderground);
    	outside.setExit("west", E, "komm14");
    	outside.setExit("east", D);
    	outside.setExit("north", F);
    	
    	E.setExit("east", outside);
    	D.setExit("west", outside);
    	D.setExit("up", D2);
    	
    	D2.setExit("up", D3);
    	D2.setExit("down", D);
    	D3.setExit("north", D3_1);
    	D3.setExit("south", DCompLab);
    	D3.setExit("down", D2);
    	
    	D3_1.setExit("south", D3);
    	DCompLab.setExit("north", D3);
    	
    	
    	
    	TekHogUnderground.setExit("up", outside);
    	
    	currentRoom = TekHogUnderground; 
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Java Adventure at KTH");
        System.out.println("Java Adventure at KTH is a new, incredibly interesting adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                TransformUsers();
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case ASK:
            	doAsk(command);
            	break;
            case TWEET:
            	doTweet(command);
            	break;
            case UNLOCK:
            	System.out.println("What should we unlock?");
            	break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    

    private void doAsk(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ask what?");
            return;
        }
        
        if(currentRoom.getPerson() != null)
        {
        	//System.out.println(command.getSecondWord());
        	System.out.println(currentRoom.getPerson().getResponse(command.getSecondWord()));
        	
        }
        

    }
    
    private void doTweet(Command command)
    {
    	if(command.getSecondWord().toLowerCase().contains("komm14"))
    	{
    		tweetCount--;
    	}
    	else
    	{
    		System.out.println("Don't forget the 'komm14' tag.");
    	}
    	
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
	private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        boolean success = true;
        String direction = command.getSecondWord();

        // Try to leave current room.
        Door nextRoomDoor = currentRoom.getDoor(direction);

        if (nextRoomDoor == null || !nextRoomDoor.hasDoor()) {
            System.out.println("Are you tryin' to open a door that doesn't exist? That might be hard.");
            success=false;
        }
        else if(nextRoomDoor.hasPassword())
        {
        	System.out.println("The room is locked. Enter the password. Use the 'unlock' command together with the password");
            Command pass = parser.getCommand();
            if(nextRoomDoor.doUnlock(pass.getSecondWord()))
            {
            	System.out.println("Success. The door is unlocked.");
            	success =true;
            }
            else
            {
            	System.out.println("Failure. The door is still unclocked.");
            	success=false;
            }
       
        }
        
        if(success)
        {
            currentRoom = nextRoomDoor.getRoom();
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    private String JockumResponse(String input)
    {
    	input = input.toLowerCase();
    	if(input.contains("leibniz"))
    	{
    		return "Of course! His notation is still used today. The key to the E-Building is 'komm14'. Good luck";
    	}
    	else if (input.contains("hi"))
    	{
    		return "Hi! What can I help you with?";
    	}
    	else if(input.contains("newton"))
    	{
    		return "Well, ....";
    	}
    	else if(input.contains("e") && input.contains("building"))
    	{
    		return "You must have a key to enter the E building. You must answer one of my questions. \n Q: Who made the largest contribution to Calculus? Leibniz or Newton?";
    	}
    	else
    	{
    		return "I don't understand your question.";
    	}
    	
    }
    
    private void TransformUsers()
    {
    	Random rn = new Random();
    	
    	if(!rn.nextBoolean())
    	{
    		Person temp = E.getPerson();
    		E.setPerson(temp);
    		outside.setPerson(temp);
    	}
    }
    
    private String JonasResponse(String input)
    {
    	if(!currentRoom.equals(outside))
    	{
	    	if(tweetCount  == MAX_TWEETS)
	    	{
	    		E.setExit("east", null);
	    		return "Hi! The course has now started and all doors are locked. In order to pass the course, "+ MAX_TWEETS+ " tweets have to be written. Use the 'tweet' command to perform this task. When done, tell me.";
	    	}
	    	else if (tweetCount > 0)
	    	{
	    		return "You have " + tweetCount + " left!";
	    	}
	    	else
	    	{
	    		E.setExit("east", outside);
	    		E.setPerson(null);
	    		return "Great, you've passed the course! Thank you for being such a benevolent person by contributing to research! Type 'east' to exit the room";
	    	}
    	}
    	else
    	{
    		return "We have a lecture today in E. You can either wait or ask Jockum about the key to the door if you want to get in earlier.";
    	}
    	
    }
}
