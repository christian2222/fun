package ecc;

public abstract class Field<T extends Number> {

	public abstract T getNewElement(int i);
	
	public abstract T add(T x, T y);
	
	public abstract T mult(T x, T y);
	
	public abstract T invertAdd(T x);
	
	public abstract T invertMult(T x);
	
	public abstract boolean isZero(T x);
	
	public abstract boolean isField();
	
	public abstract boolean isGreaterEqualZero(T x);
	
	public abstract boolean isEqual(T x, T y);
	
	public abstract boolean isF2();
	
	public abstract T get2();
	
	public abstract boolean hasSquareRoot(T x);
	
	public abstract T squareRootOf(T x);
	
	public abstract String toString();
	
	
	public T sub(T x, T y) {
		return this.add(x,this.invertAdd(y));
	}
	
	public T div(T x, T y) {
		return this.mult(x,this.invertMult(y));
	}
	
	// abbreviate some calculations
	protected T twoTimes(T x){
		return this.add(x, x);
	}
	
	protected T threeTimes(T x) {
		return this.add(x, this.twoTimes(x));
	}
	
	protected T fourTimes(T x) {
		return this.twoTimes(this.twoTimes(x));
	}
	
	protected T sixTimes(T x) {
		return this.add(this.threeTimes(x), this.threeTimes(x));
	}
	
	protected T eightTimes(T x) {
		return this.twoTimes(this.fourTimes(x));
	}
	
	protected T nineTimes(T x) {
		return this.threeTimes(this.threeTimes(x));
	}
	
	protected T twentySevenTimes(T x) {
		return this.mult(this.nineTimes(x), this.threeTimes(x));
	}
	
	protected T square(T x) {
		return this.mult(x, x);
	}
	
	protected T cube(T x) {
		return this.mult(x,this.square(x));
	}

}
