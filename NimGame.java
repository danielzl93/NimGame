
import java.io.Serializable;

//	Gaming process class
public class NimGame implements Serializable{
	private int iniStone;	// initial stones on table
	private int curStone;	// current stones on table
	private int upStone;	// max stones allowed to be removed

	private NimPlayer player1;
	private NimPlayer player2;
	private int numTurns;	// overall number of turns, use to determined players' turn
	
	// constructor
	public NimGame(int iniStone,int upStone, NimPlayer player1, NimPlayer player2){	
			this.iniStone = iniStone;
			this.curStone = iniStone;
			this.upStone = upStone;
			this.player1 = player1;
			this.player2 = player2;
			this.numTurns = 0;
			setNumPlay(player1);	// update number of play
			setNumPlay(player2);
			printInfo();
	}	
	
	// new game starting prompts
	public void printInfo(){
		System.out.println("\nInitial stone count: " + iniStone);
		System.out.println("Maximum stone removal: " + upStone);
		System.out.println("Player 1: "+ player1.getGN() +" "+ player1.getFN());
		System.out.println("Player 2: "+ player2.getGN() +" "+ player2.getFN());
		System.out.println();
	}
	
	// new turn starting prompts
	public void play(){
		
		System.out.print(curStone + " stones left:");
		for (int i = 0;i<curStone;i++){
			System.out.print(" *");
		}
		/* player1 move first & recorded the number of turn is 0
		 * so for every even number of turns up to, it is player1's turn
		 */
		if (numTurns%2==0){
			System.out.println("\n" + player1.getGN() +
				"'s turn - remove how many?\n");
		}else {
			System.out.println("\n" + player2.getGN() +
				"'s turn - remove how many?\n");
		}
	}

	//	calculation process for Stones on table
	public void process(int removal){
		
		//	check and update boundary 
		if (curStone < upStone){
			upStone = curStone;
		}
		try {
			if (removal >0 && removal <= upStone){
				curStone = curStone - removal;
				numTurns++;	//	update numTurns
			}else {
				throw new Exception("Invalid move. You must remove between 1 and " +
					upStone+" stones.\n");
			}
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//	print out the winner
	public void wins(){
			
		System.out.println("Game Over");
		if (numTurns%2==0){
			System.out.println(player1.getGN() +" "+ player1.getFN()
				+ " wins!");
			setNumWin(player1);	//	update number of winnings
		}else {
			System.out.println(player2.getGN() +" "+ player2.getFN()
				+ " wins!");
			setNumWin(player2);
		}			
			setWinRatio(player1);	//	update the winning ratio;
			setWinRatio(player2);
		
	}
	
	public void setNumPlay(NimPlayer player){
		player.setNumP(1 + player.getNumP());
	}
	
	public void setNumWin(NimPlayer player){
		player.setNumW(player.getNumW() + 1);
	}
	
	public void setWinRatio(NimPlayer player){
		double win = (double) player.getNumW();
		double play = (double) player.getNumP();
		player.setRatio(win/play);
	}
	
	public int getCStone(){
		return curStone;
	}
	
	public int getUStone(){
		return upStone;
	}
	
	public int getNumTurns(){
		return numTurns;
	}
}