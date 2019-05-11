package builder;

import java.util.*;

public class SourcePage {

	
	private ArrayList<DbAnfrage> anfragen = new ArrayList<DbAnfrage>();
	
	private String sourceCode;
	
	public ArrayList<DbAnfrage> getAnfragen() {
		return this.anfragen;
	}
	
	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
	
	public String getSourceCode() {
		return this.sourceCode;
	}
}
