package algorithms;

import data.*;

public class NextFit extends BinPackingAlgorithm {

    
    
    public NextFit() {
	this.actualBin = new Bin();
	this.name = "Sascha"; 
    }
    
    @Override
    public void add(Item i) {
	// TODO Auto-generated method stub
	
	// empty binList?
	if(this.binList.isEmpty()) {
	    this.binList.add(actualBin);
	    actualBin.add(i);
	    this.addToAmount(i.getSize());
	}
	else {
	    // the item can be added to the actualbin
	    if(actualBin.isAddable(i)) {
		actualBin.add(i);
		this.addToAmount(i.getSize());
	    }
	    // the item can't be placed in the actualbin.
	    // Hence we produce a new bin which becomes the actualbin
	    else {
		Bin b = new Bin();
		this.binList.add(b);
		actualBin = b;
		// now the last bin(=actualBin) is empty !
		actualBin.add(i);
		this.addToAmount(i.getSize());
	    }
	    
	}
	    
    }


}
