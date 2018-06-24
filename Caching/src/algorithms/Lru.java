package algorithms;

import java.util.ArrayList;
import java.util.Date;

import datastructures.*;

public class Lru<T extends TimePage> extends Random<T> {

	
	public Lru(ArrayList<T> sequence) {
		super(sequence);
	}
	
	@Override
	public void serveRequest(T request) {
		if(this.cache.hasPage(request)) {
			int k = this.cache.getPageIndex(request);
			TimePage slot = (TimePage) this.cache.memory.get(k);
			slot.refreshTimestamp();
			System.out.println("Served page "+request+" by Cache.");
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(request);
				request.refreshTimestamp();
				System.out.println("Loaded page "+request+" into an empty slot.");
			} else { // removing strategy
				TimePage p = this.getOldestPage();
				System.out.println("Replaced page "+p+" by page "+request+" in Cache");
				this.evictPageFromCache((T)p);
				this.countPagefaults++;
				this.cache.add(request);
			}
		}
	}
	
	public TimePage getOldestPage() {
	
		Date actual = ((TimePage) this.cache.memory.get(0)).getTimestamp();
		Date oldest = actual;
		TimePage actualPage = new TimePage();
		int index = 0;
		for(int i = 0; i < this.cache.getSize(); i++) {
			actualPage = (TimePage) this.cache.memory.get(i);
			actual = actualPage.getTimestamp();
			if(actual.before(oldest)) {
				oldest = actual;
				index = i;
			}
		}
		
		return actualPage;
	}






}
