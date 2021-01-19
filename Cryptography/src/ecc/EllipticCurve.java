package ecc;

public class EllipticCurve<T extends Number> {

	protected Field<T> field;

	/*
	 * coefficients in weierstrass-polynom
	 * y^2 + a1*x*y + a3*y -x^3 - a2*x^2 -a4*x -a6
	 */
	protected T a1;
	protected T a2;
	protected T a3;
	protected T a4;
	protected T a6;
	
	/*
	 * use Proposition 2.3.3 in Werner, Elliptishce Kurven in der Kryptographie 
	 * and the notes beore to calculate the discriminant
	 */
	protected T b2;
	protected T b4;
	protected T b6;
	protected T b8;
	
	protected T discriminant;
	
	
	public EllipticCurve(Field<T> field, T a1, T a2, T a3, T a4, T a6) {
		super();
		this.field = field;
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a6 = a6;
		if(!this.isNonSingular()) System.out.println("WARNING: Elliptic curve is singular!");
		if(!field.isField()) System.out.println("WARNING: You don't calculate over a field!");
	}
	

	// notes before Proposition 2.3.3
	protected void calculateBs() {
		T fourA2 = this.field.fourTimes(a2);
		T fourA6 = this.field.fourTimes(a6);
		T A2A6 = this.field.mult(a2, a6);
		T fourA2A6 = this.field.fourTimes(A2A6);
		
		// b2 = a1^2 + 4*a2
		this.b2 = this.field.add(this.field.square(a1),fourA2);
		// b4 = 2*a4 +a1*a3
		this.b4 = this.field.add(this.field.twoTimes(a4), this.field.mult(a1, a3));
		// b6 = a3^2 + 4*a6
		this.b6 = this.field.add(this.field.square(a3),fourA6);
		// b8 = a1^2*a6 + 4*a2*a6 - a1*a3*a4 + a2*a3^2 - a4^2
		// b8 = a1^2*a6 + 4*a2*a6 + a2*a3^2 - (a4^2 + a1*a3*a4)
		T subtrahend = this.field.add(this.field.square(a4), this.field.mult(a1, this.field.mult(a3, a4)));
		T addOne = this.field.mult(this.field.square(a1), a6);
		T addTwo = fourA2A6;
		T addThree = this.field.mult(a2, this.field.square(a3));
		T addition = this.field.add(addOne, this.field.add(addTwo, addThree));
		this.b8 = this.field.sub(addition, subtrahend);
	}
	
	// DELTA = 9*b2*b4*b6 -( b2^2*b8 + 8*b4^3 + 27*b6^2 )
	protected void calculateDiscriminant() {
		this.calculateBs();
		T subtrahend = this.field.mult(this.field.square(b2), b8);
		subtrahend = this.field.add(subtrahend, this.field.eightTimes(this.field.cube(b4)));
		subtrahend = this.field.add(subtrahend, this.field.twentySevenTimes(this.field.square(b6)));
		T addition = this.field.nineTimes(this.field.mult(b2, this.field.mult(b4, b6)));
		this.discriminant = this.field.sub(addition, subtrahend);
	}
	
	protected boolean hasZeroDiscriminant() {
		this.calculateDiscriminant();
		return this.field.isZero(this.discriminant);
	}
	
	public boolean isNonSingular() {
		return !this.hasZeroDiscriminant();
	}
	
	public Field<T> getField() {
		return field;
	}
	
	public T getA1() {
		return a1;
	}

	public T getA2() {
		return a2;
	}

	public T getA3() {
		return a3;
	}

	public T getA4() {
		return a4;
	}

	public T getA6() {
		return a6;
	}

	
	
}
