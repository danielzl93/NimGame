import java.io.Serializable;

// implements comparable to override compareTo() method
public abstract class NimPlayer implements Serializable, Comparable<NimPlayer> {	
	private String userName;	// userID
	private String familyName;	// player's familyName
	private String givenName;	// player's givenName
	private int numPlay;		// how many games the player have played
	private int numWin;			// how many wins the player made
	private double winRatio;	// the player's winning ratio
	
	// overloaded abstract method 
	public abstract int removeStone(int remove);
	public abstract int removeStone(int curStone, int upStone);
	
	// constructor
	public NimPlayer(String userID, String fName,String gName){	
		userName = userID;
		familyName = fName;
		givenName = gName;
		numPlay = 0;	//initialize stats
		numWin = 0;
		winRatio = 0;
	}	
	
	public String getID(){
		return userName;
	}
	
	// accessor get givenName
	public String getGN(){
		return givenName;
	}
	
	// accessor get familyName
	public String getFN(){
		return familyName;
	}
	
	// accessor get numPlay
	public int getNumP(){
		return numPlay;
	}

	// accessor get newWin	
	public int getNumW(){
		return numWin;
	}
	
	// accessor get winRatio
	public double getRatio(){
		return winRatio;
	}

	// mutator set name	
	public void setName(String fName, String gName){
		this.familyName = fName;
		this.givenName = gName;
	}

	// mutator set numPlay	
	public void setNumP(int newNum){
		this.numPlay = newNum;
	}
	
	// mutator set newWin	
	public void setNumW(int newWin){
		this.numWin = newWin;
	}
		
	// mutator use to set stats		
	public void setStats(int plays, int wins, double ratio){
		this.numPlay = plays;
		this.numWin = wins;
		this.winRatio = ratio;
	}
	
	public String toString(){
		return userName +"," + givenName + "," + familyName
			+","+numPlay+" games,"+numWin+" wins";
	}
	
	public void setRatio(double newRatio){
		this.winRatio = newRatio;
	}	
	
	//override compareTo
	public int compareTo(NimPlayer otherPlayer){
		return userName.compareTo(otherPlayer.userName);
	}
	
}