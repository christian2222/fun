package datastructures;

import java.util.Date;

public class Page implements Comparable{

	protected char token;
	
	protected static char emptyToken = ' ';
	
	
	
	public Page(char token) {
		this.token = token;
	}
	
		
	public Page() {
		this.clear();
	}
	
	public void setToken(char token) {
		this.token = token;
	}
	
	public char getToken() {
		return this.token;
	}
	
	public void clear() {
		this.token = emptyToken;
	}
	
	public boolean isEmpty() {
		return (this.token == emptyToken);
	}

	// we violate the equals contract to find a page by its character
	public boolean equals(Page q) {
		return ( this.getToken() == q.getToken() );
	}
	
	public String toString() {
		return ""+this.token;
	}


	@Override
	public int compareTo(Object o) {
		if(o == null) {
			throw new NullPointerException();
		}
		if(!(o instanceof Page)) {
			return Integer.MIN_VALUE;
		}
		Page q = (Page) o;
		return Character.compare(this.getToken(), q.getToken());
	}
}
