package ecc;

import algorithms.ExpandedEuclideanAlgorithm;
import algorithms.PrimeGenerator;
import algorithms.TonelliShanks;

public class Fp extends Field<Integer> {

	protected int prime;
	
	// NO_SOLUTION could be null, but then we can't calculate further...
	protected Integer NO_SOLUTION = Integer.valueOf(1);
	
	public Fp(int p) {
		this.prime = p;
	}
	
	private int calcMod(Integer x) {
		x = x % this.prime;
		while(x < 0) x+= this.prime;
		x = x % this.prime;
		return x;
	}
	
	private boolean isPrimeField() {
		return PrimeGenerator.isPrime(this.prime);
	}
	

	@Override
	public Integer add(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return this.calcMod(x+y);
	}

	@Override
	public Integer mult(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return this.calcMod(x*y);
	}

	@Override
	public Integer invertAdd(Integer x) {
		// TODO Auto-generated method stub
		return this.calcMod(-x);
	}

	@Override
	public Integer invertMult(Integer x) {
		// TODO Auto-generated method stub
		if(this.isZero(x)) throw new IllegalArgumentException("Division by 0");
		ExpandedEuclideanAlgorithm eea = new ExpandedEuclideanAlgorithm();
		long inverse = eea.getPositiveMultInverseModK(x.intValue(), this.prime);
		return Integer.valueOf((int)inverse);
	}

	@Override
	public Integer getNewElement(int i) {
		// TODO Auto-generated method stub
		return Integer.valueOf(i);
	}

	@Override
	public boolean isZero(Integer x) {
		// TODO Auto-generated method stub
		return x.intValue() == 0;
	}

	@Override
	public boolean isField() {
		// TODO Auto-generated method stub
		return this.isPrimeField();
	}

	@Override
	public boolean isGreaterEqualZero(Integer x) {
		// TODO Auto-generated method stub
		return x.intValue() >= 0;
	}

	@Override
	public boolean isF2() {
		// TODO Auto-generated method stub
		return this.prime == 2;
	}

	@Override
	public Integer get2() {
		// TODO Auto-generated method stub
		if(this.isF2()) {
			System.out.println("WARNING: Cannot invert 2 because we are in F2!");
			return Integer.valueOf(1);
		}
		return Integer.valueOf(2);
	}

	@Override
	public boolean hasSquareRoot(Integer x) {
		// TODO Auto-generated method stub
		return TonelliShanks.hasQuadraticRoot(x, this.prime);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if(this.isField()) {
			return "F"+this.prime;
		}
		return "no Field";
	}

	@Override
	public Integer squareRootOf(Integer x) {
		// TODO Auto-generated method stub
		if(this.isF2()) {
			System.out.println("WARNING: Cannot build sqaureRootOf("+x+") since we are in F2");
			return this.NO_SOLUTION;
		}
		if(this.hasSquareRoot(x)) {
			return TonelliShanks.runAlgorithm(x, this.prime);			
		}
		System.out.println("WARNING: No squareRootOf("+x+") modulo "+this.prime+" found!");
		return this.NO_SOLUTION;
	}

	@Override
	public boolean isEqual(Integer x, Integer y) {
		// TODO Auto-generated method stub
		return this.calcMod(x) == this.calcMod(y);
	}

}
