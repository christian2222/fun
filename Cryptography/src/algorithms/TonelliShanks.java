package algorithms;

import java.util.Random;

/*
 * this algorithm is written due to wikipedia: 
 * https://en.wikipedia.org/wiki/Tonelli%E2%80%93Shanks_algorithm
 * (17.01.2021)
 */
public class TonelliShanks {
	
	// NO_SOLUTION can't be 0 because of F_2
	public static final int NO_SOLUTION = -1;

	// due to Euler's criterion we calculate
	public static boolean hasQuadraticRoot(int n, int p) {
		if(n == 1) return true;
		boolean isQuadraticRoot = true;
		// if we are in F_2  so 0 and 1 have roots 0 and 1
		if(p == 2) return true;
		// now p has to be a prime greater 2
		isQuadraticRoot &= PrimeGenerator.isPrime(p);
		isQuadraticRoot &= p > 2;
		if(isQuadraticRoot) {
			long result = ExponentationModuloN.calculate((long)n,(long)(p-1)/2,(long)p);
			return result == 1;
		}
		return false;
	}
	
	/*
	 * we need one nonquadratic residue z for the algorithm
	 */
	public static int searchNonQuadraticResidue(int modulus) {
		int z = modulus - 1;
		Random r = new Random();
		do {
			z = r.nextInt(modulus); // random number between 0 and p-1
			if(z == 0) z++; // ensure z is not 0
		} while(hasQuadraticRoot(z, modulus));
		return z;
	}
	
	// note: return NO_SOLUTION if no such element exists
	public static int runAlgorithm(int n, int p) {
			if(p < 2) return NO_SOLUTION;
			if(!hasQuadraticRoot(n, p)) return NO_SOLUTION;
			// if we are in F_2 both values have roots
			if(p == 2) {
				if(n%p == 0) return 0;
				if(n%p == 1) return 1;
			}
			// otherwise start Tonelli-Shanks algorithm
			// note that p > 2 and p prime.
			int start = p-1;
			int Q = start;
			int S = 0;
			while(Q % 2 == 0) {
				Q = Q/2;
				S++;
			}
			// Now: Q is odd and start = Q*2^S
			int z = searchNonQuadraticResidue(p);
			// now z is a non-quadratic residue modulo p
			long M = S;
			// c == z^Q (p)
			long c = ExponentationModuloN.calculate(z,Q,p);
			long t = ExponentationModuloN.calculate(n, Q, p);
			long R = ExponentationModuloN.calculate(n, (Q+1)/2, p);
			// Now R^2 == n^(Q+1) = n * n^Q
			// if t := n^Q (p) == 1 (p) we found the root R
			// otherwise define M := S and note that R^2 == n*n^Q == n*t (p)
			// moreover we know t^(2^(M-1)) = t^(2^(S-1)) == (n^(Q*2^(S-1))) (p) by definition of t
			// hence t^(2^(M-1)) == (n^(Q*2^(S-1))) (p)  == n^((p-1)/2) == 1 (p) by definition of Q and eulers crtierion
			// Thus we are in the case where R^2 == n*t (p) and t is a 2^(M-1)-th root of 1. Note that R is not a square root of n
			long b = 0;
			// the following while loop has the invariants
			// (a) c^(2^(M-1)) == 1 (p)
			// (b) t^(2^(M-1)) == 1 (p)
			// (c) R^2 == t*n (p)
			// We don't proof these invariants here because they are proven on https://en.wikipedia.org/wiki/Tonelli%E2%80%93Shanks_algorithm
			// Moreover M decreases in each step, hence for M=1 we calculate t==1 (p) in the next iteration and return R.
			while(true) {
				// for t == 0 there is no solution
				if(t == 0) break;
				if(t == 1) return (int)R;
				long i = 1;
				// search for such an i that t^(2^l) == 1 (p)
				// by loop invariant (b) we find such an i (at least M-1)
				while((i < M) && ExponentationModuloN.calculate(t,(long)Math.pow(2, i), (long)p) != 1) {
					i++;
				}
				// => i == M or t^(2^i) == 1 (p) for some 0 < i < M
				// if i == M we have no solution
				if(i == M) break;
				// now: t^(2^i) == 1(p) for some i < M
				// set the variables according to https://en.wikipedia.org/wiki/Tonelli%E2%80%93Shanks_algorithm
				// b == c^(2^(M-i-1)) (p)
				b = ExponentationModuloN.calculate(c, (long)Math.pow(2, M-i-1), p);
				// M = i
				M = i;
				c = ExponentationModuloN.calculate(b, 2, p);
				// t == t*b^2 (p)
				t = (t*ExponentationModuloN.calculate(b, 2, p)) % p;
				// R == R*b (p)
				R = (R*b) % p;
				// hence the invariants (a)-(c) still hold
			}
			// if we break out of the while loop, we have no solution
			return NO_SOLUTION;
			
		
	}
}
