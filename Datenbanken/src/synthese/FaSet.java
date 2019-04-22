package synthese;
import java.util.*;
// script F
public class FaSet {

	protected Set<FA> faSet = new TreeSet<FA>();
	
	public void add(FA fa) {
		faSet.add(fa);
	}
	
	public boolean isEmpty() {
		return faSet.isEmpty();
	}
	
	public void remove(FA fa) {
		faSet.remove(fa);
	}
	
	public void list() {
		for(FA fa : this.faSet) {
			System.out.print(fa+", ");
		}
		System.out.println();
	}
	
	// too slow because of Powerset calculation
	private boolean leftReductionStep(FA fa) {
		Set<String> leftSide = fa.getLeftSide();
		Set<String> rightSide = fa.getRightSide();
		Set<Set<String>> leftPower = FA.calcAllSubsets(leftSide);
		boolean found = false;

		for(Set<String> subset : leftPower) {
			if(!subset.equals(leftSide)) {
				// problem: xPlus changes subset!
				if(this.xPlus(subset, rightSide) && !found) {
					found = true;
					this.faSet.remove(fa);
					FA newFa = new FA(subset,rightSide);
					this.faSet.add(newFa);
					System.out.println("remove "+fa+" and add "+ newFa);
				}
			}
		}
		
		return found;
	}
	
	// Fmin is not unique
	// algorithm leftReduction becomes more complex
	// => write explicit method for leftReductiom on one FA
	
	public boolean leftReduction(FA fa) {
		if(!this.faSet.contains(fa)) return false;
		boolean isReduceable = false;
		Set<String> leftSide = fa.getLeftSide();
		Set<String> rightSide = fa.getRightSide();
		Object[] array = leftSide.toArray();
		Set<String> rest = leftSide;
		Set<String> test;
		for(int i = 0; i < array.length; i++) {
			test = new TreeSet<String>(rest);
			test.remove(array[i]);
			if(this.xPlus(test, rightSide)) {
				rest = new TreeSet<String>(test);
				//System.out.println("Reduced "+array[i]);
				isReduceable = true;
			}
		}
		
		FA newFa = new FA(rest,rightSide);
		this.faSet.remove(fa);
		this.faSet.add(newFa);
		
		return isReduceable;
	}
	
	public boolean rightReduction(FA fa) {
		if(!this.faSet.contains(fa)) return false;
		boolean isReduceable = false;
		Set<String> leftSide = fa.getLeftSide();
		Set<String> rightSide = fa.getRightSide();
		Object[] array = rightSide.toArray();
		Set<String> rest = rightSide;
		Set<String> test;
		FaSet newFaSet = new FaSet();
		// copy all FAs
		for(FA fab: this.faSet) {
			newFaSet.add(fab);
		}
		// delete current FA
		newFaSet.remove(fa);
		for(int i = 0; i < array.length; i++) {
			test = new TreeSet<String>(rest);
			test.remove(array[i]);
			FA newFa = new FA(leftSide,test);
			newFaSet.add(newFa);
			if(newFaSet.xPlus(leftSide, rightSide)) {
				rest = new TreeSet<String>(test);
				isReduceable = true;
			}
			newFaSet.remove(newFa);
		}
		
		FA finalFa = new FA(leftSide, rest);
		newFaSet.add(finalFa);
		this.faSet.remove(fa);
		this.faSet.add(finalFa);
		
		return isReduceable;
	}
	
	public void rightReductAll() {
		if(!this.faSet.isEmpty()) {
			// copy list to avoid Exception in thread "main" java.util.ConcurrentModificationException
			Set<FA> faSetCopy = new TreeSet<FA>(this.faSet);
			for(FA fa : faSetCopy) {
				this.rightReduction(fa);
			}
		}
	}
	

	
	public void leftReductAll() {
		if(!this.faSet.isEmpty()) {
			// copy list to avoid Exception in thread "main" java.util.ConcurrentModificationException
			Set<FA> faSetCopy = new TreeSet<FA>(this.faSet);
			for(FA fa: faSetCopy) {
				this.leftReduction(fa);
			}
		}
	}

	
	public void deleteAllTrivials() {
		Iterator<FA> faIt = this.faSet.iterator();
		
		while(faIt.hasNext()) {
			FA fa = faIt.next();
			if(fa.isTrivial()) {
				faIt.remove();
			}
		}
	}
	
	protected void unifyTwoFas(FA fa1, FA fa2) {
		if(fa1.getLeftSide().equals(fa2.getLeftSide())) {
			System.out.println("Unify "+fa1+" and "+fa2);
			Set<String> newRightSide = fa1.getRightSide();
			newRightSide.addAll(fa2.getRightSide());
			//boolean success = fa1.getRightSide().addAll(fa2.getRightSide());
			FA newFA = new FA(fa1.getLeftSide(),newRightSide);
			Iterator<FA> it = this.faSet.iterator();
			while(it.hasNext()) {
				FA fa = it.next();
				if(fa.equals(fa1) || fa.equals(fa2)) {
					it.remove();
				}
			}
			this.faSet.add(newFA);
		}
	}
	
	public void unifyFas() {
		Set<FA> firstFaSet = new TreeSet<FA>();
		Set<FA> secondFaSet = new TreeSet<FA>();
		// copy
		for(FA fa: this.faSet) {
			firstFaSet.add(fa);
			secondFaSet.add(fa);
		}
		
		Iterator<FA> itFirst = firstFaSet.iterator();
		Iterator<FA> itSecond = secondFaSet.iterator();
		FA faOne,faTwo;
		while(itFirst.hasNext()) {
			faOne = itFirst.next();
			while(itSecond.hasNext()) {
				faTwo = itSecond.next();
				//System.out.println("check unification of "+faOne+" and "+faTwo);
				//System.out.println(faOne+" is in: "+this.faSet.contains(faOne));
				//System.out.println(faTwo+" is in: "+this.faSet.contains(faTwo));
				if(this.faSet.contains(faOne) && this.faSet.contains(faTwo)) {
					this.unifyTwoFas(faOne, faTwo);
				}
				
			}
			// restart second loop
			itSecond = secondFaSet.iterator();
		}
	}
	
	public void removeAllTrivials() {
		Iterator<FA> it = this.faSet.iterator();
		FA fa;
		while(it.hasNext()) {
			fa = it.next();
			if(fa.isTrivial()) {
				it.remove();
			}
		}
	}
	
	public void calcFmin() {
		this.leftReductAll();
		this.rightReductAll();
		this.removeAllTrivials();
		this.unifyAllFas();
	}
	
	public void testElements() {
		Iterator<FA> it = this.faSet.iterator();
		while(it.hasNext()) {
			FA fa = it.next();
			boolean b = this.faSet.contains(fa);
			System.out.println("Set contains "+fa+": "+b);
		}
	}
	
	/*
		
		while(it.hasNext()) {
			FA faOne = it.next();
			while(it.hasNext()) {
				FA faTwo = it.next();
				System.out.println(faOne+" and "+faTwo);
				//this.unifyTwoFas(faOne, faTwo);
			}
			//it.remove();
			
		}
	}
	*/
	
	
	/*
	protected void unifyFas() {
		Iterator<FA> itOne = this.faSet.iterator();
		Iterator<FA> itTwo = this.faSet.iterator();
		Set<FA> remover = new TreeSet<FA>();
		while(itOne.hasNext()) {
			FA faOne = itOne.next();
			if(itOne.hasNext()) {
				FA faTwo = itOne.next();
				while(faTwo != null) {
					if(!faOne.equals(faTwo) && (this.unifyTwoFas(faOne, faTwo) != null)) {
						remover.add(faOne);
						remover.add(faTwo);
					}
					
					faTwo = itOne.next();
				}
			}
			
			 while(itTwo.hasNext()) {
			 
				FA faTwo = itTwo.next();
				if(!faOne.equals(faTwo) && (this.unifyTwoFas(faOne, faTwo) != null)) {
					//this.faSet.remove(faOne);
					//this.faSet.remove(faTwo);
					remover.add(faOne);
					remover.add(faTwo);
					//itOne.remove();
					//itTwo.remove();
					this.faSet.removeAll(remover);
					this.faSet.add(this.unifyTwoFas(faOne, faTwo));
				}
			
			}
		}
	}
	*/
	
	public void unifyAllFas() {
		//for(int i = 0; i < this.faSet.size(); i++) {
			this.unifyFas();
		//}
	}

	
	
	
	
	public boolean xPlus(Set<String> X, Set<String> Y) {
		// copy X per Konstruktor, otherwise X would be changed by xPlus
		Set<String> result = new TreeSet<String>(X);
		boolean changes = true;
		while(changes) {
			changes = false;
			for(FA fa : this.faSet) {
				if(result.containsAll(fa.leftSide)) {
					changes = changes || result.addAll(fa.getRightSide());
				}
			}
		}
		return result.containsAll(Y);
	}
	
	public Set<String> calcSchluessel(Set<String> V) {
		Iterator<String> it = V.iterator();
		// for(String A: V) has iteration problems in synchronizations...
		while(it.hasNext()) {
			String A = it.next();
			Set<String> test = new TreeSet<String>(V);
			test.remove(A);
			if(this.xPlus(test, V)) {
				//System.out.println("Streiche: " + A);
				it.remove();
			}
		
		}
		
		return V;
	}
	
	public boolean isSchluesselAttributeOf(String attr, Set<String> V) {
		Set<String> schluessel = this.calcSchluessel(V);
		return schluessel.contains(attr);
	}
	
	public boolean isNotSchluesselAttributeOf(String attr, Set<String> V) {
		return !this.isSchluesselAttributeOf(attr, V);
	}
	
	public boolean isSchluesselSubsetOf(Set<String> attrs, Set<String> V) {
		Set<String> schluessel = this.calcSchluessel(V);
		// subset relation attrs c schluessel
		boolean isSchluesselSubset = schluessel.containsAll(attrs);
		/* correct but inefficient, because of recalculation of schluessel
		for(String attribute : attrs) {
			// isSchluesselSubset = isSchluesselSubset && this.isSchluesselAttributeOf(attribute, V);
		}
		*/
		return isSchluesselSubset;
	}
	
	public boolean isNotSchluesselSubsetOf(Set<String> attrs,Set<String> V) {
		return !this.isSchluesselSubsetOf(attrs, V);
	}
	
	private boolean isIn3NF(FA fa, Set<String> V) {
		boolean isIn3nf = true;
		Set<String> schluessel = this.calcSchluessel(V);
		Set<String> leftSide = fa.getLeftSide();
		Set<String> rightSide = fa.getRightSide();
		for(String rightAttribute: rightSide) {
			// fa is not trivial
			if(!leftSide.contains(rightAttribute)) {
				// rightAttribut is not a Schluesselattribute
				if(this.isNotSchluesselAttributeOf(rightAttribute,V)) {
					// leftSide is Superschlüssel
					isIn3nf = isIn3nf && leftSide.containsAll(schluessel);
				}
			}
		}
		return isIn3nf;
	}
	
	public boolean isAllIn3NF(Set<String> V) {
		boolean all3nf = true;
		for(FA fa: this.faSet) {
			all3nf = all3nf && this.isIn3NF(fa, V);
		}
		return all3nf;
	}
	
	
	
	public void dreiNFsynthese(Set<String> V) {
		this.calcFmin();
		this.unifyAllFas();
		Set<String> schluessel = this.calcSchluessel(V);
		for(FA fa: this.faSet) {
			System.out.println("Schema: "+fa.getLeftSide().toString()+" "+fa.getRightSide().toString());
		}
		System.out.println("Evtl. benötigte Schlüsseltabelle:" + schluessel.toString());
	}
	
	
	public String toString() {
		String output = "";
		for(FA fa: this.faSet) {
			output += fa.toString()+", ";
		}
		return output;
		
	}
	
	
}
