package newField;

public class EllipticCurve<T extends Number> {

	protected T a1;
	protected T a2;
	protected T a3;
	protected T a4;
	protected T a6;
	
	public EllipticCurve(T a1, T a2, T a3, T a4, T a6) {
		super();
		this.a1 = a1;
		this.a2 = a2;
		this.a3 = a3;
		this.a4 = a4;
		this.a6 = a6;
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
