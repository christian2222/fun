package algorithm;

import java.util.*;

/**
 * This is a Monom-Factory<br>
 * <br>
 *         This Factory is used to manage and work on monoms.<br>
 *         The most important thing here is to melt two monoms like 0011 and
 *         0111 to 0-11.<br>
 *         This is exactly the melting process in the QMC-Algorithm.
 * 
 * @author chrissy - date 29-03-2011
 * 
 */
public class MonomFactory {

    /**
     * Used to melt two monoms to one.<br>
     * Only used by the methods {@link melt} and {@link getMelted}
     */
    protected static Monom meltMonom = null;

    /**
     * The sucess-variable for the {@link melt} method
     */
    protected static boolean sucess;

    /**
     * reports the errors in the melting process 
     * ToDo: with booleans inside the
     * melt method, to check which error has accured!
     */
    protected static String report = "";

    /**
     * This is the important melting process of the QMC-Algorithm. The melted
     * monom is returned by the method {@link getMelted} of the factory.
     * 
     * @param m1
     *            first monom
     * @param m2
     *            second monom
     * @return a boolean value which represents the sucess of the operation.
     */
    public static boolean melt(Monom m1, Monom m2) {
	sucess = true;
	int bitA = 0;
	int bitB = 0;
	boolean bitsGood = true;
	// First we check weather the two monoms have the same length
	sucess = sucess && (m1.getLength() == m2.getLength());
	/*
	 * Check weather they have the same signature The equals method of the
	 * StringClass checks weather the two String are consisting of the same
	 * sequence of characters, thus it checks weather they represent the
	 * same String.
	 */
	sucess = sucess && (m1.getSignature().equals(m2.getSignature()));
	/*
	 * Now check if the only difference between these two monoms is exactly
	 * one bit which is 0 in one and 1 in the other monom. This implies
	 * that the Hamming-Distance between the bits that are setted 
	 * in both monom is exactly 1. So we use the method
	 * Monom.hammingDist to calculate this difference.
	 * Remind: The Hamming.Distance guarantees that there is
	 * only one bit inside the monoms which differs.
	 * We will check later if these bits are really 0 and 1
	 * or 1 and 0 see (*) in the bitsgood comment
	 */
	sucess = sucess && (m1.hammingDist(m2) == 1);
	/*
	 * Now if sucess is true the Hamming-Distance is one. So we need to
	 * check which bit is different between the two monoms and delete this
	 * bit. But before we delete something out of the original monoms m1 and
	 * m2 we create a copy of one of this monoms - called meltMonom - and
	 * correct the index which we delete after all.
	 */
	meltMonom = m1.getCopy();
	/*
	 * So now, lets search for the bit which is different:
	 */
	int differ = 0;
	for (int k = 1; k < Constraints.maxIndex; k++) {
	    /*
	     * Remind that the blanks in the monom are represented by 2. Thus we
	     * only need to a difference, since both have the same signature
	     */
	    if (m1.getMonomeBit(k) != m2.getMonomeBit(k))
		differ = k;
	}
	if (differ == 0) // wasn t set by the bit comparsion
	    sucess = false;
	else {
	    bitA = m1.getMonomeBit(differ);
	    bitB = m2.getMonomeBit(differ);
	    /**
	     * ATTENTION: 1-2 is also 1 in the absolut but, we dont have (0,1)
	     * or(1,0) for melting !
	     */
	    /*
	     * When (0,1) or (1,0) appears the monoms can be melted. These two
	     * case model bitsGood representing the (*)-case of the
	     * hamming-distance comment
	     */
	    bitsGood = ((bitA == 0) && (bitB == 1))
		    || ((bitA == 1) && (bitB == 0));
	    // iff bitsGodd we sucess.
	    sucess = sucess && bitsGood;
	}
	/*
	 * Now we delete the indes differ in the copy of the Monom by setting
	 * its value to 2 which represents '-' a deleted entry.
	 */
	meltMonom.setIntMonome(differ, 2);

	// Now we can report our sucess.
	return sucess;
    }

    /**
     * 
     * @return The melted Monom by the {@link melt} method.<br>
     *         It should be null, if the melting process wasn t sucessful.<br>
     *         <b>Attention</b>: Call this method immediately after the method
     *         {@link melt}
     */
    public static Monom getMelted() {
	if (!sucess)
	    return null;
	else
	    return meltMonom;
    }

    /**
     * A test method
     */
    public static void main(String[] args) {
	int number = 6;
	Monom m[] = new Monom[number];
	m[1] = new Monom("x1x2[x3]x4(~x5)x6(~x7)x8[x9](~x0)");
	m[2] = new Monom("x1x2[x3]x4x5x6(~x7)x8[x9](~x0)");
	m[3] = new Monom("x1x2[x3]x4x5x6(~x7)(~x8)[x9](~x0)");
	m[4] = new Monom("x1x2[x3](~x4)(~x5)x6(~x7)(~x8)[x9](~x0)");
	m[5] = new Monom("x1(~x2)[x3]x4(~x5)x6(~x7)x8[x9][x0]");

	int repr = 3; // representation number

	System.out.println();
	for (int i = 1; i < number; i++) {
	    m[i].setStringRepresentation(repr);
	    System.out.println("Monom " + i + ": " + m[i]);
	}

	Monom melted = null;
	// compare pairwise for i!=j
	for (int i = 1; i < number; i++) {
	    for (int j = 1; j < number; j++) {
		if (i != j) {
		    melt(m[i], m[j]);
		    melted = getMelted();
		    if (melted != null) {
			System.out.println("Melting sucess");
			System.out.println(m[i]);
			System.out.println(m[j]);
			melted.setStringRepresentation(repr);
			System.out.println("Melted to:");
			System.out.println(melted);
		    } else {
			
			  System.out.println("You've tried to meld");
			  System.out.println(m[i]); System.out.println(m[j]);
			  System.out.println("Unfortunately error has occured!");
			 
		    }
		}
		System.out.println();
	    }
	}

    } // end of test method

    /**
     * This method creates a monom of a String like "01201". Attention: the
     * monom must have the same length as Constraints.maxIndex and has only to
     * consist of 0,1 and 2 entries. Here entry 2 represents the "-", leaving
     * the current variable out.
     * 
     * @param s
     *            String of which the monom is created
     * @return the created monom iff it was succesful; null else.
     */
    public static Monom createMonomFrom0to2(String s) {
	boolean convertable = true;
	char c = ' ';
	// check weather the monom consits only of 0,1 or 2.
	for (int i = 1; i < s.length(); i++) {
	    c = s.charAt(i);
	    convertable = convertable
		    && ((c == '0') || (c == '1') || (c == '2'));
	}

	/*
	 * check wheater the input string has the correct length. Note that a
	 * string as "hello" has length 5. Thus we need to substract 1 from
	 * maxIndex of the Constraint class, since Constraint.maxIndex is the
	 * monom-length + 1. Because we want to begin the index of the monom
	 * with 1. So we need to initalize an array of maxIndex=length of the
	 * monom + 1.
	 */
	convertable = convertable && (s.length() == (Constraints.maxIndex - 1));
	if (convertable) {
	    Monom monom = new Monom();
	    int value = 0;
	    for (int i = 0; i < s.length(); i++) {
		/*
		 * Note that the characters of a string with length n are
		 * succesed by the charAt()-method with indexes 0..s.length()-1.
		 * Thus we need to add 1 to i to place the entries in the
		 * correct locations of the monom
		 */
		c = s.charAt(i);
		switch (c) {
		    case '0':
			monom.setIntMonome(i + 1, 0);
			break;
		    case '1':
			monom.setIntMonome(i + 1, 1);
			break;
		    case '2':
			monom.setIntMonome(i + 1, 2);
			break;
		    default:
			convertable = false;
		}
	    }
	    // if the conversion is successful the monom is returned
	    if (convertable)
		return monom;
	    else
		return null;

	} // if not convertable return null
	else
	    return null;
    }

}
