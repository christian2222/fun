package builder;

public class XmlLayout extends LayoutManager {
	
	@Override
	public void addAnfrage(DbAnfrage anfrage) {
		this.getSourcePage().getAnfragen().add(anfrage);
	}
	
	@Override
	public void renderText() {
		this.getSourcePage().setSourceCode(this.renderSourceFromAnfragen());
	}
	
	protected String renderSourceFromAnfragen() {
		return "XML Layout";
	}

}
