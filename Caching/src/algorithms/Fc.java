package algorithms;

import java.util.ArrayList;

import datastructures.*;


public class Fc <T extends CountPage> extends Random<T> {


		
	public Fc(ArrayList<T> sequence) {
		super(sequence);
	}
	
	@Override
	public void serveRequest(T request) {
		if(this.cache.hasPage(request)) {
			int k = this.cache.getPageIndex(request);
			CountPage slot = this.cache.memory.get(k);
			slot.incCounter();
			System.out.println("Served page "+request+" by Cache.");
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(request);
				System.out.println("Loaded page "+request+" into an empty slot.");
			} else { // removing strategy
				CountPage p = this.getSmallestPage();
				System.out.println("Replaced page "+p+" by page "+request+" in Cache");
				this.countPagefaults++;
				this.evictPageFromCache((T)p);
				this.cache.add(request);

			}
			
			request.flashCounter();
			request.incCounter();
		}

	}
	
	protected CountPage getSmallestPage() {
		CountPage smallest = new CountPage();
		CountPage p = new CountPage();
		int counter = Integer.MAX_VALUE;
		for(int i = 0; i < this.cache.getSize(); i++) {
			p = this.cache.memory.get(i);
			if(p.getCounter() < counter) {
				smallest = p;
				counter = p.getCounter();
			}
		}
		
		return smallest;
	}





}
