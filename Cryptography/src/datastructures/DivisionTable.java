package datastructures;

public class DivisionTable {

	public DivisionTable(long dividend, long divisor, long quotient, long remainder, long factorS, long factorT) {
		this.init(dividend, divisor, quotient, remainder, factorS, factorT);
	}

	public DivisionTable(long dividend, long divisor, long quotient, long remainder) {
		this.init(dividend, divisor, quotient, remainder, 0, 0);
	}

	private void init(long dividend, long divisor, long quotient, long remainder, long factorS, long factorT) {
		this.dividend = dividend;
		this.divisor = divisor;
		this.quotient = quotient;
		this.remainder = remainder;
		this.factorS = factorS;
		this.factorT = factorT;
	}

	protected long dividend;
	protected long divisor;
	protected long quotient;
	protected long remainder;
	protected long factorS;
	protected long factorT;

	public String toString() {
		return "(" + this.dividend + ", " + this.divisor + ", " + this.quotient + ", " + this.remainder + ", "
				+ this.factorS + ", " + this.factorT + ")";
	}

	public long getDividend() {
		return dividend;
	}

	public void setDividend(long dividend) {
		this.dividend = dividend;
	}

	public long getDivisor() {
		return divisor;
	}

	public void setDivisor(long divisor) {
		this.divisor = divisor;
	}

	public long getQuotient() {
		return quotient;
	}

	public void setQuotient(long quotient) {
		this.quotient = quotient;
	}

	public long getRemainder() {
		return remainder;
	}

	public void setRemainder(long remainder) {
		this.remainder = remainder;
	}

	public long getFactorS() {
		return factorS;
	}

	public void setFactorS(long factorS) {
		this.factorS = factorS;
	}

	public long getFactorT() {
		return factorT;
	}

	public void setFactorT(long factorT) {
		this.factorT = factorT;
	}

}
