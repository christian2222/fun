package datastructures;

import fields.Z2;

public class ProjectivePoint<T extends Z2> {

	public final ProjectivePoint<T> ZERO = new ProjectivePoint<T>();
	
	protected EllipticCurve<T> curve = null;
	protected Z2 x;
	protected Z2 y;
	protected boolean isProjectiveZero = false;

	/*
	public T typeCheck() {
		return (T)this.x;
	}

		public Z2 invert(Z2 x) {
			if(Calculator.isZero(x)) throw new IllegalArgumentException("Division by 0");
			if(x instanceof Integer) return null; // do euclidian algorithm mod p
			if(x instanceof Double) return Calculator.invert(x);
			return null;
		}
		*/
		
		public ProjectivePoint(EllipticCurve<T> curve, Z2 x, Z2 y) {
			this.curve = curve;
			this.x = x;
			this.y = y;
			this.isProjectiveZero = false;
		}
		
		protected ProjectivePoint() {
			this.isProjectiveZero = true;
		}
		
		public boolean isProjectiveZero() {
			return this.isProjectiveZero;
		}
		
		public boolean isSameProjectivePointAs(ProjectivePoint<T> q) {
			// if this (= p) is the same projective point as q
			// then by def of projective space there is a lambda != 0 in R s.t.
			// (qx,qy) = (lambda*px,lambda*py)
			// hence qx/px = lambda and qy/py = lambda and lambda != 0
			// but be aware of px = 0 or py = 0!
			// so distinct 4 cases:
			boolean pxIsZero = Calculator.isZero(this.getX());
			boolean pyIsZero = Calculator.isZero(this.getY());
			boolean qxIsZero = Calculator.isZero(q.getX());
			boolean qyIsZero = Calculator.isZero(q.getY());
			if(pxIsZero && pyIsZero) {
				// hence p = (0,0)
				return (qxIsZero && qyIsZero);
			}
			if(pxIsZero && !pyIsZero) {
				// hence p = (0,y) with y != 0
				// so qx must be 0.
				Z2 lambda = Calculator.mult(q.getY(),Calculator.invert(this.getY()));
				return qxIsZero && !Calculator.isZero(lambda);
			}
			if(pyIsZero && !pxIsZero) {
				// hence p = (x,0) with x != 0
				// so qy must be 0.
				Z2 lambda = Calculator.mult(q.getX(),Calculator.invert(this.getX()));
				return qyIsZero && !Calculator.isZero(lambda);
			}
			// last case is px != 0 and py != 0
			Z2 lambdaX = Calculator.mult(q.getX(),Calculator.invert(this.getX()));
			Z2 lambdaY = Calculator.mult(q.getY(),Calculator.invert(this.getY()));
			// exclude also the case that qx = qy = 0, where lambda would be 0
			return lambdaX.equals(lambdaY) && !Calculator.isZero(lambdaX);
		}
		
		public ProjectivePoint<T> add(ProjectivePoint<T> q) {
			if(this.isProjectiveZero()) return q;
			// hence from now on p=(x1,x2)!= ZERO in proj space
			if(!q.isProjectiveZero()) {
				if(this.isProjectiveInverse(q)) return this.ZERO;
				// else do projective addition in R^2 with points
				// p=this=(x1,y1) and q=(x2,y2)
				// here were in the case p!= ZERO and q != ZERO and p++q != ZERO in projective space
				// note that ++ in projective space is commutative, hence p++q = 0 = q++p
				// so we don't need to check if q.isProjectiveInverse(p=this), sind --p is unique for p
				// now add p and q in proj space according to Thm 2.3.13 
				// in Werner, "Elliptische kurven in der Kryptographie"
				// get the points
				Z2 x1 = this.getX();
				Z2 y1 = this.getY();
				Z2 x2 = q.getX();
				Z2 y2 = q.getY();
				// get the coefficents out of the elliptic curve
				Z2 a1 = this.curve.getA1();
				Z2 a2 = this.curve.getA2();
				Z2 a3 = this.curve.getA3();
				Z2 a4 = this.curve.getA4();
				Z2 a6 = this.curve.getA6();
				//r=(x3,y3) should be p++q
				Z2 x3;
				Z2 y3;
				Z2 lambda;
				Z2 nue;
				Z2 numerator;
				Z2 denominator;
				Z2 twoY1 = Calculator.add(y1, y1);
				Z2 threeX1 = Calculator.add(Calculator.add(x1,x1),x1);
				Z2 twoA6 = Calculator.add(a6, a6);
				Z2 twoA2 = Calculator.add(a2, a2);
				if(x1 == x2) {
					//denominator = 2*y1 + a1*x1 + a3
					denominator = Calculator.add(Calculator.add(twoY1,Calculator.mult(a1, x1)),a3);
					//numerator = 3*x1*x1 + 2*a2*x1 + a4 - a1*y1
					numerator = Calculator.add(Calculator.mult(threeX1,x1), Calculator.mult(twoA2,x1));
					numerator = Calculator.sub(Calculator.add(numerator, a4),Calculator.mult(a1, y1));
					// lambda = (3*x1*x1 + 2*a2*x1 + a4 - a1*y1) * invert(2*y1 + a1*x1 + a3);
					lambda = Calculator.div(numerator,denominator);
					// nue = (-x1*x1 + a4*x1 + 2*a6 - a3*y1) * invert(2*y1 + a1*x1 + a3); 
					numerator = Calculator.add(twoA6,Calculator.mult(a4, x1));
					numerator = Calculator.sub(Calculator.sub(numerator, Calculator.mult(x1, x1)),Calculator.mult(a3, y1));
					nue = Calculator.div(numerator, denominator);
				} else { // x1!=x2
					// denominator = x2-x1
					denominator = Calculator.sub(x2, x1);
					// lambda = (y2-y1)*this.invert(x2-x1);
					numerator = Calculator.sub(y2, y1);
					lambda = Calculator.div(numerator, denominator);
					// nue = (y1*x2 -y2*x1) * this.invert(x2-x1);
					numerator = Calculator.sub(Calculator.mult(y1, x2), Calculator.mult(y2, x1));
					nue = Calculator.div(numerator, denominator);
				}
				// x3 = lambda*lambda + a1*lambda -a2 -x1 -x2;
				x3 = Calculator.add(Calculator.mult(lambda, lambda), Calculator.mult(a1, lambda));
				x3 = Calculator.sub(Calculator.sub(Calculator.sub(x3, a2), x1), x2);
				// y3 = -(lambda+a1)*x3 - nue - a3;
				y3 = Calculator.negate(Calculator.mult(Calculator.add(lambda, a1), x3));
				y3 = Calculator.sub(Calculator.sub(y3, nue), a3);
				
				return new ProjectivePoint<T>(this.curve,x3,y3);
				
			} else { // q is projective ZERO
				// do nothing in projective addition
				return this;
			}
		}
		
		public Z2 getX() {
			return this.x;
		}
		
		public Z2 getY() {
			return this.y;
		}
		
		// check if q is projective inverse of p (= this)
		protected boolean isProjectiveInverse(ProjectivePoint<T> q) {
			// px == qx and qy == -py-a1*px-a3
			boolean isInverse = ( this.getX().equals(q.getX()) );
			Z2 a1 = this.curve.getA1();
			Z2 a3 = this.curve.getA3();
			Z2 minusQy = Calculator.add(Calculator.add( Calculator.mult(a1, this.getX()),a3),this.getY());
			Z2 qY = Calculator.negate(minusQy);
			isInverse &= q.getY().equals(qY);
			return isInverse;
		}
		
}
