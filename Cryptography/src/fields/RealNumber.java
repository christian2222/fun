package fields;

public class RealNumber extends Fp {
/*
	public RealNumber(int p) {
		super(p);
	}
	*/
	double value = 0;
	
	public RealNumber(double x) {
		super(2);
		value = x;
	}

	
	public double add(double a, double b) {
		return a+b;
	}
	
	public double mult(double a, double b) {
		return a*b;
	}
	
	public double sub(double a, double b) {
		return a-b;
	}
	
	public double invertAdd(double a) {
		return -a;
	}
	
	public double invertMult(double a) {
		if(a == 0) throw new IllegalArgumentException("Cannot divide by 0");
		else return 1/a;
	}
	
	public boolean isZero(double a) {
		return a == 0;
	}
	
	public double doubleValue() {
		return value;
	}
	
	public int intValue() {
		return 1;
	}
	
	public boolean isZero() {
		return this.value == 0;
	}
	
	public static Z2 makeInstanceOf(double x) {
		return new RealNumber(x);
	}
}
