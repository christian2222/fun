package algorithm;

import java.util.*;

/**
 * The MonomList is like the MonomSet expect of the fact that is has no
 * signature, which controls weather a monom is added or not.<br>
 * Thus we can assemble any Monoms inside a MonomList and then use this list to
 * construct a MonomPowerSet containing of all MonomSets in which the monoms of
 * this MonomList are belong to.<br>
 * We also use the MonomList is also used as input of the QMC-algorithm.<br>
 * Note: You can add 2 copies of one monom by a monomList but inside the
 * Monom(Power)Set there will be only one monom, since the MonomSets are
 * structured like sets.
 * 
 * @author chrissy - date 29-03-2011
 * 
 */
public class MonomList {

    /**
     * use TreeSet because we have a set and we possibly want to order it.
     */
    protected List<Monom> monomList = new Vector<Monom>();

    /**
     * here we add monoms and merged monoms. We need a monomList without
     * checking the signatures, because there can be many different (merged)
     * monoms.<br>
     * But we have to check the commom length
     **/
    public void add(Monom m) {
	if (m.getLength() == Constraints.maxIndex)
	    this.monomList.add(m);
    }

    /**
     * We are also able to add a complete MonomList to a already
     * constructed MonomList.
     * In this sence we can compute the union of two or more
     * monom sets.
     * @param mList the monomlist we add to this list
     */
    public void add(MonomList mList) {
	Iterator<Monom> it;
	for (it = mList.getIterator(); it.hasNext();) {
	    this.add(it.next());
	}
    }

    /**
     * An iterator over a complete monomList is important for sorting
     * the monomList inside a monompowerset or using other parts
     * of the QMC-algorithm on one monomList.
     * @return an iterator over this list
     */
    public Iterator getIterator() {
	return this.monomList.iterator();
    }

    /**
     * @return is this monomList empty
     */
    public boolean isEmpty() {
	return this.monomList.isEmpty();
    }

    /**
     * merke trick mit Iterator !
     * 
     * @return a copy of the current list
     */
    public MonomList copy() {
	MonomList mList = new MonomList();
	Monom m = null;
	Iterator<Monom> it;
	for (it = mList.getIterator(); it.hasNext();) {
	    m = it.next();
	    mList.add(m);
	}
	return mList;
    }

    /**
     * @return a list of all monoms contained in this set
     */
    public String toString() {
	StringBuffer sb = new StringBuffer();
	if (!this.isEmpty()) {
	    Iterator it = this.getIterator();
	    for (it = this.getIterator(); it.hasNext();) {
		Monom m = (Monom) it.next();
		if (m != null) {
		    sb.append(m);
		    sb.append("\n");
		}
	    }

	} else
	    sb.append("List is empty");

	return sb.toString();
    }

    /**
     * This method returns the monoms of one list in the
     * Latex format.
     * @return a list of all monoms inside this set
     */
    public String toLatex() {
	StringBuffer sb = new StringBuffer("\\{\\\\");
	int i = 0;
	int max = 4;
	if (!this.isEmpty()) {
	    Iterator<Monom> it = this.getIterator();
	    for (it = this.getIterator(); it.hasNext();) {
		Monom m = it.next();
		if (m != null) {
		    sb.append(m.toLatex());
		    sb.append(",");
		    i++;
		    if (i >= 4) {
			sb.append("\\\\");
			i = 0;
		    }
		}
	    }

	} else
	    sb.append("List is empty");

	sb.append("\\}\\\\");
	return sb.toString();
    }
}
