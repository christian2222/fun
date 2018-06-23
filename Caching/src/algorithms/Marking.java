package algorithms;

import java.util.ArrayList;

import datastructures.*;

public class Marking <T extends Page> extends Random {

	
	public <U extends MarkedPage> Marking(ArrayList<U> sequence) {
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
	public void serveRequest(Page request) {
		MarkedPage req = (MarkedPage) request;
		if(this.cache.hasPage(req)) {
			System.out.println("Served page ["+req+"] by Cache.");
			int k = this.cache.getPageIndex(req);
			MarkedPage slot = ((MarkedPage) this.cache.memory.get(k));
			slot.setBit(true);
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(req);
				req.setBit(true);
				System.out.println("Loaded page ["+req+"] into an empty slot.");
			} else { // removing strategy
				MarkedPage p = new MarkedPage();
				
				if(this.allPagesMarked()) {
					this.flashBits(false);
				}
				// get unmarked page
				do {
					p = (MarkedPage) this.getRandomCachePage();
				} while(p.getBit());
				// page is unmarked (negation of while loop condition)
				
				System.out.println("Replaced page ["+p+"] by page ["+req+"] in Cache");
				this.evictPageFromCache(p);
				this.countPagefaults++;
				this.cache.add(req);
				req.setBit(true);
			}
		}
	}

}
