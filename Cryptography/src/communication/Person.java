package communication;

public abstract class Person {

	public abstract long decode();
	
	public abstract void encode(long mess);
	
	public abstract long send();
	
	public abstract void receive(long mess);
	

	protected Translator t = new Translator();
	
	protected long publicKey = 0;
	
	protected long privateKey = 0;
	
}
