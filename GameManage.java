
import java.io.Serializable;                                     
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;

// the core action of the game
public class GameManage implements Serializable{
	private NimPlayer[] players;	// object array of players
	private int count;				// count how many players exist
	private int TOPTEN = 10;		// only the top ten players get display
	private int MAX = 100;			// only max 100 players involved
	
	private final int NOTEXIST = -1;	// return player not exist in the array
	private boolean play = true;		// playable state
	private static Scanner keyboard;
	
	// constuctor
	public GameManage(){
		System.out.println("Welcome to Nim\n");
		System.out.print("$");
		count = 0;					// initial player numbers
		players = new NimPlayer[MAX];
		keyboard = new Scanner(System.in);
	}	
	
	// read command and act accordingly
	public void process(){
		
		String command = keyboard.next();
		try {
			if (command.equalsIgnoreCase("addplayer")){
				addPlayer();
			}else if (command.equalsIgnoreCase("addaiplayer")){
				addAiPlayer();
			}else if (command.equalsIgnoreCase("removeplayer")){
				removePlayer();
			}else if (command.equalsIgnoreCase("editplayer")){
				editPlayer();
			}else if (command.equalsIgnoreCase("resetstats")){
				resetStats();
			}else if (command.equalsIgnoreCase("displayplayer")){
				displayPlayer();
			}else if (command.equalsIgnoreCase("rankings")){
				rankings();
			}else if (command.equalsIgnoreCase("startgame")){
				startGame();
			}else if (command.equalsIgnoreCase("exit")){
				play = false;	// change playable state
			}else {
				throw new Exception("\'" + command +"\' is not a valid command.");
			}
		}
		catch (Exception e){
			keyboard.nextLine();	// ignore the input line after invalid command
			System.out.println(e.getMessage());
		}
	}
	
	// check whether player exists, return its index in the array if true
	// return NOTEXIST (-1) if false
	public int exist(String userName){
		for (int i = 0;i < count; i++){
			
			// search through the array by checking userID
			if (players[i].getID().equals(userName)){
				return i;
			}
		}
		return NOTEXIST;
	}
	
	// add human player to the array
	public void addPlayer(){
		String nameLine = keyboard.nextLine();
		String delimiters = ", ";
		StringTokenizer nameSet = new StringTokenizer(nameLine,delimiters);
		
		// check valid arguments
		int arguCount = nameSet.countTokens();
		try {
			if (arguCount < 3){
				throw new Exception ("Incorrect number of arguments supplied to command.");
			}
			String userID = nameSet.nextToken();
			String fName = nameSet.nextToken();
			String gName = nameSet.nextToken();
		
		//	check if userID not exist, assign new player to array
			if (exist(userID)<0){
				NimHumanPlayer player = new NimHumanPlayer(userID,fName,gName);
				players[count] = player;
				count ++;	//	update current number of players
			}else{
				System.out.println("The player already exists.");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}	
	
	// add AI player to the array
	public void addAiPlayer(){
		String nameLine = keyboard.nextLine();
		String delimiters = ", ";
		StringTokenizer nameSet = new StringTokenizer(nameLine,delimiters);
		
		// check valid arguments
		int arguCount = nameSet.countTokens();
		try {
			if (arguCount < 3){
				throw new Exception ("Incorrect number of arguments supplied to command.");
			}
			String userID = nameSet.nextToken();
			String fName = nameSet.nextToken();
			String gName = nameSet.nextToken();
		
		//	check if userID not exist, assign new player to array
			if (exist(userID)<0){	
				NimAIPlayer player = new NimAIPlayer(userID,fName,gName);
				players[count] = player;
				count ++;	//	update current number of players
			}else{
				System.out.println("The player already exists.");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	// remove player from save data
	public void removePlayer(){
		String userLine = keyboard.nextLine();
		
		// check whether remove all players 
		if (userLine.equals("")){	// empty string
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String confirm = keyboard.next();
			if (confirm.equalsIgnoreCase("y")){
			// call the method to remove all players
				for (int i = 0; i <= count; i++){
					players[i] = null;
				}
				count = 0;
			}
		}else {	// remove certain player
			StringTokenizer id = new StringTokenizer(userLine);
			String userID = id.nextToken();
		
			if (exist(userID) < 0){
				System.out.println("The player does not exist.");
			}else{
				for (int i = 0; i < count; i++){
					if (players[i].getID().equals(userID)){
					
					//	after username located, update current index's player's
					//	information by copying from next index
						for (int j = i; j < count - 1; j++){
							players[j] = players [j+1];
						}
						count--;	//	update current number of player
						break;
					}
				}
			}		
		}
	}
	
	// edit player's info
	public void editPlayer(){
		String userLine = keyboard.nextLine();
		String delimiters = ", ";
		StringTokenizer nameSet = new StringTokenizer(userLine,delimiters);
		int arguCount = nameSet.countTokens();
		try {
			if (arguCount < 3){
				throw new Exception ("Incorrect number of arguments supplied to command.");
			}
			String userID = nameSet.nextToken();
			String fName = nameSet.nextToken();
			String gName = nameSet.nextToken();
		
			int location = exist(userID);	//	locate index of the target player
			if (location < 0){
				System.out.println("The player does not exist.");
			}else {
				players[location].setName(fName,gName);	// update target's info
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void resetStats(){
		String userLine = keyboard.nextLine();
		if (userLine.equals("")){
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String confirm = keyboard.next();
			if (confirm.equalsIgnoreCase("y")){
			// call the method to remove all players
				for (int i = 0; i <= count; i++){
					players[i].setStats(0,0,0);
				}
			}
		}else {
			StringTokenizer id = new StringTokenizer(userLine);
			String userID = id.nextToken();
		
			if (exist(userID) < 0){
				System.out.println("The player does not exist.");
			}else{
				for (int i = 0; i < count; i++){
					if (players[i].getID().equals(userID)){
						players[i].setStats(0,0,0);
					}
				}
			}		
		}
	}
	
	// display player's info
	public void displayPlayer(){
		String userLine = keyboard.nextLine();
		StringTokenizer IDSet = new StringTokenizer(userLine);
		String ID;
		
		// check whether display certain or all
		if (IDSet.hasMoreTokens()){
			ID = IDSet.nextToken();	// display certain
		}else{
			ID = "";	// display all
		}
		if (ID.equals("")){   	// display all
			NimPlayer[] toSort = Arrays.copyOf(players,count);
			Arrays.sort(toSort);
		
			for (int i = 0;i<count;i++){
				System.out.println(toSort[i]);
			}
		}else { // display certain
			if (exist(ID) < 0){
				System.out.println("The player does not exist.");
			}else{
				for (int i = 0; i < count; i++){
					if (players[i].getID().equals(ID)){
						System.out.println(players[i]);
						break;
					}
				}
			}		
		}
	}

	
	// display rankings 
	public void rankings(){
		String userLine = keyboard.nextLine();
		StringTokenizer orderSet = new StringTokenizer(userLine);
		String order;
		
		// check the order of rankings, default is descending 
		if (orderSet.hasMoreTokens()){
			order = orderSet.nextToken();
		}else{
			order = "";
		}
		
		// only top ten gets display
		int length;
		if (count<TOPTEN){
			length = count;
		}else{
			length = TOPTEN;
		}
		NimPlayer[] toSort = Arrays.copyOf(players,length);
		Arrays.sort(toSort, new Comparator <NimPlayer>() {
        
			//	override compare
			public int compare(NimPlayer p1, NimPlayer p2) {
				
				//	compare winning ratio 
				if(p1.getRatio()<p2.getRatio()){
					
					if (order.equals("")||order.equals("desc")){
					//	smaller ratio place after 
						return 1;	// default order
					}else {
						return -1;	// ascending order
					}
				}else if(p1.getRatio() == p2.getRatio()){
					
					// compare username if ratio same
					return p1.getID().compareTo(p2.getID());
				}else {
					if (order.equals("")||order.equals("desc")){
						return -1;	// default order
					}else {
						return 1;	// ascending order
					}
				}
			}
        });

		//	print out the rankings
        for (int i = 0;i<length;i++){
			String ratio = String.format("%d%%", Math.round(toSort[i].getRatio()*100));
			String pRatio = String.format("%-5s",ratio);
			System.out.printf("%s| %02d games | %s %s\n",
							pRatio, toSort[i].getNumP(),
							toSort[i].getGN(),toSort[i].getFN());
		}
	}
	
	
	// play a game 
	public void startGame(){
		String nameLine = keyboard.nextLine();
		String delimiter = ", ";
		StringTokenizer nameSet = new StringTokenizer(nameLine,delimiter);
		
		// check valid arguments
		int arguCount = nameSet.countTokens();
		try {
			if (arguCount < 4){
				throw new Exception ("Incorrect number of arguments supplied to command.");
			}
		// store information temporary, assign to real variable after username check exist
			int iniStone = Integer.parseInt(nameSet.nextToken());
			int upStone = Integer.parseInt(nameSet.nextToken());
			String player1 = nameSet.nextToken();
			String player2 = nameSet.nextToken();
		
			int p1Locate = exist(player1);
			int p2Locate = exist(player2);
			if (p1Locate != NOTEXIST && p2Locate != NOTEXIST){
				
				/* players exist, constuct NimGame to play, 
				the reason that not constuct at the beginning is that when running
				the project, user may not plan to play a game, they might just looking for
				stats hence only constuct NimGame when actually play.*/
				NimGame gamePlay = new NimGame(iniStone,upStone,
					players[p1Locate],players[p2Locate]);
				while(gamePlay.getCStone()>0){
					gamePlay.play();	// print out prompts
					// the number of stones remove
					int removal = numRemove(gamePlay,players[p1Locate],players[p2Locate]);
					gamePlay.process(removal);	// update stones on table
				}
					// if player exist, call method 'wins' from NimGame
				gamePlay.wins();	// print out the winner
			}else{
				System.out.println("one of player not exist.");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}	
	
	/*	return the number of stones removed base on their player type
		AI has algorithm according to it and human reads input*/
	public int numRemove(NimGame gamePlay, NimPlayer player1, NimPlayer player2){
		int curStone = gamePlay.getCStone();
		int upStone = gamePlay.getUStone();
		int removal = 0;
		boolean invalid = false;
		while (!invalid){
			try {
				if (gamePlay.getNumTurns()%2 == 0){	// player 1's turn
					if (player1 instanceof NimHumanPlayer){
						removal = player1.removeStone(keyboard.nextInt());
					}else{
						removal = player1.removeStone(curStone,upStone);
					}
				}else {
					if (player2 instanceof NimAIPlayer){// player2's turn 
						removal = player2.removeStone(curStone,upStone);
					}else{
						removal = player2.removeStone(keyboard.nextInt());
					}
				}
				invalid = true;
			}
			catch (InputMismatchException e){
				keyboard.nextLine();
				invalid = false;
				System.out.println("Invalid move. You must remove between 1 and " +
					upStone+" stones.\n");
			}
		}
		return removal;
	}
	
	public boolean getPlay(){
		return play;
	}
	
	public boolean setPlay(boolean state){
		return play = state;
	}
	
}