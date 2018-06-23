package algorithms;

import java.util.ArrayList;

import datastructures.*;


public class Fc <T extends Page> extends Random {


		
	public <U extends CountPage> Fc(ArrayList<U> sequence) {
		super(sequence);
	}
	
	@Override
	public void serveRequest(Page request) {
		CountPage req = (CountPage) request;
		if(this.cache.hasPage(req)) {
			int k = this.cache.getPageIndex(req);
			CountPage slot = ((CountPage) this.cache.memory.get(k));
			slot.incCounter();
			System.out.println("Served page "+req+" by Cache.");
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(req);
				System.out.println("Loaded page "+req+" into an empty slot.");
			} else { // removing strategy
				CountPage p = this.getSmallestPage();
				System.out.println("Replaced page "+p+" by page "+req+" in Cache");
				this.countPagefaults++;
				this.evictPageFromCache(p);
				this.cache.add(req);

			}
			
			req.flashCounter();
			req.incCounter();
		}

	}
	
	protected CountPage getSmallestPage() {
		CountPage smallest = new CountPage();
		CountPage p = new CountPage();
		int counter = Integer.MAX_VALUE;
		for(int i = 0; i < this.cache.getSize(); i++) {
			p = (CountPage) this.cache.memory.get(i);
			if(p.getCounter() < counter) {
				smallest = p;
				counter = p.getCounter();
			}
		}
		
		return smallest;
	}





}
