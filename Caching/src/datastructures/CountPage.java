package datastructures;

public class CountPage extends Page {

	
	protected int counter;
	
	public CountPage() {
		this.flashCounter();
	}
	
	public CountPage(char c) {
		super(c);
		this.flashCounter();
	}
	
	public void incCounter() {
		this.counter++;
	}
	
	public int getCounter() {
		return this.counter;
	}
	
	public void flashCounter() {
		this.counter = 0;
	}
	
	public String toString() {
		String s = super.toString();
		s += "("+this.getCounter()+")";
		
		return s;
	}
}
