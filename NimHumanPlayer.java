// Abstract subclass from NimPlayer

public class NimHumanPlayer extends NimPlayer{
	private int removal = 0;	//records the number of stones human made
	
	// contstuctor inhere from NimPlayer
	public NimHumanPlayer(String userID, String fName,String gName){
		super(userID,fName,gName);
	}
	
	//records the number of stones human made
	public int removeStone(int remove){
		return removal = remove;
	}
	
	// Just to make the complier happy, the abstract method needed to be overrided
	public int removeStone(int curStone,int upStone){
		return removal;
	}
	
}