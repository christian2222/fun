package random;

import data.*;

import java.awt.Color;
import java.util.Random;

public class Generation {

    protected static Random random = new Random();


    /**
     * 
     * @return a Item i with size between 0 and @see Bin.maxsize
     */
    public static Item getRandomItem() {
	int size = random.nextInt();
	size = Math.abs(size % Bin.maxSize);
	Item i = new Item(size);
	return i;
    }
    
    /**
     * 
     * @param maxInt
     * @return a natural number between 0..maxInt-1
     */
    public static int getRandomInt(int maxInt) {
	int rInt = random.nextInt();
	int retInt = Math.abs(rInt % maxInt);
	return retInt;
    }
    
    public static Color getRandomColor() {
	Color c = new Color(0,0,0);
	int r = getRandomInt(9);
	switch(r) {
	    case 0:
		c = Color.BLUE;
		break;
	    case 1:
		c = Color.CYAN;
		break;
	    case 2:
		c = Color.GREEN;
		break;
	    case 3:
		c = Color.MAGENTA;
		break;
	    case 4:
		c = Color.ORANGE;
		break;
	    case 5:
		c = Color.PINK;
		break;
	    case 6:
		c = Color.RED;
		break;
	    case 7:
		c = Color.WHITE;
		break;
	    case 8:
		c = Color.YELLOW;
		break;
	default:
	    c = Color.WHITE;			
		break;		
	} // switch
	
	return c;
    }
}
