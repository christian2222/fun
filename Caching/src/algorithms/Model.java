package algorithms;

import java.util.ArrayList;
import java.util.Random;


import datastructures.*;

public abstract class Model<T extends Page> extends GenericList<T>{

	
	
	protected Random rand = new Random();

	
	public Memory cache;
	public Memory background;
	public ArrayList<T> sequence;
	public int countPagefaults;

	
	public Model(int cacheSize, int bgSize, ArrayList<T> sequence) {
		this.background = new Memory<T>(bgSize);
		this.cache = new Memory<T>(cacheSize);
		this.sequence = sequence;
		this.countPagefaults = 0;

		this.initBackground();
		this.clearCache();
	}
	
	public Model(int cacheSize, int bgSize, String sequence) {
		this.background = new Memory<T>(bgSize);
		this.cache = new Memory<T>(cacheSize);
		this.loadSequence(sequence);
		this.countPagefaults = 0;
		
		this.initBackground();
		this.clearCache();
	}
	
	public Model(int cacheSize, int bgSize, int length) {
		this.background = new Memory<T>(bgSize);
		this.cache = new Memory<T>(cacheSize);
		this.initRandomSequence(length);
		this.countPagefaults = 0;
		
		this.initBackground();
		this.clearCache();
	}
	
	
	public int getPagefaults() {
		return this.countPagefaults;
	}
	
	protected void loadSequence(String string) {
		int size = string.length();
		this.sequence = new ArrayList<T>(size);
		for(int i = 0; i < size; i++) {
			this.sequence.add( (T) new Page(string.charAt(i)));
		}
	}
	
	protected void initRandomSequence(int length) {
		String s = "";
		for(int i = 0; i < length; i++) {
			s+= this.getRandomChar();
		}
		
		loadSequence(s);
	}

	protected char getRandomChar() {
		char c = 'a';
		int i = this.getRandomInt(26);
		switch(i) {
			case 0:		c = 'a'; 	break;
			case 1:		c = 'b'; 	break;
			case 2:		c = 'c'; 	break;
			case 3:		c = 'd'; 	break;
			case 4:		c = 'e'; 	break;
			case 5:		c = 'f'; 	break;
			case 6:		c = 'g'; 	break;
			case 7:		c = 'h'; 	break;
			case 8:		c = 'i'; 	break;
			case 9:		c = 'j'; 	break;
			case 10:		c = 'k'; 	break;
			case 11:		c = 'l'; 	break;
			case 12:		c = 'm'; 	break;
			case 13:		c = 'n'; 	break;
			case 14:		c = 'o'; 	break;
			case 15:		c = 'p'; 	break;
			case 16:		c = 'q'; 	break;
			case 17:		c = 'r'; 	break;
			case 18:		c = 's'; 	break;
			case 19:		c = 't'; 	break;
			case 20:		c = 'u'; 	break;
			case 21:		c = 'v'; 	break;
			case 22:		c = 'w'; 	break;
			case 23:		c = 'x'; 	break;
			case 24:		c = 'y'; 	break;
			case 25:		c = 'z'; 	break;
			
			default:		c = 'a'; 	break;
		}
		
		return c;
	}
	
	
	protected int getRandomInt(int maxInt) {
		int i = rand.nextInt();
		i = Math.abs(i);
		i = i % maxInt;
		return i;
	}
	
	protected T getRandomCachePage() {
		int i = this.getRandomInt(this.cache.getSize());
		return (T) this.cache.getPageAtIndex(i);
	}
	
	protected T getRandomBackgroundPage() {
		int i = this.getRandomInt(this.background.getSize());
		return (T) this.background.getPageAtIndex(i);
	}
	
	public ArrayList<T> getRandomBackgroundSequence(int length) {
		ArrayList<T> sequence = new ArrayList(this.sequence.size());
		for(int i = 0; i < length; i++) {
			sequence.add(this.getRandomBackgroundPage());
		}
		return sequence;
	}
	
	protected void initBackground() {
		int size = getDisitincElements(this.sequence).size();
		this.background = new Memory<T>(size);
		ArrayList<T> list = getDisitincElements(this.sequence);
		for(int i = 0; i < list.size(); i++) {
			this.background.add(list.get(i));
		}
	}
	
	public Memory<T> getCache() {
		return this.cache;
	}
	
	public Memory<T> getBackground() {
		return this.background;
	}
	
	
	public String printSequence() {
		String s = "";
		for(int i = 0; i < sequence.size(); i++) {
			s += this.sequence.get(i).getToken();
		}
		return s;
	}

	public boolean isInCache(T p) {
		return this.cache.hasPage(p);
	}
	
	public void clearCache() {
		this.cache.clear();
	}
	
	public boolean evictPageFromCache(T p) {
		if(this.cache.hasPage(p)) {
			this.cache.remove(p);
			return true;
		}
		return false;
		
	}
	
	public String printCache() {
		return "Cache: " + this.cache.toString();
	}
	
	public String printBackground() {
		return "Background: " + this.background.toString();
	}
}

