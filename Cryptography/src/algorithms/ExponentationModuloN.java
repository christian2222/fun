package algorithms;

public class ExponentationModuloN {

	
	
	public static long calculate(long base, long exponent, long modulo) {
		long value = 1; // = base^0
		
		if(exponent <= 0) return 0; // round down
		while(exponent > 0) {
			value = value*base % modulo;
			exponent--;
		}
		
		return value;
	}
	
	
}
