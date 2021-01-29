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
	
	protected int[] xSearchArray = new int[] {-5,-4,-3,-2,-1,0,1,2,3,4,5,6,7,8,9,10,15,20,25,50,75,100,150,200};
	
	// y-values for solving weierstrass-equation
	protected T y1;
	protected T y2;
	
	
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
	public void calculateBs() {
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
	
	/*
	 * y^2 + a1*x*y + a3*y -x^3 - a2*x^2 -a4*x -a6 = 0
	 */
	public String toString() {
		return "y^2 + "+this.getA1()+"*x*y + "+this.getA3()+"*y - x^3 - "+this.getA2()+"*x^2 - "+this.getA4()+"*x - "+this.getA6()+" = 0"; 
	}
	

	// solve weierstrass-gleichung = 0 to y
	// hence y^2 + a1*x*y + a3*y -x^3 - a2*x^2 -a4*x -a6 = 0
	// we get the discriminant D= 4*x^3 + b2*x^2 +2*b4*x + b6, where b2,b4,b6 are the same as in the elliptic curve
	protected T calculateDiscriminantToWeierstrassIsZero(T x) {
		T yToZeroDiscriminant;
		this.calculateBs();
		T b2 = this.getB2();
		T b4 = this.getB4();
		T b6 = this.getB6();
		
		T fourXPow3 = this.field.fourTimes(this.field.cube(x));
		T b2xSquare = this.field.mult(b2, this.field.square(x));
		T twoB4x = this.field.mult(this.field.twoTimes(b4),x);
		
		yToZeroDiscriminant = this.field.add(this.field.add(fourXPow3, b2xSquare), this.field.add(twoB4x,b6));
		return yToZeroDiscriminant;
	}
	
	// Weierstrass-Discriminant >= 0
	public boolean hasSolutionsDependingOnX(T x) {
		return this.field.isGreaterEqualZero(this.calculateDiscriminantToWeierstrassIsZero(x));
	}
	
	// y1/2 = -(a1*x+a3)/2 +- squareRootOf(discriminant)/2
	public T calculateStartingPointsOnX(T x) {
		if(!this.hasSolutionsDependingOnX(x)) {
			System.out.println("WARNING: Weierstrass-Equation could not be solved to y");
			System.out.println("Choose another x-value than "+x+", since this has the Discriminant " + this.calculateDiscriminantToWeierstrassIsZero(x).toString());
			return null;
		}
		if(this.field.isF2()) {
			System.out.println("WARNING: You are in F2, where you cannot calculate the Discriminant");
			return null;
		}


		T discriminant = this.calculateDiscriminantToWeierstrassIsZero(x);
		T y;
		
		if(!this.field.hasSquareRoot(discriminant)) {
			System.out.println("WARNING: Cannot find squareRoot("+discriminant+")!");
			System.out.println("Note: Your are in "+ this.field);
			return null;
		}
		
		// idea: use quadratic completion to generalize to Fp and R
		// the discriminant remains the same, since we get
		// [2y + (a1*x + a3)]^2 =4*x^3 + b2*x^2 + 2*b4*x + b6
		// solve to 2y + (a1*x + a3) =: l
		if(this.field.isGreaterEqualZero(discriminant)) {
			// Note: l = +/- squareRoot(discriminant)
			T l1 = this.field.squareRootOf(discriminant);
			T l2 = this.field.invertAdd(l1);
			// as l = 2y + (a1*x + a3) we have that y = 1/2*(l - a1*x -a3)
			T bracket = this.field.sub(this.field.sub(l1, this.field.mult(this.getA1(), x)),this.getA3());
			T two = this.field.get2();
			T inverse2 = this.field.invertMult(two);
			y = this.field.mult(inverse2, bracket);
			this.y1 = y;
			// calculate the same with l2
			bracket = this.field.sub(this.field.sub(l2, this.field.mult(this.getA1(), x)),this.getA3());
			two = this.field.get2();
			inverse2 = this.field.invertMult(two);
			y = this.field.mult(inverse2, bracket);
			this.y2 = y;
			
			return y;
		} else {
			System.out.println("WARNING: Discriminant "+discriminant+" is smaller than 0");
			return null;
		}
	}
	
	public T getFirstStartingPointOnX(T x) {
		this.calculateStartingPointsOnX(x);
		return this.y1;
	}
	
	public T getSecondStartingPointOnX(T x) {
		this.calculateStartingPointsOnX(x);
		return this.y2;
	}
	
	public boolean foundStartingPointOnX(T x) {
		return this.calculateStartingPointsOnX(x) != null;
	}
	
	public ProjPoint<T> searchPoint() throws Exception {

		boolean found = false;
		
		for(int i : this.xSearchArray) {
			T x = this.field.getNewElement(i);
			System.out.println("Try x-value "+x);
			if(this.foundStartingPointOnX(x)) {
				T y = this.calculateStartingPointsOnX(x);
				System.out.print("Returning Point P("+x+"/"+y+") ");
				System.out.println("on ellitpic curve  "+this.toString());
				return new ProjPoint<T>(this, x, y);
			} else {
				System.out.println("No Point found");
			}
		}
		
		throw new NoPointFoundException("Error: No point found on ellitpic curve "+this.toString());
	}
	
	public T resetXandCalculateY(T x) {
		T y;
		if(this.foundStartingPointOnX(x)) {
			y = this.calculateStartingPointsOnX(x);
		} else {
			y = null;
			System.out.println("WARNING: Don't calculate, because y-value is NULL. Choose another x-value.");
		}
		return y;
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

	public T getB2() {
		return b2;
	}


	public T getB4() {
		return b4;
	}


	public T getB6() {
		return b6;
	}


	public T getB8() {
		return b8;
	}


	
}
