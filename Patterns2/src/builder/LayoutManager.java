package builder;

public abstract class LayoutManager {

	
	private SourcePage sourcePage;
	
	public abstract void addAnfrage(DbAnfrage anfrage);
	public abstract void renderText();
	
	
	public LayoutManager() {
		this.sourcePage = new SourcePage();
	}
	
	public SourcePage getSourcePage() {
		return this.sourcePage;
	}
	
	
}
