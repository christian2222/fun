package algorithms;

import java.util.*;

import data.Bin;
import data.Item;

public class FirstFitDecreasing extends BinPackingAlgorithm {

    protected Vector<Item> itemList = new Vector<Item>();
    
    
    public FirstFitDecreasing() {
	this.binList = new Vector<Bin>();
	this.actualBin = new Bin();
	this.name = "FirstFitDecreasing"; 
    }
    
    @Override
    public void add(Item i) {
	// TODO Auto-generated method stub
	this.itemList.add(i);

    }
    
    public void apply() {
	// produce a sequence with decreasing itemsizes
	// the decreasing sort is achieved by the 
	// factor "-" in the compare()-method of the item-class
	Collections.sort(this.itemList);
	
	// add the items by decreasing size and firstfit algorithm
	for(Item i : this.itemList) {
	    this.addFirstFit(i);
	}
    }

    public void addFirstFit(Item i) {
	boolean added = false;
	// leere Bin Liste?
	if(this.binList.isEmpty()) {
	    this.binList.add(actualBin);
	    actualBin.add(i);
	    this.addToAmount(i.getSize());
	    added = true;
	}
	// Liste enth�lt Bins
	else {
	    // pr�fe ob das item noch in eines der vorhandenen
	    // bins reinpasst
	    for(Bin b : this.binList) {
		if(b.isAddable(i)) {
		    b.add(i);
		    this.addToAmount(i.getSize());
		    added = true;
		}
	    }
	    // falls nicht f�ge eine neues Bin zur Liste hinzu und
	    // verpacke das Item dort.
	    if(!added) {
		Bin b = new Bin();
		this.binList.add(b);
		b.add(i);
		this.addToAmount(i.getSize());
		added = true;
	    } // if
	    
	} // else
	
	
	
    } // addFirstFit

    


}
