package builder;

import java.util.*;

public class Test {
	
	public static void main(String[] args) {
		String anfrageSourceCode = "Anfrage Source Code";
		ArrayList<DbAnfrage> anfragen = new ArrayList<DbAnfrage>();
		
		for(int i = 0; i < 5; i++) {
			anfragen.add(new DbAnfrage(anfrageSourceCode));
		}
		
		Layouter layouter = new Layouter(anfragen);
		
		layouter.doLayout(new JsonLayout());
		layouter.printLayoutedSourcePage();
		
		layouter.doLayout(new XmlLayout());
		layouter.printLayoutedSourcePage();
	}
}
