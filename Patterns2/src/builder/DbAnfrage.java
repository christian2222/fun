package builder;

public class DbAnfrage {
	
	private String dgAnfrage;
	protected String result;
	
	
	public DbAnfrage(String dbAnfrage) {
		this.dgAnfrage = dbAnfrage;
	}
	
	protected void starteAnfrage() {
		this.result = "db Ergebnisse";
	}
	
	public String getResult() {
		return this.result;
	}
	
	
}
