package datastructures;

import fields.*;

public class EllipticCurve<T extends Z2> {

	// due to Weierstraßgleichung we have
	// g(X,Y,Z) = Y^2Z +a1*XYZ + a3*YZ^2 - X^3 -a2*X^2Z -a4*XZ^2 -a6*Z^3
	// note injection (x,y) |-> (x:y:1)
	// so we calc in R^2
	// f(x,y) = y^2 + a1*xy + a3*y - x^3 -a2*x^2 - a4*x - a6
	// hence store the coefficients a_i
	protected Z2 a1;
	protected Z2 a2;
	protected Z2 a3;
	protected Z2 a4;
	protected Z2 a6;
	// note that because of historic reasons there is no a5 in the
	// Weierstraßgleichung
	
	// defines the calculation type dependend on the actual class instance
	protected Z2 fieldType;

	// note: leading coefficient a1 defines the type of elliptic cure
	public EllipticCurve(Z2 type, Z2 a1, Z2 a2, Z2 a3, Z2 a4, Z2 a6) {
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a6 = a6;
		this.fieldType = type;
	}

	public Z2 getA1() {
		return a1;
	}

	public Z2 getA2() {
		return a2;
	}

	public Z2 getA3() {
		return a3;
	}

	public Z2 getA4() {
		return a4;
	}

	public Z2 getA6() {
		return a6;
	}

}
