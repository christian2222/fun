package maths;
//import java.util.Random;
import java.util.*; //Random class

import data.Constants;

/**
 * Klasse mit statischen Methoden stellt kleine SubProgramme zur Verf�gung
 * @author yz
 *
 */
public class Maths {

	
	public static Random random = new Random();
	
	/**
	 * brechnet k % N
	 * @param k Zahl, die modulo genommen wird
	 * @param N modulo Operation liefert Rest einer Zahl zwischen 0,...,N-1
	 * @return k % N
	 */
	public static int betweenZeroAndN(int k, int N) {
		k = Math.abs(k);
		k = k % N;
		return k;
	}
	
	public static int randomZeroAndN(int N) {
		int k = random.nextInt();
		k = Math.abs(k);
		k = k % N;
		return k;
	}
	
	/**
	 * 
	 * @return Gibt (zufaellig) true oder false zurück
	 */
	public static boolean randomBoolean() {
		int k = randomZeroAndN(2);
		// k = Math.abs(k) gerettet durch Rückgriff auf randomZeroAndN(2), die das garantiert
		return (k == 1);
	}
	
	/**
	 * 
	 * @return gibt einen IntegerBoolean (bzgl. Constants.isTrue oder .isFalse) zurueck.
	 */
	public static int randomIntBoolean() {
		int k = randomZeroAndN(2);
		// siehe boolean randomBoolean()
		if(k == 0) return Constants.isTrue;
		if(k == 1) return Constants.isFalse;
		return Constants.isTrue;
	}
	/**
	 * berehcnte eine natuerliche Potenz als Integer
	 * @param base
	 * @param exponent
	 * @return base^exponent
	 */
	public static int power(int base, int exponent) {
		
		int power = 1; // =base^0
		
		for(int i = 0; i < exponent; i++) {
			power = power*base;
		}
		return power;
		
	}
	
	/**
	 *	erzeugt einen base-ären String des Inputs 
	 * @param i Input
	 * @param base Basis zu der gerechnet wird
	 * @return Darstellung von i im base-System als String
	 */
	public static String toBaseString(long i, long base) {
		
		String s = "";
		i = Math.abs(i);
		
		//int numerator= 1; //Zaehler
		//int denominator = 1; //Nenner
		// c = a/b;  a~dividend b~divisor c~quotient
		// a mod n = k; k~remainder of a modulo n
		long quotient = 1;
		long remainder = 1;
		
		quotient = i;
		
		while(quotient != 0)  {
			remainder = quotient % base;
			s = remainder + s; // Inverse Schreibweise/Ergebnisse kompensieren
			quotient = quotient / base;
		}
		
		
		return s;
	}
	
	
	
	
	// teste einige der Berechnungen
	public static void main(String[] args) {
		System.out.println(toBaseString(40, 2));
		System.out.println(Maths.power(2, 16));
		String s = "";
		for(int i = 0; i < 65536; i++) {
			s = toBaseString(i, 2);
			System.out.println(s);
		}
	}
}
