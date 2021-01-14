package communication;

import java.util.Random;

public class PrimeGenerator {
	
	protected static short upperBound = 25000;
	protected static short lowerBound = 20000;
	protected static short difference = (short)(upperBound - lowerBound);
	
	public static short generatePrimeNumber() {
		Random r = new Random();
		short primeNumber = (short) (r.nextInt(difference) + lowerBound);
		
		while(!isPrime(primeNumber)) {
			primeNumber = (short) ((r.nextInt(difference)+lowerBound) % upperBound);
		}
		
		return primeNumber;
		
	}
	
	protected static boolean isPrime(int p) {
		if(p < 2) return false;
		if(p == 2) return true;
		for(long div = 2; div < p; div++) {
			if(p % div == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("huhu");
		System.out.println(generatePrimeNumber());
		System.out.println("done");
	}

}
