package algorithm;

/**
 * This class provides an n-ary counter.<br>
 * For example if we have a 3ary-counter with length = 2, it will count
 * 
 * <pre>
 * 00
 * 01
 * 02
 * 10
 * 11
 * 12
 * 20
 * 21
 * 22
 * producing overflow
 * (restatring at zero)
 * 00
 * 01
 * ...
 * ...
 * </pre>
 * 
 * @author chrissy - date 30-03-2011
 * 
 */
public class NaryCounter {

    /**
     * the Nary counter counts decimal number of length {@link length} and each
     * entry from 0.. n-1
     */
    protected int n;

    /**
     * the Nary counter counts decimal number of length {@link length} and each
     * entry from 0.. n-1
     */
    protected int length;

    /**
     * This array provides the entries of the complete Nary number
     * 
     */
    protected int[] counterArray;

    /**
     * A variable, to check weather the last adding has caused an overflow
     */
    protected boolean overflow;

    /**
     * Initializes the counter
     * 
     * @param n
     *            count the entries from 0..n-1
     * @param length
     *            is the number of characters or entries of the counter
     */
    public NaryCounter(int n, int length) {
	super();
	this.n = n;
	this.length = length;
	this.counterArray = new int[this.length];
	this.overflow = false;
    }

    /**
     * sets all entries of the counter to 0
     */
    public void setZero() {
	for (int i = 0; i < this.length; i++)
	    this.counterArray[i] = 0;

    }

    /**
     * produces the standard string
     */
    public String toString() {
	String s = "";
	for (int i = this.length - 1; i >= 0; i--) {
	    s = s + this.counterArray[i];
	}

	return s;
    }

    /**
     * Adds 1 to the actual state of the counter.<br>
     * If an overflow is produced inside you can check this with the method
     * {@link producedOverflow}
     */
    public void addOne() {
	// the arrayIndex is in 0...this.length-1
	int k = this.length - 1;
	/*
	 * walk down the entries which are n-1. Note: Here that the index entry
	 * for k == -1 will not be evaluated since then k >= 0 is already false.
	 * Thus Java wont evaluate counterArray[k] == n -1 for k =< -1.
	 */
	while ((k >= 0) && (this.counterArray[k] == this.n - 1)) {
	    k--;
	}
	/*
	 * if the while loop breaks either counterarray[k] != n-1 or k == -1. If
	 * k == -1 then all entries of the counter are n-1. Thus we will get an
	 * overflow if we iterate further.
	 */
	this.overflow = (k == -1);
	if (!this.overflow) { // hence we can increase the counter
	    int l = 0;

	    /*
	     * if the number ends with some n-1, we climb the index up until we
	     * find the first index which is not n-1; this must exist
	     * since we dont have an overflow
	     */
	    while (this.counterArray[l] == this.n - 1) {
		l++;
	    }

	    /*
	     * when the entry at position number 0 is not n-1 we easily add one
	     * to the pos numb 0.
	     */
	    if (l == 0)
		this.counterArray[l]++;
	    else {
		/*
		 * l > 0, then the index l was increased by the while-loop to
		 * the lowest position which is not n-1. Thus we increase the
		 * current number at position l by 1.
		 */
		this.counterArray[l]++;
		/*
		 * Now we have to run back until array position 0 and set all
		 * n-1 entries we climbed up to 0 since we increased the index
		 * at position l.
		 */
		/*
		 * At the current position of l we have increased the value.
		 * Thus we need first to decrease l before we set an entry to 0;
		 * otherwise we would overwrite our self increased entry
		 */
		int down = l - 1;
		while (down >= 0) {
		    this.counterArray[down] = 0;
		    down--;
		}

	    }
	} // if(!overflow)
	else
	    // Overflow
	    this.setZero();
    } // addOne

    /**
     * 
     * @return a boolean which is true iff an overflow inside the {@link addOne}
     *         -method has appeared.
     */
    public boolean producedOverflow() {
	return this.overflow;
    }

}
