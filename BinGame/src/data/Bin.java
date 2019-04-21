package data;

import java.util.*;


/**
 *	
 *	This is the Bin class the the BinPacking project.<br>
 *	A bin consits of a list of Items which is here declared by generics.
 * 
 * @author chrissy
 *
 *
 */
public class Bin {
    
    /**
     * maximal size of a Bin
     */
    public static final int maxSize = 100;
    
    /**
     * the list of items a bin contains
     */
    protected List<Item> itemList = new Vector<Item>();
    
    /**
     * Constructos creates an empty Bin
     */
    public Bin() {
	this.clearItemList();
    }
    
    public List<Item> getItemList() {
	return this.itemList;
    }
    
    /**
     * Constructor that also adds Item i as a (first) item
     * to the Bin
     * @param i
     */
    public Bin(Item i) {
	this.clearItemList();
	this.add(i);
    }
    
    /**
     * 
     * @param i Item that is added to this List
     */
    public void add(Item i) {
	this.itemList.add(i);
    }
    
    /**
     * adds an item to the Bin and checks if it was added
     * @param i Item to be added
     * @return boolean that indicates weather the item i was added
     * successful
     */
    public boolean addSuccess(Item i) {
	boolean isAddable = this.isAddable(i);
	if(isAddable) {
	    this.add(i);
	}
	return isAddable;
    }
    
    /**
     * clears the complete content of the Bin
     */
    protected void clearItemList() {
	this.itemList = new Vector<Item>();
    }

    /**
     * 
     * @return the current calculated size
     */
    public int getCurrentSize() {
	int size = 0;
	for(Item i:this.itemList) {
	    size += i.getSize();
	}
	return size;
    }
    
    /**
     * 
     * @param i Item to add
     * @return can we add the item to this Bin without overflow
     */
    public boolean isAddable(Item i) {
	return ( (this.getCurrentSize()+i.getSize()) <= maxSize);
    }
}
