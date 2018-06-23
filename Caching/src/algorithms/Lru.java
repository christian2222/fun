package algorithms;

import java.util.ArrayList;
import java.util.Date;

import datastructures.*;

public class Lru<T extends Page> extends Random {

	
	public <U extends TimePage> Lru(ArrayList<U> sequence) {
		super(sequence);
	}
	
	@Override
	public void serveRequest(Page request) {
		TimePage req = (TimePage) request;
		if(this.cache.hasPage(req)) {
			int k = this.cache.getPageIndex(req);
			TimePage slot = (TimePage) this.cache.memory.get(k);
			slot.refreshTimestamp();
			System.out.println("Served page "+req+" by Cache.");
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(req);
				req.refreshTimestamp();
				System.out.println("Loaded page "+req+" into an empty slot.");
			} else { // removing strategy
				TimePage p = this.getOldestPage();
				System.out.println("Replaced page "+p+" by page "+req+" in Cache");
				this.evictPageFromCache(p);
				this.countPagefaults++;
				this.cache.add(req);
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
