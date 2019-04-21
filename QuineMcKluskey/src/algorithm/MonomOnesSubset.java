package algorithm;

import java.util.*;

/**
 * By the implementation of MonomSet we know, that i.e. a list of the following
 * monoms is in the same monomSet, since they have the same signature X-X-XX:
 * 
 * <pre>
 * 0-0-00
 * 0-1-00
 * 0-1-10
 * 1-0-01
 * 1-1-11
 * </pre>
 * 
 * We now want to split this big MonomSet into MonomSubsets which depend on the
 * number of 1s inside the monoms. Algorithmically - due to the QuineMcKluskey -
 * we devide L_i^M into its subsets which contain a fixed number of ones; i.e.
 * L_i^M(k) would be the subset of L_i^M containing exactly k 1s.<br>
 * Thus we split the upper example into the subsets:
 * 
 * <pre>
 * 0-0-00 L_i^M(0)
 * ******
 * 0-1-00 L_i^M(1)
 * ******
 * 0-1-10 L_i^M(2)
 * 1-0-01
 * ******
 * 1-1-11 L_i^M(5)
 * </pre>
 * 
 * <b>Note</b>: L_i^M is fixed in the complete computation of this class, since
 * M represents the signature {@link signature}, which is a attribute of the
 * constructor and fixed for the whole class.
 * 
 * @author chrissy
 * 
 */
public class MonomOnesSubset {

    /**
     * The signature of one MonomOne subset is here defined as a constant. We do
     * this because we never want to change the signature.<br>
     * If we instanciate an Object of this class with the signature "XX-XX-X"
     * the whole Object only works with the given Signature. If we want a new
     * signature we will have to instanciate a new class.
     */
    protected final String signature;
    /*
     * arrays of generics arent possible
     */

    /**
     * We calsulate the maximum number of different sets.<br>
     * Note: We can use an integer, since the maximum length of a monom is 10.
     * Thus the maximum number of different ones that form the above descripted
     * subsets L_i^M(k); hence the maximum number of k is 2^10 = 1024.<br>
     * So an <b>int</b>-array is really large enough.
     */
    protected final int calculatedMaxNumber;

    /**
     * This is the list array, we will fill in the lists. Each list has a
     * different number of ones and each list represents one L_i^M(k) for each
     * k.
     */
    protected TreeSet[] lists;

    /**
     * We save the MonomSet given by the constructor in this variable to work on
     * the complete set L_i^M and search the entries with k 1s which then belong
     * to the subsets L_i^M(k).
     */
    protected MonomSet monomSet;

    public MonomOnesSubset(MonomSet monomSet, String signature) {
	super();
	this.monomSet = monomSet;
	this.signature = monomSet.getSignature();
	/*
	 * The variable calculatedMaxNumber is the index of the array lists. All
	 * 1s-subsets are stored there
	 */
	this.calculatedMaxNumber = this
		.getMaximumNumberOfDifferentEntries(this.signature) + 10;
	/*
	 * initialize the TreeSet array which contains the different sets
	 * L_i^M(k)
	 */

	this.lists = new TreeSet[this.calculatedMaxNumber];
	/*
	 * now we generate the different sets L_i^M(k) for eack k in the
	 * index-range.
	 */
	this.generateSets();
    }

    /**
     * We need now many sets - at maximum a complete Powerset.<br>
     * For example we have the signature 'XX-XX--X'.<br>
     * Thus we have 5 entries, which is returned by the method
     * {@link getNumberOfEntries}. Each of this entry can be 0 or 1. Thus we
     * will have to create 5 sets for this signature:
     * 
     * <pre>
     * 0 ones:
     * 00-00-0
     * 
     * 1 one:
     * 00-00-1
     * 00-01-0
     * 00-10-0
     * 01-00-0
     * 10-00-0
     * 
     * 2 ones:
     * 00-01-1
     * 00-10-1
     * ...
     * and so on
     * ...
     * ...
     * ...
     * until
     * ...
     * 
     * 4 ones:
     * 01-11-1
     * 10-11-1
     * 11-01-1
     * 11-10-1
     * 11-11-0
     * 
     * and
     * 
     * 5 ones:
     * 11-11-1
     * </pre>
     * 
     * In general we have to create 2^(Number of Xs in the signature)
     * sets. One set for each possible number of ones.<br>
     * Thus we will return the power of 2 and the possible numer of entries
     * {@link getNumberOfEntries}.<br>
     * Note: Inside the code we add 1 to the exponent to be sure that we have
     * enough sets.
     * 
     * @param sig
     *            a signature
     * @return an upper bound to the maximal number of possible different sets
     */
    public int getMaximumNumberOfDifferentEntries(String sig) {
	int k = 0;
	// adding 1 to save enough indices.
	k = (int) Math.pow(2, this.getNumberOfEntries() + 1);
	return k;
    }

    /**
     * 
     * @param sig
     *            the signature
     * @return the number of different entries = the number of 'X's in the
     *         signature
     */
    protected int getNumberOfEntries() {
	String sig = this.getSignature();
	int i = 0;
	for (int j = 0; j < sig.length(); j++) {
	    if (sig.charAt(j) == 'X')
		i++;
	}
	return i;

    }

    /**
     * Here comes the big (!) job of creating all sets.<br>
     * First we initialize the TreeSet-Array {@link lists} with the maximum
     * number of possible sets.<br>
     * (ToDo: correct 0..t ones subsets)
     * Then we fill {@link lists}[k] with the monoms which have k 1s. So
     * {@link lists}[k] = L_i^M(k) for each 0 <= k <= 2^t, where t is the number
     * of different entries for the signature initialized by the constructor.<br>
     * <b>Note</b>: The maximum number of different sets is 1024, see the
     * documentation of this class.
     */
    protected void generateSets() {
	Monom walk = null;
	int k = 0;
	/*
	 * the array is initialized by the maximum calculated number
	 */
	this.lists = new TreeSet[this.calculatedMaxNumber];
	// initialize the treesets
	for (int i = 0; i < this.calculatedMaxNumber; i++)
	    this.lists[i] = new TreeSet();
	for (Iterator<Monom> iterator = this.monomSet.getIterator(); iterator
		.hasNext();) {
	    walk = iterator.next();
	    k = walk.countOnes();
	    /*
	     * Attention: Here we need an object list, because the generics
	     * can't be used with arrays. But there is no Problem, since the
	     * lists-array only contains monoms for each index k.
	     */
	    this.lists[k].add(walk);
	}

	/*
	 * After the for-loop we can get the set L_i^M(k) by using the
	 * descendingIterator()-method of the array entry lists[k].
	 */
    }

    /**
     * Attention: inside we downcast implicit.
     * 
     * @param k
     *            the number of ones
     * @return the set L_i^M(k) of monoms with k ones
     */
    public TreeSet<Monom> getLiMk(int k) {
	return this.lists[k];
    }

    /**
     * @return the Signature of this completet set
     */
    public String getSignature() {
	return this.signature;
    }

}
