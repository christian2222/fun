package algorithms;

import java.util.*;
import data.*;

public abstract class BinPackingAlgorithm {

    protected List<Bin> binList = new Vector<Bin>();
    protected String name = "";
    protected Bin actualBin = new Bin();
    protected int i = 0;
    protected int usedAmount = 0;
    
    protected void addToAmount(int add) {
	this.usedAmount = this.usedAmount + add;
    }
    
    public String report() {
	this.i = 0;
	StringBuffer sb = new StringBuffer();
	sb.append(this.name+" used \n");
	for(Bin b:this.binList) {
	    this.i++;
	    sb.append("Bin "+ i + " contains:\n");
	    sb.append(this.reportBin(b));
	}
	sb.append("\n");
	sb.append(this.name+" used "+i+" bins.");
	return sb.toString();
	
    }
    
    public String shortInfo() {
	int k = this.binList.size();
	StringBuffer sb = new StringBuffer();
	sb.append(this.name+" used "+k
		+" bottles to save an total amount of " +this.getAmount()  
		+" liters of water");
	return sb.toString();
    }
    
    protected String reportBin(Bin b) {
	int sum = 0;
	StringBuffer sb = new StringBuffer();
	for(Item i : b.getItemList()) {
	    sum += i.getSize();
	    sb.append(", "+i.getSize());
	}
	sb.append("  summes to:  "+sum+"  \n\n");
	return sb.toString();
    }
    
    public abstract void add(Item i);
    
    protected int getAmount() {
	return this.usedAmount;
    }
    
}
