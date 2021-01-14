package unUsed;

public interface Operation<T extends Number> {

	public T sub(T x, T y);
	
	public T div(T x, T y);

	public T getNewElement();
	
	public T add(T x, T y);
	
	public T mult(T x, T y);
	
	public T invertAdd(T x);
	
	public T invertMult(T x);
	
	public boolean isZero(T x);


	

}
