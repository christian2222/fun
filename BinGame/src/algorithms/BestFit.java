package algorithms;

import data.*;

public class BestFit extends BinPackingAlgorithm {
    
    public BestFit() {
	this.actualBin = new Bin();
	this.name = "Buhuhu"; 
    }
    
    public void add(Item i) {
	boolean added = false;
	Bin binTmp = null;
	int spaceLeft = 100;
	int newspaceLeft = 100;
	
	// binlist empty
	if(this.binList.isEmpty()) {
	    this.binList.add(actualBin);
	    actualBin.add(i);
	    added = true;
	    this.addToAmount(i.getSize());
	}
	// binlist not empty
	else {
	    // check for an bin where the leftSpace is minimal
	    for(Bin b : this.binList) {
		if(b.isAddable(i)) {
			newspaceLeft = 100 - (b.getCurrentSize() + i.getSize());
			if(newspaceLeft < spaceLeft) {
			    binTmp = b;
			    spaceLeft = newspaceLeft;
			}
		    
		}
	    }
	    // is such a bin was found add the item
	    if(binTmp != null) {
		binTmp.add(i);
		added = true;
		this.addToAmount(i.getSize());
	    }
	    // if such a bin was not found, add anew bin
	    if(!added) {
		Bin b = new Bin();
		this.binList.add(b);
		b.add(i);
		this.addToAmount(i.getSize());
		added = true;
	    }
		
	}
	
    }
}
