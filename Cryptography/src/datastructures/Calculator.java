package datastructures;

import fields.*;

public class Calculator {
	
	//public static Z2 VarFive = new Fp(5);
	//public static Z2 VarZero = new Fp(0);

	
	/* not needed, because ie. 3P = Calc.add(P,Calc.add(P,P));
	public static final Z2 ZERO = Integer.getInteger("0");
	public static final Z2 ONE = Integer.getInteger("1");
	public static final Number TWO = Integer.getInteger("2");
	public static final Number THREE = Integer.getInteger("3");
	public static final Number FOUR = Integer.getInteger("4");
	public static final Number FIVE = Integer.getInteger("5");
	public static final Number SIX = Integer.getInteger("6");
	public static final Number SEVEN = Integer.getInteger("7");
	public static final Number EIGHT = Integer.getInteger("8");
	public static final Number NINE = Integer.getInteger("9");
	public static final Number TEN = Integer.getInteger("10");
	*/
	//public static final Double DZERO = Double.valueOf("0");
	
	public static Z2 instanceType = new Fp(17);
	// problem: need to save values in Fp!
	//public static int modulus = 17;
	
	// check if x has the same type as the specified instanceType
	private static boolean isInstanceOf(Z2 x) {
		return x.getClass().isInstance(instanceType);
	}
	
	public static Class getType(Z2 x) {
		return x.getClass();
	}
	
	public static boolean inZ2(Z2 x, Z2 y, Z2 z) {
		boolean allInZ2 = ((x instanceof Z2) && (y instanceof Z2) && (z instanceof Z2));
		allInZ2 = (!(x instanceof Fp) && !(y instanceof Fp) && !(z instanceof Fp));
		allInZ2 &= (!(x instanceof RealNumber) && !(y instanceof RealNumber) && !(z instanceof RealNumber));
		return allInZ2;
	}
	
	public static boolean inFp(Z2 x, Z2 y, Z2 z) {
		boolean allInFp = ((x instanceof Fp) && (y instanceof Fp) && (z instanceof Fp));
		allInFp &= (!(x instanceof RealNumber) && !(y instanceof RealNumber) && !(z instanceof RealNumber));
		return allInFp;
	}
	
	public static boolean inReal(Z2 x, Z2 y, Z2 z) {
		boolean allInReal = ((x instanceof RealNumber) && (y instanceof RealNumber) && (z instanceof RealNumber));
		return allInReal;
	}
	
	public static Z2 add(Z2 x, Z2 y) {
		if (inFp(x,y,instanceType)) {
			Fp fpX = (Fp)x;
			Fp fpY = (Fp)y;
			Fp modulus = (Fp) instanceType;
			int sum = modulus.add(fpX.intValue(),fpY.intValue());
			return Fp.makeInstanceOf(sum);
		}
		if (inReal(x,y,instanceType)) {
			RealNumber realX = (RealNumber) x;
			RealNumber realY = (RealNumber) y;
			RealNumber base = (RealNumber) instanceType;
			double sum = base.add(realX.doubleValue(), realY.doubleValue());
			return RealNumber.makeInstanceOf(sum);
		}
		return null;
		
	}
	
	public static Z2 sub(Z2 x, Z2 y) {
		if (inFp(x,y,instanceType)) {
			Fp fpX = (Fp)x;
			Fp fpY = (Fp)y;
			Fp modulus = (Fp) instanceType;
			int sum = modulus.sub(fpX.intValue(),fpY.intValue());
			return Fp.makeInstanceOf(sum);
		}
		if (inReal(x,y,instanceType)) {
			RealNumber realX = (RealNumber) x;
			RealNumber realY = (RealNumber) y;
			RealNumber base = (RealNumber) instanceType;
			double sum = base.sub(realX.doubleValue(), realY.doubleValue());
			return RealNumber.makeInstanceOf(sum);
		}
		return null;
	}
	
	// negate w.r.t. addition
	public static Z2 negate(Z2 x) {
		if (inZ2(x,instanceType,new Z2(ZeroOne.ZERO))) {
			Z2 z2X = (Z2) x;
			if(z2X.isZero()) return new Z2(ZeroOne.ZERO);
			else return new Z2(ZeroOne.ONE);
		}
		if(inFp(x,instanceType,new Fp(2))) {
			Fp fp = (Fp) x;
			Fp inverter = (Fp) instanceType;
			int value = inverter.invertAdd(fp.intValue());
			return Fp.makeInstanceOf(value);
			
		}
		if(inReal(x,instanceType,new RealNumber(1))) {
			RealNumber realX = (RealNumber) x;
			RealNumber calculator = (RealNumber) instanceType;
			double value = calculator.invertAdd(realX.doubleValue());
			return RealNumber.makeInstanceOf(value);
		}
		return null;
	}

	public static boolean isZero(Z2 x) {
		// could be melt together because of polymorphism
		if(inZ2(x,instanceType,new Z2(ZeroOne.ZERO))) {
			Z2 z2X = (Z2) x;
			return z2X.isZero();
			
		}
		if(inFp(x,instanceType,new Fp(2))) {
			Fp fpX = (Fp) x;
			return fpX.isZero();
		}
		if(inReal(x,instanceType,new RealNumber(1))) {
			RealNumber realX = (RealNumber) x;
			return realX.isZero();
		}
		return false;
	}
	
	// use this method for polymorphism over invert => less casts are necessary...
	public static Fp invert(Fp x) {
		return null;
	}
	
	// invert w.r.t. multiplication
	public static Z2 invert(Z2 x) {
		if(isZero(x)) throw new IllegalArgumentException("Division by 0 is not possible");
		else {
			if(inZ2(x,instanceType,new Z2(ZeroOne.ONE))) {
				return new Z2(ZeroOne.ONE);
			}
			if(inFp(x,instanceType,new Fp(2))) {
				Fp fpX = (Fp) x;
				Fp inverter = (Fp) instanceType;
				int value = inverter.invertMult(fpX.intValue());
				return Fp.makeInstanceOf(value);
			}
			if(inReal(x,instanceType, new RealNumber(1))) {
				RealNumber realX = (RealNumber) x;
				return RealNumber.makeInstanceOf(1/realX.doubleValue());
			}
		}
		return null;
	}
	
	public static Z2 mult(Z2 x, Z2 y) {
		if(inZ2(x,y,instanceType)) {
			Z2 z2X = (Z2) x;
			Z2 z2Y = (Z2) y;
			Z2 instance = (Z2) instanceType;
			ZeroOne mult = instance.mult(z2X.value(), z2Y.value());
			return new Z2(mult);
		}
		if(inFp(x,y,instanceType)) {
			Fp fpX = (Fp) x;
			Fp fpY = (Fp) y;
			Fp instance = (Fp) instanceType;
			int mult = instance.mult(fpX.intValue(), fpY.intValue());
			return new Fp(mult);
		}
		if(inReal(x,y,instanceType)) {
			RealNumber realX = (RealNumber) x;
			RealNumber realY = (RealNumber) y;
			RealNumber instance = (RealNumber) instanceType;
			double mult = instance.mult(realX.doubleValue(), realY.doubleValue());
			return new RealNumber(mult);
		}
		return null;
	}

	public static Z2 div(Z2 x, Z2 y) {
		return mult(x,invert(y));
	}
	
	
	// following checks depend onn the inheritance hirarchy
	public static boolean isRealZeroOne(Object o) {
		return (o instanceof ZeroOne) && !(o instanceof Fp) && !(o instanceof RealNumber);
	}
	
	public static boolean isRealFp(Object o) {
		return (o instanceof Fp) && !(o instanceof RealNumber);
	}
	
	public static boolean isRealReal(Object o) {
		return o instanceof RealNumber;
	}

}
