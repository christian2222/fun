package algorithm;

import unused.BinaryCounter;


/**
 * The data Structure of a Monom<br>
 * 
 * <br>
 *         A Monom is generally coded by x1x2...xn.<br>
 *         We can use negation by (~xi) and we can left out an entry by [xi]. To
 *         left out an entry is very important for the later
 *         Quine-Mc-Kluskey-Algorithm which deletes entries of Monoms if both
 *         boolean values in one signature is given. A signature is something
 *         like x1x2-x4 which results in melting the two monoms x1x2"0"x4 and
 *         x1x2"1"x4. This signature is coded by String as x1x2[x3]x4; leaving
 *         x3 deleted.<br>
 * <br>
 *         The static global variable Constraints.maxIndex controls the length
 *         of the monom. If maxIndex = 11 - the absolut maximum - the monoms
 *         have a length of 10 because their counters start at 1 not at zero as
 *         usual and maxLength is an strict upper-bound to the length of the
 *         monoms. This makes it easier to understand a monom in the following
 *         notation: x1x2...xn; here ie. x1x2...x9x0(x0 = x10).<br>
 *         To get a more precisely view to this see the Constructor <b>public
 *         Monom(boolean boolMonome[])</b><br>
 *         <br>
 *         Each monom (even melted ones) have a signature.<br>
 *         In example the monom x1(~x2)[x3]x4 or 1020 or 10-0
 *         has the signature XX-X. Every existing entry is marked
 *         by an X; an deleted entry is marked as usual with an -.
 *
 * @author chrissy - date 29-03-2011
 */

public class Monom implements Comparable {

    /**
     * used for internal conversions from String to Monom and the other way
     * around
     */
    private String stringMonom = "";

    /**
     * only used to copy the current monom
     */
    private Monom monomCopy; // rename later

    /**
     * This string is used to parse a monom like x1[x2](~x3) to 1-0.<br>
     * ATTENTION: A complete String-Representation of 10 Varibales is
     * x1x2x3...x9x0. Thus the index 0 represents the array-index 10. Look at
     * {@link getIndex} to see the correct translation.<br>
     * The parser overwrites the entries of {@link intMonom} and depends on
     * Constraints.maxLength;
     */
    private String parseString;

    /**
     * This is the main array which represents the states of the monom. We used
     * an integer array because we want to represent also delted entries by
     * QMC-Alg. which get the value 2. Values 1 and 0 have the standard meanings
     * of "true" and "false".<br>
     * Other entries greater than 2 represent some errors. But they should not
     * appear by using the standard methods.
     */
    public int intMonome[] = new int[Constraints.maxIndex];

    /**
     * This variable controlls which String output you get by the toString()
     * method.<br> 
     * 0 - standard 0 to 2 expression<br>
     * 1 - is the monom expression x1(~x3)x4 for 1201<br>
     * 2 - is the latex expression to export
     * 3 - returns a binary monon 010-1001-0<br>
     */
    protected int stringRepresentation = 3;

    /**
     * marks wheater this monom is paired with another monom by the
     * QMC-Algorithm
     */
    protected boolean isPaired = false;

    /**
     * The empty Constructor which general initializes the current monom with
     * zeros.
     * 
     */
    public Monom() {
	this.initZeros();
    }

    /**
     * boolean Constructor
     * 
     * @param booleanMonome
     *            is conveted to an Monom object
     */
    public Monom(boolean boolMonome[]) {
	for (int k = 1; k < Constraints.maxIndex; k++) {
	    if (boolMonome[k])
		this.intMonome[k] = 1;
	    else
		this.intMonome[k] = 0;
	}
    }

    /**
     * Initializes the Monom with the int-array argument
     * 
     * @param intMonom
     *            the int-array
     */
    public Monom(int intMonome[]) {
	for (int k = 1; k < Constraints.maxIndex; k++)
	    this.intMonome[k] = intMonome[k];
    }

    /**
     * Initializes the monom with a string argument<br>
     * 
     * @param s
     *            string argument
     */
    public Monom(String s) {
	this.intMonome = this.translateString(s);
    }

    /**
     * 
     * @return a copy of the current monom
     */
    public Monom getCopy() {
	this.monomCopy = new Monom();
	// copy the entries.
	for (int k = 1; k < Constraints.maxIndex; k++)
	    this.monomCopy.intMonome[k] = this.intMonome[k];
	return this.monomCopy;

    }

    /**
     * selects the String-Representation of the monom
     * 
     * @param i
     *            the representation number; see {@link toString}-method
     */
    public void setStringRepresentation(int i) {
	this.stringRepresentation = i % 4;
    }

    /**
     * initialize the monom with zeros
     */
    public void initZeros() {
	// x_1...x_n füllen
	for (int i = 1; i < (Constraints.maxIndex); i++)
	    this.intMonome[i] = 0;

    }


    /**
     * 
     * @return the Signature of the current monom.<br>
     *         The signature of a monom is for example X-XX, if the second
     *         entry is already melted. To meld monoms see the details of the
     *         QMC-Alg. The signature is described precisely at the beginning of
     *         the Monom-Class.
     * 
     */
    public String getSignature() {
	String contain = "";
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    if ((this.intMonome[i] == 1) || (this.intMonome[i] == 0))
		contain = contain + "X";
	    else
		contain = contain + "-";
	}

	return contain;
    }

    /**
     * 
     * @return the Variable-Signature of the monom. The Variable-Signature of a
     *         monom is similiar to the Signature of a monom except that the
     *         entries 'X' are now replaced by the name of the variable. For
     *         example 0-10 has the variable-signature x1-x3x4. Remark: x0 is
     *         used to notate x10.
     */
    public String getVariableSignature() {
	String var = "";
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    if ((this.intMonome[i] == 1) || (this.intMonome[i] == 0))
		var = var + ("" + (i % 10));
	    else
		var = var + "-";
	}

	return var;
    }

    /**
     * Remember that equals always has to be an equivalence relation!
     * 
     * @param m
     *            monom to compare with the actual monom
     * @return true iff the actual monom is equal to m
     */
    public boolean equals(Monom m) {
	boolean equals = true;
	if (m == null)
	    equals = false;
	else {
	    // check the same length
	    equals = equals && (this.getLength() == m.getLength());
	    if (equals) {
		// having the same length we check all same entries
		for (int i = 1; i < this.getLength(); i++)
		    equals = equals
			    && (this.getMonomeBit(i) == m.getMonomeBit(i));
	    }
	}
	return equals;
    }

    /**
     * We introduce some useful count methods here.<br>
     * ATTENTION: the count methods don t count the melted entries marked by "-"
     * 
     * @return counts the binary bits (i.e 0 or 1) inside m
     */
    public int countBinaries() {
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++)
	    if (this.intMonome[i] <= 1) // 1 oder 0
		k++;

	return k;
    }

    /**
     * 
     * @return count the entries of the monom m an returns the same value as @see
     *         countBinaries
     */
    public int countEntries() {
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++)
	    if (this.intMonome[i] != 2)
		k++;

	return 0;
    }

    /**
     * 
     * @return the number of ones in the monom
     */
    public int countOnes() {
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++)
	    if (this.intMonome[i] == 1)
		k++;

	return k;
    }

    /**
     * 
     * @return the number of zeros in the monom
     */
    public int countZeros() {
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++)
	    if (this.intMonome[i] == 0)
		k++;

	return k;
    }


    /**
     * @return the length of the current monom<br>
     *         Remeber that the length of the monom is the number of the entries
     *         plus 1 because we start counting by 1.
     */
    public int getLength() {
	return Constraints.maxIndex;
    }

    /**
     * @return an inverted monom of m
     */
    public Monom computeInverse() {
	int[] intTmpMonom = new int[Constraints.maxIndex];

	for (int i = 1; i < Constraints.maxIndex; i++) {
	    if (this.intMonome[i] == 2)
		intTmpMonom[i] = 2;
	    else
		// 1-x = inverted(x) for x in {0,1}
		intTmpMonom[i] = 1 - this.intMonome[i];
	}
	return new Monom(intTmpMonom);
    }

    /**
     * 
     * @param m
     *            the second monom
     * @return the HammingDistance of the two monoms
     */
    public int hammingDist(Monom m) {
	int dist = 0;
	int ownEntry = 0;
	int otherEntry = 0;
	boolean b1 = false;
	boolean b2 = false;
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    ownEntry = this.intMonome[i];
	    otherEntry = m.getMonomeBit(i);
	    /**
	     * ownEntry and otherEntry are Integers !
	     */
	    b1 = ((ownEntry == 0) && (otherEntry == 1));
	    b2 = ((ownEntry == 1) && (otherEntry == 0));
	    if (b1 || b2)
		dist++;
	}

	return dist;
    }

    /**
     * 
     * @param k
     * @return the integer value of the kth bit in m. Returns 5 as an error if
     *         the index k wasn't correct
     */
    public int getMonomeBit(int k) {
	if (this.isCorrectIndex(k))
	    return intMonome[k];
	else
	    return 5;
    }

    /**
     * 
     * @return the decimal interpretation of the monom m.<br>
     *         Empty entries "-" don t count in this interpretation
     */
    public int getDecimalValue() {
	int diff = 0;
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    if (this.intMonome[i] == 1)
		diff += MyMath.xHochn(2, i);
	}
	return diff;

    }

    /**
     * 
     * @param m
     *            other monom
     * @return the difference of the decimal interpretation of both monoms
     */
    public int decimalCompareTo(Monom m) {

	int k = this.getDecimalValue();
	int l = m.getDecimalValue();
	return (k - l);
	/*
	 * int k = this.countOnes(); int l = m.countOnes(); return (k - l);
	 */
    }

    /**
     * This method compares the current monom with another object. Iff the
     * parameter isn't an Monom the return value is the minimum possible int,
     * otherwise it returns the value of {@link decimalCompareTo}
     */
    public int compareTo(Object object) {
	if (object instanceof Monom)
	    return this.decimalCompareTo((Monom) object);
	return Integer.MIN_VALUE;
    }

    /**
     * 
     * @param index
     * @return checks weather the index is correct
     */
    private boolean isCorrectIndex(int index) {
	return ((index < Constraints.maxIndex) && (index >= 1));
    }

    /**
     * Sets the entry on position index to the value intValue, when the
     *         index is correct
     * 
     * @param index
     *            the index to change
     * @param intValue
     *            the value it gets
     *         
     * @return a boolean indicating if the change was successful
     */
    public boolean setIntMonome(int index, int intValue) {
	boolean correctIndex = this.isCorrectIndex(index);
	if (correctIndex)
	    this.intMonome[index] = intValue;
	return correctIndex;
    }

    /**
     * 
     * @param c
     *            Translates the char-Index to the array-index.<br>
     *            In the input-String x1x2...x9x0, x0 stands for x10; thus 0 is
     *            converted to the array index 10.
     */
    private static int getIndex(char c) {
	int k = 20;
	if (c == '1')
	    return 1;
	if (c == '2')
	    return 2;
	if (c == '3')
	    return 3;
	if (c == '4')
	    return 4;
	if (c == '5')
	    return 5;
	if (c == '6')
	    return 6;
	if (c == '7')
	    return 7;
	if (c == '8')
	    return 8;
	if (c == '9')
	    return 9;
	if (c == '0')
	    return 10;
	return k;
    }

    /**
     * Internal method to convert a given String in the notation x1[x2](~x3) as
     * 1-0 into the correct monom.<br>
     * Here only the parsing is depending on Constraints.maxLength.
     * 
     * @param sMonom
     *            the String that represents the monom
     * @return the int-Array which represets the monom.<br>
     *         This is left as an return value to be able to get also an
     *         intArray from (perhabs) outside.
     */
    protected int[] translateString(String sMonom) {
	this.parseString = sMonom;
	this.stringMonom = sMonom;
	System.out.println("PARSING-Documentation");
	System.out.println("Start parsing string:  " + this.parseString);
	System.out.println("Using the first " + (Constraints.maxIndex - 1)
		+ " entries");
	boolean translated = false;
	char c = ' ';
	char d = ' ';
	int k = 50;
	int i = 10;
	while (!parseString.isEmpty()) {
	    c = parseString.charAt(0);
	    d = parseString.charAt(1);
	    // System.out.println();
	    if (c == 'x') {
		k = getIndex(d); // read Variable-Number
		i = Constraints.mTRUE;
		System.out.println("variable x" + k + " set to 1");
		this.parseString = this.parseString.substring(2,
			this.parseString.length());
		System.out.println(parseString);
		translated = true;
	    } else if ((c == '(') && (d == '~')) {
		c = this.parseString.charAt(3); // read Variable-Number
		d = this.parseString.charAt(4); // read the closing brakets )
		k = getIndex(c); // read Variable-Number
		i = Constraints.mFALSE;
		System.out.println("variable x" + k + " set to 0");
		this.parseString = this.parseString.substring(5,
			this.parseString.length());
		System.out.println(this.parseString);
		translated = true;

	    } else if ((c == '[')) {
		c = this.parseString.charAt(1); // read x
		d = this.parseString.charAt(2); // read Variable-Number
		c = this.parseString.charAt(3); // read closing brackets ]
		k = getIndex(d); // read Variable-Number
		i = Constraints.UnUsed;
		System.out.println("variable x" + k + " not used");
		this.parseString = this.parseString.substring(4,
			this.parseString.length());
		System.out.println(this.parseString);
		translated = true;

	    }
	    try {
		if (k < Constraints.maxIndex)
		    this.intMonome[k] = i;
	    } catch (Exception e) {
		System.out.println("Error by converting");
		System.out.println(e);
	    }

	}
	if (!translated) {
	    System.out.println("Error in parsing ~> Initialize with zeros.");
	    for (int t = 1; t < Constraints.maxIndex; t++)
		this.intMonome[i] = 0;
	}
	System.out.println("Parsing End.");
	return this.intMonome;
    }

    /**
     * The Standard StringRepresentation
     * 
     * @remind There are many others
     * @return 102110201 for example
     */
    public String toStandardString() {
	stringMonom = "";
	for (int i = 1; i < Constraints.maxIndex; i++)
	    stringMonom += this.intMonome[i];
	return stringMonom;
    }

    /**
     * @return returns the string representation selected by
     *         {@link stringRepresentation}
     */
    public String toString() {
	switch (this.stringRepresentation) {
	    case 0:
		return this.toStandardString();
	    case 1:
		return this.toStringtoPureLiterals();
	    case 2:
		return this.toStringtoExport();
	    case 3:
		return this.toStringtoBinaryMonome();

	}
	return this.toStandardString();

    }

    /**
     * @param s
     *            is converted to a binary Monom. Attention: Missing entries are
     *            ignored!<br>
     *            Only values of 0,1 are converted.<br>
     *            
     * @return 01201 for (~x1)x2(~x4)x5
     * 
     */
    public String toStringtoPureLiterals() {
	stringMonom = "";
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    k = i;
	    if (k == 10)
		k = 0;
	    if (this.intMonome[i] == 0)
		stringMonom += ("(~x" + k + ")"); // false = 0
	    if (this.intMonome[i] == 1)
		stringMonom += ("x" + k); // true = 1
	    // following line not needed; only real variables are displayed !
	    // if(this.boolMonome[i]==2) this.monom +=("[x"+k+"]"); //notSet
	    // recoding 2 to - !
	}
	return stringMonom;

    }

    /**
     * Exports the monom for a Latex-Document
     * @return (~x1)x2[x3]x4 in latex code
     */
    public String toStringtoExport() {
	stringMonom = "";
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    k = i;
	    if (k == 10)
		k = 0;
	    if (this.intMonome[i] == 0)
		stringMonom += ("($\\neg$ x" + k + ")"); // false = 0
	    if (this.intMonome[i] == 1)
		stringMonom += ("x" + k); // true = 1
	    if (this.intMonome[i] == 2)
		stringMonom += ("[x" + k + "]"); // notSet recoding 2 to - !
	}
	return stringMonom;
    }

    /**
     * Returns the standard monom representation for the QMC-Algorithm
     * @return 10-110 for 102110
     */
    public String toLatex() {
	stringMonom = "";
	int k = 0;
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    k = i;
	    if (k == 10)
		k = 0;
	    if (this.intMonome[i] == 0)
		stringMonom += (" " + 0 + " "); // false = 0
	    if (this.intMonome[i] == 1)
		stringMonom += (" " + 1 + " "); // true = 1
	    if (this.intMonome[i] == 2)
		stringMonom += (" - "); // notSet recoding 2 to - !
	}

	return stringMonom;
    }

    /**
     * Does not the same job as {@link toLatex} method<br>
     * Since the toLatex String has blanks between the entries
     * @param inputMonom
     * @return a string representation of the actual monom. Missing entries are
     *         marked with '-'
     */
 
    public String toStringtoBinaryMonome() {
	stringMonom = "";
	for (int i = 1; i < Constraints.maxIndex; i++) {
	    if (this.intMonome[i] == 0)
		stringMonom += ("" + Constraints.mFALSE); // false = 0
	    if (this.intMonome[i] == 1)
		stringMonom += ("" + Constraints.mTRUE); // true = 1
	    if (this.intMonome[i] == 2)
		stringMonom += "-"; // notSet recoding 2 to - !
	}
	return stringMonom;
    }
    
    /**
     * 
     * @return a boolean which indicates wheather this monom is already paired
     *         with another monom
     */
    public boolean getPaired() {
	return this.isPaired;
    }

    /**
     * "pair" this monom with the monom m by the QMC-Algorithm.
     */
    public void pair() {
	this.isPaired = true;
    }

    /**
     * A test Routine
     */
    public static void main(String[] args) {
	Monom m = new Monom();
	System.out.println(m);
	System.out.println();
	m = new Monom("x1x2[x3]x4(~x5)x6(~x7)x8[x9](~x0)");
	System.out.println();
	System.out.println(m.toString());
	System.out.println(m.toStringtoPureLiterals());
	System.out.println(m.toStringtoExport());
    }

}