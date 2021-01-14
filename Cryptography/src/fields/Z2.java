package fields;

public class Z2 {
	
	ZeroOne value = ZeroOne.ZERO;
	
	public Z2(ZeroOne zo) {
		this.value = zo;
	}
	
	public ZeroOne add(ZeroOne a, ZeroOne b) {
		if(a == ZeroOne.ZERO) return b;
		if(b == ZeroOne.ZERO) return a;
		if(a == ZeroOne.ONE && b == ZeroOne.ONE) return ZeroOne.ZERO;
		return ZeroOne.ZERO;
	}
	
	public ZeroOne mult(ZeroOne a, ZeroOne b) {
		if(a == ZeroOne.ONE && b == ZeroOne.ONE) return ZeroOne.ONE;
		return ZeroOne.ZERO;
	}
	
	public ZeroOne invertAdd(ZeroOne a) {
		if(a == ZeroOne.ONE) return ZeroOne.ONE; // since 1+1 = 0 in Z2
		else return ZeroOne.ZERO;
	}
	
	public ZeroOne invertMult(ZeroOne a) {
		if(!(a == ZeroOne.ZERO)) return ZeroOne.ONE;
		else throw new IllegalArgumentException("You cannot invert 0 multiplicative");
	}
	
	public boolean isZero() {
		return this.value == ZeroOne.ZERO;
	}
	
	public ZeroOne value() {
		return value;
	}
	
	public int intValue(ZeroOne a) {
		if(this.isZero()) return 0;
		return 1;
	}
	
	public double doubleValue(ZeroOne a) {
		if(this.isZero()) return 0.0;
		return 1.0;
	}
	
	public static Z2 makeInstanceOf(ZeroOne zo) {
		return new Z2(zo);
	}
}
