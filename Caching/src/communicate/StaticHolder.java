package communicate;

import datastructures.Page;
import gui.MainWindow;

public final class StaticHolder {

	
	public static Page selectedPage = new Page(); 
	

	public static boolean nextStep = false;
		
	//public static String input = "abacdkljasfiouasfnmyvkljafiuafhjsajfkljiowfjlaskfpoebcbdbebabcbdbecbdbabdbcbdbebadb";
	//public static String input = "abcdefghijklmnopqrstuvwxyzabababababababababbababababababababa";
	
	
	
	public static ByHand<Page> algortihm = new ByHand<Page>(40);
	public static String input = algortihm.printSequence();
	
	public static MainWindow mainWindow;


	
}
