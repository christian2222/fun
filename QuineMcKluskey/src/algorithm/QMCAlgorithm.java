package algorithm;

import gui.Visual;

import java.io.*;
import java.util.*;


/**
 * This class provides the main part of the QuineMcKluskey-algorithm.<br>
 * We merge the monons to get new monoms or to add them to the remaining
 * set.
 * 
 * @author chrissy - date 30-03-2011
 * 
 */

public class QMCAlgorithm {

    
    /**
     * This is the whole Powerset of monoms the QMC is working on.
     * It contains all original and merged monoms.
     */
    public MonomPowerSet workingSet = new MonomPowerSet();

    /**
     * We walk through all possible signatures. 
     * Therefore we use a binaryCounter modelled by NaryCounter.<br>
     * Attetion this algortihm is complex and has many subIterations.
     * Thus we have to divide it in isolating subparts!
     */
    
    /**
     * Calculated the maximum number of different (set)signatures
     * which is 2^(maxIndex-1)
     */
    protected int maxSign = (int) Math.pow(2, Constraints.maxIndex-1);
    
    /**
     * provides an signature-array to store all possible different
     * signatures
     */
    protected String[] signatures = new String[maxSign];
    

    /**
     * This array is sorted downward to the number of signatures.
     * This means for the length of 5 we begin with<br>
     * <br>
     * sortedSignatures[0] = XXXXX<br>
     * ...<br>
     * ...<br>
     * and end with<br>
     * sortedSignatures[length-1] = -----<br>
     * <br>
     * This sorting method is implemented by getSortedSignature().
     * We add Constraints.maxIndex to the array-counter to be sure
     * that enough array-entries exist.
     */
    protected String[] sortedSignatures = new String[maxSign+Constraints.maxIndex];
    /**
     * these are two iterators storing the monoms of j and j+1 1s.
     */
    protected TreeSet<Monom> monomlistj = new TreeSet<Monom>();
    /**
     * these are two iterators storing the monoms of j and j+1 1s.
     */
    protected TreeSet<Monom> monomlistjPlus1 = new TreeSet<Monom>();

    /**
     * Now we provide two lists for the resulting monoms.
     * One list for the new merged monoms and the other for
     * the rest of the monoms where the monoms couldn't be paired.
     */
    protected MonomList melted = new MonomList();
    /**
     * Now we provide two lists for the resulting monoms.
     * One list for the new merged monoms and the other for
     * the rest of the monoms where the monoms couldn't be paired.
     */
    protected MonomList unPaired = new MonomList();
    
    /**
     * This is the list of the Primimplicants which are left over
     * after each melting process
     */
    protected MonomList PrimF = new MonomList();
    
    
    /**
     * initializes two new empty melted and paired monomLists
     */
    protected void resetLists() {
	this.melted = new MonomList();
	this.unPaired = new MonomList();
    }
    
    /**
     * construct the different signature strings
     * and stores them in the signature-array of this class
     */
    protected void constructSignatureStrings() {
	NaryCounter binaryCounter = 
	    	new NaryCounter(2,Constraints.maxIndex-1);
	binaryCounter.setZero();
	String signature = "";
	String counterString = "";
	for(int i = 0; i < maxSign; i++) {
	    signature = "";
	    // get the String-Representation of the binaryCounter
	    counterString = binaryCounter.toString();
	    /* construct the Signature dependent of this
	     * String-Representation.
	     * For example 01010 -> -X-X-
	     */
	    for(int k = 0; k < counterString.length(); k++) {
		    if(counterString.charAt(k)== '1') 
			signature = signature +"X";
		    else
			signature = signature +"-";
			
	    }
	    this.signatures[i] = signature;
	    /*
	     * the signature has been constructed and saved,
	     * thus we can increase the counter
	     */
	    binaryCounter.addOne(); 
	}
    }
    
    /**
     * We organize the order of signatures
     * that should be merged; see below
     * now we need to decide for which signatures
     * we use the core of this algorithm.
     * In the case of length 5 we remark that<br>
     * the first signature is<br>
     * <br> 
     * (starting order)<br>
     * XXXXX<br>
     * Then followed by<br>
     * XXXX-,XXX-X,XX-XX,X-XXX,-XXXX<br>
     * ....<br>
     * up until to<br>
     * ....<br>
     * ----X,---X-,--X--,-X---,X----<br>
     * and finally<br>
     * -----<br>
     * (ending order)<br>
     * <br>
     * Note that after each iteration we need to collect 
     * all merged monoms
     * and all unpaired monoms. For this we've created the
     * get-Methods.
     * Hence we also need to choose the correct order of
     * signatures.<br>
     * <br>
     * This method sorts the constructed signatures in decreasing order by
     * the number of their entries. Thus for length 5 begin with<br>
     * <br>
     * XXXXX<br>
     * ...<br>
     * ...<br>
     * end with<br>
     * -----<br>
     * <br>
     * between we put some descriptions in like<br>
     * <br>
     * 2 entries (in the case of length 5)<br>
     * -X-X-<br>
     * ---XX<br>
     * <br>
     * therfore the sortarray-index is maxIndex bigger than the
     * normal string array.
     */
    public void constructSignatureSort() {
	int k = 0;
	for(int ones = Constraints.maxIndex-1; ones >= 0; ones--) {
	//run over the number of ones
	    this.sortedSignatures[k] = ones+" entries";
	    k++;
	    for(int j = 0; j < this.maxSign; j++) {
	    // run over all signatures and took them with ones 1s.
		if( this.countEntries(this.signatures[j]) == ones) {
		    this.sortedSignatures[k] = this.signatures[j];
		    k++;
		}
	    }
	}
    }
    
    /**
     * counts the entries 'X' of one signature,
     * deleted entries '-' are ignored.
     * @param signature count the entries of this signature
     * @return the number of entries
     */
    protected int countEntries(String signature) {
	int entries = 0;
	for(int i = 0; i < signature.length(); i++) {
	    if(signature.charAt(i)=='X') {
		entries++;
	    }
	}
	
	return entries;
    }
    
    /**
     * 
     * @param n number of entries
     * @return a TreeSet of signatures which have exactly <i>n</i> entries 
     */
    public TreeSet<String> getSignaturesWithNEntries(int n) {
	TreeSet<String> nEntries = new TreeSet<String>();
	// run through all signatures
	for(int i = 0; i < this.maxSign; i++) {
	    // collect the ones with n entries
	    if(this.countEntries(this.signatures[i]) == n) {
		nEntries.add(this.signatures[i]);		
	    }
	}
	
	return nEntries;
    }
    
    /**
     * This method lists the current merging sets to the outStream.
     * Attention: the current merging sets are dependent on
     * the {@link coreOfQMC}.
     */
    protected void listMergingSets() {
	    Streams.report.println("merging the lists:");
	    Iterator<Monom> itJ,itJplus1; 
	    Streams.report.println();
	    // write the first monomList
	    for(itJ = monomlistj.descendingIterator(); itJ.hasNext(); ) {
		Monom m = itJ.next();
		Streams.report.println(m);
	    }
	    Streams.report.println();
	    // write the second monomList
	    for(itJplus1 = monomlistjPlus1.descendingIterator();
	    			itJplus1.hasNext(); ) {
		Monom m = itJplus1.next();
		Streams.report.println(m);
	    }
	    Streams.report.println();
    }
    
    /**
     * This method merges two monoms m1, m2 and set them "paired"
     * iff the merging-process was successful.
     * @param m1 first monom
     * @param m2 second monom
     * @return the merged monom - <b>null</b> iff the two monoms couldn't 
     * be merged
     */
    protected Monom melt(Monom m1,Monom m2) {
	boolean success = false;
	Monom melt = null;
	success = MonomFactory.melt(m1, m2);
	if(success) {
	    melt = MonomFactory.getMelted();
	    Streams.report.println("melt \t"+m1);
	    // /> is the tabulator in Latex
	    Streams.texFile.println("melt\\>"+m1.toLatex()+"\\\\");
	    Streams.report.print("and \t"+m2+"\t");
	    Streams.texFile.println("and\\>"+m2.toLatex()+"\\>");
	    Streams.report.println(" = "+melt);
	    Streams.texFile.println(" = "+melt.toLatex()+"\\\\[1mm]");
	    Streams.report.println();
	    /*
	     *  Iff the monoms could be melted we
	     *  return the melted monom.
	     *  Then we note that the two monoms m1, m2 has been
	     *  melted successfully by set the origin monoms paired.
	     */
	    m1.pair();
	    m2.pair();
	    return melt;
	}
	else {
	    Streams.report.println("melt \t"+m1);
	    Streams.report.print("and \t"+m2+"\t");
	    //Streams.report.println(" = "+melt);
	    Streams.report.println("failed");
	    Streams.report.println();
	    return null;
	}
    }
    
    /**
     * This method collects all unpaired Monoms of the QMC-Algorithm.<br>
     * <b>Note:</b> Only use it after {@link coreOfQMC}
     * @param mos MonomOnesSubset
     */
    protected void collectUnpaired(MonomOnesSubset mos) {
	unPaired = new MonomList();
	Monom m3 = null;
	int maxOnes = mos.getMaximumNumberOfDifferentEntries(mos.getSignature());
	for(int t = 0; t < maxOnes+1 ;t++) {
	    this.monomlistj = mos.getLiMk(t);
	    if(this.monomlistj!= null) {
		// collect all unpaired monoms
		for(Iterator<Monom> it = monomlistj.descendingIterator();
		    	it.hasNext(); ) {
		    	    m3 = it.next();
		    	    if(!m3.getPaired())
		    		unPaired.add(m3);
		    	}
	    }
	}
    }
    
    /**
     * This is the core-algorithm of QuniceMcKluskey.<br>
     * It runs on one signature of monoms.
     * @param signature the signature the core algorithm runs on
     */
    protected void coreOfQMC(String signature) {
	
	// now we choose the MonomSet with the given signature
	MonomSet ms = this.workingSet.getMonomSet(signature);
	MonomOnesSubset mos = null;
	Monom melted,m1,m2;
	boolean newpage = false;
	if(ms != null) {
	    mos = new MonomOnesSubset(ms, ms.getSignature());
	    /* 
	     * the following integer stores the number of entries
	     * that can be setted to 1; thus this number expresses
	     * how many different number of ones this mos can have.
	     * Remember that that each of this number of ones
	     * correspond to exactly one LiMk 
	     * since k is the number of ones.
	     */
	    int maxDiffSetsOfOnes = mos.getMaximumNumberOfDifferentEntries(mos.getSignature());
	    //Attention: The index goes up to k+1!
	    // divide the latex output to 14 monoms per page
	    int nu = 0;
	    int maxNu = 14;
	    Streams.texFile.println("\\newpage");
	    newpage = true;
	    Streams.texFile.println(
		    "\\section{Signature "+StringWorks.signatureToTex(mos.getSignature())+"}");	    
	    for(int t = 0; t < maxDiffSetsOfOnes; t++) { 

		/*
		 *  get two adjacent LiMks; thus the second of them
		 *  has one 1 entry more.
		 *  Hence mListj and mListjPlus1 contain all possible
		 *  merged monoms
		 */
		monomlistj = mos.getLiMk(t);
		monomlistjPlus1 = mos.getLiMk(t+1);
		// ensure that the two sets aren't empty
		if(!monomlistj.isEmpty() && !monomlistjPlus1.isEmpty()) {
		    // start the algorithm on t and t+1 subsets
		    Streams.report.println("Melting the set of");
		    Streams.report.println(monomlistj);
		    Streams.report.println("and");
		    Streams.report.println(monomlistjPlus1);
		    if(!newpage) { 
			Streams.texFile.println("\\newpage");
		    }
		    Streams.texFile.println(		    
			    "\\subsection{Signature "+StringWorks.signatureToTex(mos.getSignature())+
			    "with "+t+" ones}");
		    // reset nu by the start of a new signature
		    nu = 0;
		    // and reset newpage
		    newpage = false;
		    Streams.texFile.println("Melting the set of\\\\");
		    Streams.texFile.println(
			    StringWorks.treeSettoLatex(monomlistj)+"\\\\");
		    Streams.texFile.println("and\\\\");
		    Streams.texFile.println(
			    StringWorks.treeSettoLatex(monomlistjPlus1)+"\\\\");
		    Streams.texFile.println(StringWorks.startTabbing());
		    Iterator<Monom> itJ,itJplus1; 
		    /*
		     *  Iterate over both sets and check all resulting
		     *  monom pairs
		     */
		    for(itJ = monomlistj.descendingIterator(); itJ.hasNext(); ) {
			m1 = itJ.next();
			for(itJplus1 = monomlistjPlus1.descendingIterator();
						itJplus1.hasNext(); ) {
			    m2 = itJplus1.next();
			    /*
			     * now we try to melt the two Monoms m1,m2.
			     * The possible result is store in monom
			     */
			    melted = this.melt(m1, m2);
			    if(melted != null){// merging was successful
				nu++;
				this.melted.add(melted);
				if(nu >= maxNu) {
				    nu = 0;
				    Streams.texFile.println(StringWorks.endTabbing());
				    Streams.texFile.println("\\newpage");
				    Streams.texFile.println(StringWorks.startTabbing());
				}
			    }
			    // if the merging wasnt sucessful we
			    // dont write it to the latex-file
			} //itJPlus1 - second iteration
		    } // itJ - first iteration

		    // end tabular for Latex
		    Streams.texFile.println("\\end{tabbing}");

		} // monomlists j and j+1 not empty

	    
	    
	    
	    } // for loop over maximum number of different ones 
	    
	    /* We are now finished with the melting process.
	     * All monoms that have been melted are stored in the
	     * [melted] monom list. If a monoms has found a partner
	     * it is paired.*/
	    
	    /*
	     * We collect all other unpaired monoms now 
	     * by the following method.
	     * The first parameter is the MaximumOnesSubset.
	     * The second parameter is the maximum number of different
	     * ones for the current MaximumOnesSubset.
	     */
	    this.collectUnpaired(mos);
	    
	    
	} // ms != null
	
	/*
	 * In the end we have used the complete QMC-Algorithm
	 * for one signature String given as a parameter
	 * of this method.
	 * The melted monoms are stored in [melted].
	 * The unpaired monoms are stored in [unpaired].
	 */
	
	
    } // coreOfQMC
    
    /**
     * Constructor
     */
    public QMCAlgorithm(MonomList ml) {
	this.init(ml);
	Visual.statusInfo.setText("Starting QuineMcKluskey-Algorithm.");
	Streams.out.println("Please wait...");
    } // Constructor
    
    /**
     * an init-method for this class which initializes the <i>workingSet</i>
     * and reports its status
     */
    public void init(MonomList ml) {
	Visual.statusInfo.setText("Starting initialization.");
	// start with choosen number randomized binary Monoms

	this.workingSet.add(ml);
	
	this.workingSet.printAllMonomSets();
	// construct all possible sigantures
	Streams.report.println("WorkingSet:");
	Streams.report.println(this.workingSet.toString());
	Streams.texFile.println("\\newpage");
	Streams.texFile.println(this.workingSet.getInputLatex());
	Streams.texFile.println("\\\\");
	// print also to texFile
	this.constructSignatureStrings();
	// construct the sortedSignature-array
	this.constructSignatureSort();
	Visual.statusInfo.setText("End of initialization.");
    }
    
    
    /**
     * 
     * @return the list of the melted monoms
     */
    public MonomList getMelted() {
        return melted;
    }
    
    /**
     * 
     * @return the list of the unpaired monoms
     */
    public MonomList getUnPaired() {
        return unPaired;
    }
    
    /**
     * 
     * @return the array of sorted signatures; sorted by decreasing entries
     */
    public String[] getSortedSignatures() {
	return this.sortedSignatures;
    }
    
    /**
     * reports the set PrimF to the Latex- and Report-file.
     */
    public void reportPrimF() {
	Streams.report.println("PrimF:");
	Streams.report.println(this.PrimF);
	Streams.texFile.println("\\newpage");
	Streams.texFile.println("\\section{Primimplikantenmenge PrimF}");	
	Streams.texFile.println("PrimF:\\\\");
	Streams.texFile.println("\\\\");
	Streams.texFile.println(this.PrimF.toLatex()+"\\\\");
	Streams.texFile.println("\\\\");
	Streams.texFile.println("\\\\");
	Streams.texFile.println("\\\\");
    }
    
    /**
     * This method performs one complete run of 
     * the QuineMcKluskey-Algorithm.<br> We execute this algorithm 
     * according to the sortedSignatures' order.<br>
     * Hence we start with<br>
     * <br>
     * XXXXX<br>
     * XXXX-,XXX-X,XX-XX,X-XXX,-XXXX<br>
     * ...<br>
     * until<br>
     * -----<br>
     * <br>
     * the sortedSingatures-array is already ordered by the {@link init}
     * -method
     * Note: The order of the signatures guarantees that the QuineMcKlsukey-
     * Algorithm is correct, since merged monoms of the XXXXX set are
     * ordered into the sets XXXX-,XXX-X,XX-XX,X-XXX,-XXXX.
     * Thus after walking over XXXXX we can walk on over
     * XXXX-,XXX-X,XX-XX,X-XXX,-XXXX and have all paired monoms of XXXXX
     * in these sets or get unpaired monoms reported.
     * This invariant is true over each iteration!
     */
    public void run() {
	Visual.statusInfo.setText("Starting main algorithm - please wait...");
	for(int i = 0; i < this.sortedSignatures.length; i++) {
	    // reset the paired and unpaired list
	    this.resetLists();
	    Streams.report.println(this.sortedSignatures[i]);
	    /*
	     * for the signature "k entries" the coreAlgotihm does
	     * nothing, so we write it into the file and nothing
	     * else happen. Ie. length 5 4entries
	     * 4entires
	     * -XXXX
	     * (monoms get paired)
	     * X-XXX 
	     * (monoms get paired)
	     * ...
	     * ...
	     * until
	     * XXXX-
	     * (monoms get paired)
	     * then
	     * 3 entries
	     * --XXX
	     * and so on...
	     */
	    this.coreOfQMC(this.sortedSignatures[i]);
	    /*
	     * Now we have to take the unpaired list to
	     * PrimF and the merged list into the same PowerSet again
	     */
	    this.workingSet.add(this.melted);
	    this.PrimF.add(this.unPaired);
	    this.reportLists();
	}
	
	this.reportPrimF();
	
	Streams.mAll.println("Now the algorithm is complete");
	Visual.statusInfo.setText("Algorithm complete.");
    }
    
    public void reportLists() {
	MonomList meltList = this.getMelted();
	MonomList unpairedList = this.getUnPaired();
	if(!meltList.isEmpty()) {
	    Streams.report.println("Melted Monoms:");
	    Streams.report.println(meltList);
	}
	if(!unpairedList.isEmpty()) {
		Streams.report.println("Unpaired Monoms:");
		Streams.report.println(unpairedList);
	}
	
	Streams.report.println();
    }
    
    /**
     * after one execution of the QMC-Algortihm:<br>
     * We have the meltedList as the List we want to work on.
     * And we have the unpairedList which is the list
     * of the remaining Primimplicants Prim(f)
     * @param args
     */
    public static void completeAlgorithm(MonomList ml) {
	// Initialize the streams first to work them right!
	Streams.init();
	QMCAlgorithm alg = new QMCAlgorithm(ml);
	alg.run();
	alg.constructSignatureSort(); // may be left out.
	String[]  sorted = alg.getSortedSignatures();
	Streams.log.println("Sorted signatures:");
	for(int i = 0; i < sorted.length; i++) {
	    Streams.log.println(sorted[i]);
	}
	//Streams.mAll.println("all");
	Streams.log.println("attention 1 [all] is inside the file!");
	//Streams.mRepAtex.println("report and tex");
	Streams.log.println();
	Streams.log.println();
	Streams.log.println("WorkingSet:");	
	Streams.log.println(alg.workingSet);
	Streams.close();
	System.out.println("Huchu!");
    }
}
    
    
    
   

