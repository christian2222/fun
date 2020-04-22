package parser;

public class Parameter {


	protected String type;
	protected String name;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Parameter(String type, String name) {
		this.type = type;
		this.name = name;
	}
	
	public boolean notNull() {
		return ( (this.type != null) && (this.name != null) );
	}
	
	public Parameter(String notation) {
		CharSequence colon = new String(":");
		if(notation.contains(colon)) {
			int colonPos = notation.indexOf(':');
			this.name = notation.substring(0, colonPos);
			this.type = notation.substring(colonPos+1,notation.length());
		} else {
			this.name = null;
			this.type = null;
		}
		
	}
	
	public String getInitNotation() {
		return this.getName() + ":"+ this.getType();
	}
	
	public String toString() {
		return this.type+" "+this.name;
	}
	
	public static void main(String[] args) {
		Parameter p = new Parameter("kfzNr:int");
		if(p.notNull()) System.out.println(p);
	}
}
