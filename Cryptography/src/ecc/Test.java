package ecc;

public class Test {

	public static void main(String[] args) {
		Field<Double> dField = new Real();
		Field<Integer> fp = new Fp(47);
		EllipticCurve<Double> dCurve = new EllipticCurve<>(dField, 1.0, 2.0, 3.0, 4.0, 5.0);
		EllipticCurve<Integer> iCurve = new EllipticCurve<>(fp, 1, 2, 3, 4, 5);
		try {
			ProjPoint<Double> x = dCurve.searchPoint();
			ProjPoint<Integer> i = iCurve.searchPoint();

			System.out.println(x);
			// note: projective addition is not correct
			System.out.println(i);
			i = i.add(i);
			System.out.println(i);
			i = i.add(i);
			System.out.println(i);
			System.out.println(x.add(x));

		} catch(Exception e) {
			System.out.println(e);
			System.out.println("No points found on some curve!");
		}
	}
}
