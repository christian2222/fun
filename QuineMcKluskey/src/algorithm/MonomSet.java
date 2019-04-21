package algorithm;

import java.util.*;

/**
 * We first implement the Set of monoms. These sets are the big(!) sets which
 * constist of all monoms matching some certain type. Thus the monoms are
 * ordered only by their signature, not by their number of ones yet.<br>
 * For example the set of X-X-XX contains:<br>
 * 0-0-00<br>
 * 0-1-00<br>
 * 0-1-10<br>
 * 1-0-01<br>
 * 1-1-11<br>
 * <br>
 * The sorting of the subsets is delegate to the class {@link MonomOnesSubset}
 * .It gets an object of this class as a parameter. <br>
 * Then you can choose by the method {@link MonomOneSubSet.getLiMk} the subset
 * of the original monomSet which has exactly k 1s.
 * 
 * @author chrissy - date 30-03-2011
 * 
 */
public class MonomSet implements Comparable {

    /**
     * use TreeSet because we have a set and we possibly want to order it.
     */
    protected TreeSet<Monom> monomList = new TreeSet<Monom>();

    /**
     * the signature the QMC -algorithm works with
     */
    protected String signature = "";

    /**
     * 
     * @param signature
     *            only monoms of this signature are really added to the set.
     */
    public MonomSet(String signature) {
	this.signature = signature;
    }

    /**
     * 
     * @param m
     *            monom to add to the list
     * @return integer which describes the sucess of the method:<br>
     *         i == 1 iff signature is correct and the monom doesnt belong to
     *         the list and it is added by the method<br>
     *         i == 2 iff signature is correct but the monom is already in the
     *         list<br>
     *         i == 3 iff the signature is not correct
     */
    public int add(Monom m) {
	int i = 0;
	if (m.getSignature().equals(this.signature)) {
	    if (!monomList.contains(m)) {
		monomList.add(m);
		i = 1;
	    } else
		i = 2;
	} else
	    i = 3;
	return i;
    }

    /**
     * 
     * @param m
     *            monom to be removed from the list.
     * @return integer which describes the sucess of the method:<br>
     *         i == 1 iff signature the monom was in the list and is now removed<br>
     *         i == 2 iff signature is correct but the monom wasnt in the list<br>
     *         i == 3 iff the signature is not correct
     */
    public int remove(Monom m) {
	int i = 0;
	if (m.getSignature().equals(this.signature)) {
	    if (this.monomList.contains(m)) {
		this.monomList.remove(m);
		i = 1;
	    } else
		i = 2;
	} else
	    i = 3;

	return i;
    }

    /**
     * 
     * @return the signature of this monomset
     */
    public String getSignature() {
	return this.signature;
    }

    /**
     * 
     * @return an iterator to walk through the monom list
     */
    public Iterator getIterator() {
	return this.monomList.iterator();
    }

    /**
     * compares 2 monoms depending on their different signature.
     */
    @Override
    public int compareTo(Object o) {
	MonomSet ms = (MonomSet) o;
	int k = this.convertSignature() - ms.convertSignature();
	return k;
    }

    /**
     * 
     * @return an integer which represents the signature in the basis of 2.
     */
    public int convertSignature() {
	int i = 0;
	for (int k = 0; k < this.signature.length(); k++) {
	    if (this.signature.charAt(this.signature.length() - 1 - k) == 'X')
		i = i + (int) Math.pow(2, k);
	}
	return i;
    }
    
    /**
     * is "MS" plus the signature
     */
    public String toString() {
	return "MS " + this.signature + " " + this.convertSignature();
    }

    /**
     * 
     * @return a complete list of all monoms contained in this set
     */
    public String printCompleteSet() {
	StringBuffer sb = new StringBuffer();
	sb.append(this.toString() + "\n");
	Iterator<Monom> it;
	Monom m;
	for (it = this.monomList.descendingIterator(); it.hasNext();) {
	    m = it.next();
	    sb.append(m.toString() + "\n");
	}
	return sb.toString();

    }

    /**
     * 
     * 
     * @return the complete monomList ordered ba their number of 1s
     */
    public String printAllSubsets() {
	StringBuffer sb = new StringBuffer("");
	TreeSet<Monom> tsMonom = null;
	Iterator it;
	Object o;
	Monom m;
	if (!this.monomList.isEmpty()) {
	    MonomOnesSubset mos = new MonomOnesSubset(this, this.signature);
	    sb.append(this.toString() + "\n");
	    for (int k = 0; k < Constraints.maxIndex; k++) {
		tsMonom = mos.getLiMk(k);
		// Important: In the conditions we can delete the isEmpty()-condition
		if ((tsMonom != null) && !tsMonom.isEmpty()) {

		    sb.append(k + "times 1s:\n");
		    for (it = tsMonom.descendingIterator(); it.hasNext();) {
			o = it.next();
			m = (Monom) o;
			sb.append(m + "\n");
		    }
		}
	    }
	} // if !isempty
	return sb.toString();
    }

}
