package algorithms;

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
	
	public static int generatePrimeNumberBetween(int lowerBound, int upperBound) {
		Random r = new Random();
		
		int prime;
		int difference = upperBound - lowerBound;
		do {
			prime = (r.nextInt(difference) + lowerBound) % upperBound;
		} while(!isPrime(prime));
		
		return prime;
	}
	
	public static int generatePrimeNumberCongruent3Mod4(int lowerBound, int upperBound) {
		int prime;
		int modulus;
		
		do {
			prime = generatePrimeNumberBetween(lowerBound, upperBound);
			modulus = prime % 4;
		} while(modulus != 3);
		
		return prime;
	}
	
	
	public static boolean isPrime(int p) {
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
