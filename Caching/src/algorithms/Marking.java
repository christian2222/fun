package algorithms;

import java.util.ArrayList;

import datastructures.*;

public class Marking <T extends MarkedPage> extends Random<T> {

	
	public Marking(ArrayList<T> sequence) {
		super(sequence);
	}
	
	public void flashBits(boolean bit) {
		MarkedPage p;
		for(int i = 0; i < this.cache.getSize(); i++) {
			p = (MarkedPage) this.cache.memory.get(i);
			p.setBit(bit);;
		}
	}
	
	public boolean allPagesMarked() {
		boolean b = true;
		MarkedPage p;
		for(int i = 0; i < this.cache.getSize(); i++) {
			p = (MarkedPage) this.cache.memory.get(i);
			b = b && p.getBit();
		}
		
		return b;
	}

	

	@Override
	public void serveRequest(T  request) {
		//MarkedPage request = (MarkedPage) request;
		if(this.cache.hasPage(request)) {
			System.out.println("Served page ["+request+"] by Cache.");
			int k = this.cache.getPageIndex(request);
			MarkedPage slot = ((MarkedPage) this.cache.memory.get(k));
			slot.setBit(true);
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(request);
				request.setBit(true);
				System.out.println("Loaded page ["+request+"] into an empty slot.");
			} else { // removing strategy
				MarkedPage p = new MarkedPage();
				
				if(this.allPagesMarked()) {
					this.flashBits(false);
				}
				// get unmarked page
				do {
					p = this.getRandomCachePage();
				} while(p.getBit());
				// page is unmarked (negation of while loop condition)
				
				System.out.println("Replaced page ["+p+"] by page ["+request+"] in Cache");
				this.evictPageFromCache((T)p);
				this.countPagefaults++;
				this.cache.add(request);
				request.setBit(true);
			}
		}
	}

}
