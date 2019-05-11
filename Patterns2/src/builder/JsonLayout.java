package builder;

public class JsonLayout extends LayoutManager {
	
	@Override
	public void addAnfrage(DbAnfrage anfrage) {
		this.getSourcePage().getAnfragen().add(anfrage);
	}
	
	@Override
	public void renderText() {
		this.getSourcePage().setSourceCode(this.renderSourceFromAnfragen());
	}
	
	protected String renderSourceFromAnfragen() {
		return "JSON Layout";
	}

}
