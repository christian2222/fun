package newField;

public abstract class Field<T extends Number> {

	public abstract T getNewElement();
	
	public abstract T add(T x, T y);
	
	public abstract T mult(T x, T y);
	
	public abstract T invertAdd(T x);
	
	public abstract T invertMult(T x);
	
	public abstract boolean isZero(T x);

	
	public T sub(T x, T y) {
		return this.add(x,this.invertAdd(y));
	}
	
	public T div(T x, T y) {
		return this.mult(x,this.invertMult(y));
	}

}
