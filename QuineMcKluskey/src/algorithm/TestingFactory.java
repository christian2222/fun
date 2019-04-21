package algorithm;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;

/**
 * This class provides some useful parts to test parts of the QMC algorithm.<br>
 * The word "arbitrary" in the method names indicates that the monoms we work on
 * can have arbitrary entries hence also merged entires "-" or 2.<br>
 * The word "binary" in the method names indicates that we only work with binary
 * monoms, ie. monoms which have only entries 0 or 1.
 * 
 * @author chrissy
 * 
 */
public class TestingFactory {

    /**
     * a monom for some tests
     */
    protected static Monom monom = new Monom();

    /**
     * in the main method we decide which tests are running
     * 
     * @param args
     *            the standard parameters
     */
    public static void main(String[] args) {
	testSorting();
	//testStreams();
	// testPowerSet();
    }

    public static void testStringlength(String s) {
	System.out.println(s.length());
	System.out.println("hello".charAt(4));
    }

    public static void testSorting() {
	int count = 2;
	NaryCounter bc = new NaryCounter(2,Constraints.maxIndex-1);
//	BinaryCounter bc = new BinaryCounter();
	String signature = "";
	for (int i = 1; i < Constraints.maxIndex; i++)
	    signature = signature + "X";
	System.out.println(signature);
	MonomSet monomSet = new MonomSet(signature);
	String s = "";
	int k = (int) MyMath.intXhochY(2, Constraints.maxIndex - 1);
	/*
	 * note: we can divide k int the for-loop by 2,4,...2^t then we get not
	 * all the monoms
	 */
	for (int i = 0; i < k; i++) {
	    s = bc.toString();
	    monom = MonomFactory.createMonomFrom0to2(s);
	    if (monom != null) {
		monomSet.add(monom);
		System.out.println("monom " + monom.toStandardString()
			+ " added sucessfully.");
	    }

	    bc.addOne();
	}
	// ensure that such a set exists
	if ((0 <= count) && (count < Constraints.maxIndex)) {

	    MonomOnesSubset mos = new MonomOnesSubset(monomSet, signature);
	    System.out.println("Ouput all Monoms with " + count + " 1s:");
	    TreeSet<Monom> treeset = mos.getLiMk(count);
	    // Iterator it = treeset.descendingIterator();
	    for (Iterator it = treeset.descendingSet().iterator(); it.hasNext();) {
		System.out.println((Monom) it.next());
	    }
	} else
	    System.out.println("Didnt find a set with " + count + " ones.");

    }


    /**
     * tests the powerset-sorting with arbitrary monoms. These monoms can also
     * have an merged entry 2 or "-"
     */
    public static void testArbitraryPowersetSorting() {

	MonomList mList = createArbitraryRandomizedMonomList(40);
	MonomPowerSet mps = new MonomPowerSet();
	mps.add(mList);
	System.out.println(mps.toString());

    }

    /**
     * tests the powerset-sorting with only binary monoms, ie. monoms have only
     * entries of 0 or 1 and hence are binary monoms
     */
    public static void testBinaryPowersetSorting() {
	MonomList mList = createBinaryRandomizedMonomList(40);
	MonomPowerSet mps = new MonomPowerSet();
	mps.add(mList);
	System.out.println(mps.toString());

    }

    /**
     * 
     * @return a monom with randomized entries between 0..2. Such monoms are
     *         called "arbitrary" monoms.
     */
    public static Monom createArbitraryRandomizedMonom() {
	Monom m = new Monom();
	int k = 0;
	Random rand = new Random();
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    k = Math.abs(rand.nextInt() % 3);
	    m.setIntMonome(i, k);
	}

	return m;
    }

    /**
     * 
     * @return a monom with randomized entries only 0 or 1. No deleted entries
     *         are produced.<br>
     *         Such monoms are called "binary" monoms.
     */
    public static Monom createBinaryRandomizedMonom() {
	Monom m = new Monom();
	int k = 0;
	Random rand = new Random();
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    k = Math.abs(rand.nextInt() % 2);
	    m.setIntMonome(i, k);
	}

	return m;
    }

    /**
     * 
     * @return a monomList with k arbitrary randomized monoms
     */
    public static MonomList createArbitraryRandomizedMonomList(int k) {
	MonomList mList = new MonomList();
	for (int i = 0; i < k; i++) {
	    mList.add(createArbitraryRandomizedMonom());
	}
	return mList;
    }

    /**
     * 
     * @return a monomList with k binary randomized monoms
     */
    public static MonomList createBinaryRandomizedMonomList(int k) {
	MonomList mList = new MonomList();
	for (int i = 0; i < k; i++) {
	    mList.add(createBinaryRandomizedMonom());
	}
	return mList;
    }


    /**
     * tests the NaryCounter
     */
    public static void testNaryCounter() {
	NaryCounter nCounter = new NaryCounter(7, 3);
	for (int i = 0; i < 350; i++) {
	    nCounter.addOne();
	    if (nCounter.producedOverflow())
		System.out.println("Overflow!");
	    System.out.println(nCounter);
	}

    }

    /**
     * test the MonomPowerSet
     */

    public static void testPowerSet() {
	MonomPowerSet mps = new MonomPowerSet();
	System.out.println(mps.toString());
    }

    public static void createRandomizedMonomSet() {
    }

    public static void testStreams() {
	File file = new File("test.txt");
	FileOutputStream fos = null;
	try {
	    fos = new FileOutputStream(file);
	} catch (Exception e) {
	    System.out.println("Dateiporblem");
	}
	PrintStream ps = new PrintStream(fos);
	PrintStream qs = new PrintStream(fos);
	ps.println("Hello");
	qs.println("Goodbye");
	ps.close();
	qs.close();
    }
    
    /**
     * construts all monoms (ie of length 5)<br>
     * from<br>
     * 00000<br>
     * 00001<br>
     * 00010<br>
     * 00011<br>
     * .....<br>
     * .....<br>
     * 11100<br>
     * 11101<br>
     * 11110<br>
     * 11111<br>
     * 
     * @return saves the complete powerset in this list
     */
    public static MonomList constructThePowerSet() {
	MonomList ml = new MonomList();
	Monom m;
	NaryCounter binaryCounter = new NaryCounter(2, Constraints.maxIndex-1);
	int maxPS = (int) Math.pow(2, Constraints.maxIndex-1);
	for(int i = 0; i < maxPS; i++) {
	    m = StringWorks.binaryStringToMonom(binaryCounter.toString());
	    ml.add(m);
	    binaryCounter.addOne();
	}
	return ml;
    }
}
