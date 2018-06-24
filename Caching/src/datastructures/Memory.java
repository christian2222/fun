package datastructures;

import java.util.ArrayList;


public class Memory<T extends Page> {

	public ArrayList<T> memory;
	
	private static int ERROR = -1; 
	
	public Memory(int size) {
		this.memory = new ArrayList<T>(size);
		
		for(int i = 0; i < size; i++) {
			this.memory.add((T) new Page());
		}
		
		this.clear();
	}
	
	public int getSize() {
		return this.memory.size();
	}
	
	public T getPageAtIndex(int index) {
		int i = Math.abs(index);
		i = i % this.memory.size();
		return this.memory.get(i);
	}
	
	public void clear() {
		for(int i = 0; i < this.memory.size(); i++) {
			this.memory.get(i).clear();
		}
	}
	
	public boolean hasPage(T p) {
		for(int i = 0; i < this.memory.size(); i++) {
			if(this.memory.get(i).equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public Page getPage(T p) {
		Page q = new Page();
		for(int i = 0; i < this.memory.size(); i++) {
			if(this.memory.get(i).equals(p)) {
				q = this.memory.get(i);
			}
		}
		return q;
	}
	
	public int getPageIndex(T p) {
		if(this.hasPage(p)) {
			for(int i = 0; i < this.memory.size(); i++) {
				if(this.memory.get(i).equals(p)) {
					return i;
				}
			}
		}
		
		return ERROR;
		
	}
	
	public boolean hasEmptySlot() {
		for(int i = 0; i < this.memory.size(); i++) {
			if(this.memory.get(i).isEmpty()) {
				return true;
			}
		}
		return false;
	}
	
	protected int getEmptyIndex() {
		if(this.hasEmptySlot()) {
			for(int i = 0; i < this.memory.size(); i++) {
				if(this.memory.get(i).isEmpty()) {
					return i;
				}
			}
		}
		
		return ERROR;
	}
	
	
	
	
	public void add(T p) {
		if(!this.hasPage(p)) {
			if(this.hasEmptySlot()) {
				int e = this.getEmptyIndex();
				this.memory.set(e, p);
			} else {
				System.out.println("WARNING: page ["+p.getToken()+"] can't be added!");
			}
		} else {
			System.out.println("Notice: page ["+p.getToken()+"] is already loaded");
		}
	}
	
	public void remove( T p) {
		if(this.hasPage(p)) {
			int d = this.getPageIndex(p);
			this.memory.set(d, (T) new Page());
		} else {
			System.out.println("WARNING: Can't remove page ["+p.getToken()+"]");
		}
	}


	public String toString() {
		String s = "[ ";
		for(int i = 0; i < this.memory.size()-1; i++) {
			s += this.memory.get(i).toString()+", ";
		}
		s += this.memory.get(this.memory.size()-1).toString();
		s +="]";
		return s;
	}
	
	
	
}
