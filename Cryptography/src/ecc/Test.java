package ecc;

public class Test {

	public static void main(String[] args) {
		Field<Double> dField = new Real();
		Field<Integer> fp = new Fp(47);
		EllipticCurve<Double> dCurve = new EllipticCurve<>(dField, 1.0, 2.0, 3.0, 4.0, 5.0);
		ProjPoint<Double> x = new ProjPoint<>(dCurve,Double.valueOf(2),3d);
		x.searchPointOnCurve();

		System.out.println(x);
		System.out.println(x.add(x));


		EllipticCurve<Integer> iCurve = new EllipticCurve<>(fp, 1, 2, 3, 4, 5);
		ProjPoint<Integer> i = new ProjPoint<>(iCurve,2,Integer.valueOf(3));
		i.searchPointOnCurve();
		ProjPoint<Integer> j = i.createNewProjPointOnSameCurveAndField(3, 4);
		System.out.println(i);
		System.out.println(j);
		i = i.add(j);
		System.out.println(i);
		x.rechne();
		i.rechne();
	}
}
