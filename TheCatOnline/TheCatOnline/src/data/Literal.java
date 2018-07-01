package data;


public class Literal {

	Variable variable;
	boolean isNegated;
	
	public Literal(Variable variable, boolean isNegated) {
		super();
		this.variable = variable;
		this.isNegated = isNegated;
	}

	public boolean isNegated() {
		return isNegated;
	}

	public void setNegated(boolean isNegated) {
		this.isNegated = isNegated;
	}

	public Variable getVariable() {
		return variable;
	}
	
	public String toString() {
		String s = "";
		if(this.isNegated) {
			s = "~"+this.variable.toString();
		}
		else {
			s = this.variable.toString();
		}
		return s;
	}
	
	public boolean isTrue() {
		if(this.isNegated){
			return (this.variable.isFalse());
		}
		else{
			return (this.variable.isTrue());
		}
	}
	
	public boolean isFalse() {
		if(this.isNegated){
			return (this.variable.isTrue());
		}
		else{
			return (this.variable.isFalse());
		}
	}
	
	public boolean isDefined() {
		return (this.variable.getValue() != Constants.isUndefined);
	}

	public boolean isUndefined() {
		return (this.variable.getValue() == Constants.isUndefined);
	}
	
	
	
	

	
	
}
