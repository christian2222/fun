package unUsed;

public class Element<T extends Number> {
	
	T value;
	
	public void setValue(T value) {
		this.value = value;
	}
	
	public T getValue() {
		return this.value;
	}
	
	public boolean isZero() {
		return this.value.doubleValue() == 0.0;
	}

}
