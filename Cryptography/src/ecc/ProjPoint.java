package ecc;


public class ProjPoint<T extends Number> {

	public final ProjPoint<T> ZERO = null;
	
	protected EllipticCurve<T> curve;
	protected T x;
	protected T y;
	protected Field<T> field;
	protected boolean isProjectiveZero = false;
	
	
	public ProjPoint(EllipticCurve<T> curve, T x, T y) {
		this.isProjectiveZero = false;
		this.curve = curve;
		this.field = curve.getField();
		this.x = x;
		this.y = y;
		System.out.println("Construct new Projective Point P("+x.toString()+"/"+y.toString()+") on curve "+this.curve);
		
		if(this.curve.foundStartingPointOnX(x)) {
			this.y = this.curve.calculateStratingPointOnX(x);
			System.out.println("Note: calculated starting point dependent on x="+this.x);
			System.out.println("Now ("+this.x+"/"+this.y+") is a point on the elliptic curve "+this.curve);
			if(!this.field.isEqual(this.y, y)) {
				System.out.println("Somthing went wrong in calculation:");
				System.out.println("y-Value "+y+" was given, but y-Value "+this.y+" was calculated!");
			} else {
				System.out.println("Calculation was great, since "+y+" is equal to "+this.y+" in "+this.field);
			}
		} else {
			System.out.println("WARNING: No Point on curve found!");
			this.y = y;
		} 
	}
	
	// don't use this constructor because of stack overflow!
	protected ProjPoint() {
		this.isProjectiveZero = true;
	}
	
	public ProjPoint<T> createNewProjPointOnSameCurveAndField(T x, T y) {
		ProjPoint<T> newPoint = new ProjPoint<T>(this.curve, x, y);
		return newPoint;
	}
	
	public boolean isProjectiveZero() {
		return this.isProjectiveZero;
	}
	
	public T getXCoordinate() {
		return x;
	}
	
	public T getYCoordinate() {
		return y;
	}
	
	public boolean isSameProjectivePointAs(ProjPoint<T> q) {
		// if this (= p) is the same projective point as q
		// then by def of projective space there is a lambda != 0 in R s.t.
		// (qx,qy) = (lambda*px,lambda*py)
		// hence qx/px = lambda and qy/py = lambda and lambda != 0
		// but be aware of px = 0 or py = 0!
		// so distinct 4 cases:
		boolean pxIsZero = this.field.isZero(this.getXCoordinate());
		boolean pyIsZero = this.field.isZero(this.getYCoordinate());
		boolean qxIsZero = this.field.isZero(q.getXCoordinate());
		boolean qyIsZero = this.field.isZero(q.getYCoordinate());

		if(pxIsZero && pyIsZero) {
			// hence p = (0,0)
			return (qxIsZero && qyIsZero);
		}
		if(pxIsZero && !pyIsZero) {
			// hence p = (0,y) with y != 0
			// so qx must be 0.
			T lambda = this.field.div(q.getYCoordinate(), this.getYCoordinate());
			return qxIsZero && !this.field.isZero(lambda);
		}
		if(pyIsZero && !pxIsZero) {
			// hence p = (x,0) with x != 0
			// so qy must be 0.
			T lambda = this.field.div(q.getXCoordinate(), this.getXCoordinate());
			return qyIsZero && !this.field.isZero(lambda);
		}
		// last case is px != 0 and py != 0
		T lambdaX = this.field.div(q.getXCoordinate(), this.getXCoordinate());
		T lambdaY = this.field.div(q.getXCoordinate(), this.getYCoordinate());
		// exclude also the case that qx = qy = 0, where lambda would be 0
		return lambdaX.equals(lambdaY) && ! this.field.isZero(lambdaX);
	}
	
	// check if q is projective inverse of p (= this)
	protected boolean isProjectiveInverse(ProjPoint<T> q) {
		// px == qx and qy == -py-a1*px-a3
		boolean isInverse = ( this.getXCoordinate().equals(q.getXCoordinate()) );
		T a1 = this.curve.getA1();
		T a3 = this.curve.getA3();
		T minusQy = this.field.add(this.field.add( this.field.mult(a1, this.getXCoordinate()),a3),this.getYCoordinate());
		T qY = this.field.invertAdd(minusQy);
		isInverse &= q.getYCoordinate().equals(qY);
		return isInverse;
	}
	
	public ProjPoint<T> add(ProjPoint<T> q) {
		if(this.isProjectiveZero()) return q;
		// hence from now on p=(x1,x2)!= ZERO in proj space
		if(!q.isProjectiveZero()) {
			if(this.isProjectiveInverse(q)) return this.ZERO;
			// else do projective addition in R^2 with points
			// p=this=(x1,y1) and q=(x2,y2)
			// here were in the case p!= ZERO and q != ZERO and p++q != ZERO in projective space
			// note that ++ in projective space is commutative, hence p++q = 0 = q++p
			// so we don't need to check if q.isProjectiveInverse(p=this), since --p is unique for p
			// now add p and q in proj space according to Thm 2.3.13 
			// in Werner, "Elliptische kurven in der Kryptographie"
			// get the points
			T x1 = this.getXCoordinate();
			T y1 = this.getYCoordinate();
			T x2 = q.getXCoordinate();
			T y2 = q.getYCoordinate();
			// get the coefficents out of the elliptic curve
			T a1 = this.curve.getA1();
			T a2 = this.curve.getA2();
			T a3 = this.curve.getA3();
			T a4 = this.curve.getA4();
			T a6 = this.curve.getA6();
			//r=(x3,y3) should be p++q (in projective space)
			T x3;
			T y3;
			T lambda;
			T nue;
			T numerator;
			T denominator;
			T twoY1 = this.field.twoTimes(y1);
			T threeX1 = this.field.threeTimes(x1);
			T twoA6 = this.field.twoTimes(a6);
			T twoA2 = this.field.twoTimes(a2);
			if(x1.equals(x2)) { // x1 == x2; note that we need to use equals because of reference equivalences
				//denominator = 2*y1 + a1*x1 + a3
				denominator = this.field.add(this.field.add(twoY1,this.field.mult(a1, x1)),a3);
				//numerator = 3*x1*x1 + 2*a2*x1 + a4 - a1*y1
				numerator = this.field.add(this.field.mult(threeX1,x1), this.field.mult(twoA2,x1));
				numerator = this.field.sub(this.field.add(numerator, a4),this.field.mult(a1, y1));
				// lambda = (3*x1*x1 + 2*a2*x1 + a4 - a1*y1) * invert(2*y1 + a1*x1 + a3);
				lambda = this.field.div(numerator,denominator);
				// nue = (-x1*x1 + a4*x1 + 2*a6 - a3*y1) * invert(2*y1 + a1*x1 + a3); 
				numerator = this.field.add(twoA6,this.field.mult(a4, x1));
				numerator = this.field.sub(this.field.sub(numerator, this.field.square(x1)),this.field.mult(a3, y1));
				nue = this.field.div(numerator, denominator);
			} else { // x1!=x2
				// denominator = x2-x1
				denominator = this.field.sub(x2, x1);
				// lambda = (y2-y1)*this.invert(x2-x1);
				numerator = this.field.sub(y2, y1);
				lambda = this.field.div(numerator, denominator);
				// nue = (y1*x2 -y2*x1) * this.invert(x2-x1);
				numerator = this.field.sub(this.field.mult(y1, x2), this.field.mult(y2, x1));
				nue = this.field.div(numerator, denominator);
			}
			// x3 = lambda*lambda + a1*lambda -a2 -x1 -x2;
			x3 = this.field.add(this.field.square(lambda), this.field.mult(a1, lambda));
			x3 = this.field.sub(this.field.sub(this.field.sub(x3, a2), x1), x2);
			// y3 = -(lambda+a1)*x3 - nue - a3;
			y3 = this.field.invertAdd(this.field.mult(this.field.add(lambda, a1), x3));
			y3 = this.field.sub(this.field.sub(y3, nue), a3);
			
			return new ProjPoint<T>(this.curve,x3,y3);
			
		} else { // q is projective ZERO
			// do nothing in projective addition
			return this;
		}
	}
	
	/*
	public ProjPoint<T> findPointOnCurve(EllipticCurve<T> curve) {

		T tmpX = this.x;
		T tmpY = this.y;
		EllipticCurve<T> ec = this.curve;
		
		this.curve = curve;
		boolean found = false;
		
		for(int x : this.xBigSearchArray) {
			this.x = this.field.getNewElement(x);
			if(this.foundStartingPointOnX()) {
				this.y = this.calculateStratingPointOnX();
				System.out.print("Found Point P("+this.getXCoordinate()+"/"+this.getYCoordinate()+") ");
				System.out.println("on ellitpic curve  "+this.curve.toString());
				found = true;
				return new ProjPoint<T>(this.curve, this.x, this.y);
			}
		}
		
		if(!found) {
			System.out.println("WARNING: Search for a point P on the elliptic curve "+this.curve+" was not successful");
			System.out.println("WARNING: No values initialized!");
		}
		
		this.curve = ec;
		this.x = tmpX;
		this.y = tmpY;
		
		return this;

	}
	*/
	
	public String toString() {
		String output = "("+this.getXCoordinate()+"/"+this.getYCoordinate()+")";
		return output;
	}
	
	// test calculation
	public void rechne() {
		this.checkIntegrity();
		T z = field.mult(field.add(x, y),y);
		System.out.println(z.intValue());
		z = field.invertMult(z);
		if(field.getClass().toString().equalsIgnoreCase("class ecc.Fp")) System.out.println(z.intValue());
		if(field.getClass().toString().equalsIgnoreCase("class ecc.Real")) System.out.println(z.doubleValue());
		if(field.isZero(z)) {
			System.out.println(0);
		}
	}
	
	// no longer needed from here on
	//Operation<T> operator = new Fp(7);
	Field<T> workingField = (Field<T>) new Real();
	Field<? extends Number> field2 = new Fp(7);
	//Operation<T> myField = (Operation<T>)new Real();
	Real r = new Real();


	
	
	public void checkIntegrity() {
		final String varInt = "class java.lang.Integer";
		final String varDoub = "class java.lang.Double";
		final String fieldInt = "class ecc.Fp";
		final String fieldDoub = "class ecc.Real";
		String xType = x.getClass().toString();
		String yType = y.getClass().toString();
		String fieldType = field.getClass().toString();
		System.out.println(xType.equalsIgnoreCase("class java.lang.Integer"));
		System.out.println(fieldType.equalsIgnoreCase("class ecc.Fp"));
		if(xType.equalsIgnoreCase(varInt) && yType.equalsIgnoreCase(varInt) && (fieldType.equalsIgnoreCase(fieldInt))) {
			System.out.println("Calculation in Fp");
			return;
		}
		if(xType.equalsIgnoreCase(varDoub) && yType.equalsIgnoreCase(varDoub) && (fieldType.equalsIgnoreCase(fieldDoub))) {
			System.out.println("Calculation in R");
			return;
		}
		System.out.println("Usage of ProjPoint:");
		System.exit(1);
	}
	
	
}
