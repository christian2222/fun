package data;




import maths.Maths;



/**
 * A list of distinct elements
 * @author Administrator
 *
 */
public class KNF {

	protected DisjClause3[] disjArray = new DisjClause3[Constants.maxDisjClauseNumber];
	
	public KNF() {
		this.disjArray = new DisjClause3[Constants.maxDisjClauseNumber];
		Variable v = new Variable(Constants.maxVariableNumber+1,Constants.isFalse);
		Literal l = new Literal(v,Constants.notNegated);
		for(int i = 0; i < Constants.maxDisjClauseNumber; i++) {
			this.disjArray[i] = new DisjClause3(l, l, l);
		}
	}
	
	public void randomInit() {
		for(int d = 0; d < Constants.maxDisjClauseNumber; d++) {
			Literal lit[] = new Literal[3];
			int varNumber = 0;
			int value = 0;
			boolean negated = false;
			for(int l = 0; l < 3; l++) {
				varNumber = Maths.randomZeroAndN(Constants.maxVariableNumber);
				value = Maths.randomIntBoolean();
				negated = Maths.randomBoolean();
				lit[l] = new Literal(new Variable(varNumber,value),negated);
			}
			
			this.setClause(d, new DisjClause3(lit[0], lit[1], lit[2]));
		}
	}
			
	public void setClause(int i, DisjClause3 dnf) {
			i = Maths.betweenZeroAndN(i, Constants.maxDisjClauseNumber);
			this.disjArray[i] = dnf;
	}
	
	public DisjClause3 getClause(int i) {
		i = Maths.betweenZeroAndN(i, Constants.maxDisjClauseNumber);
		return this.disjArray[i];
	}
		
	public int getSize() {
		return this.disjArray.length;

	}
	
	public boolean isTrue() {
		boolean isTrue = true;
		for(int c = 0; c < Constants.maxDisjClauseNumber; c++) {
			isTrue = isTrue && this.disjArray[c].isTrue();
		}
		return isTrue;
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i < this.getSize(); i++) {
			s += this.disjArray[i].toString();
			if(i < this.getSize()-1) {
				s+="^";
			}
		}
		return s;
	}
	
	public static void main(String[] args) {
		KNF knf = new KNF();
		Variable v = new Variable(10,0);
		Variable w = new Variable(12,0);
		Variable x = new Variable(8,1);
		Literal l1 = new Literal(v,false);
		Literal l2 = new Literal(w,false);
		Literal l3 = new Literal(x,true);
		DisjClause3 dnf1 = new DisjClause3(l1, l2, l3);
		System.out.println(dnf1.isTrue());
		int k = 4;
		
		for(int i = 0; i < k; i++) {
			knf.setClause(i, dnf1);
		}
		
		for(int i = 0; i < k; i++) {
			System.out.println(knf.getClause(i)+" is element "+i);
		}
		
		System.out.println(knf.getSize());
		System.out.println(knf);
		knf.randomInit();
		System.out.println(knf);
	}
	
}
