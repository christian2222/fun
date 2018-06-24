package communicate;

import java.util.ArrayList;
import algorithms.Random;
import datastructures.Page;

public class ByHand<T extends Page> extends Random<T> {

	

	
	public ByHand(ArrayList<T> sequence) {
		super(sequence);
		// TODO Auto-generated constructor stub
	}
	
	public ByHand(int length) {
		super(length);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void serveRequest(T request) {
		StaticHolder.mainWindow.addChar(request.getToken());
		StaticHolder.mainWindow.setTitle("Requested Page: "+request.getToken());
		try {
			Thread.sleep(500);
		}catch(Exception e) {
			
		}
		if(this.cache.hasPage(request)) {
			// serve by cache
			StaticHolder.mainWindow.serveByCache(this.cache.getPageIndex(request));
			StaticHolder.nextStep = true;
		} else {
			if(this.cache.hasEmptySlot()) {
				this.cache.add(request);
				// serve by new page
				StaticHolder.nextStep = true;
			} else { // removing strategy
				StaticHolder.nextStep = false;
				do {
					StaticHolder.mainWindow.reload();
				} while(!StaticHolder.nextStep);
				Page p = this.selectEvitablePage();
				this.evictPageFromCache((T)p);
				this.countPagefaults++;
				this.cache.add(request);
				StaticHolder.mainWindow.reload();
			}
		}
		StaticHolder.mainWindow.reload();
	}
	
	public Page selectEvitablePage() {
		return StaticHolder.selectedPage;
	}
	
	public void runAlgorithm() {

		System.out.println("Sequence to serve: "+this.printSequence());
		for(int i = 0; i < this.sequence.size(); i++) {
			T request = this.sequence.get(i);
			System.out.println(this.cache);
			System.out.println("Requested page: "+request);

			this.serveRequest(request);
		}
		
		System.out.println(this.cache);
		System.out.println("page faults: "+this.getPagefaults());
		StaticHolder.mainWindow.setTitle("Finished.");
	}


}
