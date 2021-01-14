package algorithms;

import java.util.ArrayList;
import datastructures.*;

public class ExpandedEuclideanAlgorithm {

	ArrayList<DivisionTable> dtList = new ArrayList<DivisionTable>();
	long gcd = 0;
	
	
	protected long max(long a, long b) {
		if(a >= b) return a;
		else return b;
	}
	
	protected long min(long a, long b) {
		if(a < b) return a;
		else return b;
	}
	
	public void writeOutTable() {
		for(int i = 0; i < this.dtList.size(); i++) {
			System.out.println(dtList.get(i));
		}
	}
	
	public long getGcd() {
		return this.gcd;
	}
	
	protected long[] sort(long a, long b) {
		long[] sorted = new long[2];
		if( a == this.max(a, b) ) {
			sorted[0] = a;
			sorted[1] = b;
		} else {
			sorted[0] = b;
			sorted[1] = a;
		}
		return sorted;
	}
	
	protected boolean bothPositive(long a, long b) {
		return (a >=0) && (b >=0);
	}
	
	
	public boolean hasMultInverseModK(long hasInversve, long k) {
		this.runAlgortihm(hasInversve, k);
		return (this.getGcd() == 1);
	}
	
	public long getPositiveMultInverseModK(long toInverse, long k) {
		this.runAlgortihm(toInverse, k);
		long gcd = this.getGcd();
		long inverse = 0;
		if( (!this.dtList.isEmpty()) && (gcd == 1) ) {
			DivisionTable dt = this.dtList.get(0);
			long s = dt.getFactorS();
			long t = dt.getFactorT();
			// remind: S*ToInverse + t*k = 1 or s*k +t*toInverse = 1
			if(s*toInverse + t*k == gcd) {
				inverse = s;
			}
			if(s*k + t*toInverse == gcd) {
				inverse = t;
			}
			// => inverse*toInverse == 1 (k)
			// => inverse*toInverse + c*k = 1
			// => (inverse + q*k)*toInverse +c*k = inverse*toInverse + q*k*toInverse + c*k
			// 		== inverse*toInverse + 0 (k) == 1 (k)
			// => find q*k st. inverse + q*k > 0
			
			while(inverse < 0) {
				inverse += k;
			}
		}
		
		return inverse%k;
	}
	
	public long[] getMultiplikators(long a, long b) {
		this.runAlgortihm(a, b);
		long gcd = this.getGcd();
		// remind: s*a + t*b = gcd
		//				 (s, a, t, b, gcd)
		long[] retArray = {0, 0, 0, 0, 0};
		if( !this.dtList.isEmpty()) {
			DivisionTable dt = this.dtList.get(0);
			//System.out.println(dt);
			long s = dt.getFactorS();
			long t = dt.getFactorT();
			//this.writeOutTable();
			/*
			System.out.println(dt.getDividend());
			System.out.println(dt.getDivisor());
			System.out.println(gcd);
			System.out.println(a);
			System.out.println(b);
			System.out.println(s);
			System.out.println(t);
			System.out.println(s*a+b*t);
			System.out.println(t*a+s*b);
			System.out.println(s*a+b*t == gcd);
			System.out.println(t*a+b*s == gcd);
			*/
			// overflow problem because of linear combination
			if(s*a + b*t == gcd) {
				retArray[0] = s;
				retArray[2] = t;
			} else if (s*b + a*t == gcd) {
				retArray[0] = t;
				retArray[2] = s;
			}
			retArray[1] = a;
			retArray[3] = b;
			retArray[4] = (int)gcd;
		}
		//=> [0]*[1] + [2]*[3] = [4]
		//<=> [0]*a + [2]*b = gcd
		return retArray;
		
	}
	
	public boolean nonTrivialLinearKombination(long[] entries) {
		boolean valid = true;
		valid &= entries.length == 5;
		valid &= (entries[0] != 0) || (entries[2] != 0);
		valid &= (entries[0]*entries[1] + entries[2]*entries[3] == entries[4]);
		return valid;
	}
	
	public void runAlgortihm(long a, long b) {
		this.dtList = new ArrayList<DivisionTable>();
		
		if(!this.bothPositive(a, b) || ((a == 0) && (b == 0)) ) {
			System.out.println("Error: Division by 0");
			this.dtList.add(new DivisionTable(0, 0, 0, 0, 0, 0));
			return;
		}
		
		// ensure wlog that a >= b
		long[] sorted = this.sort(a,b);
		a = sorted[0];
		b = sorted[1];
		
		long q = 0;
		long r = 0;
		long gcd = 0;
		while( (a != 0) && (b != 0) ) {
			q = a / b;
			r = a % b;
			this.dtList.add(new DivisionTable(a, b, q, r, 0, 0));
			a = b;
			b = r;
		}
		
		// why can the following if block be omitted?
		if( (a == 0) && (b == 0) ) {
			System.out.println("Error: result(0,0) is unusual");
			this.dtList.add(new DivisionTable(0, 0, 0, 0, 0, 0));
			return;
		}
		
		if(a == 0) gcd = b;
		if(b == 0) gcd = a;
		//System.out.println("(" +a+ ", "+ b +", "+ q +", "+ r +", 0, 0)");
		// as gcd is found, create a linear combination of original a and b for gcd
		// hance, lets find t and s such that gcd = as + bt
		// remind: either a or b is 0;
		long t = 0;
		long s = 1;
		DivisionTable dtEntry = new DivisionTable(0,0,0,0,0,0);
		for(int k = this.dtList.size() -1; k >=0; k--) {
			dtEntry = this.dtList.get(k);
			s = t;
			// a is actual dividend, b is actual divisor
			// ensurce gcd = as + bt;
			t = (gcd -s*dtEntry.getDividend())/dtEntry.getDivisor();
			// save s and t in current divisiontableEntry
			dtEntry.setFactorS(s);
			dtEntry.setFactorT(t);
			// write back
			this.dtList.set(k, dtEntry);
		}
		
		this.gcd = gcd;
		
	}
	
	public void test() {
		ArrayList<DivisionTable> list = new ArrayList<DivisionTable>();
		list.add(new DivisionTable(0, 0, 0, 0, 0, 0));
		list.add(new DivisionTable(1, 1, 1, 1, 1, 1));
		list.add(new DivisionTable(2, 2, 2, 2, 2, 2));
		list.add(new DivisionTable(3, 3, 3, 3, 3, 3));
		list.add(new DivisionTable(4, 4, 4, 4, 4, 4));
		
		for(int i = list.size()-1; i >= 0; i--) {
			System.out.println(list.get(i));
		}
	}
	
	public static void main(String[] args) {
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		//eea.runAlgortihm(78, 99);
		//eea.runAlgortihm(65, 105);
		//eea.writeOutTable();
		//System.out.println(eea.getGcd());
		long[] multiplicators = eea.getMultiplikators(78, 99);
		System.out.print("(");
		for(int i = 0; i < multiplicators.length; i++) {
			System.out.print(multiplicators[i]+", ");
		}
		System.out.println(")");
	}
}
