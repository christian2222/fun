package algorithm;

/**
 * This class provides some static math methods used in the program.
 * 
 * @author chrissy - date 30-03-2011
 *
 */
public class MyMath {

    /**
     * 
     * @param x
     * @param y
     * @return computes x^y for double arguments
     */
    public static int intXhochY(double x, double y) {
	int i = 0;
	i = (int) Math.exp((y) * Math.log1p(x - 1));
	return i;
    }

    /**
     * 
     * @param x
     * @param n
     * @return computes x^n for a natural n >=0
     */
    public static long xHochn(int x, int n) {
	if (n == 0)
	    return 1;
	long potenz = 1;
	while (n > 0) {
	    n--;
	    potenz = x * potenz;
	}
	return potenz;
    }
}
