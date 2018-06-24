package algorithms;

import java.util.ArrayList;

import datastructures.Page;

public class Random<T extends Page> extends Model<T> {


	public Random(ArrayList<T> sequence) {
		super(8,30,sequence);
	}
	
	public Random(int length) {
		super(8,30,length);
	}
	
	public void serveRequest(T request) {
		if(this.cache.hasPage(request)) {
			System.out.println("Served page ["+request+"] by Cache.");
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(request);
				System.out.println("Loaded page ["+request+"] into an empty slot.");
			} else { // removing strategy
				T p = this.getRandomCachePage();
				System.out.println("Replaced page ["+p+"] by page ["+request+"] in Cache");
				this.evictPageFromCache(p);
				this.countPagefaults++;
				this.cache.add(request);
			}
		}
	}
	
	public void runAlgorithm() {
		System.out.println("Sequence to serve: "+this.printSequence());
		for(int i = 0; i < this.sequence.size(); i++) {
			T request = (T) this.sequence.get(i);
			System.out.println(this.cache);
			System.out.println("Requested page: "+request);
			this.serveRequest(request);
		}
		
		System.out.println(this.cache);
		System.out.println("page faults: "+this.getPagefaults());
	}

}
