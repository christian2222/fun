package algorithm;

import java.util.*;

/**
 * This PowerSet contains all Signature sets.<br>
 * Since we have 10 entries there can be up to 1024 monomSets; each with its own
 * signature.<br>
 * Thus we will use a binary counter to create all sets.<br>
 * Note: If a set is empty they are not printed in the QMC algorithm
 * 
 * @author chrissy
 */
public class MonomPowerSet {

    /**
     * This TreeSet contains all MonomSets which are identificated by their
     * Signature (see .toString()-method of MonomSet
     */
    protected TreeSet<MonomSet> powerset;

    /**
     * We use this binary counter to construct all signatures from<br>
     * "-----",<br>
     * "----X",<br>
     * up..to,<br> 
     * "XXXX"<br>
     * for the length of 5.<br>
     * Note: we have 2^(maxIndex-1) different sets.
     */
    protected NaryCounter binaryCounter = new NaryCounter(2,
	    Constraints.maxIndex - 1);

    /**
     * these are used variables in the iteration
     */
    protected String signature, counterString;

    /**
     * This constructor construct for each signature a different MonomSet.<br>
     * The powerset itself is the union of all these MonomSets.
     */
    public MonomPowerSet() {
	char c = ' ';
	this.powerset = new TreeSet<MonomSet>();
	binaryCounter.setZero();
	int monomLength = Constraints.maxIndex - 1;
	int maxPS = (int) Math.pow(2, monomLength);
	for (int i = 0; i < maxPS; i++) {
	    /*
	     * construct the signature
	     */
	    signature = "";
	    // get the String-Representation of the binaryCounter
	    counterString = binaryCounter.toString();
	    /*
	     * construct the Signature dependent of this String-Representation.
	     * For example 01010 -> -X-X-
	     */
	    for (int k = 0; k < counterString.length(); k++) {
		if (counterString.charAt(k) == '1')
		    signature = signature + "X";
		else
		    signature = signature + "-";

	    }
	    this.powerset.add(new MonomSet(signature));
	    binaryCounter.addOne();
	}
    }

    /**
     * A toString method which devides the different MonomSet signatures by a
     * new line.
     */
    public String toString() {
	StringBuffer sb = new StringBuffer();
	Iterator it;
	MonomSet ms;
	for (it = this.powerset.descendingIterator(); it.hasNext();) {
	    Object o = it.next();
	    ms = (MonomSet) o;
	    sb.append(ms.printAllSubsets());
	    sb.append("\n");
	}
	return sb.toString();

    }

    /**
     * adds a monom to this powerset
     * 
     * @param monom
     *            to be added
     */
    public void add(Monom m) {
	String sig = m.getSignature();
	boolean added = false;
	MonomSet ms = null;
	Iterator<MonomSet> it;
	// Search trough all MonomSets for the MonomSet with 
	// the same signature and add the current monom to this set.
	for (it = this.powerset.descendingIterator(); it.hasNext();) {
	    ms = it.next();
	    if (sig.equals(ms.getSignature())) {
		ms.add(m);
		added = true;
	    }

	}

	// report the result
	if (added)
	    Streams.log.println("Monom added " + added);
	else
	    Streams.log.println("Monom lost:" + m.toString());
    } // add(Monom m)

    /**
     * adds a complete monom-list to the powerset
     * 
     * @param mList
     *            monom-list to be added
     */
    public void add(MonomList mList) {
	Iterator<Monom> it;
	Monom m = null;
	for (it = mList.getIterator(); it.hasNext();) {
	    m = it.next();
	    this.add(m);
	}
    }

    /**
     * prints all Monom-Sets contained in this powerset to the report-file
     */
    public void printAllMonomSets() {
	Iterator it;
	Object o = null;
	MonomSet ms = null;
	// Print for each element of the powerset its complete
	// subsets to the report-file.
	for (it = this.powerset.descendingIterator(); it.hasNext();) {
	    o = it.next();
	    ms = (MonomSet) o;
	    Streams.report.println(ms.printAllSubsets());
	}
    }

    /**
     * 
     * @return the signature of this powerset
     */
    public String getSignature() {
	return this.signature;
    }

    /**
     * 
     * @return a Latex-representation of the input set
     */
    public String getInputLatex() {
	MonomSet ms;
	String sig = "";
	for (int k = 0; k < counterString.length(); k++) {
	    sig = sig + "X";
	}

	StringBuffer latex = new StringBuffer();
	latex.append("\\section{Input-Set}");
	ms = this.getMonomSet(sig);
	Monom m;
	int add = 0;
	int max = 4;
	Iterator<Monom> it;
	latex.append("\\{\\\\");
	for (it = ms.getIterator(); it.hasNext();) {
	    add++;
	    m = it.next();
	    latex.append(m.toLatex() + ",");
	    if (add >= max) {
		latex.append("\\\\");
		add = 0;
	    }
	}
	latex.append("\\\\ \\} \\\\");
	latex.append("\\\\");

	return latex.toString();

    }

    /**
     * 
     * @param signature
     *            specify the searched signature
     * @return the monom-set specified by the signature if its
     * 		contained in this powerset.<br>
     * 		Returns <b>null</b> iff
     * 		no such monom-set is found in the actual powerset.
     */
    public MonomSet getMonomSet(String signature) {
	Iterator<MonomSet> it;
	MonomSet ms;
	String sig;
	for (it = this.powerset.descendingIterator(); it.hasNext();) {
	    ms = it.next();
	    if (ms.getSignature().equals(signature))
		return ms;
	}

	return null;
    }

}
