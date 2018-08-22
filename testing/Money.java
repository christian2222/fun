
public class Money implements Expression{
	
	
	protected int amount;
	protected String currency;
	
	
	public Money(int amount, String currency) {
		this.amount = amount;
		this.currency = currency;
	}
	
	public boolean equals(Object object) {
		Money money = (Money) object;
		return this.amount == money.amount && this.currency().equals(money.currency());
	}
	
	public String currency() {
		return this.currency;
	}
	
	public static Money dollar(int amount) {
		return new Money(amount,"USD");
	}
	
	public static Money franc(int amount) {
		return new Money(amount,"CHF");
	}
	
	protected String getCurrency() {
		return this.currency;
	}
	
	public String toString() {
		return this.amount + " " + this.currency;
	}
	
	public Money times(int multiplier) {
		return new Money(this.amount * multiplier, this.currency);
	}
	
	public Expression plus(Money addend) {
		return new Sum(this, addend);
	}
	
	public Money reduce(String to) {
		int rate = (this.currency.equals("CHF") && to.equals("USD")) ? 2 : 1;
		return new Money(amount / rate, to);
	}
}
