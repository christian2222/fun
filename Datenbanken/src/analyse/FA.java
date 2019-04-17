package analyse;
import java.io.Serializable;
import java.util.*;


public class FA implements Comparable<Object>,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 775128507437370194L;
	protected Set<String> leftSide,rightSide;
	
	
	public FA(Set<String> left, Set<String> right) {
		this.leftSide = left;
		this.rightSide = right;
	}
	
	public static Set<Set<String>> calcAllSubsets(Set<String> set) {
		Set<Set<String>> powerSet = new HashSet<Set<String>>();
		if(set.isEmpty()) {
			powerSet.add(new TreeSet<String>());
			return powerSet;
		}
		List<String> list = new ArrayList<String>(set);
		String head = list.get(0);
		Set<String> rest = new TreeSet<String>(list.subList(1, list.size()));
		for(Set<String> aktSet: calcAllSubsets(rest)) {
			Set<String> newSet = new TreeSet<String>();
			newSet.add(head);
			newSet.addAll(aktSet);
			powerSet.add(newSet);
			powerSet.add(aktSet);
		}
		
		return powerSet;
		
	}
	
	public static String printAllSubsets(Set<String> set) {
		Set<Set<String>> powerSet = calcAllSubsets(set);
		String output = "{";
		for(Set<String> subset: powerSet) {
			output += subset.toString();
		}
		
		return output+"}";
	}
	
	
	
	public FA(String left, String right) {
		this.parseLeft(left);
		this.parseRight(right);
	}
	
	public Set<String> getLeftSide() {
		return this.leftSide;
	}

	public Set<String> getRightSide() {
		return this.rightSide;
	}

	
	public String toString() {
		String s = "";
		
		s += this.oneToString(this.leftSide) + " -> " + this.oneToString(rightSide);
			
		return s;
	}
	/*
	public boolean equals(FA fa2) {
		return false;
	}
	*/
	protected String oneToString(Set<String> set) {
		String output = "(";
		for(String s: set) {
			output += s + " ";
		}
		output = output.trim();
		output += ")";
		return output;
	}
	
	private void parseLeft(String input) {
		this.leftSide = parse(input);
	}
	
	private void parseRight(String input) {
		this.rightSide = parse(input);
	}
	
	public boolean isTrivial() {
		return this.leftSide.containsAll(this.rightSide);
	}
	
	public static Set<String> parse(String s) {
		Set<String> set = new TreeSet<String>();
		s = s.trim();
		if(!s.isEmpty()) {
			String [] output = s.split("\\s");
			for(int i = 0; i < output.length; i++) {
				set.add(output[i]);
			}
		}
		
		return set; 
	}
	
	@Override
	public int hashCode() {
		return this.leftSide.hashCode() * 37 + this.rightSide.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(! (obj instanceof FA)) return false;
		FA convert = (FA) obj;
		return (this.compareTo(convert) == 0);
	}
	
	
	// compareTo violates contract, so tree degenerates and elements cant be found
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		if(!(arg0 instanceof FA)) return -1;
		FA fa = (FA) arg0;
		return Integer.compare(this.hashCode(), fa.hashCode());
		//if(fa.leftSide.equals(this.leftSide) && fa.rightSide.equals(this.rightSide)) return 0;
		//return 1;
	}
	
	
}
