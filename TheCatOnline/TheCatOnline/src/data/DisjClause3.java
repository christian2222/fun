package data;

/**
 * Clause as in logic:
 * (x1 v x2 v x3) 
 * @author Administrator
 *
 *
 */
public class DisjClause3 {

	/**
	 * three literals for one disjunctive clause
	 */
	protected Literal[] literal = new Literal[3];
	int number;

	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public DisjClause3(Literal l1, Literal l2, Literal l3) {
		super();
		this.literal[0] = l1;
		this.literal[1] = l2;
		this.literal[2] = l3;
		this.number = 0;
	}
	
	public Literal getLiteral(int i) {
		i = Math.abs(i);
		i = i%3;
		return this.literal[i];
		
	}
	
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append("(");
		for(int i = 0; i < this.literal.length; i++) {
			ret.append(this.literal[i].toString());
			if(i < 2) ret.append(" v ");
		}
		ret.append(")");
		return ret.toString();
	}


	
	
	
	public boolean isTrue() {
		if( (literal[0] == null) || (literal[1] == null) || (literal[2] == null) ) return false;
		return ( literal[0].isTrue() || literal[1].isTrue() || literal[2].isTrue() );
	}
}
