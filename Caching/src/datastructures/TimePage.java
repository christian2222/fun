package datastructures;


import java.util.Date;

public class TimePage extends Page{

	
	protected Date timestamp;
	
	
	public TimePage(char token) {
		super(token);
		this.refreshTimestamp();
	}

	public TimePage() {
		super();
		this.refreshTimestamp();
	}
		
	public Date getTimestamp() {
		return this.timestamp;
	}
	
	public String getTime() {
		//String s = this.timestamp.getMinutes()+":"+this.timestamp.getSeconds();
		String s = ""+this.timestamp.getTime();
		return s;
	}
	
	public boolean isOlder(TimePage p) {
		return this.timestamp.after(p.getTimestamp());
	}
	
	public void refreshTimestamp() {
		this.timestamp = new Date();
	}
	
	public String toString() {
		String s = super.toString();
		s += "("+this.getTime()+")";
		
		return s;
	}
	
	
}
