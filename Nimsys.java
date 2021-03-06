/************************************************
 *	COMP90041 Project 3, 25 May 2017
 *	Nim game program for project 3 by 
 * 		Liang Zhang
 * 		Student number: 695353
 * 		liangz2@student.unimelb.edu.au
 * 		Programming is Fun! ^ ^
 *	Notes: the previous Project 3 I submitted has different class
 *			which is not used in the newest version, please ignore!
 *			since I cant delete it from the submit channel >_<
************************************************/

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;


public class Nimsys{
		
	public static void main (String[] args){	
		
		GameManage pm = new GameManage();
		
		// check whether the saved data file exists
		File saved = new File("players.dat");
		if (saved.exists()){
			try {
				// read the saved data file and assign to the GameManage object
				ObjectInputStream inputStream = new ObjectInputStream
					(new FileInputStream("players.dat"));
					pm = (GameManage)inputStream.readObject();
			}
			catch (ClassNotFoundException e){
				System.out.println("no class.");
			}
			catch (IOException e){
				System.out.println("problems input.");
			}
		}
		
		// reset the playable state each time running the game
		pm.setPlay(true);
		while (pm.getPlay()){
			pm.process();	// read command and act accordingly
			
			// playable state is false, try to save data
			if (!pm.getPlay()){
				try {
					ObjectOutputStream outputStream = 
						new ObjectOutputStream (new FileOutputStream("players.dat"));
					outputStream.writeObject(pm);
					outputStream.close();					
					System.out.println();
					System.exit(0);
				}
				catch (IOException e){
					System.out.println("Problems!!!");
				}
			}
			System.out.print("\n$");
		}
	}
}