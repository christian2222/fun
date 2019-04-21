package algorithm;

/**
 * These constraints are used during the complete time of the program
 * and of the algorithm
 * 
 * @author chrissy - 29-03-2011
 *
 */
public class Constraints {
    
    /**
     * A monom has the length of (maxIndex-1).
     * See the monom-class for more information
     */
    public static int maxIndex = 6; // maximum is 11 ~> length=10
    
    public final static int n = maxIndex;

    /**
     * coding monom constants
     */
    public static final int mTRUE = 1; // 1 coding True
    public static final int mFALSE = 0; // 0 coding False
    public static final int UnUsed = 2; // 2 coding NotUsed

}
