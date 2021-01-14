package algorithms;

public class ExponentationModuloN {

	
	long base = 0;
	long exponent = 0;
	long modulo = 0;
	
	public ExponentationModuloN(long base, long exponent, long modulo) {
		super();
		this.base = base;
		this.exponent = exponent;
		this.modulo = modulo;
	}
	
	public long evaluate() {
		long value = 1; // = this.base^0
		long exponent = this.exponent;
		
		if(exponent <= 0) return 0; // round down
		//System.out.println(this.base+"^"+this.exponent + "%"+this.modulo);
		while(exponent > 0) {
			value = value*this.base % this.modulo;
			exponent--;
		}
		
		return value;
	}
	
	
}
