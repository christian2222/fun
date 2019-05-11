package builder;

//import java.awt.LayoutManager;
import java.util.*;

public class Layouter {
	
	private ArrayList<DbAnfrage> anfragen;
	
	private SourcePage sourcePage;
	
	public Layouter(ArrayList<DbAnfrage> anfragen) {
		this.anfragen = anfragen;
	}
	
	public void doLayout(LayoutManager layoutManager) {
		for(DbAnfrage anfrage : this.anfragen) {
			layoutManager.addAnfrage(anfrage);
		}
		
		layoutManager.renderText();
		
		this.sourcePage = layoutManager.getSourcePage();
	}
	
	public void printLayoutedSourcePage() {
		System.out.println("Source: " + this.sourcePage.getSourceCode());
	}

}
