package datastructures;


public class MarkedPage extends Page{

	
	protected boolean bit;
	
	public MarkedPage(char token) {
		super(token);
		this.bit = false;
	}

	public MarkedPage() {
		super();
		this.setBit(false);
	}

	public void setBit(boolean bit) {
		this.bit = bit;
	}
	
	public boolean getBit() {
		return this.bit;
	}
	
	public String toString() {
		String s = super.toString();
		if(this.getBit()) {
			s +="*";
		}
		return s;
	}
	
	
	
	
	
	
}
