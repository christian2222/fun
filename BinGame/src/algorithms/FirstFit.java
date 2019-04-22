package algorithms;

import data.Bin;
import data.Item;

public class FirstFit extends BinPackingAlgorithm {

    public FirstFit() {
	this.actualBin = new Bin();
	this.name = "FirstFit";
    }

    @Override
    public void add(Item i) {
	boolean added = false;
	// leere Bin Liste?
	if (this.binList.isEmpty()) {
	    actualBin = new Bin();
	    this.binList.add(actualBin);
	    actualBin.add(i);
	    this.addToAmount(i.getSize());
	    added = true;
	}
	// Liste enth�lt Bins
	else {
	    // pr�fe ob das item noch in eines der vorhandenen
	    // bins reinpasst
	    for (Bin b : this.binList) {
		if (b.isAddable(i)) {
		    b.add(i);
		    this.addToAmount(i.getSize());
		    added = true;
		}
	    }
	}
	// falls nicht f�ge eine neues Bin zur Liste hinzu und
	// verpacke das Item dort.
	if (!added) {
	    Bin b = new Bin();
	    this.binList.add(b);
	    b.add(i);
	    this.addToAmount(i.getSize());
	    added = true;
	}

	// TODO Auto-generated method stub

    }

}
