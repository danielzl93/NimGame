/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.3, 2.4 and 2.5 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
	
	Coded by: Jin Huang
	Modified by: Jianzhong Qi, 29/04/2015
*/

public class NimAIPlayer extends NimPlayer implements Testable {
	// you may further extend a class or implement an interface
	// to accomplish the task in Section 2.3	

	private int removal = 0;	// number of Stones AI removed
	
	// contstuctor inhere from NimPlayer
	public NimAIPlayer(String userID, String fName,String gName) {
		super(userID,fName,gName);	
	}
	
	// AI's victory guaranteed algorithm returns the number of Stones AI should remove
	public int removeStone(int curStone, int upStone){
		int i = (curStone - 1)/(upStone + 1);
		removal = curStone - (i * (upStone + 1) + 1);
		if (removal == 0){
			return removal = 1;
		}else {
			return removal;
		}
	}
	
	// Just to make the complier happy, the abstract method needed to be overrided
	public int removeStone(int remove){
		return removal;
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}
}
