package fields;

import datastructures.EllipticCurve;

public class Test {
	
	public static void main(String[] args) {
		System.out.println(-7%5);	
		System.out.println((new Z2(ZeroOne.ONE)).getClass());
		RealNumber a1 = new RealNumber(1.0);
		RealNumber a2 = new RealNumber(2.0);
		RealNumber a3 = new RealNumber(3.0);
		RealNumber a4 = new RealNumber(4.0);
		RealNumber a6 = new RealNumber(6.0);
		RealNumber type = new RealNumber(10.0);
		EllipticCurve<RealNumber> realEC = new EllipticCurve<RealNumber>(type,a1, a2, a3, a4, a6);
	}
}
