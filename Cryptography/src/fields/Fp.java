package fields;

import algorithms.ExpandedEuclideanAlgorithm;

public class Fp extends Z2 {

	protected static int prime = 2;
	
	public Fp(int p) {
		super(ZeroOne.ONE);
		prime = p;
	}
	
	private static int calcMod(int a) {
		a = a % prime;
		// now make it positive
		while(a < 0) a += prime;
		a = a % prime;
		return a;
	}
	
	// note that the following methods are not static, since they are dependend on this.prime.
	public int add(int a, int b) {
		a = calcMod(a);
		b = calcMod(b);
		return calcMod(a+b);
	}
	
	public int sub(int a, int b) {
		a = calcMod(a);
		b = calcMod(b);
		return calcMod(a-b);
	}
	
	public int mult(int a, int b) {
		a = calcMod(a);
		b = calcMod(b);
		return calcMod(a*b);
	}
	
	public int invertAdd(int a) {
		a = calcMod(a);
		int b = calcMod(prime - a);
		return b;
	}
	
	public int invertMult(int a) {
		a = calcMod(a);
		if(a == 0) throw new IllegalArgumentException("You cannot invert 0 multiplicative");
		// hence if a != 0 then a and this.prime are relative prime
		// ensure that a is positive.
		while(a < 0) a = a + prime;
		a = calcMod(a);
		// do euclidian algorithm
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		eea.runAlgortihm(prime, a);
		if(eea.getGcd() == 1) {
			long l = eea.getPositiveMultInverseModK(prime, a);
			int inverse = (int)(l % prime);
			return inverse;
		} else {
			String warning = "You cannot invert "+a+" multiplicative mod "+ prime+" since their gcd is not 1, but: "+eea.getGcd();
			throw new IllegalArgumentException(warning);
		}
		
	}
	
	public boolean isZero() {
		return prime == 0;
	}
	
	public static int intValue(int a) {
		return a;
	}
	
	public int intValue() {
		return prime;
	}
	
	public static Z2 makeInstanceOf(int i) {
		return new Fp(i);
	}
	
	public double doubleValue(int a) {
		return (double)a;
	}
	
}
