package ecc;

public class NoPointFoundException extends Exception {

	public static final long serialVersionUID = 10l;
	
	public NoPointFoundException(String str) {
		super(str);
	}
}
